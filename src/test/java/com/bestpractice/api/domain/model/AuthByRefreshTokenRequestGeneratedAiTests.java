package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;

import java.util.Set;

class AuthByRefreshTokenRequestGeneratedAiTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenValidRefreshToken_ShouldBeValid() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("valid_refresh_token_123");

        // WHEN
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isEmpty();
    }

    @Test
    void givenNullRefreshToken_ShouldHaveViolation() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken(null);

        // WHEN
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).contains("must not be null");
    }

    @Test
    void givenEmptyRefreshToken_ShouldHaveViolation() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("");

        // WHEN
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).contains("must not be empty");
    }

    @Test
    void givenWhitespaceOnlyRefreshToken_ShouldHaveViolation() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("   ");

        // WHEN
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).contains("must not be empty");
    }

    @Test
    void givenValidRefreshToken_ShouldReturnCorrectValue() {
        // GIVEN
        String expectedToken = "abc123xyz";
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken(expectedToken);

        // WHEN
        String actualToken = request.getRefreshToken();

        // THEN
        assertThat(actualToken).isEqualTo(expectedToken);
    }

    @Test
    void givenNullRefreshToken_ShouldReturnNull() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken(null);

        // WHEN
        String actualToken = request.getRefreshToken();

        // THEN
        assertThat(actualToken).isNull();
    }

    @Test
    void givenEmptyRefreshToken_ShouldReturnEmptyString() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("");

        // WHEN
        String actualToken = request.getRefreshToken();

        // THEN
        assertThat(actualToken).isEmpty();
    }
}
