package com.bestpractice.api.domain.model;

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
    void testGettersAndSetters() {
        // GIVEN
        String username = "john_doe";
        String email = "john@example.com";
        String password = "secret123";

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
    void testConvertCreatesUserWithProvidedValues() {
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
        assertThat(user).isNotSameAs(userRequest);
    }

    @Test
    void testConvertWithNullValues() {
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

    @Test
    void testOriginalUserRequestUnchangedAfterConvert() {
        // GIVEN
        userRequest.setUsername("bob");
        userRequest.setEmail("bob@example.com");
        userRequest.setPassword("bobPassword");
        String id = "id-001";
        String encodedPassword = "encodedBob";

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo("bob");
        assertThat(userRequest.getEmail()).isEqualTo("bob@example.com");
        assertThat(userRequest.getPassword()).isEqualTo("bobPassword");
        assertThat(user.getUsername()).isEqualTo("bob");
        assertThat(user.getEmail()).isEqualTo("bob@example.com");
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }
}
