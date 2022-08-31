package ro.robert.ducklin.facade;

import lombok.NonNull;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.model.UserModel;

/**
 * Facade responsible for operation on {@link UserModel}
 */
public interface UserFacade {

    /**
     * Add the given user
     *
     * @param userData the user to be added
     * @return UserData
     */
    UserData signIn(@NonNull UserData userData) throws Exception;

    /**
     * Login
     * @param userData the user to be logged in
     * @return UserData
     */
    UserData login(UserData userData);

    /**
     * Verification account
     * @param token
     */
    void verifyAccount(String token) throws Exception;

    void deleteToken(String token);
}
