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
    private RequestInfoComponent requestInfoComponent;
    private InterceptorController interceptorController;

    @BeforeEach
    public void setUp() {
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = mock(RequestInfoComponent.class);
        interceptorController = new InterceptorController(authComponent, requestInfoComponent);
    }

    @Test
    public void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertFalse(result);
    }

    @Test
    public void givenDisabledAuthEndpoint_whenPreHandle_thenReturnsTrueWithoutAuth() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertTrue(result);
        verify(requestInfoComponent).setPath("/api/v1/user");
    }

    @Test
    public void givenNoAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        // WHEN / THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenInvalidBearerToken_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        // WHEN / THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenValidBearerToken_whenPreHandle_thenSetsRequestInfoAndReturnsTrue() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer sometoken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        Claim emailClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("user@example.com");
        Claim refreshClaim = mock(Claim.class);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("sometoken")).thenReturn(decodedJWT);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertTrue(result);
        verify(requestInfoComponent).setUserId("user123");
        verify(requestInfoComponent).setUserEmail("user@example.com");
        verify(requestInfoComponent).setRefreshToken(true);
    }
}
