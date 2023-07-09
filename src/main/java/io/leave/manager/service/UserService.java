package io.leave.manager.service;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.exception.collection.EmployeeNotFoundException;

import javax.mail.MessagingException;

public interface UserService {


    User createEmployee(User user) throws MessagingException, EmailExistsException, EmployeeNotFoundException, EmailNotFoundException;

    User findByEmail(String email);


}
