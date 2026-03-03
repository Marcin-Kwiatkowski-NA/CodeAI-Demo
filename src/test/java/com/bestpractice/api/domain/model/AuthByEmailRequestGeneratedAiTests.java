package com.bestpractice.api.domain.model;

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
        request = new AuthByEmailRequest();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN
        String email = "user@example.com";

        // WHEN
        request.setEmail(email);

        // THEN
        assertThat(request.getEmail()).isEqualTo(email);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "StrongPassword123";

        // WHEN
        request.setPassword(password);

        // THEN
        assertThat(request.getPassword()).isEqualTo(password);
    }

    @Test
    void testEmailValidationNotNull() {
        // GIVEN
        request.setEmail(null);
        request.setPassword("password");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).hasSize(1);
        ConstraintViolation<AuthByEmailRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
        assertThat(violation.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(NotNull.class);
    }

    @Test
    void testEmailValidationInvalidFormat() {
        // GIVEN
        request.setEmail("not-an-email");
        request.setPassword("password");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).hasSize(1);
        ConstraintViolation<AuthByEmailRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
        assertThat(violation.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(Email.class);
    }

    @Test
    void testEmailValidationValid() {
        // GIVEN
        request.setEmail("valid@example.com");
        request.setPassword("password");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isEmpty();
    }

    @Test
    void testPasswordValidationNotNull() {
        // GIVEN
        request.setEmail("user@example.com");
        request.setPassword(null);

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).hasSize(1);
        ConstraintViolation<AuthByEmailRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("password");
        assertThat(violation.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(NotNull.class);
    }

    @Test
    void testPasswordValidationValid() {
        // GIVEN
        request.setEmail("user@example.com");
        request.setPassword("StrongPassword123");

        // WHEN
        Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isEmpty();
    }
}
