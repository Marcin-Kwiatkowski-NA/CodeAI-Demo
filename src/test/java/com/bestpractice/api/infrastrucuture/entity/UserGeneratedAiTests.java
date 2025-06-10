package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        String id = "123";
        // WHEN
        user.setId(id);
        // THEN
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String username = "testUser";
        // WHEN
        user.setUsername(username);
        // THEN
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String email = "test@example.com";
        // WHEN
        user.setEmail(email);
        // THEN
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String password = "password123";
        // WHEN
        user.setPassword(password);
        // THEN
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testConstructor() {
        // GIVEN
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "password123";
        // WHEN
        User newUser = new User(id, username, email, password);
        // THEN
        assertEquals(id, newUser.getId());
        assertEquals(username, newUser.getUsername());
        assertEquals(email, newUser.getEmail());
        assertEquals(password, newUser.getPassword());
    }

}
