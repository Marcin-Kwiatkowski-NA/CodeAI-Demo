package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("someRefreshToken");
    }

    @Test
    void getRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The getRefreshToken() method is called.
        // THEN: The refreshToken attribute's value is returned.
        String refreshToken = request.getRefreshToken();
        assertEquals("someRefreshToken", refreshToken);
    }

    @Test
    void setRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The setRefreshToken() method is called with a non-empty string.
        // THEN: The refreshToken attribute is set to the provided string.
        String newRefreshToken = "someRefreshToken";
        request.setRefreshToken(newRefreshToken);
        assertEquals(newRefreshToken, request.getRefreshToken());
    }
}
