package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be non-null and have no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message and no cause
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the cause and the message should match cause.toString()
        assertNotNull(exception);
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out due to network issues";
        Throwable cause = new RuntimeException("Network failure");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-10-06 14:19:05.434 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:05.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:05.436 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
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

2025-10-06 14:19:05.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:19:11.382 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2235, outputTokenCount = 793, totalTokenCount = 3028 }
2025-10-06 14:19:11.382 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:11.383 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:19:11.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:19:11.384 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:11.384 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:19:30.241 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:30.241 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:30.241 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
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

2025-10-06 14:19:30.243 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:19:35.607 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4561, outputTokenCount = 818, totalTokenCount = 5379 }
2025-10-06 14:19:35.607 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:35.608 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:19:35.609 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:19:35.610 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:35.611 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:55.215 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
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

2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:20:05.758 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6927, outputTokenCount = 818, totalTokenCount = 7745 }
2025-10-06 14:20:05.760 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:20:05.760 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:20:05.761 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:20:05.763 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:20:05.763 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 15:12:50.052 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 15:12:50.054 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 15:12:50.054 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: A default constructor call
        // WHEN: Creating a new RequestTimeout instance using default constructor
        RequestTimeout exception = new RequestTimeout();
        // THEN: The exception should be created and message should be null
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Request timed out";
        // WHEN: Creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: The exception should contain the provided message
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause exception
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: The exception should contain the provided cause
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific error message and cause
        String message = "Request timed out";
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN: Creating a new RequestTimeout instance with both message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: The exception should contain both the provided message and cause
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-10-06 14:19:05.434 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:05.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:05.436 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
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

2025-10-06 14:19:05.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:19:11.382 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2235, outputTokenCount = 793, totalTokenCount = 3028 }
2025-10-06 14:19:11.382 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:11.383 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:19:11.383 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:19:11.384 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:11.384 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:19:30.241 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:30.241 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:30.241 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
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

2025-10-06 14:19:30.243 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:19:35.607 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4561, outputTokenCount = 818, totalTokenCount = 5379 }
2025-10-06 14:19:35.607 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:35.608 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:19:35.609 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:19:35.610 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:19:35.611 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-10-06 14:19:55.215 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
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

2025-10-06 14:19:55.215 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:20:05.758 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6927, outputTokenCount = 818, totalTokenCount = 7745 }
2025-10-06 14:20:05.760 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:20:05.760 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:20:05.761 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 14:20:05.763 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 14:20:05.763 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
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

2025-10-06 15:12:50.058 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 15:12:56.170 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 13266, outputTokenCount = 821, totalTokenCount = 14087 }
2025-10-06 15:12:56.170 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 15:12:56.170 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message and no cause
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 15:12:56.171 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-10-06 15:12:56.173 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-10-06 15:12:56.173 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new RequestTimeout instance
        RequestTimeout exception = new RequestTimeout();
        // THEN: the exception should be created with no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN: creating a new RequestTimeout instance with the message
        RequestTimeout exception = new RequestTimeout(message);
        // THEN: the exception should contain the provided message and no cause
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the cause
        RequestTimeout exception = new RequestTimeout(cause);
        // THEN: the exception should contain the provided cause and message from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new RequestTimeout instance with the message and cause
        RequestTimeout exception = new RequestTimeout(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingRequestTimeoutUsingDefaultConstructor() {
        // GIVEN: a default RequestTimeout instance
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout();
        });
    }

    @Test
    void testThrowingRequestTimeoutWithMessage() {
        // GIVEN: a specific message
        String message = "Request timed out";
        // WHEN & THEN: throwing the exception should be caught and message verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingRequestTimeoutWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: throwing the exception should be caught and both message and cause verified
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
*/
