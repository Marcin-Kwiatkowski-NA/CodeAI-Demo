package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.ArgumentMatchers.anyString;

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

    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interceptorController = new InterceptorController(authComponent, requestInfo);
    }

    @Test
    void preHandle_shouldSetRequestInfoAndReturnTrue_whenPathStartsWithError() throws IOException {
        // GIVEN
        String path = "error";
        when(request.getRequestURI()).thenReturn(path);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
        assertThat(result).isTrue();
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenAuthorizationHeaderIsEmpty() throws IOException {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");

        // THEN
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void preHandle_shouldThrowUnAuthorized_whenBearerTokenIsInvalid() throws IOException {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("InvalidToken");

        // WHEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");

        // THEN
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void preHandle_shouldSetUserInfo_whenBearerTokenIsValid() throws IOException {
        // GIVEN
        String path = "/api/v1/resource";
        String bearerToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(request.getRequestURI()).thenReturn(path);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(bearerToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(true);
        assertThat(result).isTrue();
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
