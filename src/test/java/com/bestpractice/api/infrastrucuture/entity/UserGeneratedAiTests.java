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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("123");
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String expectedId = "123";
        String expectedUsername = "testUser";
        String expectedEmail = "test@example.com";
        String expectedPassword = "securePassword";

        // WHEN
        String actualId = user.getId();
        String actualUsername = user.getUsername();
        String actualEmail = user.getEmail();
        String actualPassword = user.getPassword();

        // THEN
        assertEquals(expectedId, actualId);
        assertEquals(expectedUsername, actualUsername);
        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testConstructorInitialization() {
        // GIVEN
        String id = "456";
        String username = "constructorUser";
        String email = "constructor@example.com";
        String password = "constructorPassword";

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    void testInheritanceFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        sharedData.onPrePersist();

        // WHEN
        user.setCreatedAt(sharedData.getCreatedAt());

        // THEN
        assertThat(user.getCreatedAt()).isNotNull();
        assertEquals(sharedData.getCreatedAt(), user.getCreatedAt());
    }

    @Test
    void testSettersUpdateValues() {
        // GIVEN
        String newId = "789";
        String newUsername = "updatedUser";
        String newEmail = "updated@example.com";
        String newPassword = "updatedPassword";

        // WHEN
        user.setId(newId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // THEN
        assertEquals(newId, user.getId());
        assertEquals(newUsername, user.getUsername());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testCreatedAtIsSetOnPersist() {
        // GIVEN
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt()).isNotNull();
    }

    @Test
    void testSetPasswordToNullDoesNotThrowException() {
        // GIVEN
        User localUser = new User();

        // WHEN
        localUser.setPassword(null);

        // THEN
        assertEquals(null, localUser.getPassword());
    }

    @Test
    void testSetEmailToNullDoesNotThrowException() {
        // GIVEN
        User localUser = new User();

        // WHEN
        localUser.setEmail(null);

        // THEN
        assertEquals(null, localUser.getEmail());
    }

    @Test
    void testSetUsernameToNullDoesNotThrowException() {
        // GIVEN
        User localUser = new User();

        // WHEN
        localUser.setUsername(null);

        // THEN
        assertEquals(null, localUser.getUsername());
    }

    @Test
    void testSetIdToNullDoesNotThrowException() {
        // GIVEN
        User localUser = new User();

        // WHEN
        localUser.setId(null);

        // THEN
        assertEquals(null, localUser.getId());
    }
}
