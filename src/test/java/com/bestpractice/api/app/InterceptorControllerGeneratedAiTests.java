package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        // No reset needed
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
    void givenDisabledAuthEndpoint_whenPreHandle_thenReturnsTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/user");
        when(request.getMethod()).thenReturn("POST");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setPath("/api/v1/user");
    }

    @Test
    void givenMissingAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeaders("Authorization")).thenReturn(new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() { return false; }
            @Override
            public String nextElement() { return null; }
        });

        MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class);
        utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void givenInvalidBearerFormat_whenPreHandle_thenThrowsUnAuthorized() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = new Enumeration<String>() {
            private boolean hasMore = true;
            @Override
            public boolean hasMoreElements() { return hasMore; }
            @Override
            public String nextElement() { hasMore = false; return "InvalidToken"; }
        };
        when(request.getHeaders("Authorization")).thenReturn(headers);

        MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class);
        utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenReturnsTrueAndSetsRequestInfo() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = new Enumeration<String>() {
            private boolean hasMore = true;
            @Override
            public boolean hasMoreElements() { return hasMore; }
            @Override
            public String nextElement() { hasMore = false; return "Bearer validToken"; }
        };
        when(request.getHeaders("Authorization")).thenReturn(headers);

        MockedStatic<Util> utilMock = Mockito.mockStatic(Util.class);
        utilMock.when(Util::getSpringProfileActive).thenReturn("prod");

        var decodedJWT = mock(com.auth0.jwt.interfaces.DecodedJWT.class);
        var emailClaim = mock(com.auth0.jwt.interfaces.Claim.class);
        var refreshClaim = mock(com.auth0.jwt.interfaces.Claim.class);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setUserId("user123");
        verify(requestInfo).setUserEmail("user@example.com");
        verify(requestInfo).setRefreshToken(false);
    }

    @Test
    void givenAnyRequest_whenPostHandle_thenDoesNothing() {
        // GIVEN
        ModelAndView modelAndView = new ModelAndView();

        // WHEN
        interceptorController.postHandle(request, response, new Object(), modelAndView);

        // THEN
        assertThat(modelAndView).isNotNull();
    }

    @Test
    void givenAnyRequest_whenAfterCompletion_thenDoesNothing() {
        // GIVEN
        Exception ex = new Exception("test");

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), ex);

        // THEN
        assertThat(ex).isNotNull();
    }
}
