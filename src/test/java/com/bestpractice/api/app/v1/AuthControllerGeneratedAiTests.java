package com.bestpractice.api.app.v1;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

    @Mock
    private AuthService authService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        // No manual instantiation or reset needed
    }

    @Test
    void optionsAuth_shouldReturnOkResponseWithHeaders() {
        // GIVEN
        // No specific setup required

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HttpHeaders headers = response.getHeaders();
        assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
        assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
    }

    @Test
    void login_withEmailRequest_shouldReturnAuthResponse_whenNoValidationErrors() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getToken()).isEqualTo("token123");
        assertThat(actualResponse.getRefreshToken()).isEqualTo("refresh123");
    }

    @Test
    void login_withEmailRequest_shouldThrowBadRequest_whenValidationErrorsExist() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("authByEmailRequest");
    }

    @Test
    void login_withRefreshTokenRequest_shouldReturnAuthResponse_whenNoValidationErrors() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenXYZ", "refreshXYZ", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getTokenType()).isEqualTo("Bearer");
        assertThat(actualResponse.getToken()).isEqualTo("tokenXYZ");
    }

    @Test
    void login_withRefreshTokenRequest_shouldThrowBadRequest_whenValidationErrorsExist() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("authByRefreshTokenRequest");
    }
}