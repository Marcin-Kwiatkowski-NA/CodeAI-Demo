package com.bestpractice.api.infrastrucuture.entity;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructorGivesNullFields() {
        // GIVEN: a new User instance created by default constructor
        // WHEN: accessing fields via getters
        // THEN: all fields should be null
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
    }

    @Test
    void testParameterizedConstructorSetsFields() {
        // GIVEN: values for all fields
        String id = "123";
        String username = "john_doe";
        String email = "john@example.com";
        String password = "securePass";

        // WHEN: creating a User with parameterized constructor
        User paramUser = new User(id, username, email, password);

        // THEN: getters return the same values
        assertThat(paramUser.getId()).isEqualTo(id);
        assertThat(paramUser.getUsername()).isEqualTo(username);
        assertThat(paramUser.getEmail()).isEqualTo(email);
        assertThat(paramUser.getPassword()).isEqualTo(password);
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN: a User instance
        // WHEN: setting each field
        user.setId("456");
        user.setUsername("alice");
        user.setEmail("alice@example.com");
        user.setPassword("alicePass");

        // THEN: getters return the updated values
        assertThat(user.getId()).isEqualTo("456");
        assertThat(user.getUsername()).isEqualTo("alice");
        assertThat(user.getEmail()).isEqualTo("alice@example.com");
        assertThat(user.getPassword()).isEqualTo("alicePass");
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a User instance with no createdAt set
        // WHEN: invoking onPrePersist lifecycle callback
        user.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to now
        Date now = new Date();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getCreatedAt()).isCloseTo(now, within(1, ChronoUnit.SECONDS));
    }
}
