package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @BeforeEach
    void setUp() {
        reset(authComponent, requestInfoComponent);
    }

    @Test
    void GIVEN_SwaggerConfig_WHEN_swaggerSpringMvcPlugin_THEN_docketIsValid() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = docket.getApiInfo();
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).isEqualTo("Spring boot best practice API");
        assertThat(apiInfo.getDescription()).isEqualTo("Spring boot best practice API document");
        assertThat(apiInfo.getVersion()).isEqualTo("0.0.1");
    }

    @Test
    void GIVEN_WebMvcConfig_WHEN_interceptorControllerCalled_THEN_instanceIsCreatedWithDependencies() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig config = new AppBean.SwaggerConfig.WebMvcConfig();

        // Inject mocks via reflection
        java.lang.reflect.Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(config, requestInfoComponent);

        java.lang.reflect.Field authComponentField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authComponentField.setAccessible(true);
        authComponentField.set(config, authComponent);

        // WHEN
        InterceptorController interceptor = config.interceptorController();

        // THEN
        assertThat(interceptor).isNotNull();
        java.lang.reflect.Field authField = InterceptorController.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        assertThat(authField.get(interceptor)).isSameAs(authComponent);

        java.lang.reflect.Field requestInfoField2 = InterceptorController.class.getDeclaredField("requestInfo");
        requestInfoField2.setAccessible(true);
        assertThat(requestInfoField2.get(interceptor)).isSameAs(requestInfoComponent);
    }

    @Test
    void GIVEN_WebMvcConfig_WHEN_addInterceptorsCalled_THEN_interceptorRegisteredWithPathPattern() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig config = new AppBean.SwaggerConfig.WebMvcConfig();

        // Inject mocks via reflection
        java.lang.reflect.Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(config, requestInfoComponent);

        java.lang.reflect.Field authComponentField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authComponentField.setAccessible(true);
        authComponentField.set(config, authComponent);

        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorRegistration registration = mock(InterceptorRegistration.class);
        when(registry.addInterceptor(any())).thenReturn(registration);

        // WHEN
        config.addInterceptors(registry);

        // THEN
        ArgumentCaptor<InterceptorController> captor = ArgumentCaptor.forClass(InterceptorController.class);
        verify(registry).addInterceptor(captor.capture());
        InterceptorController capturedInterceptor = captor.getValue();
        assertThat(capturedInterceptor).isNotNull();

        verify(registration).addPathPatterns("/api/**");
    }
}
