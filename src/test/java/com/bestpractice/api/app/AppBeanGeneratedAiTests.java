package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new AppBean.SwaggerConfig();
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void givenWebMvcConfig_whenInterceptorControllerCreated_thenShouldReturnNonNullInstance() throws Exception {
        // GIVEN
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfoComponent);

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
        assertThat(controller).isInstanceOf(InterceptorController.class);
    }

    @Test
    void givenWebMvcConfig_whenAddInterceptorsCalled_thenShouldRegisterInterceptor() throws Exception {
        // GIVEN
        webMvcConfig = Mockito.spy(new AppBean.SwaggerConfig.WebMvcConfig());
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfoComponent);
        InterceptorController mockController = Mockito.mock(InterceptorController.class);
        when(webMvcConfig.interceptorController()).thenReturn(mockController);

        // WHEN
        webMvcConfig.addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry).addInterceptor(mockController);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenShouldReturnDocketInstance() {
        // GIVEN
        // Security-sensitive: Swagger configuration exposes API documentation
        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void givenSwaggerConfig_whenApiInfoCalled_thenShouldReturnValidApiInfo() throws Exception {
        // GIVEN
        java.lang.reflect.Method method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
        method.setAccessible(true);

        // WHEN
        Object apiInfoObj = method.invoke(swaggerConfig);

        // THEN
        assertThat(apiInfoObj).isNotNull();
        assertThat(apiInfoObj.toString()).contains("Spring boot best practice API");
    }
}
