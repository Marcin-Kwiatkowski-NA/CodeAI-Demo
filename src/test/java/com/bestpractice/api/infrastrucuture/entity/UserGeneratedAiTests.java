package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed

        // WHEN
        User defaultUser = new User();

        // THEN
        assertThat(defaultUser.getId()).isNull();
        assertThat(defaultUser.getUsername()).isNull();
        assertThat(defaultUser.getEmail()).isNull();
        assertThat(defaultUser.getPassword()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        // GIVEN
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        User parameterizedUser = new User(id, username, email, password);

        // THEN
        assertThat(parameterizedUser.getId()).isEqualTo(id);
        assertThat(parameterizedUser.getUsername()).isEqualTo(username);
        assertThat(parameterizedUser.getEmail()).isEqualTo(email);
        assertThat(parameterizedUser.getPassword()).isEqualTo(password);
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        String id = "123";

        // WHEN
        user.setId(id);

        // THEN
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String username = "testUser";

        // WHEN
        user.setUsername(username);

        // THEN
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        user.setEmail(email);

        // THEN
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        user.setPassword(password);

        // THEN
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
