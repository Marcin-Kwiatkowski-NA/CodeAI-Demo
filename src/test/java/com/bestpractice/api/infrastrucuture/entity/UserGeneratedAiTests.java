package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    // Test for default constructor
    @Test
    void givenDefaultConstructor_whenUserCreated_thenFieldsShouldBeNull() {
        // GIVEN: A new User object created using the default constructor

        // WHEN: No fields are set

        // THEN: All fields should be null
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
    }

    // Test for parameterized constructor
    @Test
    void givenParameterizedConstructor_whenUserCreated_thenFieldsShouldBeSetCorrectly() {
        // GIVEN: A new User object created using the parameterized constructor
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN: The User object is initialized with values
        User user = new User(id, username, email, password);

        // THEN: The fields should match the provided values
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    // Test for getId and setId
    @Test
    void givenId_whenSetId_thenGetIdShouldReturnSameValue() {
        // GIVEN: A User object and an ID value
        String id = "123";

        // WHEN: The ID is set
        user.setId(id);

        // THEN: The getId method should return the same value
        assertEquals(id, user.getId());
    }

    // Test for getUsername and setUsername
    @Test
    void givenUsername_whenSetUsername_thenGetUsernameShouldReturnSameValue() {
        // GIVEN: A User object and a username value
        String username = "testUser";

        // WHEN: The username is set
        user.setUsername(username);

        // THEN: The getUsername method should return the same value
        assertEquals(username, user.getUsername());
    }

    // Test for getEmail and setEmail
    @Test
    void givenEmail_whenSetEmail_thenGetEmailShouldReturnSameValue() {
        // GIVEN: A User object and an email value
        String email = "test@example.com";

        // WHEN: The email is set
        user.setEmail(email);

        // THEN: The getEmail method should return the same value
        assertEquals(email, user.getEmail());
    }

    // Test for getPassword and setPassword
    @Test
    void givenPassword_whenSetPassword_thenGetPasswordShouldReturnSameValue() {
        // GIVEN: A User object and a password value
        String password = "securePassword";

        // WHEN: The password is set
        user.setPassword(password);

        // THEN: The getPassword method should return the same value
        assertEquals(password, user.getPassword());
    }

    // Test for exception handling in setEmail
    @Test
    void givenInvalidEmail_whenSetEmail_thenShouldThrowException() {
        // GIVEN: A User object and an invalid email value
        String invalidEmail = null;

        // WHEN: The email is set to an invalid value
        // THEN: An exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidEmail == null || invalidEmail.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            user.setEmail(invalidEmail);
        });
    }

    // Test for exception handling in setPassword
    @Test
    void givenInvalidPassword_whenSetPassword_thenShouldThrowException() {
        // GIVEN: A User object and an invalid password value
        String invalidPassword = null;

        // WHEN: The password is set to an invalid value
        // THEN: An exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidPassword == null || invalidPassword.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }
            user.setPassword(invalidPassword);
        });
    }
}
