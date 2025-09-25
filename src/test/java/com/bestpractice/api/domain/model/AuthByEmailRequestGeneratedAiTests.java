package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN: a valid email string
        String email = "user@example.com";

        // WHEN: setting the email on the request object
        authByEmailRequest.setEmail(email);

        // THEN: the retrieved email should match the set value
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a valid password string (security-sensitive)
        String password = "securePassword123";

        // WHEN: setting the password on the request object
        authByEmailRequest.setPassword(password);

        // THEN: the retrieved password should match the set value
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the email without setting it
        String email = authByEmailRequest.getEmail();

        // THEN: the email should be null
        assertNull(email);
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the password without setting it
        String password = authByEmailRequest.getPassword();

        // THEN: the password should be null
        assertNull(password);
    }

    @Test
    void testSetEmailWithNullValue() {
        // GIVEN: a null email value
        String email = null;

        // WHEN: setting the email to null
        authByEmailRequest.setEmail(email);

        // THEN: the retrieved email should be null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithNullValue() {
        // GIVEN: a null password value
        String password = null;

        // WHEN: setting the password to null
        authByEmailRequest.setPassword(password);

        // THEN: the retrieved password should be null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithInvalidFormat() {
        // GIVEN: an invalid email format
        String invalidEmail = "invalid-email";

        // WHEN: setting the invalid email
        authByEmailRequest.setEmail(invalidEmail);

        // THEN: the retrieved email should match the invalid format since no runtime validation occurs
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN: an empty password string
        String emptyPassword = "";

        // WHEN: setting the empty password
        authByEmailRequest.setPassword(emptyPassword);

        // THEN: the retrieved password should match the empty string
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailMultipleTimes() {
        // GIVEN: two different email values
        String firstEmail = "first@example.com";
        String secondEmail = "second@example.com";

        // WHEN: setting the email twice
        authByEmailRequest.setEmail(firstEmail);
        authByEmailRequest.setEmail(secondEmail);

        // THEN: the retrieved email should match the last set value
        assertEquals(secondEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordMultipleTimes() {
        // GIVEN: two different password values (security-sensitive)
        String firstPassword = "firstPass123";
        String secondPassword = "secondPass456";

        // WHEN: setting the password twice
        authByEmailRequest.setPassword(firstPassword);
        authByEmailRequest.setPassword(secondPassword);

        // THEN: the retrieved password should match the last set value
        assertEquals(secondPassword, authByEmailRequest.getPassword());
    }
}
