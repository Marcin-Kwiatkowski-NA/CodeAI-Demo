package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("deprecation")
class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The getRefreshToken() method is called.
        // THEN: The getRefreshToken() method returns the value set in the refreshToken field.
        String refreshTokenValue = request.getRefreshToken();
        assertEquals("testRefreshToken", refreshTokenValue);
    }

    @Test
    void testSetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The setRefreshToken() method is called with a new value.
        // THEN: The refreshToken field is updated with the provided value.
        String newValue = "newRefreshToken";
        request.setRefreshToken(newValue);
        assertEquals(newValue, request.getRefreshToken());
    }
}
