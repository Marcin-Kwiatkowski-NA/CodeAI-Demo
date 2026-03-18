package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
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
    void testInheritedCreatedAtFieldFromSharedData() {
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
        assertNull(user.getCreatedAt());

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
        assertEquals(Date.class, user.getCreatedAt().getClass());
    }

    @Test
    void testSetPasswordWithNullDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        user.setPassword(nullPassword);

        // THEN
        assertNull(user.getPassword());
    }

    @Test
    void testSettersAndGettersIndependence() {
        // GIVEN
        String id = "789";
        String username = "independentUser";
        String email = "independent@example.com";
        String password = "independentPassword"; // Security-sensitive field

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

        // WHEN - change only one field
        String newEmail = "updated@example.com";
        user.setEmail(newEmail);

        // THEN - ensure other fields remain unchanged
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(newEmail, user.getEmail());
        assertEquals(password, user.getPassword());
    }
}
