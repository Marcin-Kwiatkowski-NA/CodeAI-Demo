package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Arrays;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    private InterceptorController interceptorController;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interceptorController = new InterceptorController(authComponent, requestInfoComponent);
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    @Test
    void testInterceptorControllerPreHandle() throws IOException {
        // GIVEN
        String path = "/api/v1/test";
        String method = "GET";
        String authorizationHeader = "Bearer testToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(httpServletRequest.getRequestURI()).thenReturn(path);
        when(httpServletRequest.getMethod()).thenReturn(method);
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(Collections.enumeration(Arrays.asList(authorizationHeader)));
        when(authComponent.decodeJwt("testToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("userEmail");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfoComponent).setRequestId(anyString());
        verify(requestInfoComponent).setPath(path);
        verify(requestInfoComponent).setHttpMethod(method);
        verify(requestInfoComponent).setUserId("userId");
        verify(requestInfoComponent).setUserEmail("userEmail");
        verify(requestInfoComponent).setRefreshToken(false);
    }

    @Test
    void testInterceptorControllerPreHandleUnauthorized() {
        // GIVEN
        String path = "/api/v1/test";
        String method = "GET";

        when(httpServletRequest.getRequestURI()).thenReturn(path);
        when(httpServletRequest.getMethod()).thenReturn(method);
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(Collections.enumeration(Arrays.asList()));

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization header is empty");
    }

    @Test
    void testWebMvcConfigAddInterceptors() {
        // GIVEN
        InterceptorController interceptorController = mock(InterceptorController.class);

        // WHEN
        new AppBean.SwaggerConfig.WebMvcConfig().addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry).addInterceptor(interceptorController);
    }

    @Test
    void testSwaggerSpringMvcPlugin() {
        // GIVEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // WHEN
        DocumentationType documentationType = docket.getDocumentationType();

        // THEN
        assertThat(documentationType).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void testApiInfo() {
        // GIVEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // WHEN
        DocumentationType documentationType = docket.getDocumentationType();

        // THEN
        assertThat(documentationType).isEqualTo(DocumentationType.SWAGGER_2);
    }
}
