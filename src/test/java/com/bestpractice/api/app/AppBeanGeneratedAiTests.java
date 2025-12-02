package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Enumeration<String> headers;

    @InjectMocks
    private InterceptorController interceptorController;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    @Test
    void testInterceptorControllerPreHandle_WhenAuthorizationHeaderIsEmpty_ShouldThrowUnAuthorized() throws IOException {
        // GIVEN
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(request, response, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization header is empty");
    }

    @Test
    void testInterceptorControllerPreHandle_WhenAuthorizationHeaderIsInvalid_ShouldThrowUnAuthorized() throws IOException {
        // GIVEN
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("InvalidToken");

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(request, response, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization supports Bearer format");
    }

    @Test
    void testInterceptorControllerPreHandle_WhenAuthorizationHeaderIsValid_ShouldSetRequestInfo() throws IOException {
        // GIVEN
        String bearerToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(bearerToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("userEmail");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("userEmail");
        verify(requestInfo).setRefreshToken(true);
    }

    @Test
    void testSwaggerSpringMvcPlugin_ShouldReturnDocketInstance() {
        // GIVEN

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void testInterceptorControllerPreHandle_WhenPathStartsWithError_ShouldReturnFalse() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("error/path");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void testInterceptorControllerPreHandle_WhenPathDoesNotStartWithError_ShouldReturnTrue() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testInterceptorControllerPreHandle_ShouldSetRequestInfoFields() throws IOException {
        // GIVEN
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        interceptorController.preHandle(request, response, new Object());

        // THEN
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
    }

    @Test
    void testInterceptorControllerPreHandle_WhenAuthorizationHeaderIsValid_ShouldLogAccess() throws IOException {
        // GIVEN
        String bearerToken = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(request.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn(bearerToken);
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("userEmail");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(request.getRequestURI()).thenReturn("/api/v1/resource");
        when(request.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = interceptorController.preHandle(request, response, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfo).setRequestId(anyString());
        verify(requestInfo).setPath("/api/v1/resource");
        verify(requestInfo).setHttpMethod("GET");
        verify(requestInfo).setUserId("userId");
        verify(requestInfo).setUserEmail("userEmail");
        verify(requestInfo).setRefreshToken(true);
    }
}
