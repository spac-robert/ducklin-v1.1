package ro.robert.ducklin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.facade.UserFacade;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final UserFacade userFacade;

    public AuthenticationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //TODO dupa ce expira token-ul nu pot sa fac signin, cred ca trebuie dupa expirare sa se stearga token-ul
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserData> login(@RequestBody UserData userData) {
        ResponseEntity<UserData> response;
        try {
            userData = userFacade.login(userData);
            response = new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (CustomException e) {
            LOGGER.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<UserData> signIn(@RequestBody UserData userData) {
        ResponseEntity<UserData> response;
        try {
            userData = userFacade.signIn(userData);
            response = new ResponseEntity<>(userData, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/account-verification/{token}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<UserData> verifyAccount(@PathVariable String token) {
        ResponseEntity<UserData> response;
        try {
            userFacade.verifyAccount(token);
            response = new ResponseEntity<>(HttpStatus.ACCEPTED);
            userFacade.deleteToken(token);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
//TODO future reset password

//    @RequestMapping(value = "/reset-password/{token}", method = RequestMethod.GET)
//    public ResponseEntity<UserData> resetPassword(@PathVariable String token) {
//        ResponseEntity<UserData> response;
//        try {
//            //userFacade.verifyTokenResetPassword(token);
//            response = new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            MultiValueMap<String, String> map = new HttpHeaders();
//            map.add("error", e.getMessage());
//            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }
}
