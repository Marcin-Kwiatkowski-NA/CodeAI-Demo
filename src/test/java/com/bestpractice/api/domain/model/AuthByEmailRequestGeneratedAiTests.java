package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void testGetAndSetEmail() {
        // GIVEN: a valid email string
        String email = "user@example.com";

        // WHEN: setting the email on the request object
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return the same email
        assertEquals(email, authByEmailRequest.getEmail());
        assertThat(authByEmailRequest.getEmail()).isEqualTo(email);
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN: a valid password string
        String password = "securePassword123";

        // WHEN: setting the password on the request object
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return the same password
        assertEquals(password, authByEmailRequest.getPassword());
        assertThat(authByEmailRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the email without setting it
        String email = authByEmailRequest.getEmail();

        // THEN: the email should be null
        assertNull(email);
        assertThat(email).isNull();
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the password without setting it
        String password = authByEmailRequest.getPassword();

        // THEN: the password should be null
        assertNull(password);
        assertThat(password).isNull();
    }

    @Test
    void testSetEmailWithNullValue() {
        // GIVEN: a null email value
        String email = null;

        // WHEN: setting the email to null
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return null
        assertNull(authByEmailRequest.getEmail());
        assertThat(authByEmailRequest.getEmail()).isNull();
    }

    @Test
    void testSetPasswordWithNullValue() {
        // GIVEN: a null password value
        String password = null;

        // WHEN: setting the password to null
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return null
        assertNull(authByEmailRequest.getPassword());
        assertThat(authByEmailRequest.getPassword()).isNull();
    }

    @Test
    void testSetInvalidEmailFormat() {
        // GIVEN: an invalid email format
        String invalidEmail = "invalid-email";

        // WHEN: setting the invalid email
        authByEmailRequest.setEmail(invalidEmail);

        // THEN: the getter should return the same invalid email
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
        assertThat(authByEmailRequest.getEmail()).isEqualTo(invalidEmail);
    }

    @Test
    void testSetEmptyPassword() {
        // GIVEN: an empty password string
        String emptyPassword = "";

        // WHEN: setting the empty password
        authByEmailRequest.setPassword(emptyPassword);

        // THEN: the getter should return the empty string
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
        assertThat(authByEmailRequest.getPassword()).isEmpty();
    }

    @Test
    void testSetEmailAndPasswordTogether() {
        // GIVEN: valid email and password
        String email = "user@example.com";
        String password = "securePassword123";

        // WHEN: setting both email and password
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN: getters should return the correct values
        assertEquals(email, authByEmailRequest.getEmail());
        assertEquals(password, authByEmailRequest.getPassword());
        assertThat(authByEmailRequest.getEmail()).isEqualTo(email);
        assertThat(authByEmailRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testSetEmailAndPasswordNullTogether() {
        // GIVEN: null values for both email and password
        String email = null;
        String password = null;

        // WHEN: setting both email and password to null
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN: both getters should return null
        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
        assertThat(authByEmailRequest.getEmail()).isNull();
        assertThat(authByEmailRequest.getPassword()).isNull();
    }
}
