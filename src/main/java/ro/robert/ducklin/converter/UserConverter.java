package ro.robert.ducklin.converter;

import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.model.UserModel;

/**
 * Converter responsible for converting {@link UserData} to {@link UserModel}
 * and
 * {@link UserModel} to {@link UserData}
 */
public class UserConverter {

    public UserData from(UserModel source) {
        UserData target = new UserData();
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setUid(source.getUid());

        return target;
    }

    public UserModel to(UserData source) {
        UserModel target = new UserModel();
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setUid(source.getUid());

        return target;
    }

}
