package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN
        String refreshToken = "sample-refresh-token";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(refreshToken);

        // THEN
        assertEquals(refreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    public void givenNullRefreshToken_whenSetRefreshToken_thenValidationFails() {
        // GIVEN
        String refreshToken = null;

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(refreshToken);
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(authByRefreshTokenRequest);

        // THEN
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    public void givenEmptyRefreshToken_whenSetRefreshToken_thenValidationPasses() {
        // GIVEN
        String refreshToken = "";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(refreshToken);
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(authByRefreshTokenRequest);

        // THEN
        assertEquals(0, violations.size());
    }

    @Test
    public void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN
        String refreshToken = null;

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(refreshToken);

        // THEN
        assertEquals(null, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    public void givenValidRefreshToken_whenValidate_thenNoConstraintViolations() {
        // GIVEN
        String refreshToken = "valid-refresh-token";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(refreshToken);
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(authByRefreshTokenRequest);

        // THEN
        assertEquals(0, violations.size());
    }
}
