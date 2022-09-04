package ro.robert.ducklin.facade.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.robert.ducklin.converter.UserConverter;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.facade.UserFacade;
import ro.robert.ducklin.model.UserModel;
import ro.robert.ducklin.service.AuthenticationService;

import java.util.UUID;

@Component
public class DefaultAuthenticationFacade implements UserFacade {

    private final UserConverter converter;
    private final AuthenticationService userService;

    @Autowired
    public DefaultAuthenticationFacade(UserConverter converter, AuthenticationService userService) {
        this.converter = converter;
        this.userService = userService;
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
    public UserData login(UserData userData) {
        UserModel userModel = converter.to(userData);
        UserModel foundUser;
        try {
            foundUser = userService.findUserByEmailAndPassword(userModel);
        } catch (CustomException e) {
            throw e;
        }
        return converter.from(foundUser);
    }

    public void verifyAccount(String token) throws Exception {
        userService.verifyAccount(token);
    }

    @Override
    public void deleteToken(String token) {
        userService.deleteToken(token);
    }
}
