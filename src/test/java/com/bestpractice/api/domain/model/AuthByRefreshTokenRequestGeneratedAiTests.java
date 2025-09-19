package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(DisplayNameGenerator.class)
class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void testSetAndGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken is set to a non-empty string.
        request.setRefreshToken("someRefreshToken");
        // THEN: The refreshToken field is set to "someRefreshToken".
        assertEquals("someRefreshToken", request.getRefreshToken());
    }

    @Test
    void testRefreshTokenIsRequired() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken is not set.
        // THEN: An exception is thrown because refreshToken is not allowed to be null or empty.
        assertThrows(NullPointerException.class, () -> request.setRefreshToken(null));
    }
}
