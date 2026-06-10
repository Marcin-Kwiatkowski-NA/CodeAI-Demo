package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class AppBeanGeneratedAiTests {

    @ExtendWith(SpringExtension.class)
    public static class SwaggerConfigGeneratedAiTests {

        @InjectMocks
        private AppBean.SwaggerConfig swaggerConfig;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenDocketIsCreated() {
            // GIVEN
            // SwaggerConfig initialized

            // WHEN
            Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

            // THEN
            assertThat(docket).isNotNull();
            assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
        }

        @Test
        void givenSwaggerConfig_whenApiInfoCalled_thenApiInfoIsReturned() throws Exception {
            // GIVEN
            var method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
            method.setAccessible(true);

            // WHEN
            Object apiInfo = method.invoke(swaggerConfig);

            // THEN
            assertThat(apiInfo).isNotNull();
            assertThat(apiInfo.toString()).contains("Spring boot best practice API");
        }

        @ExtendWith(SpringExtension.class)
        public static class WebMvcConfigGeneratedAiTests {

            @Mock
            private RequestInfoComponent requestInfoComponent;

            @Mock
            private AuthComponent authComponent;

            private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

            @BeforeEach
            void setUp() throws Exception {
                MockitoAnnotations.openMocks(this);
                webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

                Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
                authField.setAccessible(true);
                authField.set(webMvcConfig, authComponent);

                Field requestField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
                requestField.setAccessible(true);
                requestField.set(webMvcConfig, requestInfoComponent);
            }

            @Test
            void givenWebMvcConfig_whenInterceptorControllerCalled_thenInterceptorControllerIsCreated() {
                // GIVEN
                // WebMvcConfig initialized with mocks

                // WHEN
                InterceptorController controller = webMvcConfig.interceptorController();

                // THEN
                assertThat(controller).isNotNull();
            }

            @Test
            void givenWebMvcConfig_whenAddInterceptorsCalled_thenInterceptorIsAdded() {
                // GIVEN
                InterceptorRegistry registry = mock(InterceptorRegistry.class);
                AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
                InterceptorController controller = mock(InterceptorController.class);
                doReturn(controller).when(configSpy).interceptorController();

                // WHEN
                configSpy.addInterceptors(registry);

                // THEN
                verify(configSpy, times(1)).interceptorController();
            }
        }
    }
}