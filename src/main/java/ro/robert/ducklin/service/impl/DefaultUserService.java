package ro.robert.ducklin.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.repository.UserRepository;
import ro.robert.ducklin.service.UserService;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull UserModel sigIn(@NonNull UserModel userModel) {
        return userRepository.save(userModel);
    }
}
