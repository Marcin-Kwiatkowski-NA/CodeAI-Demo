package com.bestpractice.api.app;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
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
    void preHandle_shouldReturnTrueForErrorPath() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(requestInfo.getPath()).thenReturn("error");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("error");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
    }

    @Test
    void preHandle_shouldThrowUnAuthorizedWhenAuthorizationHeaderIsEmpty() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        when(requestInfo.getPath()).thenReturn("/api/v1/resource");
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("/api/v1/resource");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
    }

    @Test
    void preHandle_shouldThrowUnAuthorizedWhenBearerTokenIsInvalid() throws IOException {
        // GIVEN
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(requestInfo.getPath()).thenReturn("/api/v1/resource");
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("/api/v1/resource");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
    }

    @Test
    void preHandle_shouldDecodeJwtAndSetRequestInfo() throws IOException {
        // GIVEN
        String bearerToken = "Bearer validToken";
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList(bearerToken));
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(requestInfo.getPath()).thenReturn("/api/v1/resource");
        when(Util.getSpringProfileActive()).thenReturn("prod");
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        booleanresult = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("/api/v1/resource");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
        verify(requestInfo, times(1)).setUserId("userId");
        verify(requestInfo, times(1)).setUserEmail("user@example.com");
        verify(requestInfo, times(1)).setRefreshToken(true);
    }

    @Test
    void preHandle_shouldSkipAuthForDisabledEndpoints() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(requestInfo.getPath()).thenReturn("/api/v1/user");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("/api/v1/user");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
        verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_shouldSkipAuthForLocalProfile() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(requestInfo.getPath()).thenReturn("/api/v1/resource");
        when(Util.getSpringProfileActive()).thenReturn("local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath("/api/v1/resource");
        verify(requestInfo, times(1)).setHttpMethod(anyString());
        verifyNoInteractions(authComponent);
    }

    @Test
    void postHandle_shouldDoNothing() {
        // GIVEN
        // No specific setup required

        // WHEN
        interceptorController.postHandle(request, response, new Object(), null);

        // THEN
        // No interactions or state changes to verify
        verifyNoInteractions(requestInfo);
        verifyNoInteractions(authComponent);
    }

    @Test
    void afterCompletion_shouldDoNothing() {
        // GIVEN
        // No specific setup required

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), null);

        // THEN
        // No interactions or state changes to verify
        verifyNoInteractions(requestInfo);
        verifyNoInteractions(authComponent);
    }
}