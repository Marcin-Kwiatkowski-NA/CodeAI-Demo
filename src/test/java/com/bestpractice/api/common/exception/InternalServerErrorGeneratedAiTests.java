package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating an instance using the default constructor
        InternalServerError error = new InternalServerError();
        // THEN: the message and cause should be null
        assertNull(error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Internal server error occurred";
        // WHEN: creating an instance using the message constructor
        InternalServerError error = new InternalServerError(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating an instance using the cause constructor
        InternalServerError error = new InternalServerError(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, error.getCause());
        assertTrue(error.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Internal server error with cause";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: creating an instance using the message and cause constructor
        InternalServerError error = new InternalServerError(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, error.getMessage());
        assertEquals(cause, error.getCause());
    }
}

/*
2025-10-03 11:20:00.961 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:20:00.969 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-10-03 11:20:00.969 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating an instance using the default constructor
        InternalServerError error = new InternalServerError();
        // THEN: the message and cause should be null
        assertNull(error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Internal server error occurred";
        // WHEN: creating an instance using the message constructor
        InternalServerError error = new InternalServerError(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating an instance using the cause constructor
        InternalServerError error = new InternalServerError(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, error.getCause());
        assertTrue(error.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Internal server error with cause";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: creating an instance using the message and cause constructor
        InternalServerError error = new InternalServerError(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, error.getMessage());
        assertEquals(cause, error.getCause());
    }
}

/*
2025-09-11 16:35:07.321 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:35:07.329 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-09-11 16:35:07.329 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
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

2025-09-11 16:35:07.330 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:35:11.987 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2123, outputTokenCount = 647, totalTokenCount = 2770 }
2025-09-11 16:35:11.988 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:35:11.988 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:35:11.988 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refining code...
2025-09-11 16:35:11.989 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:35:11.989 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-09-11 16:36:01.254 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:36:01.254 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-09-11 16:36:01.255 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
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

2025-09-11 16:36:01.255 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:36:05.867 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4162, outputTokenCount = 674, totalTokenCount = 4836 }
2025-09-11 16:36:05.868 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:36:05.868 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:36:05.868 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refining code...
2025-09-11 16:36:05.869 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:36:05.869 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-09-11 16:36:53.670 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:36:53.671 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-09-11 16:36:53.672 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
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

2025-09-11 16:36:53.672 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:36:58.280 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6255, outputTokenCount = 677, totalTokenCount = 6932 }
2025-09-11 16:36:58.281 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:36:58.281 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:36:58.281 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refining code...
2025-09-11 16:36:58.282 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-09-11 16:36:58.282 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
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

2025-10-03 11:20:00.973 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:20:06.932 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 28062, outputTokenCount = 677, totalTokenCount = 28739 }
2025-10-03 11:20:06.933 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-10-03 11:20:06.933 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-03 11:20:06.933 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refining code...
2025-10-03 11:20:06.934 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-10-03 11:20:06.934 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-03 11:20:54.527 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:20:54.527 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-10-03 11:20:54.527 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
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

2025-10-03 11:20:54.528 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:20:55.269 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
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
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.improveGeneratedUnitTests(ImproveGeneratedTestsStep.java:62)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.process(ImproveGeneratedTestsStep.java:41)
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
2025-10-03 11:20:55.270 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Failed to generate code
2025-10-03 11:20:55.271 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-10-03 11:20:55.271 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-03 11:21:43.289 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:21:43.289 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generating code...
2025-10-03 11:21:43.289 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
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

2025-10-03 11:21:43.289 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:21:48.492 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 31528, outputTokenCount = 677, totalTokenCount = 32205 }
2025-10-03 11:21:48.492 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-10-03 11:21:48.493 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-03 11:21:48.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refining code...
2025-10-03 11:21:48.494 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Done
2025-10-03 11:21:48.494 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
*/
