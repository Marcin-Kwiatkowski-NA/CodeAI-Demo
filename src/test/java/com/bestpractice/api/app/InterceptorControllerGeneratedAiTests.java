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
        requestInfoComponent = new RequestInfoComponent();
        interceptorController = new InterceptorController(authComponent, requestInfoComponent);
    }

    @Test
    public void givenNoAuthorizationHeader_whenPreHandle_thenThrowUnAuthorized() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());
        mockStaticUtilProfile("prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenInvalidBearerFormat_whenPreHandle_thenThrowUnAuthorized() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStaticUtilProfile("prod");

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(request, response, new Object()));
    }

    @Test
    public void givenValidBearerToken_whenPreHandle_thenReturnTrue() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validtoken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        mockStaticUtilProfile("prod");

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
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertTrue(result);
        assertEquals("user123", requestInfoComponent.getUserId());
        assertEquals("user@example.com", requestInfoComponent.getUserEmail());
    }

    @Test
    public void givenErrorPath_whenPreHandle_thenReturnFalse() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("error");
        when(request.getMethod()).thenReturn("GET");
        mockStaticUtilProfile("prod");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertFalse(result);
    }