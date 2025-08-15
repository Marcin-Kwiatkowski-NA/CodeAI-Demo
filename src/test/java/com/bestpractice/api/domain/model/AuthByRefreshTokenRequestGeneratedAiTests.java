package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;

class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void testSetAndGetRefreshToken() {
        // GIVEN: A new AuthByRefreshTokenRequest object is created.
        // WHEN: The refreshToken is set to "testRefreshToken".
        request.setRefreshToken("testRefreshToken");
        // THEN: The refreshToken attribute is set to "testRefreshToken".
        assertEquals("testRefreshToken", request.getRefreshToken());
    }
}