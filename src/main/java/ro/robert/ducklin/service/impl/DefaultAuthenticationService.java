package ro.robert.ducklin.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.UserService;

import java.util.Optional;

@Service
public class DefaultAuthenticationService implements UserService {

    @Autowired
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public DefaultAuthenticationService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    @NonNull
    public UserModel sigIn(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByEmail(userModel.getEmail());

        if (foundUser.isPresent()) {
            throw new CustomException("Another account was created with this email");
        }
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    @Override
    @NonNull
    public UserModel findUserByEmailAndPassword(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByEmail(userModel.getEmail());

        if (foundUser.isPresent()) {
            if (encoder.matches(userModel.getPassword(), foundUser.get().getPassword())) {
                return foundUser.get();
            }
        }
        throw new CustomException("Email or password are incorrect");
    }
}
