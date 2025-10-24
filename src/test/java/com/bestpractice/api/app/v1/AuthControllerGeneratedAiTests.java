package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
        authService = mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    public void testOptionsAuthReturnsCorrectHeaders() {
        // GIVEN
        // No specific setup required

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    public void testLoginWithEmailSuccess() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", new Date());
        when(authService.login("test@example.com", "password")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
        verify(authService, times(1)).login("test@example.com", "password");
    }

    @Test
    public void testLoginWithEmailValidationErrorThrowsBadRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    public void testLoginWithRefreshTokenSuccess() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token456", "refresh123", new Date());
        when(authService.login("refresh123")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getRefreshToken(), actualResponse.getRefreshToken());
        verify(authService, times(1)).login("refresh123");
    }

    @Test
    public void testLoginWithRefreshTokenValidationErrorThrowsBadRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
        verify(authService, never()).login(anyString());
    }
}
