package com.bestpractice.api.app;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
        void givenSwaggerConfig_whenApiInfo_thenApiInfoIsCreated() throws Exception {
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

            @InjectMocks
            private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;

            @BeforeEach
            void setUp() {
                MockitoAnnotations.openMocks(this);
            }

            @Test
            void givenWebMvcConfig_whenInterceptorController_thenBeanIsCreated() {
                // GIVEN
                // WebMvcConfig initialized with mocks

                // WHEN
                InterceptorController interceptorController = webMvcConfig.interceptorController();

                // THEN
                assertThat(interceptorController).isNotNull();
            }

            @Test
            void givenWebMvcConfig_whenAddInterceptors_thenInterceptorIsAdded() {
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
