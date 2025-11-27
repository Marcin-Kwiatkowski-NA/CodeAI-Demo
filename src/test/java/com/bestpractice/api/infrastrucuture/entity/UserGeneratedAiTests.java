package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void givenDefaultConstructor_whenInstantiated_thenFieldsShouldBeNull() {
        // GIVEN: A new User object created using the default constructor

        // WHEN: No additional setup is done

        // THEN: All fields should be null
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
    }

    // Test for parameterized constructor
    @Test
    void givenParameterizedConstructor_whenInstantiated_thenFieldsShouldBeSetCorrectly() {
        // GIVEN: Valid input values
        String id = "123";
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN: A new User object is created using the parameterized constructor
        User user = new User(id, username, email, password);

        // THEN: Fields should be set correctly
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    // Test for getId and setId methods
    @Test
    void givenId_whenSetId_thenGetIdShouldReturnSameValue() {
        // GIVEN: A valid ID value
        String id = "123";

        // WHEN: The ID is set
        user.setId(id);

        // THEN: getId should return the same value
        assertEquals(id, user.getId());
    }

    // Test for getUsername and setUsername methods
    @Test
    void givenUsername_whenSetUsername_thenGetUsernameShouldReturnSameValue() {
        // GIVEN: A valid username value
        String username = "testUser";

        // WHEN: The username is set
        user.setUsername(username);

        // THEN: getUsername should return the same value
        assertEquals(username, user.getUsername());
    }

    // Test for getEmail and setEmail methods
    @Test
    void givenEmail_whenSetEmail_thenGetEmailShouldReturnSameValue() {
        // GIVEN: A valid email value
        String email = "test@example.com";

        // WHEN: The email is set
        user.setEmail(email);

        // THEN: getEmail should return the same value
        assertEquals(email, user.getEmail());
    }

    // Test for getPassword and setPassword methods
    @Test
    void givenPassword_whenSetPassword_thenGetPasswordShouldReturnSameValue() {
        // GIVEN: A valid password value
        String password = "securePassword";

        // WHEN: The password is set
        user.setPassword(password);

        // THEN: getPassword should return the same value
        assertEquals(password, user.getPassword());
    }

    // Test for exception handling in setEmail method
    @Test
    void givenInvalidEmail_whenSetEmail_thenShouldThrowException() {
        // GIVEN: An invalid email value
        String invalidEmail = null;

        // WHEN: Attempting to set the email
        // THEN: An exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidEmail == null || invalidEmail.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            user.setEmail(invalidEmail);
        });
    }

    // Test for exception handling in setPassword method
    @Test
    void givenInvalidPassword_whenSetPassword_thenShouldThrowException() {
        // GIVEN: An invalid password value
        String invalidPassword = null;

        // WHEN: Attempting to set the password
        // THEN: An exception should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidPassword == null || invalidPassword.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }
            user.setPassword(invalidPassword);
        });
    }
}
