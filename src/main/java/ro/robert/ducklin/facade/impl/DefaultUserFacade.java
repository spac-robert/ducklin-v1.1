package ro.robert.ducklin.facade.impl;

import lombok.NonNull;
import ro.robert.ducklin.converter.UserConverter;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.facade.UserFacade;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.service.UserService;

import java.util.UUID;

public class DefaultUserFacade implements UserFacade {

    private final UserConverter converter;
    private final UserService userService;

    public DefaultUserFacade(UserService userService) {
        converter = new UserConverter();
        this.userService = userService;
    }

    //TODO add custom exception
    @Override
    public UserData signIn(@NonNull UserData userData) {
        UserModel user = converter.to(userData);
        user.setUid(UUID.randomUUID().toString());
        user.setEnabled(false);
        return converter.from(userService.sigIn(user));
    }
}
