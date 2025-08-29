package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void testLoginWithValidEmail() {
        AuthByEmailRequest request = new AuthByEmailRequest("test@example.com");
        AuthResponse response = authService.login(request);
        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void testLoginWithInvalidEmail() {
        AuthByEmailRequest request = new AuthByEmailRequest("invalid-email");
        assertThrows(BadRequest.class, () -> authService.login(request));
    }

    @Test
    void testRefreshTokenLogin() {
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest("refresh_token");
        AuthResponse response = authService.login(request);
        assertNotNull(response);
        assertEquals("refresh_token", response.getRefreshToken());
    }
}
