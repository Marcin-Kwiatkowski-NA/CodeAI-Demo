package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testSetAndGetUsername() {
        // GIVEN
        String username = "testUser";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(username);
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN
        String email = "user@example.com";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertThat(userRequest.getEmail()).isEqualTo(email);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertThat(userRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testConvertCreatesUserWithCorrectValues() {
        // GIVEN
        String username = "testUser";
        String email = "user@example.com";
        String password = "plainPassword";
        String encodedPassword = "encodedPassword";
        String id = "user-123";

        userRequest.setUsername(username);
        userRequest.setEmail(email);
        userRequest.setPassword(password);

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    void testConvertWithNullFields() {
        // GIVEN
        String id = "user-456";
        String encodedPassword = "encodedPassword";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }
}
