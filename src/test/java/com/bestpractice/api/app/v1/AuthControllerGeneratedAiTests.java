package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerGeneratedAiTests {

    private AuthService authService;
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        authService = Mockito.mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    public void testOptionsAuthReturnsCorrectHeaders() {
        // GIVEN - setup preconditions
        // No special preconditions needed

        // WHEN - perform the action
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN - verify the outcome
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    public void testEmailLoginSuccess() {
        // GIVEN - setup preconditions
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken123", "refreshToken123", "userId123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN - perform the action
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN - verify the outcome
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testEmailLoginValidationErrorThrowsBadRequest() {
        // GIVEN - setup preconditions
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN & THEN - perform the action and verify exception
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }

    @Test
    public void testRefreshTokenLoginSuccess() {
        // GIVEN - setup preconditions
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token-123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken456", "refreshToken456", "userId456", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN - perform the action
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN - verify the outcome
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testRefreshTokenLoginValidationErrorThrowsBadRequest() {
        // GIVEN - setup preconditions
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN & THEN - perform the action and verify exception
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }
}
