package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        String token = "sampleRefreshToken";

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same token
        assertEquals(token, request.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN: a null refresh token
        String token = null;

        // WHEN: setting the refresh token to null
        request.setRefreshToken(token);

        // THEN: the getter should return null
        assertNull(request.getRefreshToken());
    }

    @Test
    void givenEmptyRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsEmptyString() {
        // GIVEN: an empty refresh token string
        String token = "";

        // WHEN: setting the refresh token to empty string
        request.setRefreshToken(token);

        // THEN: the getter should return empty string
        assertEquals("", request.getRefreshToken());
    }

    @Test
    void givenUnsetRefreshToken_whenGetRefreshToken_thenReturnsNull() {
        // GIVEN: refresh token not set

        // WHEN: calling getter without setting
        String result = request.getRefreshToken();

        // THEN: should return null
        assertNull(result);
    }

    @Test
    void givenNullRefreshToken_whenValidated_thenConstraintViolationExceptionThrown() {
        // GIVEN: refresh token is null
        request.setRefreshToken(null);

        // WHEN & THEN: validating should throw ConstraintViolationException due to @NotNull
        assertThrows(ConstraintViolationException.class, () -> {
            Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    void givenValidRefreshToken_whenValidated_thenNoConstraintViolations() {
        // GIVEN: a valid refresh token
        request.setRefreshToken("validToken");

        // WHEN: validating the request
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN: there should be no violations
        assertEquals(0, violations.size());
    }

    @Test
    void givenNullRefreshToken_whenValidatedDirectly_thenViolationsContainExpectedPropertyPath() {
        // GIVEN: refresh token is null
        request.setRefreshToken(null);

        // WHEN: validating the request
        Set<ConstraintViolation<AuthByRefreshTokenRequest>> violations = validator.validate(request);

        // THEN: there should be exactly one violation with the expected property path
        assertEquals(1, violations.size());
        ConstraintViolation<AuthByRefreshTokenRequest> violation = violations.iterator().next();
        assertEquals("refreshToken", violation.getPropertyPath().toString());
    }

    @Test
    void givenWhitespaceRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: a whitespace refresh token string
        String token = "   ";

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same whitespace string
        assertEquals(token, request.getRefreshToken());
    }
}
