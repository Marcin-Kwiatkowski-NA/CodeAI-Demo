package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.mockito.Mockito.*;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@MockitoJUnitRunner
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setUsername("user");
        userRequest.setEmail("user@example.com");
    }

    @Test
    void testConvert_validInput() {
        // GIVEN a valid UserRequest object with username, email, and password
        String id = "123";
        String encodePw = "password123";

        // WHEN the convert method is called
        User user = userRequest.convert(id, encodePw);

        // THEN the resulting User object should have the correct values
        assertEquals("123", user.getId());
        assertEquals("password123", user.getPassword());
        assertEquals("user", user.getUsername());
        assertEquals("user@example.com", user.getEmail());
    }

    @Test
    void testConvert_emptyInput() {
        // GIVEN an empty UserRequest object
        String id = "123";
        String encodePw = "password123";

        // WHEN the convert method is called
        User user = userRequest.convert(id, encodePw);

        // THEN the resulting User object should have default values
        assertEquals("123", user.getId());
        assertEquals("password123", user.getPassword());
        assertEquals("", user.getUsername());
        assertEquals("", user.getEmail());
    }
}
