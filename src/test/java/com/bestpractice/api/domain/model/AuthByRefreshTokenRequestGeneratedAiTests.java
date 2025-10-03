package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;
    private Validator validator;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: a valid refresh token string
        String token = "sampleRefreshToken123";

        // WHEN: setting the refresh token on the request
        request.setRefreshToken(token);

        // THEN: getting the refresh token should return the same value
        assertEquals(token, request.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN: a null refresh token
        String token = null;

        // WHEN: setting the refresh token to null
        request.setRefreshToken(token);

        // THEN: getting the refresh token should return null
        assertNull(request.getRefreshToken());
    }

    @Test
    void givenEmptyRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsEmptyString() {
        // GIVEN: an empty refresh token string
        String token = "";

        // WHEN: setting the refresh token to an empty string
        request.setRefreshToken(token);

        // THEN: getting the refresh token should return an empty string
        assertEquals("", request.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenValidated_thenConstraintViolationExceptionIsThrown() {
        // GIVEN: a null refresh token
        request.setRefreshToken(null);

        // WHEN & THEN: validating should throw ConstraintViolationException
        assertThrows(ConstraintViolationException.class, () -> {
            Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    void givenNonNullRefreshToken_whenValidated_thenNoExceptionIsThrown() {
        // GIVEN: a valid refresh token
        request.setRefreshToken("validToken123");

        // WHEN: validating the request
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN: there should be no violations
        assertEquals(0, violations.size());
    }

    @Test
    void givenNullRefreshToken_whenGetRefreshTokenCalled_thenReturnsNullWithoutThrowingException() {
        // GIVEN: refresh token is null
        request.setRefreshToken(null);

        // WHEN: calling getRefreshToken
        String result = request.getRefreshToken();

        // THEN: it should return null and not throw an exception
        assertNull(result);
    }
}
