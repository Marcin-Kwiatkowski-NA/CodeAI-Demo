package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
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
    void givenInterceptorController_whenBeanCreated_thenNotNull() {
        // GIVEN
        // Mock dependencies are already injected

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertThat(interceptorController).isNotNull();
    }

    @Test
    void givenInterceptorRegistry_whenAddInterceptors_thenInterceptorAdded() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        verify(registry).addInterceptor(any());
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
}