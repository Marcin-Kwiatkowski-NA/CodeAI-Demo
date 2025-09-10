package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void getUsername_returnsUsername() {
        // GIVEN a UserRequest object
        userRequest.setUsername("testUser");
        // WHEN we call getUsername()
        String username = userRequest.getUsername();
        // THEN the returned username should be "testUser"
        assertEquals("testUser", username);
    }

    @Test
    void getEmail_returnsEmail() {
        // GIVEN a UserRequest object
        userRequest.setEmail("test@example.com");
        // WHEN we call getEmail()
        String email = userRequest.getEmail();
        // THEN the returned email should be "test@example.com"
        assertEquals("test@example.com", email);
    }

    @Test
    void getPassword_returnsPassword() {
        // GIVEN a UserRequest object
        userRequest.setPassword("secretPassword");
        // WHEN we call getPassword()
        String password = userRequest.getPassword();
        // THEN the returned password should be "secretPassword"
        assertEquals("secretPassword", password);
    }

    @Test
    void convert_convertsUserRequestToUser() {
        // GIVEN a UserRequest object
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("secretPassword");
        // WHEN we call convert(null, null)
        User user = userRequest.convert(null, null);
        // THEN the converted user should have the correct id, username, email, and password
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("secretPassword", user.getPassword());
        assertEquals(null, user.getId());
    }
}

// Mock extension class
class MyExtension {}