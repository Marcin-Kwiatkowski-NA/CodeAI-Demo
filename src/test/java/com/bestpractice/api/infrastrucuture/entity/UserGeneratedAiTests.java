package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN a new User instance created with the default constructor
        // WHEN the instance is created
        // THEN all fields should be null
        Assertions.assertThat(user.getId()).isNull();
        Assertions.assertThat(user.getUsername()).isNull();
        Assertions.assertThat(user.getEmail()).isNull();
        Assertions.assertThat(user.getPassword()).isNull();
        Assertions.assertThat(user.getCreatedAt()).isNull();
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN a new User instance created with all fields
        User paramUser = new User("123", "john_doe", "john@example.com", "secret");
        // WHEN the instance is created
        // THEN all fields should match the constructor arguments
        Assertions.assertThat(paramUser.getId()).isEqualTo("123");
        Assertions.assertThat(paramUser.getUsername()).isEqualTo("john_doe");
        Assertions.assertThat(paramUser.getEmail()).isEqualTo("john@example.com");
        Assertions.assertThat(paramUser.getPassword()).isEqualTo("secret");
        Assertions.assertThat(paramUser.getCreatedAt()).isNull();
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN a User instance
        // WHEN setting all fields
        user.setId("456");
        user.setUsername("alice");
        user.setEmail("alice@example.com");
        user.setPassword("password");
        // THEN getters should return the set values
        Assertions.assertThat(user.getId()).isEqualTo("456");
        Assertions.assertThat(user.getUsername()).isEqualTo("alice");
        Assertions.assertThat(user.getEmail()).isEqualTo("alice@example.com");
        Assertions.assertThat(user.getPassword()).isEqualTo("password");
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN a User instance
        // WHEN onPrePersist is invoked
        Date before = new Date();
        user.onPrePersist();
        Date after = new Date();
        // THEN createdAt should be set to a value between before and after
        Assertions.assertThat(user.getCreatedAt()).isNotNull();
        Assertions.assertThat(user.getCreatedAt()).isAfterOrEqualTo(before);
        Assertions.assertThat(user.getCreatedAt()).isBeforeOrEqualTo(after);
    }

    @Test
    void testIdCanBeNull() {
        // GIVEN a User instance
        // WHEN setting id to null
        user.setId(null);
        // THEN getId should return null
        Assertions.assertThat(user.getId()).isNull();
    }
}
