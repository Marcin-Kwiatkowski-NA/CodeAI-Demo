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

class UserRequestGeneratedAiTests {

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
    void testConvertSetsFieldsCorrectly() {
        // GIVEN
        userRequest.setUsername("alice");
        userRequest.setEmail("alice@example.com");
        userRequest.setPassword("pass123");
        String id = "user-001";
        String encodedPassword = "encodedPass";

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
    void testConvertWithNullIdAndEncodedPassword() {
        // GIVEN
        userRequest.setUsername("bob");
        userRequest.setEmail("bob@example.com");
        userRequest.setPassword("bobpass");
        String id = null;
        String encodedPassword = null;

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
        assertThat(user.getPassword()).isNull();
        assertThat(user.getUsername()).isEqualTo("bob");
        assertThat(user.getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    void testConvertReturnsNewUserInstanceEachCall() {
        // GIVEN
        userRequest.setUsername("charlie");
        userRequest.setEmail("charlie@example.com");
        userRequest.setPassword("charliepass");
        String id = "id-123";
        String encodedPassword = "encPass";

        // WHEN
        User firstUser = userRequest.convert(id, encodedPassword);
        User secondUser = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(firstUser).isNotSameAs(secondUser);
        assertThat(firstUser.getId()).isEqualTo(id);
        assertThat(secondUser.getId()).isEqualTo(id);
    }
}
