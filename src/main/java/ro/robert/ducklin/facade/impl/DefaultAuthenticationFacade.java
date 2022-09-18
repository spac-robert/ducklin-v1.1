package ro.robert.ducklin.facade.impl;

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
import org.springframework.stereotype.Component;
import ro.robert.ducklin.converter.UserConverter;
import ro.robert.ducklin.dto.AuthenticationResponse;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.facade.UserFacade;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.security.JwtProvider;
import ro.robert.ducklin.service.AuthenticationService;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
public class DefaultAuthenticationFacade implements UserFacade {

    private final UserConverter converter;
    private final AuthenticationService userService;
    private final AuthenticationManager authManager;

    @Autowired
    public DefaultAuthenticationFacade(UserConverter converter, AuthenticationService userService, AuthenticationManager authManager) {
        this.converter = converter;
        this.userService = userService;
        this.authManager = authManager;
    }

    @Override
    public UserData signIn(@NonNull UserData userData) throws Exception {
        UserModel user = converter.to(userData);
        user.setUid(UUID.randomUUID().toString());
        user.setEnabled(false);
        try {
            return converter.from(userService.sigIn(user));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AuthenticationResponse login(UserData userData) throws Exception {
        UserModel userModel = converter.to(userData);
        AuthenticationResponse response;
        try {
            response = userService.login(userModel,authManager);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public void verifyAccount(String token) throws Exception {
        userService.verifyAccount(token);
    }

    @Override
    public void deleteToken(String token) {
        userService.deleteToken(token);
    }
}
