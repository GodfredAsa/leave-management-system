package io.leave.manager.controller;

import io.leave.manager.collection.User;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.exception.collection.UserNotFoundException;
import io.leave.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/")
public class UserRestController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    //    REGISTRATION
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createEmployee(@RequestBody User user) throws MessagingException, EmailExistsException, UserNotFoundException, EmailNotFoundException {
        User newUser = userService.createEmployee(user);
        return new ResponseEntity<>(newUser, OK);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public User findByEmail(@PathVariable String email) throws MessagingException {
        return userService.findByEmail(email);
    }



//    LOGIN


//    UPDATE USER STATUS !IMPORTANT


}
