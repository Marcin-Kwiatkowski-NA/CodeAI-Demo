package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}

/*
2025-10-06 14:02:34.954 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:02:34.957 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:02:34.957 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
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

2025-10-06 14:02:34.958 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:02:41.347 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4164, outputTokenCount = 855, totalTokenCount = 5019 }
2025-10-06 14:02:41.348 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:02:41.348 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
    }
}
2025-10-06 14:02:41.350 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:02:41.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:02:41.351 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
    }
}

2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:03:00.383 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
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

2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:03:04.781 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7083, outputTokenCount = 827, totalTokenCount = 7910 }
2025-10-06 14:03:04.781 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:04.782 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
    }
}
2025-10-06 14:03:04.783 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:03:04.785 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:04.785 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
    }
}

2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:03:23.650 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
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

2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:03:30.138 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 9946, outputTokenCount = 917, totalTokenCount = 10863 }
2025-10-06 14:03:30.139 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:30.139 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
2025-10-06 14:03:30.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:03:30.142 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:30.142 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}

2025-10-06 14:57:17.879 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:57:17.883 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:57:17.883 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}

/*
2025-10-06 14:02:34.954 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:02:34.957 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:02:34.957 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
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

2025-10-06 14:02:34.958 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:02:41.347 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4164, outputTokenCount = 855, totalTokenCount = 5019 }
2025-10-06 14:02:41.348 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:02:41.348 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
    }
}
2025-10-06 14:02:41.350 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:02:41.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:02:41.351 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
    }
}

2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:03:00.383 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorThrowsException() {
        // GIVEN
        Exception exception = new Exception("Simulated error");

        // WHEN & THEN
        // The method itself does not throw, but we ensure it handles the exception gracefully
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
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

2025-10-06 14:03:00.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:03:04.781 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7083, outputTokenCount = 827, totalTokenCount = 7910 }
2025-10-06 14:03:04.781 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:04.782 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
    }
}
2025-10-06 14:03:04.783 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:03:04.785 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:04.785 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
    }
}

2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:03:23.650 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            adviceController.serverError(exception);
        });
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

2025-10-06 14:03:23.650 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:03:30.138 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 9946, outputTokenCount = 917, totalTokenCount = 10863 }
2025-10-06 14:03:30.139 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:30.139 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
2025-10-06 14:03:30.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:03:30.142 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:03:30.142 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
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

2025-10-06 14:57:17.887 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:57:25.379 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 17321, outputTokenCount = 917, totalTokenCount = 18238 }
2025-10-06 14:57:25.379 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:57:25.379 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
2025-10-06 14:57:25.382 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:57:25.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:57:25.383 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}

2025-10-06 14:57:44.744 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:57:44.744 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:57:44.744 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
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

2025-10-06 14:57:44.744 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:57:45.228 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingImprovementGenerator.create(PersistingImprovementGenerator.java:36)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:80)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:105)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.create(RetryImprovementGenerator.java:64)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.ExceptionsHandlingGeneratorStep.improveExceptionsHandlingInGeneratedUnitTests(ExceptionsHandlingGeneratorStep.java:62)
	at io.github.adamw7.testing.steps.ExceptionsHandlingGeneratorStep.process(ExceptionsHandlingGeneratorStep.java:41)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 14:57:45.229 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:57:45.229 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:57:45.229 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:58:04.642 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:58:04.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generating code...
2025-10-06 14:58:04.643 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
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

2025-10-06 14:58:04.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:58:12.585 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 22487, outputTokenCount = 1016, totalTokenCount = 23503 }
2025-10-06 14:58:12.585 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:58:12.586 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorWithBadRequestException() {
        // GIVEN
        BadRequest badRequestException = new BadRequest("Bad request occurred");

        // WHEN
        ErrorResponse response = adviceController.serverError(badRequestException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
2025-10-06 14:58:12.586 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refining code...
2025-10-06 14:58:12.587 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Done
2025-10-06 14:58:12.587 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.AdviceControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Test error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorHandlesNullException() {
        // GIVEN
        Exception exception = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    public void testServerErrorWithRuntimeException() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime error");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorWithBadRequestException() {
        // GIVEN
        BadRequest badRequestException = new BadRequest("Bad request occurred");

        // WHEN
        ErrorResponse response = adviceController.serverError(badRequestException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
*/
