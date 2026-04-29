package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.clearInvocations;
import org.mockito.Mockito;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
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
import java.util.Collections;
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
    private DecodedJWT decodedJWT;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        clearInvocations(authComponent, requestInfo, request, response, decodedJWT);
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
    void givenLocalProfile_whenPreHandle_thenSkipsAuthAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/test");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/test");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void givenNoAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> emptyEnum = Collections.emptyEnumeration();
        when(request.getHeaders("Authorization")).thenReturn(emptyEnum);

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void givenInvalidBearerFormat_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenSetsUserInfoAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("user123");
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setUserId("user123");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void givenDisableAuthEndpoint_whenPreHandle_thenSkipsAuthAndReturnsTrue() throws IOException {
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
    void givenPostHandle_whenCalled_thenNoException() {
        // GIVEN
        ModelAndView modelAndView = new ModelAndView();

        // WHEN / THEN
        interceptorController.postHandle(request, response, new Object(), modelAndView);
        assertThat(modelAndView).isNotNull();
    }

    @Test
    void givenAfterCompletion_whenCalled_thenNoException() {
        // GIVEN
        Exception ex = new Exception("test");

        // WHEN / THEN
        interceptorController.afterCompletion(request, response, new Object(), ex);
        assertThat(ex).isNotNull();
    }
}