package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Test
public void preHandle_shouldSetRequestIdOnAllRequests() throws IOException {
    // GIVEN
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    when(request.getRequestURI()).thenReturn("/api/v1/profile");
    when(request.getMethod()).thenReturn("GET");
    Enumeration<String> headers = Mockito.mock(Enumeration.class);
    when(headers.hasMoreElements()).thenReturn(true);
    when(headers.nextElement()).thenReturn("Bearer abc123");
    when(request.getHeaders(anyString())).thenReturn(headers);

    DecodedJWT mockDecodedJWT = Mockito.mock(DecodedJWT.class);
    when(mockDecodedJWT.getSubject()).thenReturn("user123");
    when(mockDecodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
    when(mockDecodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

    when(authComponent.decodeJwt(anyString())).thenReturn(mockDecodedT);

    // WHEN
    boolean result = interceptorController.preHandle(request, response, any(Object.class));

    // THEN
    assertThat(result).isTrue();
    Mockito.verify(requestInfo).setPath("/api/v1/profile");
    Mockito.verify(requestInfo).setHttpMethod("GET");
    Mockito.verify(requestInfo).setUserId("user123");
    Mockito.verify(requestInfo).setUserEmail("user@example.com");
    Mockito.verify(requestInfo).setRefreshToken(true);
    Mockito.verify(requestInfo).setRequestId(anyString());
}
