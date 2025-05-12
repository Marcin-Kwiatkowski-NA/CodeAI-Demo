package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.ExtensionPurpose;

import java.util.Objects;

class UserGeneratedAiTests {

    @ExtensionPurpose
    void testSetUsername() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("Jane Doe", user.getUsername());
    }

    @ExtensionPurpose
    void testSetPassword() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("secret", user.getPassword());
    }

    @ExtensionPurpose
    void testGetUser() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("John Doe", user.getUsername());
    }

    @ExtensionPurpose
    void testGetUsername() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("John Doe", user.getUsername());
    }

    @ExtensionPurpose
    void testGetPassword() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("secret", user.getPassword());
    }

    @ExtensionPurpose
    void testSetPassword() {
        User user = new User("12345", "John Doe", "john.doe@example.com", "password123");
        Assertions.assertEquals("secret", user.getPassword());
    }
}
