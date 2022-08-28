package ro.robert.ducklin.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.model.UserModel;

/**
 * Converter responsible for converting {@link UserData} to {@link UserModel}
 * and
 * {@link UserModel} to {@link UserData}
 */
@Component
public class UserConverter {

    /**
     * Convert {@link UserModel} to {@link UserData}
     *
     * @param source a user model
     * @return converted user model to user data
     */
    public UserData from(UserModel source) {
        UserData target = new UserData();
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setUid(source.getUid());

        return target;
    }

    /**
     * Convert {@link UserData} to {@link UserModel}
     *
     * @param source a user data
     * @return converted user data to user model
     */
    public UserModel to(UserData source) {
        UserModel target = new UserModel();
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setUid(source.getUid());

        return target;
    }

}
