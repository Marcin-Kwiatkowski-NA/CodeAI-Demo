package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Mock
    private DecodedJWT decodedJWT;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidRequest_whenPreHandle_thenReturnsTrue() throws IOException {
        // GIVEN
        String path = "/api/v1/resource";
        String method = "GET";
        String bearerToken = "Bearer validToken";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(bearerToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(method);
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void givenRequestWithoutAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void givenRequestWithInvalidBearerToken_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        String path = "/api/v1/resource";
        String invalidToken = "InvalidToken";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(invalidToken);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization supports Bearer format");
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void givenLocalProfile_whenPreHandle_thenReturnsTrue() throws IOException {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);
        when(Util.getSpringProfileActive()).thenReturn("local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        String path = "error";
        when(request.getRequestURI()).thenReturn(path);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isFalse();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath(path);
        verify(requestInfo).setHttpMethod(anyString());
    }

    @Test
    void givenValidRequest_whenPostHandle_thenDoesNothing() {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);

        // WHEN
        interceptorController.postHandle(request, response, new Object(), null);

        // THEN
        verifyNoInteractions(requestInfo);
    }

    @Test
    void givenValidRequest_whenAfterCompletion_thenDoesNothing() {
        // GIVEN
        String path = "/api/v1/resource";
        when(request.getRequestURI()).thenReturn(path);

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), null);

        // THEN
        verifyNoInteractions(requestInfo);
    }
}