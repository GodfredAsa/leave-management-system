package io.leave.manager.service;

import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.EmailExistsException;
import io.leave.manager.exception.collection.UserNotFoundException;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock EmailService emailService;
    @InjectMocks UserServiceImpl underTest;

   private  User user;

   @BeforeEach
   void setUp(){
       user = User.builder()
               .roles("ADMIN")
               .firstName("A")
               .lastName("B")
               .password("pass")
               .username("ab")
               .email("a@test.com")
               .build();
   }

    @Test
    void givenUserEmail_whenFindByEmail_returnUser() throws UserNotFoundException, EmailExistsException {
       when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
       User userExpected = underTest.findByEmail(user.getEmail());
       assertAll(
               ()-> assertEquals(userExpected, user),
               ()-> assertEquals(userExpected.getId(), user.getId()),
               ()-> assertEquals(userExpected.getEmail(), user.getEmail()),
               ()-> assertEquals(userExpected.getRoles(), user.getRoles())
       );
    }

    @Test
    void givenTwoUsers_whenFindAllUsers_returnNumberOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user, User.builder().build()));
        int numberOfUsers = underTest.findAllUsers().size();
        assertEquals(numberOfUsers, 2);
    }
}