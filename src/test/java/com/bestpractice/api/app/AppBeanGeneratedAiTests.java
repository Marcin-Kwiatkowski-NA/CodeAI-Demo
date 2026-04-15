package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @InjectMocks
    private AppBean.SwaggerConfig swaggerConfig;

    @Mock
    private RequestInfoComponent requestInfoComponent;

    @Mock
    private AuthComponent authComponent;

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

    @BeforeEach
    void setUp() {
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPlugin_thenDocketCreated() {
        // GIVEN
        // SwaggerConfig initialized

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isNotNull();
    }

    @Test
    void givenWebMvcConfig_whenInterceptorControllerBeanCreated_thenNotNull() throws Exception {
        // GIVEN
        java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        java.lang.reflect.Field requestField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestField.setAccessible(true);
        requestField.set(webMvcConfig, requestInfoComponent);

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
    }

    @Test
    void givenWebMvcConfig_whenAddInterceptors_thenInterceptorAdded() throws Exception {
        // GIVEN
        java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        java.lang.reflect.Field requestField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestField.setAccessible(true);
        requestField.set(webMvcConfig, requestInfoComponent);

        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        verify(registry, times(1)).addInterceptor(org.mockito.ArgumentMatchers.any(InterceptorController.class));
    }
}