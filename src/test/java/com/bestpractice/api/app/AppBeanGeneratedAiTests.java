package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    @Test
    void givenInterceptorController_whenBeanCreated_thenDependenciesInjected() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        assertThat(webMvcConfig.interceptorController()).isNotNull();
        verify(registry, times(1)).addInterceptor(any());
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCreated_thenDocketConfigured() {
        // GIVEN
        String expectedTitle = "Spring boot best practice API";

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();
        ApiInfo apiInfo = docket.getApiInfo().orElse(null);

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(springfox.documentation.spi.DocumentationType.SWAGGER_2);
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).isEqualTo(expectedTitle);
    }
}
