package io.leave.manager.service;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.exception.collection.UserNotFoundException;

import javax.mail.MessagingException;

public interface UserService {


    User createEmployee(User user) throws MessagingException, EmailExistsException, UserNotFoundException, EmailNotFoundException;

    User findByEmail(String email);


}
