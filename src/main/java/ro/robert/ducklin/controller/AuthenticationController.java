package ro.robert.ducklin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.exception.CustomException;
import ro.robert.ducklin.facade.UserFacade;

@RestController
@RequestMapping("/auth/*")
public class AuthenticationController {

    @Autowired
    private final UserFacade userFacade;

    public AuthenticationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserData> login(@RequestBody UserData userData) {
        ResponseEntity<UserData> response;
        try {
            userData = userFacade.login(userData);
            response = new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (CustomException e) {
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
            response = new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (CustomException e) {
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
