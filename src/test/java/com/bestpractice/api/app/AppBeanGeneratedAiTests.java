package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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

    @InjectMocks
    private InterceptorController interceptorController;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @InjectMocks
    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInterceptorControllerPreHandle() throws IOException {
        // GIVEN
        String path = "/api/v1/test";
        String method = "GET";
        String authorizationHeader = "Bearer validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(httpServletRequest.getRequestURI()).thenReturn(path);
        when(httpServletRequest.getMethod()).thenReturn(method);
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(Collections.enumeration(Arrays.asList(authorizationHeader)));
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
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
    void testInterceptorControllerPreHandleUnauthorized() throws IOException {
        // GIVEN
        String path = "/api/v1/test";
        String method = "GET";

        when(httpServletRequest.getRequestURI()).thenReturn(path);
        when(httpServletRequest.getMethod()).thenReturn(method);
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(Collections.enumeration(Collections.emptyList()));

        // WHEN THEN
        assertThatThrownBy(() -> interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Authorization header is empty");
    }

    @Test
    void testWebMvcConfigAddInterceptors() {
        // GIVEN
        InterceptorController interceptorControllerMock = mock(InterceptorController.class);
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfigSpy = Mockito.spy(webMvcConfig);
        Mockito.doReturn(interceptorControllerMock).when(webMvcConfigSpy).interceptorController();

        // WHEN
        webMvcConfigSpy.addInterceptors(interceptorRegistry);

        // THEN
        Mockito.verify(interceptorRegistry).addInterceptor(interceptorControllerMock);
    }

    @Test
    void testSwaggerSpringMvcPlugin() {
        // GIVEN WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }
}
