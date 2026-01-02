package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class InterceptorControllerGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    private RequestInfoComponent requestInfo;

    private InterceptorController interceptor;

    @BeforeEach
    void setUp() {
        requestInfo = new RequestInfoComponent();
        interceptor = new InterceptorController(authComponent, requestInfo);
    }

    @Test
    void preHandle_whenPathStartsWithError_returnsFalseAndSetsRequestInfo() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/error/404");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptor.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isFalse();
        assertThat(requestInfo.getRequestId()).isNotNull();
        assertThat(requestInfo.getPath()).isEqualTo("/error/404");
        assertThat(requestInfo.getHttpMethod()).isEqualTo("GET");
    }

    @Test
    void preHandle_whenPathIsDisabledAuthEndpoint_returnsTrueWithoutAuthorization() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("POST");

        // WHEN
        boolean result = interceptor.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        assertThat(requestInfo.getRequestId()).isNotNull();
        assertThat(requestInfo.getPath()).isEqualTo("/api/v1/user");
        assertThat(requestInfo.getHttpMethod()).isEqualTo("POST");
        assertThat(requestInfo.getUserId()).isNull();
    }

    @Test
    void preHandle_whenAuthorizationHeaderMissing_throwsUnAuthorized() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(Collections.emptyEnumeration());

        // WHEN / THEN
        assertThatThrownBy(() -> interceptor.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void preHandle_whenAuthorizationHeaderNotBearer_throwsUnAuthorized() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(List.of("Basic abcdef"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        // WHEN / THEN
        assertThatThrownBy(() -> interceptor.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void preHandle_whenValidBearerToken_decodesJwtAndSetsUserInfo() throws IOException {
        // GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(List.of("Bearer abcdefg"));
        when(request.getHeaders("Authorization")).thenReturn(headers);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        Claim emailClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("user@example.com");
        Claim refreshClaim = mock(Claim.class);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt(anyString())).thenReturn(decodedJWT);

        // WHEN
        boolean result = interceptor.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        assertThat(requestInfo.getUserId()).isEqualTo("user123");
        assertThat(requestInfo.getUserEmail()).isEqualTo("user@example.com");
        assertThat(requestInfo.isRefreshToken()).isTrue();
        assertThat(requestInfo.getRequestId()).isNotNull();
        assertThat(requestInfo.getPath()).isEqualTo("/api/v1/secure");
    }
}
