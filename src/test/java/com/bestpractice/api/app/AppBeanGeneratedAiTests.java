package com.bestpractice.api.app;

import org.mockito.Mockito;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @Mock
    private AuthComponent authComponent;

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    @Test
    void givenWebMvcConfig_whenInterceptorControllerCreated_thenNotNull() throws Exception {
        // GIVEN
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfo);

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
        assertThat(controller).isInstanceOf(InterceptorController.class);
    }

    @Test
    void givenWebMvcConfig_whenAddInterceptors_thenRegistryCalled() throws Exception {
        // GIVEN
        webMvcConfig = spy(new AppBean.SwaggerConfig.WebMvcConfig());
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfo);
        InterceptorController controller = mock(InterceptorController.class);
        doReturn(controller).when(webMvcConfig).interceptorController();

        // WHEN
        webMvcConfig.addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry, atLeastOnce()).addInterceptor(controller);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCreated_thenDocketIsValid() {
        // GIVEN
        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(springfox.documentation.spi.DocumentationType.SWAGGER_2);
    }

    @Test
    void givenSwaggerConfig_whenApiInfoCalled_thenApiInfoIsValid() throws Exception {
        // GIVEN
        Method method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
        method.setAccessible(true);

        // WHEN
        ApiInfo apiInfo = (ApiInfo) method.invoke(swaggerConfig);

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
