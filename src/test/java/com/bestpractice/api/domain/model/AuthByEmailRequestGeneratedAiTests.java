package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AuthByEmailRequestGeneratedAiTests {

    @InjectMocks
    private AuthByEmailRequest authByEmailRequest;

    @Mock
    private ValidatorFactory validatorFactory;

    @Mock
    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void givenValidEmailAndPassword_WhenValidating_ThenNoViolations() {
        // GIVEN
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("password123");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(authByEmailRequest);

        // THEN
        assertTrue(violations.isEmpty());
    }

    @Test
    void givenInvalidEmail_WhenValidating_ThenViolation() {
        // GIVEN
        authByEmailRequest.setEmail("invalid-email");
        authByEmailRequest.setPassword("password123");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(authByEmailRequest);

        // THEN
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenNullEmail_WhenValidating_ThenViolation() {
        // GIVEN
        authByEmailRequest.setEmail(null);
        authByEmailRequest.setPassword("password123");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(authByEmailRequest);

        // THEN
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenNullPassword_WhenValidating_ThenViolation() {
        // GIVEN
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword(null);

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(authByEmailRequest);

        // THEN
        assertFalse(violations.isEmpty());
    }
}
