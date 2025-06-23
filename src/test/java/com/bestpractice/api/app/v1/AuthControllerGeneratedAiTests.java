package com.bestpractice.api.app.v1;

import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.*;

package com.bestpractice.api.app.v1;

public class AuthControllerTest {

    @Test
    public void testOptionsAuth() {
        // Arrange
        AuthService authService = new AuthService();
        String email = "test@example.com";
        String password = "password123";

        // Act
        ResponseEntity<Object> response = authService.optionsAuth();
        assertEquals("OK", response.getBody());
    }

    @Test
    public void testLogin(AuthByEmailRequest request) {
        // Arrange
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.getEmail();
        request.getPassword();

        // Act
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        assertEquals("Login successful", response.getTokenType());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/email-login")
    public AuthResponse login(String email, String password) {
        // Arrange
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.getEmail();
        request.getPassword();

        // Act
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return response;
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/refreshtoken-login")
    public AuthResponse login(AuthByRefreshTokenRequest request) {
        // Arrange
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.getRefreshToken();

        // Act
        AuthResponse response = authService.login(request.getRefreshToken(), request.getToken());
        return response;
    }
}
