package ro.robert.ducklin.service;

import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import ro.robert.ducklin.dto.AuthenticationResponse;
import ro.robert.ducklin.model.UserModel;

/**
 * Contains business logic related to users
 */
public interface AuthenticationService {

    /**
     * Create a new account
     *
     * @param userModel data for the new user
     * @return boolean
     */
    @NonNull
    UserModel sigIn(@NonNull UserModel userModel) throws Exception;

    /**
     * Find User by email and password
     *
     * @param userModel the user to be found
     * @return a UserModel
     */
    @NonNull
    UserModel findUserByEmailAndPassword(@NonNull UserModel userModel);

    /**
     *
     * Verification account
     * @param token a token to verify account
     */
    void verifyAccount(@NonNull String token) throws Exception;

    void deleteToken(String token);

    AuthenticationResponse login(UserModel userModel, AuthenticationManager authManager);
}
