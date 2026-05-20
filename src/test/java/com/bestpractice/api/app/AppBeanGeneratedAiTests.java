package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig swaggerConfig;

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        swaggerConfig = new AppBean.SwaggerConfig();
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(webMvcConfig, requestInfoComponent);
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
        var method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
        method.setAccessible(true);

        // WHEN
        ApiInfo apiInfo = (ApiInfo) method.invoke(swaggerConfig);

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
    }

    @Test
    void givenWebMvcConfig_whenInterceptorController_thenBeanCreated() {
        // GIVEN
        // WebMvcConfig initialized with mocks

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
    }

    @Test
    void givenWebMvcConfig_whenAddInterceptors_thenInterceptorAdded() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController controllerMock = mock(InterceptorController.class);
        doReturn(controllerMock).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry, times(1)).addInterceptor(controllerMock);
    }
}