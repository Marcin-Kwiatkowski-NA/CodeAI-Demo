package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    public void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @AfterEach
    public void tearDown() {
        request = null;
    }

    @Test
    public void testSetAndGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to a non-empty string.
        String refreshTokenValue = "some-refresh-token";
        request.setRefreshToken(refreshTokenValue);
        // THEN: The refreshToken field is set to the provided value.
        String retrievedRefreshToken = request.getRefreshToken();
        assertEquals(refreshTokenValue, retrievedRefreshToken);
    }

    @Test
    public void testSetEmptyRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to an empty string.
        // THEN: The refreshToken field is set to an empty string.
        request.setRefreshToken("");
        String retrievedRefreshToken = request.getRefreshToken();
        assertEquals("", retrievedRefreshToken);
    }

    @Test
    public void testSetNullRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to null.
        // THEN: The refreshToken field is set to null.
        request.setRefreshToken(null);
        String retrievedRefreshToken = request.getRefreshToken();
        assertEquals(null, retrievedRefreshToken);
    }
}
