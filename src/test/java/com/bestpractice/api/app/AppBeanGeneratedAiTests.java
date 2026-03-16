package com.bestpractice.api.app;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
import java.lang.reflect.Method;

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
    void setUp() {
        swaggerConfig = new AppBean.SwaggerConfig();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void givenAuthAndRequestInfo_whenInterceptorControllerCreated_thenNotNull() throws Exception {
        // GIVEN
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfoComponent);

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertThat(controller).isNotNull();
        assertThat(controller).isInstanceOf(InterceptorController.class);
    }

    @Test
    void givenInterceptorController_whenAddInterceptors_thenRegistryCalled() throws Exception {
        // GIVEN
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();
        setPrivateField(webMvcConfig, "authComponent", authComponent);
        setPrivateField(webMvcConfig, "requestInfo", requestInfoComponent);
        InterceptorController mockController = mock(InterceptorController.class);
        AppBean.SwaggerConfig.WebMvcConfig spyConfig = Mockito.spy(webMvcConfig);
        doReturn(mockController).when(spyConfig).interceptorController();

        // WHEN
        spyConfig.addInterceptors(interceptorRegistry);

        // THEN
        verify(interceptorRegistry, times(1)).addInterceptor(mockController);
    }

    @Test
    void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenDocketCreated() {
        // GIVEN
        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertThat(docket).isNotNull();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }

    @Test
    void givenSwaggerConfig_whenApiInfoCalled_thenApiInfoReturned() throws Exception {
        // GIVEN
        Method method = AppBean.SwaggerConfig.class.getDeclaredMethod("apiInfo");
        method.setAccessible(true);

        // WHEN
        ApiInfo apiInfo = (ApiInfo) method.invoke(swaggerConfig);

        // THEN
        assertThat(apiInfo).isNotNull();
        assertThat(apiInfo.getTitle()).contains("Spring boot best practice API");
    }
}