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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest request;
    private Validator validator;

    @BeforeEach
    void setUp() {
        // GIVEN a fresh AuthByEmailRequest instance
        request = new AuthByEmailRequest();

        // GIVEN a validator for constraint checks
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN an email value
        String email = "user@example.com";

        // WHEN setting the email
        request.setEmail(email);

        // THEN the getter should return the same value
        assertThat(request.getEmail()).isEqualTo(email);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN a password value
        String password = "SecurePass123";

        // WHEN setting the password
        request.setPassword(password);

        // THEN the getter should return the same value
        assertThat(request.getPassword()).isEqualTo(password);
    }

    @Test
    void testValidationWithValidEmail() {
        // GIVEN a valid email and non-null password
        request.setEmail("valid.user@example.com");
        request.setPassword("password");

        // WHEN validating the request
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN there should be no violations
        assertThat(violations).isEmpty();
    }

    @Test
    void testValidationWithInvalidEmail() {
        // GIVEN an invalid email format
        request.setEmail("invalid-email");
        request.setPassword("password");

        // WHEN validating the request
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN there should be a violation for the email field
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    void testValidationWithNullEmail() {
        // GIVEN a null email
        request.setEmail(null);
        request.setPassword("password");

        // WHEN validating the request
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN there should be a violation for the email field due to @NotNull
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    void testValidationWithNullPassword() {
        // GIVEN a null password
        request.setEmail("user@example.com");
        request.setPassword(null);

        // WHEN validating the request
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN there should be a violation for the password field due to @NotNull
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("password"));
    }
}
