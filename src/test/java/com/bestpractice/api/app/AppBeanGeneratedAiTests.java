package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

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
    void testInterceptorControllerBeanCreation() {
        // GIVEN
        // Mock dependencies are already injected

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertThat(interceptorController).isNotNull();
        assertThat(interceptorController).isInstanceOf(InterceptorController.class);
    }

    @Test
    void testAddInterceptors() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorController interceptorController = mock(InterceptorController.class);
        doReturn(interceptorController).when(webMvcConfig).interceptorController();

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(interceptorController);
    }

    @Test
    void testSwaggerSpringMvcPluginCreation() {
        // GIVEN
        // No specific setup required

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(springfox.documentation.spi.DocumentationType.SWAGGER_2);
    }
}
