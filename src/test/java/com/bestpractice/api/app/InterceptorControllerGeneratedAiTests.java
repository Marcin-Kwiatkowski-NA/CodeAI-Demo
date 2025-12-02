package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.verifyNoInteractions;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
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

    @Mock
    private DecodedJWT decodedJWT;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void preHandle_shouldReturnTrue_whenPathStartsWithError() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error/somePath");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo, never()).setRequestId(anyString());
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenAuthorizationHeaderIsEmpty() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secureEndpoint");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenBearerTokenIsInvalid() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secureEndpoint");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("InvalidToken");
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");
    }

    @Test
    void preHandle_shouldSetRequestInfo_whenBearerTokenIsValid() throws IOException {
        // GIVEN
        String validToken = "Bearer validToken";
        String userId = "12345";
        String userEmail = "user@example.com";

        when(request.getRequestURI()).thenReturn("/api/v1/secureEndpoint");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(validToken);
        when(Util.getSpringProfileActive()).thenReturn("prod");
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn(userId);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(userEmail);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secureEndpoint");
        verify(requestInfo).setHttpMethod("GET");
        verify(requestInfo).setUserId(userId);
        verify(requestInfo).setUserEmail(userEmail);
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void preHandle_shouldReturnTrue_whenPathIsDisabledAuthEndpoint() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/user");
        verify(requestInfo, never()).setUserId(anyString());
        verify(requestInfo, never()).setUserEmail(anyString());
        verify(requestInfo, never()).setRefreshToken(anyBoolean());
    }

    @Test
    void preHandle_shouldReturnTrue_whenSpringProfileIsLocal() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secureEndpoint");
        when(Util.getSpringProfileActive()).thenReturn("local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secureEndpoint");
        verify(requestInfo).setHttpMethod(anyString());
        verify(requestInfo, never()).setUserId(anyString());
        verify(requestInfo, never()).setUserEmail(anyString());
        verify(requestInfo, never()).setRefreshToken(anyBoolean());
    }

    @Test
    void postHandle_shouldDoNothing() {
        // GIVEN
        Object handler = new Object();

        // WHEN
        interceptorController.postHandle(request, response, handler, null);

        // THEN
        verifyNoInteractions(requestInfo);
    }

    @Test
    void afterCompletion_shouldDoNothing() {
        // GIVEN
        Object handler = new Object();

        // WHEN
        interceptorController.afterCompletion(request, response, handler, null);

        // THEN
        verifyNoInteractions(requestInfo);
    }
}
