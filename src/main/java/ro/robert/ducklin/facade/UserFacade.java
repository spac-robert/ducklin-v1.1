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
    UserData signIn(@NonNull UserData userData);
}
