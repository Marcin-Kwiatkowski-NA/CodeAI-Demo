package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.app.InterceptorController;
import com.bestpractice.api.common.Util;
import com.bestpractice.api.app.InterceptorControllerGeneratedAiTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@ExtendWith(MockitoExtension.class)
class InterceptorControllerGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private InterceptorController controller;

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
        reset(authComponent, requestInfoComponent, request, response);
    }

    @Test
    void preHandle_whenErrorPath_returnsFalse() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/error/something");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = controller.preHandle(request, response, null);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void preHandle_whenDisabledAuthEndpoint_returnsTrueWithoutAuth() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("POST");

        // Mock static Util.getSpringProfileActive() to return a profile that is not local
        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("dev"));

            // WHEN
            boolean result = controller.preHandle(request, response, null);

            // THEN
            assertThat(result).isTrue();
            verify(authComponent, never()).decodeJwt(anyString());
        }
    }

    @Test
    void preHandle_whenMissingAuthorizationHeader_throwsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> emptyEnum = mock(Enumeration.class);
        when(emptyEnum.hasMoreElements()).thenReturn(false);
        when(request.getHeaders("Authorization")).thenReturn(emptyEnum);

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("dev"));

            // WHEN
            Throwable thrown = assertThatCode(() -> controller.preHandle(request, response, null))
                    .isInstanceOf(UnAuthorized.class)
                    .extracting("message")
                    .isEqualTo("Authorization header missing");

            // THEN
            assertThat(thrown).isInstanceOf(UnAuthorized.class);
        }
    }

    @Test
    void preHandle_whenAuthorizationHeaderNotBearer_throwsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> enumHeaders = mock(Enumeration.class);
        when(enumHeaders.hasMoreElements()).thenReturn(true, false);
        when(enumHeaders.nextElement()).thenReturn("Token abcdef");
        when(request.getHeaders("Authorization")).thenReturn(enumHeaders);

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("dev"));

            // WHEN
            Throwable thrown = assertThatCode(() -> controller.preHandle(request, response, null))
                    .isInstanceOf(UnAuthorized.class)
                    .extracting("message")
                    .isEqualTo("Authorization header must contain Bearer");

            // THEN
            assertThat(thrown).isInstanceOf(UnAuthorized.class);
        }
    }

    @Test
    void preHandle_whenValidBearerToken_setsRequestInfoAndReturnsTrue() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("PUT");
        Enumeration<String> enumHeaders = mock(Enumeration.class);
        when(enumHeaders.hasMoreElements()).thenReturn(true, false);
        when(enumHeaders.nextElement()).thenReturn("Bearer validtoken");
        when(request.getHeaders("Authorization")).thenReturn(enumHeaders);

        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim("refresh")).thenReturn(refreshClaim);
        when(decodedJWT.getClaim("email")).thenReturn(emailClaim);
        when(authComponent.decodeJwt(anyString())).thenReturn(decodedJWT);

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("dev"));

            // WHEN
            boolean result = controller.preHandle(request, response, null);

            // THEN
            assertThat(result).isTrue();
            verify(requestInfoComponent).setRequestId(anyString());
            assertThat(requestInfoComponent.getRequestId()).isNotNull().isNotEmpty();
            assertThat(requestInfoComponent.getPath()).isEqualTo("/api/v1/secure");
            assertThat(requestInfoComponent.getHttpMethod()).isEqualTo("PUT");
            assertThat(requestInfoComponent.getUserId()).isEqualTo("user123");
            assertThat(requestInfoComponent.getUserEmail()).isEqualTo("user@example.com");
            assertThat(requestInfoComponent.isRefreshToken()).isTrue();
        }
    }

    @Test
    void preHandle_whenExpiredToken_throwsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> enumHeaders = mock(Enumeration.class);
        when(enumHeaders.hasMoreElements()).thenReturn(true, false);
        when(enumHeaders.nextElement()).thenReturn("Bearer expiredtoken");
        when(request.getHeaders("Authorization")).thenReturn(enumHeaders);

        when(authComponent.decodeJwt(anyString())).thenThrow(new UnAuthorized("Token expired"));

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("dev"));

            // WHEN
            Throwable thrown = assertThatCode(() -> controller.preHandle(request, response, null))
                    .isInstanceOf(UnAuthorized.class)
                    .extracting("message")
                    .isEqualTo("Token expired");

            // THEN
            assertThat(thrown).isInstanceOf(UnAuthorized.class);
        }
    }

    @Test
    void postHandle_doesNothing() {
        // GIVEN
        // No state changes expected

        // WHEN
        Throwable thrown = assertThatCode(() -> controller.postHandle(request, response, null, null))
                .doesNotThrowAnyException();

        // THEN
        assertThat(thrown).isNull();
    }

    @Test
    void afterCompletion_doesNothing() {
        // GIVEN
        // No state changes expected

        // WHEN
        Throwable thrown = assertThatCode(() -> controller.afterCompletion(request, response, null, null))
                .doesNotThrowAnyException();

        // THEN
        assertThat(thrown).isNull();
    }
}
