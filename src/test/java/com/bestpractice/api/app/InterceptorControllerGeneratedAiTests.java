package com.bestpractice.api.app;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mockStatic;

import com.auth0.jwt.interfaces.Claim;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class InterceptorControllerGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Object handler;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        clearInvocations(authComponent, requestInfo, request, response, handler);
    }

    @Test
    void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertThat(result).isFalse();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("error");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void givenDisabledAuthEndpoint_whenPreHandle_thenReturnsTrueWithoutAuthCheck() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("POST");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/user");
        verify(requestInfo).setHttpMethod("POST");
        verifyNoInteractions(authComponent);
    }

    @Test
    void givenMissingAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> authorization = new Enumeration<>() {
            @Override
            public boolean hasMoreElements() { return false; }
            @Override
            public String nextElement() { return null; }
        };
        when(request.getHeaders("Authorization")).thenReturn(authorization);
        mockStatic(Util.class);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, handler))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void givenInvalidBearerFormat_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = new Enumeration<>() {
            private boolean hasMore = true;
            @Override
            public boolean hasMoreElements() { return hasMore; }
            @Override
            public String nextElement() { hasMore = false; return "InvalidToken"; }
        };
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStatic(Util.class);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, handler))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenSetsRequestInfoAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = new Enumeration<>() {
            private boolean hasMore = true;
            @Override
            public boolean hasMoreElements() { return hasMore; }
            @Override
            public String nextElement() { hasMore = false; return "Bearer valid.jwt.token"; }
        };
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStatic(Util.class);
        when(Util.getSpringProfileActive()).thenReturn("prod");

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(authComponent.decodeJwt("valid.jwt.token")).thenReturn(decodedJWT);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setUserId("user123");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void givenPostHandle_whenCalled_thenNoException() {
        // GIVEN
        ModelAndView modelAndView = mock(ModelAndView.class);

        // WHEN
        interceptorController.postHandle(request, response, handler, modelAndView);

        // THEN
        assertThat(modelAndView).isNotNull();
    }

    @Test
    void givenAfterCompletion_whenCalled_thenNoException() {
        // GIVEN
        Exception ex = new Exception("test");

        // WHEN
        interceptorController.afterCompletion(request, response, handler, ex);

        // THEN
        assertThat(ex).isInstanceOf(Exception.class);
    }
}