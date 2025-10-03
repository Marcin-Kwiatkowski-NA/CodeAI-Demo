package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
        bindingResult = Mockito.mock(BindingResult.class);
    }

    @Test
    public void givenValidUserRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        verify(userService, times(1)).generateUser(request);
    }

    @Test
    public void givenInvalidUserRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail("invalid-email");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest exception = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", exception.getMessage());
        verify(userService, never()).generateUser(any());
    }
}

/*
2025-09-11 16:19:19.033 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:19:19.045 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generating code...
2025-09-11 16:19:19.046 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  @PostMapping()
  public UserResponse createUser(
      @RequestBody @Validated UserRequest request,
      BindingResult bdResult) {
    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.userService.generateUser(request);
  }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
    }
}


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

2025-09-11 16:19:19.046 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:19:23.249 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4199, outputTokenCount = 565, totalTokenCount = 4764 }
2025-09-11 16:19:23.250 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:19:23.250 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
    }

    @Test
    public void givenValidRequest_whenServiceThrowsException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }
}
2025-09-11 16:19:23.250 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refining code...
2025-09-11 16:19:23.251 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:19:23.251 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
    }

    @Test
    public void givenValidRequest_whenServiceThrowsException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }
}

2025-09-11 16:20:21.965 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:20:21.966 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generating code...
2025-09-11 16:20:21.966 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  @PostMapping()
  public UserResponse createUser(
      @RequestBody @Validated UserRequest request,
      BindingResult bdResult) {
    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.userService.generateUser(request);
  }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
    }

    @Test
    public void givenValidRequest_whenServiceThrowsException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }
}


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

2025-09-11 16:20:21.967 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:20:27.128 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6240, outputTokenCount = 722, totalTokenCount = 6962 }
2025-09-11 16:20:27.129 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:20:27.129 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsRuntimeException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsBadRequest_thenPropagatesBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        BadRequest badRequest = new BadRequest("Invalid data");
        when(userService.generateUser(request)).thenThrow(badRequest);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Invalid data", thrown.getMessage());
    }
}
2025-09-11 16:20:27.129 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refining code...
2025-09-11 16:20:27.130 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:20:27.130 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsRuntimeException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsBadRequest_thenPropagatesBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        BadRequest badRequest = new BadRequest("Invalid data");
        when(userService.generateUser(request)).thenThrow(badRequest);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Invalid data", thrown.getMessage());
    }
}

2025-09-11 16:21:19.946 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:21:19.947 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generating code...
2025-09-11 16:21:19.947 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  @PostMapping()
  public UserResponse createUser(
      @RequestBody @Validated UserRequest request,
      BindingResult bdResult) {
    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.userService.generateUser(request);
  }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsRuntimeException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsBadRequest_thenPropagatesBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        BadRequest badRequest = new BadRequest("Invalid data");
        when(userService.generateUser(request)).thenThrow(badRequest);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Invalid data", thrown.getMessage());
    }
}


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

2025-09-11 16:21:19.947 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:21:25.753 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8595, outputTokenCount = 785, totalTokenCount = 9380 }
2025-09-11 16:21:25.754 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:21:25.754 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsRuntimeException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsBadRequest_thenPropagatesBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        BadRequest badRequest = new BadRequest("Invalid data");
        when(userService.generateUser(request)).thenThrow(badRequest);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Invalid data", thrown.getMessage());
    }

    @Test
    public void givenNullRequest_whenCreateUser_thenThrowsNullPointerException() {
        // GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> userController.createUser(null, bindingResult));
    }
}
2025-09-11 16:21:25.754 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refining code...
2025-09-11 16:21:25.755 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Done
2025-09-11 16:21:25.755 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        bindingResult = Mockito.mock(BindingResult.class);
        userController = new UserController(userService);
    }

    @Test
    public void givenValidRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsRuntimeException_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenValidRequest_whenServiceThrowsBadRequest_thenPropagatesBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        BadRequest badRequest = new BadRequest("Invalid data");
        when(userService.generateUser(request)).thenThrow(badRequest);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Invalid data", thrown.getMessage());
    }

    @Test
    public void givenNullRequest_whenCreateUser_thenThrowsNullPointerException() {
        // GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> userController.createUser(null, bindingResult));
    }
}
*/
