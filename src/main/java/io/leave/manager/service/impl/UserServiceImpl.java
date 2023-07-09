package io.leave.manager.service.impl;

import io.leave.manager.collection.User;
import io.leave.manager.collection.UserPrincipal;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.EmailNotFoundException;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.EmailService;
import io.leave.manager.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import javax.mail.MessagingException;

import static io.leave.manager.constant.UserImplConstant.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Autowired private EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public User createEmployee(User user) throws MessagingException, EmailExistsException, EmailNotFoundException {
        if(!isValidEmail(user.getEmail())) throw new EmailNotFoundException(INVALID_EMAIL);
        if(findByEmail(user.getEmail()) != null) throw new EmailExistsException(EMAIL_ALREADY_EXISTS);

        User newUser = buildEmployee(user);
        emailService.sendNewPasswordEmail(user.getFirstName(), newUser.getPassword(), user.getEmail());
//        emailService.sendNewPasswordEmail(employee.getFirstName(), , CC_EMAIL);
        return userRepository.save(newUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User buildEmployee(User user){
        if(user.getEmail().equals("peswa@peswa.io" ) || user.getEmail().equals("jonathantaye@peswa.io")) user.setSupervisor(true);

//        TODO ADD THE AUTHORITIES
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .isSupervisor(user.isSupervisor())
                .build();
    }

    private static boolean isValidEmail(String email){
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_EMAIL + username);
            return userPrincipal;
        }
    }
}
