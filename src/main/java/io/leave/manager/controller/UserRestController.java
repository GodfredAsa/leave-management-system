package io.leave.manager.controller;

import io.leave.manager.collection.User;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.exception.collection.*;
import io.leave.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserRestController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user)
            throws MessagingException, EmailExistsException,
            UserNotFoundException, EmailNotFoundException,
            InvalidEmailException, UserExistsException
    {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public User findByEmail(@PathVariable String email) throws MessagingException, EmailExistsException {
        return userService.findByEmail(email);
    }

}
