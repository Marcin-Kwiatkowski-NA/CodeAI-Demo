package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.TestExtensions.*;

class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void getRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The getRefreshToken() method is called.
        // THEN: The refreshToken attribute's value is returned.
        String refreshToken = request.getRefreshToken();
        assertEquals(null, refreshToken);
    }

    @Test
    void setRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The setRefreshToken() method is called with a non-null value.
        // THEN: The refreshToken attribute is set to the provided value.
        String refreshToken = "testRefreshToken";
        request.setRefreshToken(refreshToken);
        assertEquals(refreshToken, request.getRefreshToken());
    }
}
