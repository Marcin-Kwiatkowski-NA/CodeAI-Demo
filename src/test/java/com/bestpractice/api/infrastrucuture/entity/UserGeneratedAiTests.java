package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructorAndSettersAndGetters() {
        // GIVEN
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword"; // Security-sensitive field

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

    @Test
    void testParameterizedConstructor() {
        // GIVEN
        String id = "456";
        String username = "anotherUser";
        String email = "another@example.com";
        String password = "anotherPassword"; // Security-sensitive field

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    void testInheritedCreatedAtFromSharedData() {
        // GIVEN
        Date now = new Date();

        // WHEN
        user.setCreatedAt(now);

        // THEN
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        user.setCreatedAt(null);

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void testSettersWithNullValues() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;
        String password = null;

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

    @Test
    void testNoExceptionThrownWhenSettingNullPassword() {
        // GIVEN
        String password = null;

        // WHEN & THEN
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    void testNoExceptionThrownWhenCallingOnPrePersistMultipleTimes() {
        // GIVEN
        user.setCreatedAt(null);

        // WHEN
        user.onPrePersist();
        Date firstDate = user.getCreatedAt();
        user.onPrePersist();
        Date secondDate = user.getCreatedAt();

        // THEN
        assertNotNull(firstDate);
        assertNotNull(secondDate);
    }

    @Test
    void testNullValuesDoNotThrowExceptions() {
        // GIVEN
        User nullUser = new User();

        // WHEN & THEN
        nullUser.setId(null);
        nullUser.setUsername(null);
        nullUser.setEmail(null);
        nullUser.setPassword(null);

        assertEquals(null, nullUser.getId());
        assertEquals(null, nullUser.getUsername());
        assertEquals(null, nullUser.getEmail());
        assertEquals(null, nullUser.getPassword());
    }

    @Test
    void testConstructorWithNullValuesDoesNotThrowException() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;
        String password = null;

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, constructedUser.getId());
        assertEquals(username, constructedUser.getUsername());
        assertEquals(email, constructedUser.getEmail());
        assertEquals(password, constructedUser.getPassword());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenAlreadySet() {
        // GIVEN
        Date existingDate = new Date();
        user.setCreatedAt(existingDate);

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
    }
}
