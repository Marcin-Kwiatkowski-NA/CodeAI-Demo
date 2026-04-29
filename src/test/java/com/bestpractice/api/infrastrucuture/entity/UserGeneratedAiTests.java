package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testUser", "test@example.com", "securePassword");
    }

    @Test
    void testGetIdAndSetId() {
        // GIVEN
        String newId = "2";

        // WHEN
        user.setId(newId);

        // THEN
        assertEquals(newId, user.getId());
    }

    @Test
    void testGetUsernameAndSetUsername() {
        // GIVEN
        String newUsername = "updatedUser";

        // WHEN
        user.setUsername(newUsername);

        // THEN
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void testGetEmailAndSetEmail() {
        // GIVEN
        String newEmail = "updated@example.com";

        // WHEN
        user.setEmail(newEmail);

        // THEN
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testGetPasswordAndSetPassword() {
        // GIVEN
        String newPassword = "newSecurePassword"; // SECURITY-SENSITIVE

        // WHEN
        user.setPassword(newPassword);

        // THEN
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        // GIVEN
        User newUser = new User();

        // WHEN
        newUser.setId("3");
        newUser.setUsername("defaultUser");
        newUser.setEmail("default@example.com");
        newUser.setPassword("defaultPassword"); // SECURITY-SENSITIVE

        // THEN
        assertEquals("3", newUser.getId());
        assertEquals("defaultUser", newUser.getUsername());
        assertEquals("default@example.com", newUser.getEmail());
        assertEquals("defaultPassword", newUser.getPassword());
    }

    @Test
    void testInheritedCreatedAtFromSharedData() {
        // GIVEN
        Date beforePersist = new Date();
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isAfterOrEqualTo(beforePersist);
    }

    @Test
    void testSetCreatedAtAndGetCreatedAt() {
        // GIVEN
        Date customDate = new Date();
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.setCreatedAt(customDate);

        // THEN
        assertEquals(customDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetNullPasswordDoesNotThrowException() {
        // GIVEN
        User newUser = new User();

        // WHEN
        newUser.setPassword(null); // SECURITY-SENSITIVE

        // THEN
        assertEquals(null, newUser.getPassword());
    }

    @Test
    void testSetNullEmailDoesNotThrowException() {
        // GIVEN
        User newUser = new User();

        // WHEN
        newUser.setEmail(null);

        // THEN
        assertEquals(null, newUser.getEmail());
    }

    @Test
    void testParameterizedConstructorSetsAllFieldsCorrectly() {
        // GIVEN
        String id = "10";
        String username = "paramUser";
        String email = "param@example.com";
        String password = "paramPassword"; // SECURITY-SENSITIVE

        // WHEN
        User paramUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, paramUser.getId());
        assertEquals(username, paramUser.getUsername());
        assertEquals(email, paramUser.getEmail());
        assertEquals(password, paramUser.getPassword());
    }
}
