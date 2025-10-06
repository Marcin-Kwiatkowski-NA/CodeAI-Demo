package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is down";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying issue"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Network error");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-10-06 14:20:25.267 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:20:25.270 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-10-06 14:20:25.270 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class ServiceUnavailable extends RuntimeException {
  public ServiceUnavailable() {
    super();
  }

  public ServiceUnavailable(String msg) {
    super(msg);
  }

  public ServiceUnavailable(Throwable cause) {
    super(cause);
  }

  public ServiceUnavailable(String msg, Throwable cause) {
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

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
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

2025-10-06 14:20:25.270 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:20:30.789 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2223, outputTokenCount = 786, totalTokenCount = 3009 }
2025-10-06 14:20:30.790 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:20:30.790 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:20:30.790 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-10-06 14:20:30.792 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:20:30.792 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:20:49.808 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:20:49.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-10-06 14:20:49.808 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class ServiceUnavailable extends RuntimeException {
  public ServiceUnavailable() {
    super();
  }

  public ServiceUnavailable(String msg) {
    super(msg);
  }

  public ServiceUnavailable(Throwable cause) {
    super(cause);
  }

  public ServiceUnavailable(String msg, Throwable cause) {
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
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

2025-10-06 14:20:49.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:20:54.774 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4535, outputTokenCount = 828, totalTokenCount = 5363 }
2025-10-06 14:20:54.774 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:20:54.774 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Cause of failure"));
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:20:54.775 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-10-06 14:20:54.775 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:20:54.775 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Cause of failure"));
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-06 14:21:13.748 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:21:13.748 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-10-06 14:21:13.748 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class ServiceUnavailable extends RuntimeException {
  public ServiceUnavailable() {
    super();
  }

  public ServiceUnavailable(String msg) {
    super(msg);
  }

  public ServiceUnavailable(Throwable cause) {
    super(cause);
  }

  public ServiceUnavailable(String msg, Throwable cause) {
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Cause of failure"));
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
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

2025-10-06 14:21:13.748 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:21:18.354 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6931, outputTokenCount = 828, totalTokenCount = 7759 }
2025-10-06 14:21:18.354 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:21:18.354 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Cause of failure"));
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-06 14:21:18.355 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-10-06 14:21:18.356 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-10-06 14:21:18.356 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is unavailable";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the cause should match and message should contain cause message
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service down";
        // WHEN & THEN: throwing the exception should be caught and message verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Cause of failure");
        // WHEN & THEN: throwing the exception should be caught and cause verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Cause of failure"));
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught and both verified
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
*/
