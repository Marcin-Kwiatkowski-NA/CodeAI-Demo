package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
    void testGetAndSetId() {
        // GIVEN
        String newId = "2";

        // WHEN
        user.setId(newId);

        // THEN
        assertEquals(newId, user.getId());
    }

    @Test
    void testGetAndSetUsername() {
        // GIVEN
        String newUsername = "updatedUser";

        // WHEN
        user.setUsername(newUsername);

        // THEN
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN
        String newEmail = "updated@example.com";

        // WHEN
        user.setEmail(newEmail);

        // THEN
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN
        String newPassword = "newSecurePassword";

        // WHEN
        user.setPassword(newPassword);

        // THEN
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void testConstructorInitialization() {
        // GIVEN
        String id = "10";
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
    void testInheritedCreatedAtFromSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isBeforeOrEqualTo(new Date());
    }

    @Test
    void testSetCreatedAtInSharedData() {
        // GIVEN
        SharedData sharedData = new SharedData();
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);

        // THEN
        assertEquals(now, sharedData.getCreatedAt());
    }

    @Test
    void testDefaultConstructorCreatesEmptyUser() {
        // GIVEN
        User emptyUser = new User();

        // WHEN & THEN
        assertThat(emptyUser.getId()).isNull();
        assertThat(emptyUser.getUsername()).isNull();
        assertThat(emptyUser.getEmail()).isNull();
        assertThat(emptyUser.getPassword()).isNull();
    }

    @Test
    void testSetNullPasswordDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        user.setPassword(nullPassword);

        // THEN
        assertThat(user.getPassword()).isNull();
    }

    @Test
    void testSetNullEmailDoesNotThrowException() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        user.setEmail(nullEmail);

        // THEN
        assertThat(user.getEmail()).isNull();
    }

    @Test
    void testSetNullUsernameDoesNotThrowException() {
        // GIVEN
        String nullUsername = null;

        // WHEN
        user.setUsername(nullUsername);

        // THEN
        assertThat(user.getUsername()).isNull();
    }

    @Test
    void testSetNullIdDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN
        user.setId(nullId);

        // THEN
        assertThat(user.getId()).isNull();
    }

    @Test
    void testSharedDataOnPrePersistSetsCreatedAt() {
        // GIVEN
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt()).isNotNull();
    }

    @Test
    void testSharedDataSetCreatedAtWithNull() {
        // GIVEN
        SharedData sharedData = new SharedData();

        // WHEN
        sharedData.setCreatedAt(null);

        // THEN
        assertThat(sharedData.getCreatedAt()).isNull();
    }
}
