package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.MappedInterceptor;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() throws Exception {
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = new RequestInfoComponent();
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(webMvcConfig, requestInfoComponent);
    }

    @Test
    void givenAuthAndRequestInfo_whenInterceptorControllerCalled_thenReturnsNonNull() {
        // GIVEN
        // Dependencies are set in setUp

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(controller);
    }

    @Test
    void givenInterceptorController_whenAddInterceptorsCalled_thenRegistryContainsInterceptor() throws Exception {
        // GIVEN
        InterceptorRegistry registry = new InterceptorRegistry();

        // WHEN
        webMvcConfig.addInterceptors(registry);

        // THEN
        Field interceptorsField = InterceptorRegistry.class.getDeclaredField("registrations");
        interceptorsField.setAccessible(true);
        List<?> registrations = (List<?>) interceptorsField.get(registry);
        assertFalse(registrations.isEmpty());
        assertTrue(registrations.stream().anyMatch(r -> {
            try {
                Field interceptorField = r.getClass().getDeclaredField("interceptor");
                interceptorField.setAccessible(true);
                Object interceptor = interceptorField.get(r);
                return interceptor instanceof InterceptorController;
            } catch (Exception e) {
                return false;
            }
        }));
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenReturnsDocket() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals("springfox.documentation.spring.web.plugins.Docket", docket.getClass().getName());
    }
}
