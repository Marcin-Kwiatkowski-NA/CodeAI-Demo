package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

public class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent mockAuthComponent;
    private RequestInfoComponent mockRequestInfoComponent;

    @BeforeEach
    public void setUp() {
        mockAuthComponent = mock(AuthComponent.class);
        mockRequestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig() {
            {
                try {
                    java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
                    authField.setAccessible(true);
                    authField.set(this, mockAuthComponent);
                    java.lang.reflect.Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
                    requestInfoField.setAccessible(true);
                    requestInfoField.set(this, mockRequestInfoComponent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Test
    public void givenAuthAndRequestInfoComponents_whenInterceptorControllerCalled_thenReturnsNonNullInterceptorController() {
        // GIVEN - setup done in setUp()

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(interceptorController);
    }

    @Test
    public void givenInterceptorRegistry_whenAddInterceptorsCalled_thenInterceptorIsAdded() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockInterceptor = mock(InterceptorController.class);
        doReturn(mockInterceptor).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockInterceptor);
    }

    @Test
    public void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenReturnsDocketWithSwagger2DocumentationType() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }
}

/*
2025-10-03 10:48:34.498 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 10:48:34.513 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generating code...
2025-10-03 10:48:34.513 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class AppBean {

  @Configuration
  @EnableSwagger2
  public static  class SwaggerConfig {

    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
      @Autowired
      private RequestInfoComponent requestInfo;
      @Autowired
      private AuthComponent authComponent;

      @Bean
      public InterceptorController interceptorController() {
        return new InterceptorController(this.authComponent, this.requestInfo);
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorController()).addPathPatterns("/api/**");
      }
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {

      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .paths(Predicates.not(PathSelectors.regex("/error")))
          .build()
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfo(
          "Spring boot best practice API",
          "Spring boot best practice API document",
          "0.0.1",
          "",
          "Spring boot best practice",
          "",
          ""
      );
    }
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
* /


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 10:48:34.514 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:48:39.666 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 23285, outputTokenCount = 705, totalTokenCount = 23990 }
2025-10-03 10:48:39.666 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:48:39.666 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}
2025-10-03 10:48:39.666 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refining code...
2025-10-03 10:48:39.667 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:48:39.667 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refined generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}

2025-10-03 10:49:48.161 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 10:49:48.162 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generating code...
2025-10-03 10:49:48.162 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class AppBean {

  @Configuration
  @EnableSwagger2
  public static  class SwaggerConfig {

    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
      @Autowired
      private RequestInfoComponent requestInfo;
      @Autowired
      private AuthComponent authComponent;

      @Bean
      public InterceptorController interceptorController() {
        return new InterceptorController(this.authComponent, this.requestInfo);
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorController()).addPathPatterns("/api/**");
      }
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {

      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .paths(Predicates.not(PathSelectors.regex("/error")))
          .build()
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfo(
          "Spring boot best practice API",
          "Spring boot best practice API document",
          "0.0.1",
          "",
          "Spring boot best practice",
          "",
          ""
      );
    }
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
* /


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 10:49:48.163 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:49:53.759 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 27926, outputTokenCount = 705, totalTokenCount = 28631 }
2025-10-03 10:49:53.759 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:49:53.760 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}
2025-10-03 10:49:53.760 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refining code...
2025-10-03 10:49:53.760 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:49:53.760 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refined generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}

2025-10-03 10:50:58.768 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 10:50:58.768 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generating code...
2025-10-03 10:50:58.768 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class AppBean {

  @Configuration
  @EnableSwagger2
  public static  class SwaggerConfig {

    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
      @Autowired
      private RequestInfoComponent requestInfo;
      @Autowired
      private AuthComponent authComponent;

      @Bean
      public InterceptorController interceptorController() {
        return new InterceptorController(this.authComponent, this.requestInfo);
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorController()).addPathPatterns("/api/**");
      }
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {

      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .paths(Predicates.not(PathSelectors.regex("/error")))
          .build()
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfo(
          "Spring boot best practice API",
          "Spring boot best practice API document",
          "0.0.1",
          "",
          "Spring boot best practice",
          "",
          ""
      );
    }
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
* /


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 10:50:58.769 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:51:03.776 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 32567, outputTokenCount = 705, totalTokenCount = 33272 }
2025-10-03 10:51:03.777 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:51:03.777 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}
2025-10-03 10:51:03.777 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refining code...
2025-10-03 10:51:03.778 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 10:51:03.778 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refined generated code:
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
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockController = mock(InterceptorController.class);
        doReturn(mockController).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockController);
    }

    @Test
    void GIVEN_webMvcConfigWithNullDependencies_WHEN_interceptorController_called_THEN_throwNullPointerException() throws Exception {
        // GIVEN
        AppBean.SwaggerConfig.WebMvcConfig configWithNulls = new AppBean.SwaggerConfig.WebMvcConfig();
        Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
        authField.setAccessible(true);
        authField.set(configWithNulls, null);
        Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
        requestInfoField.setAccessible(true);
        requestInfoField.set(configWithNulls, null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, configWithNulls::interceptorController);
    }
}

2025-10-03 14:24:33.721 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-03 14:24:33.727 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generating code...
2025-10-03 14:24:33.728 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[31,21] authComponent has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[32,21] requestInfo has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[58,29] getInterceptors() has protected access in org.springframework.web.servlet.config.annotation.InterceptorRegistry
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[31,21] authComponent has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[32,21] requestInfo has private access in com.bestpractice.api.app.AppBean.SwaggerConfig.WebMvcConfig
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-10426268673099803578/src/test/java/com/bestpractice/api/app/AppBeanGeneratedAiTests.java:[58,29] getInterceptors() has protected access in org.springframework.web.servlet.config.annotation.InterceptorRegistry
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

2025-10-03 14:24:33.728 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 14:24:38.069 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5095, outputTokenCount = 572, totalTokenCount = 5667 }
2025-10-03 14:24:38.069 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 14:24:38.069 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

public class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent mockAuthComponent;
    private RequestInfoComponent mockRequestInfoComponent;

    @BeforeEach
    public void setUp() {
        mockAuthComponent = mock(AuthComponent.class);
        mockRequestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig() {
            {
                try {
                    java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
                    authField.setAccessible(true);
                    authField.set(this, mockAuthComponent);
                    java.lang.reflect.Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
                    requestInfoField.setAccessible(true);
                    requestInfoField.set(this, mockRequestInfoComponent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Test
    public void givenAuthAndRequestInfoComponents_whenInterceptorControllerCalled_thenReturnsNonNullInterceptorController() {
        // GIVEN - setup done in setUp()

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(interceptorController);
    }

    @Test
    public void givenInterceptorRegistry_whenAddInterceptorsCalled_thenInterceptorIsAdded() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockInterceptor = mock(InterceptorController.class);
        doReturn(mockInterceptor).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockInterceptor);
    }

    @Test
    public void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenReturnsDocketWithSwagger2DocumentationType() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }
}
2025-10-03 14:24:38.070 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refining code...
2025-10-03 14:24:38.071 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Done
2025-10-03 14:24:38.071 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AppBeanGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;

public class AppBeanGeneratedAiTests {

    private AppBean.SwaggerConfig.WebMvcConfig webMvcConfig;
    private AuthComponent mockAuthComponent;
    private RequestInfoComponent mockRequestInfoComponent;

    @BeforeEach
    public void setUp() {
        mockAuthComponent = mock(AuthComponent.class);
        mockRequestInfoComponent = mock(RequestInfoComponent.class);
        webMvcConfig = new AppBean.SwaggerConfig.WebMvcConfig() {
            {
                try {
                    java.lang.reflect.Field authField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("authComponent");
                    authField.setAccessible(true);
                    authField.set(this, mockAuthComponent);
                    java.lang.reflect.Field requestInfoField = AppBean.SwaggerConfig.WebMvcConfig.class.getDeclaredField("requestInfo");
                    requestInfoField.setAccessible(true);
                    requestInfoField.set(this, mockRequestInfoComponent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Test
    public void givenAuthAndRequestInfoComponents_whenInterceptorControllerCalled_thenReturnsNonNullInterceptorController() {
        // GIVEN - setup done in setUp()

        // WHEN
        InterceptorController interceptorController = webMvcConfig.interceptorController();

        // THEN
        assertNotNull(interceptorController);
    }

    @Test
    public void givenInterceptorRegistry_whenAddInterceptorsCalled_thenInterceptorIsAdded() {
        // GIVEN
        InterceptorRegistry registry = spy(new InterceptorRegistry());
        AppBean.SwaggerConfig.WebMvcConfig configSpy = spy(webMvcConfig);
        InterceptorController mockInterceptor = mock(InterceptorController.class);
        doReturn(mockInterceptor).when(configSpy).interceptorController();

        // WHEN
        configSpy.addInterceptors(registry);

        // THEN
        verify(registry, atLeastOnce()).addInterceptor(mockInterceptor);
    }

    @Test
    public void givenSwaggerConfig_whenSwaggerSpringMvcPluginCalled_thenReturnsDocketWithSwagger2DocumentationType() {
        // GIVEN
        AppBean.SwaggerConfig swaggerConfig = new AppBean.SwaggerConfig();

        // WHEN
        Docket docket = swaggerConfig.swaggerSpringMvcPlugin();

        // THEN
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }
}
*/
