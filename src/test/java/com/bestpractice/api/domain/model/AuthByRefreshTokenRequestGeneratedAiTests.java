package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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

    @Test
    public void testSetAndGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to "testRefreshToken".
        request.setRefreshToken("testRefreshToken");
        // THEN: The refreshToken field is set to "testRefreshToken".
        assertEquals("testRefreshToken", request.getRefreshToken());
    }

    @Test
    public void testSetRefreshTokenWithNull() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to null.
        request.setRefreshToken(null);
        // THEN: The refreshToken field is set to null.
        assertEquals(null, request.getRefreshToken());
    }

    @Test
    public void testSetRefreshTokenWithEmptyString() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken field is set to an empty string.
        request.setRefreshToken("");
        // THEN: The refreshToken field is set to an empty string.
        assertEquals("", request.getRefreshToken());
    }
}
