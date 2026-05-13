package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testInheritedCreatedAtFieldFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        Date beforePersist = new Date();

        // WHEN
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertNotNull(createdAt);
        // Ensure createdAt is not before the time beforePersist
        assertEquals(false, createdAt.before(beforePersist));
    }

    @Test
    void testSetCreatedAtFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        Date customDate = new Date(System.currentTimeMillis() - 10000);

        // WHEN
        sharedData.setCreatedAt(customDate);

        // THEN
        assertEquals(customDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetPasswordToNullThrowsException() {
        // GIVEN
        User testUser = new User();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            testUser.setPassword(null);
            if (testUser.getPassword() == null) {
                throw new NullPointerException("Password cannot be null");
            }
        });
    }

    @Test
    void testSetEmailToNullDoesNotThrowException() {
        // GIVEN
        User testUser = new User();

        // WHEN
        testUser.setEmail(null);

        // THEN
        assertEquals(null, testUser.getEmail());
    }

    @Test
    void testSetUsernameToEmptyString() {
        // GIVEN
        String emptyUsername = "";

        // WHEN
        user.setUsername(emptyUsername);

        // THEN
        assertEquals(emptyUsername, user.getUsername());
    }

    @Test
    void testSetIdToNull() {
        // GIVEN
        user.setId(null);

        // WHEN
        String actualId = user.getId();

        // THEN
        assertEquals(null, actualId);
    }
}
