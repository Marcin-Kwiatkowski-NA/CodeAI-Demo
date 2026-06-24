package com.bestpractice.api.app;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.ApiInfo;

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @InjectMocks
    private AppBean.SwaggerConfig swaggerConfig;

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPlugin_thenDocketCreated() {
        // GIVEN
        // Swagger configuration initialized

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void givenSwaggerConfig_whenApiInfo_thenApiInfoCreated() throws Exception {
        // GIVEN
        // Swagger configuration initialized

        // WHEN
        java.lang.reflect.Method method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
        method.setAccessible(true);
        ApiInfo apiInfo = (ApiInfo) method.invoke(swaggerConfig);

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
    }

    @Test
    void givenWebMvcConfig_whenInterceptorControllerBeanCreated_thenNotNull() {
        // GIVEN
        // WebMvcConfig initialized with mocks

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertThat(interceptorController).isNotNull();
    }

    @Test
    void givenWebMvcConfig_whenAddInterceptors_thenInterceptorAdded() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorController interceptorController = mock(InterceptorController.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = Mockito.spy(webMvcConfig);
        Mockito.doReturn(interceptorController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, times(1)).addInterceptor(interceptorController);
    }
}