package ro.robert.ducklin.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull UserModel sigIn(@NonNull UserModel userModel) {
        Optional<UserModel> foundUser = userRepository.findUserModelByPasswordAndEmail(userModel.getPassword(), userModel.getEmail());
        if (foundUser.isPresent()) {
            throw new EntityNotFoundException("Username or password incorrect!");
        }
        return userRepository.save(userModel);
    }
}
