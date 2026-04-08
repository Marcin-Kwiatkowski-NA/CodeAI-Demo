package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InterceptorControllerGeneratedAiTests {

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

    @Mock
    private Claim claimUserEmail;

    @Mock
    private Claim claimRefresh;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("SPRING_PROFILES_ACTIVE", "");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("SPRING_PROFILES_ACTIVE");
    }

    @BeforeEach
    void setUp() {
        if (interceptorController == null) {
            interceptorController = new InterceptorController(authComponent, requestInfo);
        }
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
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        System.setProperty("SPRING_PROFILES_ACTIVE", "local");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/secure");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void givenNoAuthorizationHeader_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> emptyEnum = Collections.emptyEnumeration();
        when(request.getHeaders("Authorization")).thenReturn(emptyEnum);
        System.clearProperty("SPRING_PROFILES_ACTIVE");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void givenInvalidBearerToken_whenPreHandle_thenThrowsUnAuthorized() {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("InvalidToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        System.clearProperty("SPRING_PROFILES_ACTIVE");

        // WHEN / THEN
        assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void givenValidBearerToken_whenPreHandle_thenReturnsTrueAndSetsUserInfo() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/secure");
        when(request.getMethod()).thenReturn("GET");
        Enumeration<String> headers = Collections.enumeration(Collections.singletonList("Bearer validToken"));
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("user123");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(claimUserEmail);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(claimRefresh);
        when(claimUserEmail.asString()).thenReturn("user@example.com");
        when(claimRefresh.asBoolean()).thenReturn(false);
        System.clearProperty("SPRING_PROFILES_ACTIVE");

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

        // WHEN
        interceptorController.postHandle(request, response, new Object(), modelAndView);

        // THEN
        assertThat(modelAndView).isNotNull();
    }

    @Test
    void givenAfterCompletion_whenCalled_thenNoException() {
        // GIVEN
        Exception ex = new Exception("test");

        // WHEN
        interceptorController.afterCompletion(request, response, new Object(), ex);

        // THEN
        assertThat(ex).isNotNull();
    }
}
