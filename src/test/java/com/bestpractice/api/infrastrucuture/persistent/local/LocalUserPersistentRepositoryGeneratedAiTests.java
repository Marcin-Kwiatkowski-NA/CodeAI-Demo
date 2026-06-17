package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "john", "john@example.com", "password");
    }

    @Test
    void testUserConstructorAndGetters() {
        // GIVEN
        String expectedId = "1";
        String expectedName = "john";
        String expectedEmail = "john@example.com";
        String expectedPassword = "password";

        // WHEN
        String actualId = user.getId();
        String actualName = user.getName();
        String actualEmail = user.getEmail();
        String actualPassword = user.getPassword();

        // THEN
        assertThat(actualId).isEqualTo(expectedId);
        assertThat(actualName).isEqualTo(expectedName);
        assertThat(actualEmail).isEqualTo(expectedEmail);
        assertThat(actualPassword).isEqualTo(expectedPassword);
    }

    @Test
    void testUserEquality() {
        // GIVEN
        User sameUser = new User("1", "john", "john@example.com", "password");

        // WHEN
        boolean isEqual = user.equals(sameUser);

        // THEN
        assertThat(isEqual).isTrue();
    }

    @Test
    void testUserInequality() {
        // GIVEN
        User differentUser = new User("2", "jane", "jane@example.com", "pass");

        // WHEN
        boolean isEqual = user.equals(differentUser);

        // THEN
        assertThat(isEqual).isFalse();
    }

    @Test
    void testUserHashCodeConsistency() {
        // GIVEN
        int initialHash = user.hashCode();

        // WHEN
        int newHash = user.hashCode();

        // THEN
        assertThat(initialHash).isEqualTo(newHash);
    }

    @Test
    void testUserToStringContainsFields() {
        // GIVEN
        // WHEN
        String result = user.toString();

        // THEN
        assertThat(result).contains("john");
        assertThat(result).contains("john@example.com");
    }
}
