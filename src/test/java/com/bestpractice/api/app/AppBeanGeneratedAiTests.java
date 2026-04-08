package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.ApiInfo;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        void givenSwaggerConfig_whenSwaggerSpringMvcPlugin_thenDocketIsCreated() {
            // GIVEN
            // Swagger configuration initialized

            // WHEN
            Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

            // THEN
            assertThat(docket).isNotNull();
            assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
        }

        @Test
        void givenSwaggerConfig_whenApiInfo_thenApiInfoIsReturned() throws Exception {
            // GIVEN
            var method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
            method.setAccessible(true);

            // WHEN
            ApiInfo apiInfo = (ApiInfo) method.invoke(swaggerConfig);

            // THEN
            assertThat(apiInfo).isNotNull();
            assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
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
            void givenWebMvcConfig_whenInterceptorControllerBeanCreated_thenNotNull() {
                // GIVEN
                // WebMvcConfig initialized with mocks

                // WHEN
                InterceptorController interceptorController = webMvcConfig.interceptorController();

                // THEN
                assertThat(interceptorController).isNotNull();
            }

            @Test
            void givenWebMvcConfig_whenAddInterceptors_thenRegistryCalled() {
                // GIVEN
                InterceptorRegistry registry = mock(InterceptorRegistry.class);
                when(registry.addInterceptor(any())).thenReturn(registry);

                // WHEN
                webMvcConfig.addInterceptors(registry);

                // THEN
                verify(registry, times(1)).addInterceptor(any());
            }
        }
    }
}