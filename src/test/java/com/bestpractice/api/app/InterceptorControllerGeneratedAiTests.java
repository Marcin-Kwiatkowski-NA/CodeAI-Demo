package com.bestpractice.api.app;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.ArgumentMatchers.anyString;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
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

    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interceptorController = new InterceptorController(authComponent, requestInfo);
    }

    @Test
    void preHandle_shouldAllowRequestForDisabledAuthEndpoints() throws IOException {
        // GIVEN
        String path = "/api/v1/user";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod("GET");
        verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_shouldThrowUnAuthorizedWhenAuthorizationHeaderIsEmpty() {
        // GIVEN
        String path = "/api/v1/secure";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void preHandle_shouldThrowUnAuthorizedWhenBearerTokenIsInvalid() {
        // GIVEN
        String path = "/api/v1/secure";
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void preHandle_shouldDecodeJwtAndSetRequestInfo() throws IOException {
        // GIVEN
        String path = "/api/v1/secure";
        String bearerToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList(bearerToken));
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod("GET");
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(true);
    }

    @Test
    void postHandle_shouldDoNothing() {
        // GIVEN
        Object handler = new Object();

        // WHEN
        interceptorController.postHandle(request, response, handler, null);

        // THEN
        verifyNoInteractions(requestInfo);
        verifyNoInteractions(authComponent);
    }

    @Test
    void afterCompletion_shouldDoNothing() {
        // GIVEN
        Object handler = new Object();

        // WHEN
        interceptorController.afterCompletion(request, response, handler, null);

        // THEN
        verifyNoInteractions(requestInfo);
        verifyNoInteractions(authComponent);
    }
}