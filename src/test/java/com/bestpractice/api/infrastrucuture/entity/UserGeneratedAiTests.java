package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void GIVEN_defaultConstructor_WHEN_gettersCalled_THEN_allFieldsAreNull() {
        // GIVEN
        // user initialized in setUp()

        // WHEN
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        java.util.Date createdAt = user.getCreatedAt();

        // THEN
        assertThat(id).isNull();
        assertThat(username).isNull();
        assertThat(email).isNull();
        assertThat(password).isNull();
        assertThat(createdAt).isNull();
    }

    @Test
    void GIVEN_parameterizedConstructor_WHEN_gettersCalled_THEN_fieldsMatchConstructorParameters() {
        // GIVEN
        String id = "123";
        String username = "john_doe";
        String email = "john@example.com";
        String password = "secret";

        // WHEN
        User constructedUser = new User(id, username, email, password);

        // THEN
        assertThat(constructedUser.getId()).isEqualTo(id);
        assertThat(constructedUser.getUsername()).isEqualTo(username);
        assertThat(constructedUser.getEmail()).isEqualTo(email);
        assertThat(constructedUser.getPassword()).isEqualTo(password);
        assertThat(constructedUser.getCreatedAt()).isNull();
    }

    @Test
    void GIVEN_userWithSetters_WHEN_fieldsSet_THEN_gettersReturnUpdatedValues() {
        // GIVEN
        String newId = "456";
        String newUsername = "alice";
        String newEmail = "alice@example.com";
        String newPassword = "newpass";

        // WHEN
        user.setId(newId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // THEN
        assertThat(user.getId()).isEqualTo(newId);
        assertThat(user.getUsername()).isEqualTo(newUsername);
        assertThat(user.getEmail()).isEqualTo(newEmail);
        assertThat(user.getPassword()).isEqualTo(newPassword);
    }

    @Test
    void GIVEN_user_WHEN_setNullValues_THEN_fieldsAreNull() {
        // GIVEN
        // user initialized in setUp()

        // WHEN
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);

        // THEN
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
    }

    @Test
    void GIVEN_user_WHEN_setEmptyStrings_THEN_fieldsAreEmpty() {
        // GIVEN
        // user initialized in setUp()

        // WHEN
        user.setId("");
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");

        // THEN
        assertThat(user.getId()).isEqualTo("");
        assertThat(user.getUsername()).isEqualTo("");
        assertThat(user.getEmail()).isEqualTo("");
        assertThat(user.getPassword()).isEqualTo("");
    }

    @Test
    void GIVEN_user_WHEN_setCreatedAt_THEN_getCreatedAtReturnsSetDate() {
        // GIVEN
        java.util.Date now = new java.util.Date();

        // WHEN
        user.setCreatedAt(now);

        // THEN
        assertThat(user.getCreatedAt()).isEqualTo(now);
    }
}
