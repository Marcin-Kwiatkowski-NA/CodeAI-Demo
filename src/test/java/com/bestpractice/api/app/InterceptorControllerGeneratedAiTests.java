package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.clearInvocations;

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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;


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

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        clearInvocations(authComponent, requestInfo, request, response);
    }

    @Test
    void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isFalse();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("error");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void givenDisableAuthEndpoint_whenPreHandle_thenReturnsTrueWithoutAuthCheck() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("POST");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setPath("/api/v1/user");
        verify(requestInfo).setHttpMethod("POST");
    }

    @Test
    void givenMissingAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());

        try (MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

            // WHEN / THEN
            assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                    .isInstanceOf(UnAuthorized.class)
                    .hasMessageContaining("Authorization header is empty");
        }
    }

    @Test
    void givenInvalidBearerTokenFormat_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        try (MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

            // WHEN / THEN
            assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                    .isInstanceOf(UnAuthorized.class)
                    .hasMessageContaining("Authorization supports Bearer format");
        }
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenSetsUserInfoAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);

        try (MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

            // WHEN
            boolean result = interceptorController.preHandle(request, response, new Object());

            // THEN
            assertThat(result).isTrue();
            verify(requestInfo).setUserId("user123");
            verify(requestInfo).setUserEmail("user@example.com");
            verify(requestInfo).setRefreshToken(false);
        }
    }

    @Test
    void givenLocalProfile_whenPreHandle_thenSkipsAuthCheck() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");

        try (MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn("local");

            // WHEN
            boolean result = interceptorController.preHandle(request, response, new Object());

            // THEN
            assertThat(result).isTrue();
            verify(requestInfo).setPath("/api/v1/secure");
            verify(requestInfo).setHttpMethod("GET");
        }
    }

    @Test
    void givenPostHandle_whenCalled_thenNoException() {
        // GIVEN
        // WHEN
        interceptorController.postHandle(request, response, new Object(), null);
        // THEN
        assertThat(true).isTrue();
    }

    @Test
    void givenAfterCompletion_whenCalled_thenNoException() {
        // GIVEN
        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), null);
        // THEN
        assertThat(true).isTrue();
    }
}