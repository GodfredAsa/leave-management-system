package io.leave.manager.controller;

import io.leave.manager.collection.User;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.exception.collection.EmployeeNotFoundException;
import io.leave.manager.service.UserService;
import io.leave.manager.utils.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "api/employees")
public class UserRestController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager, UserService userService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //    REGISTRATION
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createEmployee(@RequestBody User user) throws MessagingException, EmailExistsException, EmployeeNotFoundException, EmailNotFoundException {
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
