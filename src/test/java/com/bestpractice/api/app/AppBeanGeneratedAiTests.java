package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.util.Util;
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
import static org.assertj.core.api.Assertions.catchThrowable;


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
    void testPreHandle_WithValidAuthorizationHeader() throws IOException {
        // GIVEN
        String bearerToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(bearerToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("userEmail");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("userEmail");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void testPreHandle_WithMissingAuthorizationHeader() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(request, response, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization header is empty");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void testPreHandle_WithInvalidBearerToken() {
        // GIVEN
        String invalidToken = "InvalidToken";
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(invalidToken);

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(request, response, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization supports Bearer format");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void testPreHandle_WithSpringErrorPath() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isFalse();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/error");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void testPostHandle() {
        // GIVEN
        // No specific setup required

        // WHEN
        interceptorController.postHandle(request, response, new Object(), null);

        // THEN
        // No assertions required as postHandle does not modify state or throw exceptions
        verifyNoInteractions(requestInfo);
    }

    @Test
    void testAfterCompletion() {
        // GIVEN
        Exception exception = new Exception("Test Exception");

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), exception);

        // THEN
        // No assertions required as afterCompletion does not modify state or throw exceptions
        verifyNoInteractions(requestInfo);
    }
}