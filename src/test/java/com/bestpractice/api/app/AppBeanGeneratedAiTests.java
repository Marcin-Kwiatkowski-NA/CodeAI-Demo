package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
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

@ExtendWith(MockitoExtension.class)
public class AppBeanGeneratedAiTests {

    @ExtendWith(MockitoExtension.class)
    public static class SwaggerConfigGeneratedAiTests {

        @InjectMocks
        private AppBean.SwaggerConfig swaggerConfig;

        @Mock
        private InterceptorRegistry interceptorRegistry;

        private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

        @Mock
        private RequestInfoComponent requestInfoComponent;

        @Mock
        private AuthComponent authComponent;

        @BeforeEach
        void setUp() throws Exception {
            webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
            java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
            authField.setAccessible(true);
            authField.set(webMvcConfig, authComponent);

            java.lang.reflect.Field requestField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
            requestField.setAccessible(true);
            requestField.set(webMvcConfig, requestInfoComponent);
        }

        @Test
        void givenValidDependencies_whenInterceptorControllerCreated_thenNotNull() {
            // GIVEN
            // Dependencies are injected in setup

            // WHEN
            InterceptorController controller = webMvcConfig.interceptorController();

            // THEN
            assertThat(controller).isNotNull();
        }

        @Test
        void givenInterceptorController_whenAddInterceptors_thenRegistryCalled() {
            // GIVEN
            InterceptorController controller = mock(InterceptorController.class);
            AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
            doReturn(controller).when(configSpy).interceptorController();

            // WHEN
            configSpy.addInterceptors(interceptorRegistry);

            // THEN
            verify(interceptorRegistry, times(1)).addInterceptor(controller);
        }

        @Test
        void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenDocketReturned() {
            // GIVEN
            // SwaggerConfig instance already created

            // WHEN
            Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

            // THEN
            assertThat(docket).isNotNull();
            assertThat(docket.getDocumentationType().getName()).isEqualTo("swagger");
        }
    }
}