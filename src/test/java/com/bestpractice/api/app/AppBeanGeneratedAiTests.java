package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

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
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    @InjectMocks
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    private AppBean.SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() throws Exception {
        swaggerConfig = new AppBean.SwaggerConfig();
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfoComponent);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void givenValidComponents_whenInterceptorControllerCreated_thenShouldReturnInstance() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig config = new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(config, "authComponent", authComponent);
        setPrivateField(config, "requestInfo", requestInfoComponent);

        // WHEN
        InterceptorController controller = config.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
        assertThat(controller).isInstanceOf(InterceptorController.class);
    }

    @Test
    void givenInterceptorController_whenAddInterceptors_thenRegistryShouldBeUsed() throws Exception {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorController controller = mock(InterceptorController.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = Mockito.spy(webMvcConfig);
        Mockito.doReturn(controller).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(controller);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenShouldReturnDocket() {
        // GIVEN
        AppBean.SwaggerConfig config = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = config.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void givenSwaggerConfig_whenApiInfoCalled_thenShouldReturnApiInfo() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig config = new AppBean.SwaggerConfig();

        // WHEN
        ApiInfo apiInfo = (ApiInfo) org.powermock.reflect.Whitebox.invokeMethod(config, "apiInfo");

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
    }
}