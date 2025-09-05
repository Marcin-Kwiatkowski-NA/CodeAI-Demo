package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.bestpractice.api.app.v1.dto.AuthByRefreshTokenRequest;
import com.bestpractice.api.app.v1.dto.AuthRequest;

import java.util.UUID;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    @DisplayName("Authenticate with Refresh Token")
    void authenticateWithRefreshToken() {
        String refreshToken = UUID.randomUUID().toString();
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest(refreshToken);
        String response = authService.authenticate(request);
        assert response != null;
    }
}
