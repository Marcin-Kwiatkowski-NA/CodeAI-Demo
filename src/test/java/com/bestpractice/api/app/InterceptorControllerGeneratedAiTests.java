package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.app.InterceptorController;
import com.bestpractice.api.util.Util;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest(Util.class)
class InterceptorControllerGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;
    @Mock
    private RequestInfoComponent requestInfoComponent;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private DecodedJWT decodedJwt;
    @Mock
    private Claim emailClaim;
    @Mock
    private Claim refreshClaim;
    @Mock
    private Enumeration<String> headersEnum;

    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        interceptorController = new InterceptorController(authComponent, requestInfoComponent);
    }

    @Test
    void preHandleWithAuthSuccess() {
        // GIVEN
        String path = "/api/secure";
        String method = "GET";
        String token = "validToken";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(true);
        when(headersEnum.nextElement()).thenReturn("Bearer " + token);

        when(authComponent.decodeJwt(token)).thenReturn(decodedJwt);
        when(decodedJwt.getSubject()).thenReturn("user123");
        when(decodedJwt.getClaim("email")).thenReturn(emailClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(decodedJwt.getClaim("refresh")).thenReturn(refreshClaim);
        when(refreshClaim.asBoolean()).thenReturn(true);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, null);

        // THEN
        assertThat(result).isTrue();
        verify(requestInfoComponent).setRequestId(anyString());
        verify(requestInfoComponent).setPath(path);
        verify(requestInfoComponent).setHttpMethod(method);
        verify(requestInfoComponent).setUserId("user123");
        verify(requestInfoComponent).setUserEmail("user@example.com");
        verify(requestInfoComponent).setRefreshToken(true);
    }

    @Test
    void preHandleWithoutAuthorizationHeader() {
        // GIVEN
        String path = "/api/secure";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, null))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is missing");
    }

    @Test
    void preHandleWithInvalidBearerFormat() {
        // GIVEN
        String path = "/api/secure";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(true);
        when(headersEnum.nextElement()).thenReturn("Token abc");

        // WHEN & THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, null))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Bearer token is missing");
    }

    @Test
    void preHandleWithErrorPath() {
        // GIVEN
        String path = "error/something";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, null);

        // THEN
        assertThat(result).isFalse();
        verify(requestInfoComponent, never()).decodeJwt(anyString());
    }

    @Test
    void preHandleWithDisabledAuthEndpoint() {
        // GIVEN
        String path = "/api/v1/user";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(true);
        when(headersEnum.nextElement()).thenReturn("Bearer dummy");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, null);

        // THEN
        assertThat(result).isTrue();
        verify(authComponent, never()).decodeJwt(anyString());
    }

    @Test
    void preHandleWithLocalProfile() {
        // GIVEN
        String path = "/api/secure";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(true);
        when(headersEnum.nextElement()).thenReturn("Bearer dummy");

        mockStatic(Util.class);
        when(Util.getSpringProfileActive()).thenReturn("local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, null);

        // THEN
        assertThat(result).isTrue();
        verify(authComponent, never()).decodeJwt(anyString());
    }

    @Test
    void postHandleAndAfterCompletion() {
        // GIVEN
        String path = "/api/secure";
        String method = "GET";
        when(request.getRequestURI()).thenReturn(path);
        when(request.getMethod()).thenReturn(method);
        when(request.getHeaders("Authorization")).thenReturn(headersEnum);
        when(headersEnum.hasMoreElements()).thenReturn(true);
        when(headersEnum.nextElement()).thenReturn("Bearer token");

        // WHEN
        interceptorController.postHandle(request, response, null, null);
        interceptorController.afterCompletion(request, response, null, null);

        // THEN
        verify(requestInfoComponent).setRequestId(anyString());
        verify(requestInfoComponent).setPath(path);
        verify(requestInfoComponent).setHttpMethod(method);
    }
}
