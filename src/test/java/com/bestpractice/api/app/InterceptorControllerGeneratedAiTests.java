package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
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

    @Mock
    private DecodedJWT decodedJWT;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        Mockito.reset(authComponent, requestInfo, request, response, decodedJWT);
    }

    @Test
    void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        boolean result = interceptorController.preHandle(request, response, new Object());

        assertThat(result).isFalse();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("error");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void givenNoAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("prod"));

            assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                    .isInstanceOf(UnAuthorized.class)
                    .hasMessageContaining("Authorization header is empty");
        }
    }

    @Test
    void givenInvalidBearerFormat_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("prod"));

            assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                    .isInstanceOf(UnAuthorized.class)
                    .hasMessageContaining("Authorization supports Bearer format");
        }
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenReturnsTrue() throws IOException {
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("POST");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(decodedJWT.getSubject()).thenReturn("user123");

        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("prod"));

            boolean result = interceptorController.preHandle(request, response, new Object());

            assertThat(result).isTrue();
            verify(requestInfo).setUserId("user123");
            verify(requestInfo).setUserEmail("user@example.com");
            verify(requestInfo).setRefreshToken(false);
        }
    }

    @Test
    void givenLocalProfile_whenPreHandle_thenSkipsAuthAndReturnsTrue() throws IOException {
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("local"));

            boolean result = interceptorController.preHandle(request, response, new Object());

            assertThat(result).isTrue();
            verify(requestInfo).setRequestId(anyString());
            verify(requestInfo).setPath("/api/v1/secure");
            verify(requestInfo).setHttpMethod("GET");
        }
    }

    @Test
    void givenDisableAuthEndpoint_whenPreHandle_thenSkipsAuthAndReturnsTrue() throws IOException {
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("GET");
        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(Util::getSpringProfileActive).thenReturn(List.of("prod"));

            boolean result = interceptorController.preHandle(request, response, new Object());

            assertThat(result).isTrue();
            verify(requestInfo).setRequestId(anyString());
            verify(requestInfo).setPath("/api/v1/user");
            verify(requestInfo).setHttpMethod("GET");
        }
    }

    @Test
    void givenPostHandle_whenCalled_thenNoException() {
        interceptorController.postHandle(request, response, new Object(), null);
        assertThat(true).isTrue();
    }

    @Test
    void givenAfterCompletion_whenCalled_thenNoException() {
        interceptorController.afterCompletion(request, response, new Object(), null);
        assertThat(true).isTrue();
    }
}