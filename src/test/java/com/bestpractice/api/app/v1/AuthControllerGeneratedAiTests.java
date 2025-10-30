package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
        // GIVEN
        // No special setup required

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    public void testEmailLoginSuccess() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("token123", "refreshToken123", "userId123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testEmailLoginValidationErrorThrowsBadRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
        assertEquals("authByEmailRequest", thrown.getMessage());
    }

    @Test
    public void testRefreshTokenLoginSuccess() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("token456", "refreshToken456", "userId456", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testRefreshTokenLoginValidationErrorThrowsBadRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
        assertEquals("authByRefreshTokenRequest", thrown.getMessage());
    }

    @Test
    public void testEmailLoginThrowsRuntimeExceptionFromService() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authService.login(request.getEmail(), request.getPassword())).thenThrow(new RuntimeException("Service failure"));

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> authController.login(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void testRefreshTokenLoginThrowsRuntimeExceptionFromService() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authService.login(request.getRefreshToken())).thenThrow(new RuntimeException("Service failure"));

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> authController.login(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }
}
