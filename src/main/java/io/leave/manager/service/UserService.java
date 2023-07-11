package io.leave.manager.service;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.*;

import javax.mail.MessagingException;
import java.util.List;
public interface UserService {

    User createUser(User user) throws MessagingException, EmailExistsException, UserNotFoundException, EmailNotFoundException, InvalidEmailException, UserExistsException;
    User findByEmail(String email) throws EmailExistsException;
    List<User> findAllUsers();
}
