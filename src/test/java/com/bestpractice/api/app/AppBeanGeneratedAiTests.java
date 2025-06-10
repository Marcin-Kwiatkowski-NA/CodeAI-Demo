package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AppBeanGeneratedAiTests {

    @Mock
    private com.bestpractice.api.domain.component.RequestInfoComponent requestInfo;

    @Mock
    private com.bestpractice.api.domain.component.AuthComponent authComponent;

    @InjectMocks
    private InterceptorController interceptorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInterceptorControllerBeanCreation() {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(interceptorController);
    }

    @Test
    public void testAddInterceptors() {
        // GIVEN
        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        AppBean.SwaggerConfig.WebMvcConfig webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        webMvcConfig.requestInfo = requestInfo;
        webMvcConfig.authComponent = authComponent;

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        verify(registry).addInterceptor(any(InterceptorController.class)).addPathPatterns("/api/**");
    }

    @Test
    public void testSwaggerSpringMvcPlugin() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
    }

    @Test
    public void testApiInfo() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        ApiInfo apiInfo = swaggerConfig.apiInfo();

        // THEN
        assertEquals("Spring boot best practice API", apiInfo.getTitle());
        assertEquals("Spring boot best practice API document", apiInfo.getDescription());
    }
}