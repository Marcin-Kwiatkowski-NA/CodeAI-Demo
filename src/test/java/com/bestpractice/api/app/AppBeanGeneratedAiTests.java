package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit5.PowerMockExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Enumeration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest(Util.class)
class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoMock;
    @Mock
    private AuthComponent authComponentMock;
    @Mock
    private InterceptorRegistry interceptorRegistryMock;
    @Mock
    private InterceptorRegistration interceptorRegistrationMock;
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private DecodedJWT decodedJwtMock;

    @BeforeEach
    void setUp() {
        reset(requestInfoMock, authComponentMock, interceptorRegistryMock, interceptorRegistrationMock,
                requestMock, responseMock, decodedJwtMock);
        mockStatic(Util.class);
        when(Util.getSpringProfileActive()).thenReturn("prod");
    }

    @Test
    void testSwaggerSpringMvcPluginReturnsConfiguredDocket() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = docket.getApiInfo();
        assertThat(apiInfo.getTitle()).isEqualTo("Spring boot best practice API");
        assertThat(apiInfo.getDescription()).isEqualTo("Spring boot best practice API document");
        assertThat(apiInfo.getVersion()).isEqualTo("0.0.1");
        // Ensure the docket excludes the /error path by checking that the PathSelectors regex is present
        // (the actual predicate cannot be inspected directly, but we can verify that the selector was built)
        assertThat(docket.getPathSelectors()).isNotNull();
    }

    @Test
    void testInterceptorControllerBeanCreation() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        // Inject mocks manually
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
        // Verify that the controller uses the injected components by invoking a method that depends on them
        // We will mock the AuthComponent and RequestInfoComponent behavior in the next test
    }

    @Test
    void testAddInterceptorsRegistersInterceptor() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);
        when(interceptorRegistryMock.addInterceptor(any())).thenReturn(interceptorRegistrationMock);

        // WHEN
        webMvcConfig.addInterceptors(interceptorRegistryMock);

        // THEN
        ArgumentCaptor<InterceptorController> captor = ArgumentCaptor.forClass(InterceptorController.class);
        verify(interceptorRegistryMock).addInterceptor(captor.capture());
        InterceptorController captured = captor.getValue();
        assertThat(captured).isNotNull();
        verify(interceptorRegistrationMock).addPathPatterns("/api/**");
    }

    @Test
    void testInterceptorPreHandleSetsRequestInfoAndReturnsTrue() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);
        InterceptorController controller = webMvcConfig.interceptorController();

        when(requestMock.getRequestURI()).thenReturn("/api/test");
        when(requestMock.getMethod()).thenReturn("GET");
        Enumeration<String> headerEnum = mock(Enumeration.class);
        when(headerEnum.hasMoreElements()).thenReturn(true);
        when(headerEnum.nextElement()).thenReturn("Bearer testtoken");
        when(requestMock.getHeaders("Authorization")).thenReturn(headerEnum);

        when(authComponentMock.decodeJwt("testtoken")).thenReturn(decodedJwtMock);
        when(decodedJwtMock.getSubject()).thenReturn("user123");
        when(decodedJwtMock.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(
                mock(org.auth0.jwt.interfaces.Claim.class));
        when(decodedJwtMock.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("user@example.com");
        when(decodedJwtMock.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(
                mock(org.auth0.jwt.interfaces.Claim.class));
        when(decodedJwtMock.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        // WHEN
        boolean result = controller.preHandle(requestMock, responseMock, new Object());

        // THEN
        assertThat(result).isTrue();
        verify(requestInfoMock).setRequestId(anyString());
        verify(requestInfoMock).setPath("/api/test");
        verify(requestInfoMock).setHttpMethod("GET");
        verify(requestInfoMock).setUserId("user123");
        verify(requestInfoMock).setUserEmail("user@example.com");
        verify(requestInfoMock).setRefreshToken(true);
    }

    @Test
    void testInterceptorPreHandleWithoutAuthorizationThrows() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);
        InterceptorController controller = webMvcConfig.interceptorController();

        when(requestMock.getRequestURI()).thenReturn("/api/test");
        when(requestMock.getMethod()).thenReturn("GET");
        Enumeration<String> headerEnum = mock(Enumeration.class);
        when(headerEnum.hasMoreElements()).thenReturn(false);
        when(requestMock.getHeaders("Authorization")).thenReturn(headerEnum);

        // WHEN & THEN
        assertThatThrownBy(() -> controller.preHandle(requestMock, responseMock, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization header is empty");
    }

    @Test
    void testInterceptorPreHandleWithInvalidBearerThrows() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);
        InterceptorController controller = webMvcConfig.interceptorController();

        when(requestMock.getRequestURI()).thenReturn("/api/test");
        when(requestMock.getMethod()).thenReturn("GET");
        Enumeration<String> headerEnum = mock(Enumeration.class);
        when(headerEnum.hasMoreElements()).thenReturn(true);
        when(headerEnum.nextElement()).thenReturn("Basic abcdef");
        when(requestMock.getHeaders("Authorization")).thenReturn(headerEnum);

        // WHEN & THEN
        assertThatThrownBy(() -> controller.preHandle(requestMock, responseMock, new Object()))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Authorization supports Bearer format");
    }

    @Test
    void testInterceptorPreHandleWithErrorPathReturnsFalse() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig =
                new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "requestInfo", requestInfoMock);
        setPrivateField(webMvcConfig, "authComponent", authComponentMock);
        InterceptorController controller = webMvcConfig.interceptorController();

        when(requestMock.getRequestURI()).thenReturn("/error");
        when(requestMock.getMethod()).thenReturn("GET");

        // WHEN
        boolean result = controller.preHandle(requestMock, responseMock, new Object());

        // THEN
        assertThat(result).isFalse();
        verify(requestInfoMock, never()).setUserId(anyString());
    }

    // Utility method to set private fields via reflection
    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
