package ro.robert.ducklin.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.dto.AuthenticationResponse;
import ro.robert.ducklin.dto.EmailNotification;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.model.VerificationTokenModel;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.repository.VerificationTokenRepository;
import ro.robert.ducklin.service.MailService;
import ro.robert.ducklin.service.AuthenticationService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DefaultAuthenticationService implements AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final MailService mailService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public DefaultAuthenticationService(PasswordEncoder encoder, UserRepository userRepository, VerificationTokenRepository tokenRepository, MailService mailService, UserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @NonNull
    @Transactional
    public UserModel sigIn(@NonNull UserModel userModel) throws Exception {
        Optional<UserModel> foundUser = userRepository.findUserModelByEmail(userModel.getEmail());

        if (foundUser.isPresent()) {
            throw new CustomException("Another account was created with this email");
        }
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        String token = generateVerificationToken(userModel);
        mailService.sendEmail(new EmailNotification("Please activate your account", "Thank you for signing up. Please click the below url to activate your account: localhost:8080/auth/account-verification/" + token, userModel.getEmail()));

        return userRepository.save(userModel);
    }

    @Override
    @NonNull
    public UserModel findUserByEmailAndPassword(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByEmail(userModel.getEmail());
        if (foundUser.isPresent()) {
            if (foundUser.get().getEnabled()) {
                if (encoder.matches(userModel.getPassword(), foundUser.get().getPassword())) {
                    return foundUser.get();
                }
            }
        }
        throw new CustomException("Email or password are incorrect");
    }

    @Override
    public void verifyAccount(@NonNull String token) throws Exception {
        Optional<VerificationTokenModel> foundToken = tokenRepository.findByToken(token);
        foundToken.orElseThrow(() -> new Exception("Invalid Token"));
        if (foundToken.get().getExpiryDate().isBefore(Instant.now())) {
            throw new Exception("Expired Token");
        }
        fetchUserAndEnable(foundToken.get());
    }

    @Override
    public void deleteToken(String token) {
        tokenRepository.deleteVerificationTokenModelByToken(token);
    }

    @Override
    public AuthenticationResponse login(UserModel userModel, AuthenticationManager authManager) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail(),
                userModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        GrantedAuthority role = roles.iterator().next();
        String token = Jwts.builder()
                .setId(userModel.getEmail())
                .setIssuedAt(new Date())
                .setSubject(userModel.getEmail())
                .claim("role", role.getAuthority())
                .setIssuer("bugreport")
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                ).compact();
        return new AuthenticationResponse(userModel.getEmail(), token);
    }

    @Transactional
    public void fetchUserAndEnable(VerificationTokenModel verificationTokenModel) throws Exception {
        String email = verificationTokenModel.getUser().getEmail();
        UserModel user = userRepository.findUserModelByEmail(email).orElseThrow(() -> new Exception("Email not found"));

        user.setEnabled(true);
    }

    private String generateVerificationToken(UserModel user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenModel verificationToken = new VerificationTokenModel();
        Instant timeNow = Instant.now();

        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(timeNow.plus(1, ChronoUnit.MINUTES));
        tokenRepository.save(verificationToken);

        return token;
    }
}
