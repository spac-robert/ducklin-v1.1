package ro.robert.ducklin.dto;

import lombok.Data;

@Data
public class UserData {

    private String uid;
    private String username;
    private String email;
    private String password;
    private Boolean enable;
}
