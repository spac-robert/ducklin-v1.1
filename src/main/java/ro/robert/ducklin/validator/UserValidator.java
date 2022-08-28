package ro.robert.ducklin.validator;

import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.model.UserModel;

public class UserValidator {

    public static void validate(UserModel user, UserModel foundUser) {
        if (user.getPassword().equals(foundUser.getPassword()) && user.getEmail().equals(foundUser.getEmail())) {
            return;
        }
        throw new CustomException("Email or password are incorrect");
    }
}
