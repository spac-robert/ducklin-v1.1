package ro.robert.ducklin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.ducklin.dto.UserData;
import ro.robert.ducklin.facade.UserFacade;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/auth/*")
public class AuthenticationController {

    @Autowired
    private final UserFacade userFacade;

    public AuthenticationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(name = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<UserData> signIn(@RequestBody UserData userData) {
        ResponseEntity<UserData> response;
        try {
            userData = userFacade.signIn(userData);
            response = new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
