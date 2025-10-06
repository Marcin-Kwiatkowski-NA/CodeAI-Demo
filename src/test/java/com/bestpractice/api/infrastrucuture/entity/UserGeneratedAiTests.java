package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testDefaultConstructorAndSettersGetters() {
        // GIVEN
        String id = "123";
        String username = "testuser";
        String email = "test@example.com";
        String password = "securePassword";

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
    public void testParameterizedConstructor() {
        // GIVEN
        String id = "456";
        String username = "anotheruser";
        String email = "another@example.com";
        String password = "anotherPassword";

        // WHEN
        User paramUser = new User(id, username, email, password);

        // THEN
        assertEquals(id, paramUser.getId());
        assertEquals(username, paramUser.getUsername());
        assertEquals(email, paramUser.getEmail());
        assertEquals(password, paramUser.getPassword());
    }

    @Test
    public void testCreatedAtFromSharedData() {
        // GIVEN
        Date now = new Date();

        // WHEN
        user.setCreatedAt(now);

        // THEN
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        assertNull(user.getCreatedAt());

        // WHEN
        user.onPrePersist();

        // THEN
        assertNotNull(user.getCreatedAt());
        assertTrue(user.getCreatedAt().getTime() <= new Date().getTime());
    }

    @Test
    public void testSettersAllowNullValues() {
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
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    public void testNoExceptionThrownWhenSettingNullValues() {
        // GIVEN
        // WHEN
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);

        // THEN
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    public void testAssertThrowsWhenAccessingNullPasswordLength() {
        // GIVEN
        user.setPassword(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> user.getPassword().length());
    }

    @Test
    public void testAssertThrowsWhenAccessingNullEmailLength() {
        // GIVEN
        user.setEmail(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> user.getEmail().length());
    }

    @Test
    public void testAssertThrowsWhenAccessingNullUsernameLength() {
        // GIVEN
        user.setUsername(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> user.getUsername().length());
    }

    @Test
    public void testAssertThrowsWhenAccessingNullIdLength() {
        // GIVEN
        user.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> user.getId().length());
    }
}
