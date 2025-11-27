package com.bestpractice.api.app;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class InterceptorControllerGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Enumeration<String> headers;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void preHandle_shouldReturnTrue_whenPathStartsWithError() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("error");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenAuthorizationHeaderIsEmpty() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        // THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");

        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secure");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenBearerTokenIsInvalid() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("InvalidToken");
        when(request.getMethod()).thenReturn("POST");

        // WHEN
        // THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");

        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secure");
        verify(requestInfo).setHttpMethod("POST");
    }

    @Test
    void preHandle_shouldDecodeJwtAndSetRequestInfo_whenBearerTokenIsValid() throws IOException {
        // GIVEN
        String validToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(validToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(request.getMethod()).thenReturn("PUT");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secure");
        verify(requestInfo).setHttpMethod("        verify(requestInfo).setHttpMethod("PUT");
        verify(requestInfo).setUserId("user123");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(true);
    }

    @Test
    void preHandle_shouldReturnTrue_whenPathIsInDisableAuthEndpoints() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/user");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void postHandle_shouldDoNothing() {
        // GIVEN
        // No specific setup required

        // WHEN
        interceptorController.postHandle(request, response, new Object(), null);

        // THEN
        verifyNoInteractions(requestInfo, authComponent);
    }

    @Test
    void afterCompletion_shouldDoNothing() {
        // GIVEN
        // No specific setup required

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), null);

        // THEN
        verifyNoInteractions(requestInfo, authComponent);
    }
}