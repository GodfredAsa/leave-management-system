package io.leave.manager.service.impl;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.InvalidEmailException;
import io.leave.manager.exception.collection.UserExistsException;
import io.leave.manager.repository.LeaveBookingRepository;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.EmailService;
import io.leave.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;
import static io.leave.manager.constant.UserImplConstant.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired private UserRepository userRepository;
    @Autowired private EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired private LeaveBookingRepository leaveBookingRepository;

    @Override
    public User createUser(User user) throws MessagingException, EmailExistsException, InvalidEmailException, UserExistsException {
        if(!isValidEmail(user.getEmail())) throw new InvalidEmailException(INVALID_EMAIL);
        findUserByUsername(user.getUsername());
        if(findByEmail(user.getEmail()) != null) throw new EmailExistsException(EMAIL_ALREADY_EXISTS);
        User newUser = buildEmployee(user);
        if(user.getRoles().equals("ADMIN")) newUser.setSupervisor(true);
        LOGGER.info(String.format(USER_REGISTRATION_SUCCESSFUL), user.getEmail());
        emailService.sendRegistrationEmailNotification(newUser.getEmail(), USER_REGISTRATION_SUCCESSFUL);
        return userRepository.save(newUser);
    }

    private User buildEmployee(User user){
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .password(encodePassword(user.getPassword()))
                .build();
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    private static boolean isValidEmail(String email){
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private String encodePassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public User findByEmail(String email) throws EmailExistsException {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new EmailExistsException(EMAIL_ALREADY_EXISTS);
        return user;
    }

    public void findUserByUsername(String username) throws UserExistsException {
        User user = userRepository.findByUsername(username);
        if(user != null) throw new UserExistsException(USERNAME_EXISTS);
    }
}
