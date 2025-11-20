package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class UserGeneratedAiTests {

    @InjectMocks
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: A new User instance created using the default constructor

        // WHEN: No properties are set

        // THEN: All properties should be null
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN: Valid input data
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN: A new User instance is created using the parameterized constructor
        User user = new User(id, username, email, password);

        // THEN: All properties should be set correctly
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void testSetAndGetId() {
        // GIVEN: A User instance and a valid ID
        String id = "123";

        // WHEN: The ID is set
        user.setId(id);

        // THEN: The ID should be retrievable
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetUsername() {
        // GIVEN: A User instance and a valid username
        String username = "testUser";

        // WHEN: The username is set
        user.setUsername(username);

        // THEN: The username should be retrievable
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN: A User instance and a valid email
        String email = "test@example.com";

        // WHEN: The email is set
        user.setEmail(email);

        // THEN: The email should be retrievable
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: A User instance and a valid password
        String password = "securePassword";

        // WHEN: The password is set
        user.setPassword(password);

        // THEN: The password should be retrievable
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
