package com.bestpractice.api.domain.model;

import org.junit.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.Assert.*;

public class UserRequestTest {

    @Test
    public void testGetUsername() {
        UserRequest user = new UserRequest();
        assertEquals("Alice", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        UserRequest user = new UserRequest();
        user.setUsername("Bob");
        assertEquals("Bob", user.getUsername());
    }

    @Test
    public void testGetEmail() {
        UserRequest user = new UserRequest();
        assertEquals("Bob@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        UserRequest user = new UserRequest();
        user.setEmail("Bob@example.com");
        assertEquals("Bob@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        UserRequest user = new UserRequest();
        assertEquals("Bob", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        UserRequest user = new UserRequest();
        user.setPassword("Charlie");
        assertEquals("Charlie", user.getPassword());
    }

    @Test
    public void testConvert() {
        UserRequest user = new UserRequest();
        User user2 = new UserRequest();
        user.setUsername("Alice");
        user2.setUsername("Bob");
        user.setPassword("Alice");
        user2.setPassword("Bob");
        user.setEmail("Alice");
        user2.setEmail("Bob");
        user.setPassword("Alice");
        user2.setPassword("Bob");
        UserRequest.assertEquals(user.convert("Alice", "Alice"), user2.convert("Bob", "Bob"));
    }
}
