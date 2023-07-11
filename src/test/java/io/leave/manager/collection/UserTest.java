package io.leave.manager.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void createUserTest(){
        User user  = User.builder()
                .roles("ADMIN")
                .firstName("A")
                .lastName("B")
                .username("ab")
                .email("a@test.com")
                .build();

        assertAll(
                ()-> assertEquals(user.getEmail(), "a@test.com"),
                ()-> assertEquals(user.getFirstName(), "A"),
                ()-> assertEquals(user.getLastName(), "B"),
                ()-> assertNull(user.getId())
        );
    }
}