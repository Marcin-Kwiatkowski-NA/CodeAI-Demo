package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DecodedJWT decodedJWT;

    @BeforeEach
    public void setUp() {
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = mock(RequestInfoComponent.class);
        when(requestInfoComponent.getRequestId()).thenReturn("req-123");
        when(requestInfoComponent.getPath()).thenReturn("/secure/path");
        when(requestInfoComponent.getHttpMethod()).thenReturn("GET");
        when(requestInfoComponent.getUserId()).thenReturn("user123");
        when(requestInfoComponent.getUserEmail()).thenReturn("user@example.com");
        when(requestInfoComponent.isRefreshToken()).thenReturn(true);
        interceptorController = new InterceptorController(authComponent, requestInfoComponent);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        decodedJWT = mock(DecodedJWT.class);
    }

    @Test
    public void givenErrorPath_whenPreHandle_thenReturnsFalse() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertFalse(result);
    }

    @Test
    public void givenDisableAuthEndpoint_whenPreHandle_thenReturnsTrueWithoutAuth() throws IOException {
        // GIVEN
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
        when(request.getRequestURI()).thenReturn("/secure/path");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        mockStaticSpringProfile("prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenInvalidBearerToken_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/secure/path");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStaticSpringProfile("prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenValidBearerToken_whenPreHandle_thenSetsRequestInfoAndReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/secure/path");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validtoken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStaticSpringProfile("prod");

        when(authComponent.decodeJwt("validtoken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("user123");
        Claim emailClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Claim refreshClaim = mock(Claim.class);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertTrue(result);
        verify(requestInfoComponent).setUserId("user123");
        verify(requestInfoComponent).setUserEmail("user@example.com");
        verify(requestInfoComponent).setRefreshToken(true);
    }
