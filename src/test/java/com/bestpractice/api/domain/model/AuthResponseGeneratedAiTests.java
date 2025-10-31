package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void testGetTokenTypeReturnsCorrectValue() {
        // GIVEN: an AuthResponse instance with predefined tokenType
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: getTokenType is called
        String result = authResponse.getTokenType();

        // THEN: the returned tokenType should match the initialized value
        assertEquals(tokenType, result);
    }

    @Test
    void testGetTokenReturnsCorrectValue() {
        // GIVEN: an AuthResponse instance with predefined token
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: getToken is called
        String result = authResponse.getToken();

        // THEN: the returned token should match the initialized value
        assertEquals(token, result);
    }

    @Test
    void testGetRefreshTokenReturnsCorrectValue() {
        // GIVEN: an AuthResponse instance with predefined refreshToken
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: getRefreshToken is called
        String result = authResponse.getRefreshToken();

        // THEN: the returned refreshToken should match the initialized value
        assertEquals(refreshToken, result);
    }

    @Test
    void testGetExpiresAtReturnsCorrectValue() {
        // GIVEN: an AuthResponse instance with predefined expiresAt date
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: getExpiresAt is called
        Date result = authResponse.getExpiresAt();

        // THEN: the returned expiresAt should match the initialized value
        assertEquals(expiresAt, result);
    }

    @Test
    void testAuthResponseFieldsAreNotNull() {
        // GIVEN: an AuthResponse instance with all fields initialized
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: accessing all fields via getters
        String actualTokenType = authResponse.getTokenType();
        String actualToken = authResponse.getToken();
        String actualRefreshToken = authResponse.getRefreshToken();
        Date actualExpiresAt = authResponse.getExpiresAt();

        // THEN: none of the returned values should be null
        assertNotNull(actualTokenType);
        assertNotNull(actualToken);
        assertNotNull(actualRefreshToken);
        assertNotNull(actualExpiresAt);
    }
}
