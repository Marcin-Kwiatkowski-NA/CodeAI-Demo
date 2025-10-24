package com.bestpractice.api.app;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InterceptorControllerGeneratedAiTests {

    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;
    private InterceptorController interceptorController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        authComponent = mock(AuthComponent.class);
        requestInfo = new RequestInfoComponent();
        interceptorController = new InterceptorController(authComponent, requestInfo);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        handler = new Object();
        System.clearProperty("SPRING_PROFILES_ACTIVE");
    }

    @Test
    void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertFalse(result);
    }

    @Test
    void givenDisabledAuthEndpoint_whenPreHandle_thenReturnsTrueWithoutAuthCheck() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertTrue(result);
        assertEquals("/api/v1/user", requestInfo.getPath());
    }

    @Test
    void givenLocalProfile_whenPreHandle_thenReturnsTrueWithoutAuthCheck() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        System.setProperty("SPRING_PROFILES_ACTIVE", "local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertTrue(result);
    }

    @Test
    void givenMissingAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, handler));
    }

    @Test
    void givenInvalidBearerTokenFormat_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, handler));
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenSetsRequestInfoAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validtoken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt("validtoken")).thenReturn(decodedJWT);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, handler);

        // THEN
        assertTrue(result);
        assertEquals("user123", requestInfo.getUserId());
        assertEquals("user@example.com", requestInfo.getUserEmail());
        assertFalse(requestInfo.getRefreshToken());
    }

    @Test
    void givenErrorPath_whenPostHandle_thenNoException() {
        // GIVEN
        // WHEN
        interceptorController.postHandle(request, response, handler, null);
        // THEN
        assertNull(requestInfo.getUserId());
    }

    @Test
    void givenErrorPath_whenAfterCompletion_thenNoException() {
        // GIVEN
        Exception ex = null;

        // WHEN
        interceptorController.afterCompletion(request, response, handler, ex);

        // THEN
        assertNull(ex);
    }
}