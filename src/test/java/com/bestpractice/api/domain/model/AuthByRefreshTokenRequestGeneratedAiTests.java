package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    @DisplayName("Test setting and getting refreshToken")
    void testSetAndGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken is set to a valid value.
        String refreshTokenValue = "validRefreshToken";
        request.setRefreshToken(refreshTokenValue);
        // THEN: The refreshToken field is set to the specified value.
        assertEquals(refreshTokenValue, request.getRefreshToken());
    }

    @Test
    @DisplayName("Test when refreshToken is null")
    void testSetRefreshTokenNull() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken is set to null.
        request.setRefreshToken(null);
        // THEN: The refreshToken field is set to null.
        assertEquals(null, request.getRefreshToken());
    }
}
