package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Security-sensitive: this test class exercises the conversion of user credentials,
 * which is a security-critical operation. The class is marked as security-sensitive
 * to indicate that it handles sensitive data (user passwords) during testing.
 */
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String username = "john_doe";
        String email = "john@example.com";
        String password = "secret";

        // WHEN
        userRequest.setUsername(username);
        userRequest.setEmail(email);
        userRequest.setPassword(password);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(username);
        assertThat(userRequest.getEmail()).isEqualTo(email);
        assertThat(userRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testConvertCreatesNewUserWithProvidedValues() {
        // GIVEN
        userRequest.setUsername("alice");
        userRequest.setEmail("alice@example.com");
        userRequest.setPassword("plainPassword");

        String id = "user-123";
        String encodedPassword = "encodedPassword";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo("alice");
        assertThat(user.getEmail()).isEqualTo("alice@example.com");
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    void testConvertDoesNotAlterOriginalUserRequest() {
        // GIVEN
        userRequest.setUsername("bob");
        userRequest.setEmail("bob@example.com");
        userRequest.setPassword("originalPassword");

        String id = "user-456";
        String encodedPassword = "encodedBob";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo("bob");
        assertThat(userRequest.getEmail()).isEqualTo("bob@example.com");
        assertThat(userRequest.getPassword()).isEqualTo("originalPassword");
    }

    @Test
    void testConvertCreatesDistinctUserInstances() {
        // GIVEN
        userRequest.setUsername("charlie");
        userRequest.setEmail("charlie@example.com");
        userRequest.setPassword("pw1");

        String id1 = "id1";
        String id2 = "id2";
        String encodedPw1 = "enc1";
        String encodedPw2 = "enc2";

        // WHEN
        User user1 = userRequest.convert(id1, encodedPw1);
        User user2 = userRequest.convert(id2, encodedPw2);

        // THEN
        assertThat(user1).isNotSameAs(user2);
        assertThat(user1.getId()).isEqualTo(id1);
        assertThat(user2.getId()).isEqualTo(id2);
        assertThat(user1.getPassword()).isEqualTo(encodedPw1);
        assertThat(user2.getPassword()).isEqualTo(encodedPw2);
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        // GIVEN
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);

        String id = null;
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
    }
}
