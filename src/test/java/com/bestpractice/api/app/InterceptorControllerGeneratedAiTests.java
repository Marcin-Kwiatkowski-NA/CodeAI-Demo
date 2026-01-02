package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.util.Util;
import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;

@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest(Util.class)
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

    @Mock
    private Enumeration<String> headerEnumeration;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Reset mocks before each test
        Mockito.reset(authComponent, requestInfo, request, response, decodedJWT, headerEnumeration);
        // Default behavior for requestInfo setters to do nothing
        Mockito.doNothing().when(requestInfo).setRequestId(Mockito.anyString());
        Mockito.doNothing().when(requestInfo).setPath(Mockito.anyString());
        Mockito.doNothing().when(requestInfo).setHttpMethod(Mockito.anyString());
        Mockito.doNothing().when(requestInfo).setUserId(Mockito.anyString());
        Mockito.doNothing().when(requestInfo).setUserEmail(Mockito.anyString());
        Mockito.doNothing().when(requestInfo).setRefreshToken(Mockito.anyBoolean());
    }

    @Test
    void preHandle_returnsFalse_whenPathIsError() throws Exception {
        // GIVEN
        Mockito.when(request.getRequestURI()).thenReturn("/error");
        Mockito.when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        Assertions.assertThat(result).isFalse();
        Mockito.verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_skipsAuth_whenPathIsDisableAuthEndpoint() throws Exception {
        // GIVEN
        Mockito.when(request.getRequestURI()).thenReturn("/api/v1/user");
        Mockito.when(request.getMethod()).thenReturn("POST");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        Assertions.assertThat(result).isTrue();
        Mockito.verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_throwsUnAuthorized_whenNoAuthorizationHeader() throws Exception {
        // GIVEN
        Mockito.when(request.getRequestURI()).thenReturn("/api/v1/resource");
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getHeaders("Authorization")).thenReturn(headerEnumeration);
        Mockito.when(headerEnumeration.hasMoreElements()).thenReturn(false);

        // WHEN
        Assertions.assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");

        // THEN
        Mockito.verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_throwsUnAuthorized_whenAuthorizationHeaderNotBearer() throws Exception {
        // GIVEN
        Mockito.when(request.getRequestURI()).thenReturn("/api/v1/resource");
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getHeaders("Authorization")).thenReturn(headerEnumeration);
        Mockito.when(headerEnumeration.hasMoreElements()).thenReturn(true);
        Mockito.when(headerEnumeration.nextElement()).thenReturn("Basic abcdef");

        // WHEN
        Assertions.assertThatThrownBy(() -> interceptorController.preHandle(request, response, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");

        // THEN
        Mockito.verifyNoInteractions(authComponent);
    }

    @Test
    void preHandle_setsUserInfo_andReturnsTrue_whenValidBearerToken() throws Exception {
        // GIVEN
        String token = "Bearer valid.jwt.token";
        Mockito.when(request.getRequestURI()).thenReturn("/api/v1/resource");
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getHeaders("Authorization")).thenReturn(headerEnumeration);
        Mockito.when(headerEnumeration.hasMoreElements()).thenReturn(true);
        Mockito.when(headerEnumeration.nextElement()).thenReturn(token);

        Mockito.when(authComponent.decodeJwt("valid.jwt.token")).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getSubject()).thenReturn("user123");
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(
                Mockito.mock(com.auth0.jwt.interfaces.Claim.class));
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(
                Mockito.mock(com.auth0.jwt.interfaces.Claim.class));
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        Assertions.assertThat(result).isTrue();

        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> refreshCaptor = ArgumentCaptor.forClass(Boolean.class);

        Mockito.verify(requestInfo).setUserId(idCaptor.capture());
        Mockito.verify(requestInfo).setUserEmail(emailCaptor.capture());
        Mockito.verify(requestInfo).setRefreshToken(refreshCaptor.capture());

        Assertions.assertThat(idCaptor.getValue()).isEqualTo("user123");
        Assertions.assertThat(emailCaptor.getValue()).isEqualTo("user@example.com");
        Assertions.assertThat(refreshCaptor.getValue()).isTrue();
    }

    @Test
    void preHandle_skipsAuth_whenProfileIsLocal() throws Exception {
        // GIVEN
        PowerMockito.mockStatic(Util.class);
        Mockito.when(Util.getSpringProfileActive()).thenReturn("local");

        Mockito.when(request.getRequestURI()).thenReturn("/api/v1/resource");
        Mockito.when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        Assertions.assertThat(result).isTrue();
        Mockito.verifyNoInteractions(authComponent);
    }
}
