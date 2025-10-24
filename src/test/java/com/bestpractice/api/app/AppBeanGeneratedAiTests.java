package com.bestpractice.api.app;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = new RequestInfoComponent();
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        // Use reflection to inject private fields
        try {
            var authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
            authField.setAccessible(true);
            authField.set(webMvcConfig, authComponent);

            var requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
            requestInfoField.setAccessible(true);
            requestInfoField.set(webMvcConfig, requestInfoComponent);
        } catch (Exception e) {
            fail("Failed to set up test: " + e.getMessage());
        }
    }

    @Test
    void givenAuthAndRequestInfo_whenInterceptorController_thenNotNull() {
        // GIVEN
        AuthComponent givenAuth = authComponent;
        RequestInfoComponent givenRequestInfo = requestInfoComponent;

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(controller);
        // Use reflection to verify private fields
        try {
            var authField = InterceptorController.class.getDeclaredField("authComponent");
            authField.setAccessible(true);
            assertEquals(givenAuth, authField.get(controller));

            var requestInfoField = InterceptorController.class.getDeclaredField("requestInfo");
            requestInfoField.setAccessible(true);
            assertEquals(givenRequestInfo, requestInfoField.get(controller));
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    void givenInterceptorController_whenAddInterceptors_thenRegistryContainsInterceptor() {
        // GIVEN
        InterceptorRegistry registry = new InterceptorRegistry();

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        assertNotNull(registry);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPlugin_thenDocketCreated() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertTrue(docket.isEnabled());
    }
}