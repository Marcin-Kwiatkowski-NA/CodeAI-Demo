package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.entity.SharedData;

public class UserRequestGeneratedAiTests {
    private UserRequest userRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");
    }

    @org.junit.jupiter.api.Test
    void testConvert_validInput_returnsUser() {
        // GIVEN
        String id = "123";
        String encodePw = "password";
        // WHEN
        User user = userRequest.convert(id, encodePw);
        // THEN
        assert user != null;
        assert user.getId().equals(id);
        assert user.getUsername().equals("testuser");
        assert user.getEmail().equals("test@example.com");
        assert user.getPassword().equals(encodePw);
    }
}
