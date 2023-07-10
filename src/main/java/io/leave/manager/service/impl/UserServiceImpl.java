package io.leave.manager.service.impl;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.EmailService;
import io.leave.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

import static io.leave.manager.constant.Authority.SUPER_ADMIN_AUTHORITIES;
import static io.leave.manager.constant.Authority.USER_AUTHORITIES;
import static io.leave.manager.constant.UserImplConstant.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired private UserRepository userRepository;
    @Autowired private EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public User createEmployee(User user) throws MessagingException, EmailExistsException, EmailNotFoundException {
        if(!isValidEmail(user.getEmail())) throw new EmailNotFoundException(INVALID_EMAIL);
        if(findByEmail(user.getEmail()) != null) throw new EmailExistsException(EMAIL_ALREADY_EXISTS);
        User newUser = buildEmployee(user);
//        emailService.sendRegistrationEmailNotification(user.getEmail());
        LOGGER.info(String.format(USER_REGISTRATION_SUCCESSFUL), user.getEmail());
//        TODO END NOTIFICATION TO SUPERVISOR AS WELL
//        TODO DO A METHOD OF OVERLOADING FOR SENDING MULTIPLE EMAILS
        return userRepository.save(newUser);
    }

    private User buildEmployee(User user){
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(encodePassword(user.getPassword()))
                .build();

        if(user.getEmail().equals("peswa@peswa.io" ) || user.getEmail().equals("godfred@yahoo.com")) {
            newUser.setSupervisor(true);
            newUser.setRole("SUPERVISOR");
            newUser.setAuthorities(SUPER_ADMIN_AUTHORITIES);
        }else{
            newUser.setSupervisor(false);
            newUser.setRole("USER");
            newUser.setAuthorities(USER_AUTHORITIES);
        }
        return newUser;
    }

    private static boolean isValidEmail(String email){
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private String encodePassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
