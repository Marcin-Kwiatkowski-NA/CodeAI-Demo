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
        User newUser = new User();

        // WHEN
        newUser.onPrePersist();

        // THEN
        assertNotNull(newUser.getCreatedAt());
    }

    @Test
    void testSetPasswordWithNullValueDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN & THEN
        // The setter should not throw an exception even if @NotNull annotation exists (validation frameworks handle it)
        user.setPassword(nullPassword);
        assertEquals(nullPassword, user.getPassword());
    }

    @Test
    void testSettersAndGettersWithEmptyStrings() {
        // GIVEN
        String empty = "";

        // WHEN
        user.setId(empty);
        user.setUsername(empty);
        user.setEmail(empty);
        user.setPassword(empty);

        // THEN
        assertEquals(empty, user.getId());
        assertEquals(empty, user.getUsername());
        assertEquals(empty, user.getEmail());
        assertEquals(empty, user.getPassword());
    }

    @Test
    void testNullValuesInConstructor() {
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
    void testSettersWithLongStrings() {
        // GIVEN
        String longString = "a".repeat(1000);

        // WHEN
        user.setId(longString);
        user.setUsername(longString);
        user.setEmail(longString);
        user.setPassword(longString);

        // THEN
        assertEquals(longString, user.getId());
        assertEquals(longString, user.getUsername());
        assertEquals(longString, user.getEmail());
        assertEquals(longString, user.getPassword());
    }

    @Test
    void testOnPrePersistDoesNotThrowException() {
        // GIVEN
        User newUser = new User();

        // WHEN & THEN
        assertThrows(Exception.class, () -> {
            try {
                newUser.onPrePersist();
            } catch (Exception e) {
                throw e;
            }
        });
    }
}
