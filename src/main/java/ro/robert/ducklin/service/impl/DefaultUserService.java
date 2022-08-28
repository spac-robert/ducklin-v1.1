package ro.robert.ducklin.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.UserService;
import ro.robert.ducklin.validator.UserValidator;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public UserModel sigIn(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByPasswordAndEmail(userModel.getPassword(), userModel.getEmail());
        if (foundUser.isPresent()) {
            throw new CustomException("Another account was created with this email");
        }
        return userRepository.save(userModel);
    }

    @Override
    @NonNull
    public UserModel findUserByEmailAndPassword(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByPasswordAndEmail(userModel.getPassword(), userModel.getEmail());
        UserModel user = new UserModel();
        if (foundUser.isPresent()) {
            user = foundUser.get();
        }
        try {
            UserValidator.validate(userModel, user);
            return user;
        } catch (
                CustomException e) {
            throw e;
        }
    }
}
