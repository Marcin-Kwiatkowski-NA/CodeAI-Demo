package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @InjectMocks
    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAuthComponentAndRequestInfoComponent_whenInterceptorControllerCreated_thenNotNull() {
        // GIVEN
        // Mock dependencies are already injected

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertThat(interceptorController).isNotNull();
    }

    @Test
    void givenInterceptorController_whenAddInterceptorsCalled_thenInterceptorAdded() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorController interceptorController = mock(InterceptorController.class);
        doReturn(interceptorController).when(webMvcConfig).interceptorController();

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        verify(registry, times(1)).addInterceptor(interceptorController);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCreated_thenNotNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
    }

    @Test
    void givenSwaggerConfig_whenApiInfoCreated_thenNotNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        ApiInfo apiInfo = swaggerConfig.swaggerSpringMvcPlugin().getDocumentationContext().getApiInfo();

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).isEqualTo("Spring boot best practice API");
        assertThat(apiInfo.getDescription()).isEqualTo("Spring boot best practice API document");
        assertThat(apiInfo.getVersion()).isEqualTo("0.0.1");
    }
}
