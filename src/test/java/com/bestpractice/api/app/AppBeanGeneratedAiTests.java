package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

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
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private Enumeration<String> headers;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInterceptorControllerPreHandle_AuthorizationHeaderMissing() throws IOException {
        // GIVEN
        when(httpServletRequest.getRequestURI()).thenReturn("/api/v1/test");
        when(httpServletRequest.getMethod()).thenReturn("GET");
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(false);

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization header is empty");
    }

    @Test
    void testInterceptorControllerPreHandle_InvalidBearerTokenFormat() throws IOException {
        // GIVEN
        when(httpServletRequest.getRequestURI()).thenReturn("/api/v1/test");
        when(httpServletRequest.getMethod()).thenReturn("GET");
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("InvalidToken");

        // WHEN
        Throwable thrown = catchThrowable(() -> interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object()));

        // THEN
        assertThat(thrown).isInstanceOf(UnAuthorized.class).hasMessage("Authorization supports Bearer format");
    }

    @Test
    void testInterceptorControllerPreHandle_ValidToken() throws IOException {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(httpServletRequest.getRequestURI()).thenReturn("/api/v1/test");
        when(httpServletRequest.getMethod()).thenReturn("GET");
        when(httpServletRequest.getHeaders("Authorization")).thenReturn(headers);
        when(headers.hasMoreElements()).thenReturn(true);
        when(headers.nextElement()).thenReturn("Bearer validToken");
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("userId");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("userEmail");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN
        boolean result = interceptorController.preHandle(httpServletRequest, httpServletResponse, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfoComponent).setRequestId(anyString());
        verify(requestInfoComponent).setPath("/api/v1/test");
        verify(requestInfoComponent).setHttpMethod("GET");
        verify(requestInfoComponent).setUserId("userId");
        verify(requestInfoComponent).setUserEmail("userEmail");
        verify(requestInfoComponent).setRefreshToken(false);
    }

    @Test
    void testSwaggerSpringMvcPlugin() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
void testApiInfo() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        ApiInfo apiInfo = swaggerConfig.swaggerSpringMvcPlugin().getApiInfo().orElse(null);

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).isEqualTo("Spring boot best practice API");
        assertThat(apiInfo.getDescription()).isEqualTo("Spring boot best practice API document");
        assertThat(apiInfo.getVersion()).isEqualTo("0.0.1");
        assertThat(apiInfo.getContact().getName()).isEqualTo("Spring boot best practice");
    }

    @Test
    void testAddInterceptors() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        webMvcConfig.interceptorController();

        // WHEN
        webMvcConfig.addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry).addInterceptor(any(HandlerInterceptor.class));
    }
}
