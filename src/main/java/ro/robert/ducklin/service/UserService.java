package ro.robert.ducklin.service;

import lombok.NonNull;
import ro.robert.ducklin.model.UserModel;

/**
 * Contains business logic related to users
 */
public interface UserService {

    /**
     * Create a new account
     *
     * @param userModel data for the new user
     * @return boolean
     */
    @NonNull
    UserModel sigIn(@NonNull UserModel userModel);
}
