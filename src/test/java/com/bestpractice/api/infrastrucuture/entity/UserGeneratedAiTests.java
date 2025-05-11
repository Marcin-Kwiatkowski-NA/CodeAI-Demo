package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

import java.util.Date;

class UserGeneratedAiTests {

    @Test
    void testGetUserGeneratedAiTests() {
        // GIVEN
        User user = new User();

        // WHEN
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // THEN
        assert user.getUsername().equals("testUser") : "Username should be testUser";
        assert user.getEmail().equals("test@example.com") : "Email should be test@example.com";
        assert user.getPassword().equals("password123") : "Password should be password123";
    }

    @Test
    void testGetCreatedAtGeneratedAiTests() {
        // GIVEN
        User user = new User();

        // WHEN
        // THEN
        assert user.getCreatedAt() != null : "CreatedAt should not be null";
    }
}
