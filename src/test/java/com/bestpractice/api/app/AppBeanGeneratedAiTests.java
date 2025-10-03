package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig swaggerConfig;
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() throws Exception {
        swaggerConfig = new AppBean.SwaggerConfig();
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(webMvcConfig, requestInfoComponent);
    }

    @Test
    void GIVEN_swaggerConfig_WHEN_swaggerSpringMvcPlugin_called_THEN_returnDocket() {
        // GIVEN

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_interceptorController_called_THEN_returnInterceptorController() {
        // GIVEN

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(controller);
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_addInterceptors_called_THEN_registryHasInterceptor() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }
}

/*
2025-10-02 15:02:24.788 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-02 15:02:24.800 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generating code...
2025-10-02 15:02:24.800 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[33,21] authComponent has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[34,21] requestInfo has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[75,29] getInterceptors() has protected access in org.springframework.web.servlet.config.annotation.InterceptorRegistry
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[33,21] authComponent has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[34,21] requestInfo has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[75,29] getInterceptors() has protected access in org.springframework.web.servlet.config.annotation.InterceptorRegistry
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-02 15:02:24.800 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-02 15:02:29.431 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5496, outputTokenCount = 537, totalTokenCount = 6033 }
2025-10-02 15:02:29.432 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-02 15:02:29.432 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig swaggerConfig;
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() throws Exception {
        swaggerConfig = new AppBean.SwaggerConfig();
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(webMvcConfig, requestInfoComponent);
    }

    @Test
    void GIVEN_swaggerConfig_WHEN_swaggerSpringMvcPlugin_called_THEN_returnDocket() {
        // GIVEN

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_interceptorController_called_THEN_returnInterceptorController() {
        // GIVEN

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(controller);
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_addInterceptors_called_THEN_registryHasInterceptor() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }
}
2025-10-02 15:02:29.432 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refining code...
2025-10-02 15:02:29.433 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-02 15:02:29.433 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig swaggerConfig;
    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() throws Exception {
        swaggerConfig = new AppBean.SwaggerConfig();
        authComponent = mock(AuthComponent.class);
        requestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig();

        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(webMvcConfig, authComponent);

        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(webMvcConfig, requestInfoComponent);
    }

    @Test
    void GIVEN_swaggerConfig_WHEN_swaggerSpringMvcPlugin_called_THEN_returnDocket() {
        // GIVEN

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_interceptorController_called_THEN_returnInterceptorController() {
        // GIVEN

        // WHEN
        InterceptorController controller = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(controller);
    }

    @Test
    void GIVEN_webMvcConfig_WHEN_addInterceptors_called_THEN_registryHasInterceptor() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }
}
*/
