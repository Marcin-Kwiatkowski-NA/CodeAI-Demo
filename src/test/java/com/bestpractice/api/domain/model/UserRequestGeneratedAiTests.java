package com.bestpractice.api.domain.model;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.extension.ExtendWith.withExtension;

@ExtendWith(withExtension(MyExtension.class))
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testConvert() {
        // GIVEN a UserRequest object with username, email, and password
        String id = "123";
        String encodePw = "encodedPassword";

        // WHEN the convert method is called with id and encodePw
        User user = userRequest.convert(id, encodePw);

        // THEN the resulting User object should have the correct id, password, email, and username
        assertEquals("123", user.getId());
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("email", user.getEmail());
        assertEquals("username", user.getUsername());
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN a UserRequest object
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";

        // WHEN the setters are called
        userRequest.setUsername(username);
        userRequest.setEmail(email);
        userRequest.setPassword(password);

        // THEN the getters should return the correct values
        assertEquals(username, userRequest.getUsername());
        assertEquals(email, userRequest.getEmail());
        assertEquals(password, userRequest.getPassword());
    }
}