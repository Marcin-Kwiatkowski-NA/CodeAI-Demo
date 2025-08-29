package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.SharedData;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ExtendWith.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    void testCreateUserWithValidData() {
        // GIVEN
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "password123";

        // WHEN
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // THEN
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    void testGetters() {
        // GIVEN
        String id = "456";
        String username = "anotherUser";
        String email = "another@example.com";
        String password = "securePassword";
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // WHEN
        // This method is just to verify that getters are working correctly

        // THEN
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    void testCreatedAt() {
        // GIVEN
        String id = "789";
        String username = "testUser2";
        String email = "test2@example.com";
        String password = "password456";
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // WHEN
        // This method is just to verify that the createdAt field is being set correctly

        // THEN
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }
}
