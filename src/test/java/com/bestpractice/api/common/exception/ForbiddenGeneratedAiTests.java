package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: A default constructor call
        // WHEN: Creating a Forbidden instance using the default constructor
        Forbidden exception = new Forbidden();
        // THEN: The message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Access denied";
        // WHEN: Creating a Forbidden instance with the message
        Forbidden exception = new Forbidden(message);
        // THEN: The message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: Creating a Forbidden instance with the cause
        Forbidden exception = new Forbidden(cause);
        // THEN: The cause should match and message should be cause.toString()
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: Creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: Both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-10-03 11:16:58.143 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:16:58.151 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-10-03 11:16:58.152 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a Forbidden instance with no arguments
        Forbidden exception = new Forbidden();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN: creating a Forbidden instance with a message
        Forbidden exception = new Forbidden(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause exception
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with a cause
        Forbidden exception = new Forbidden(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied due to policy";
        Throwable cause = new RuntimeException("Policy violation");
        // WHEN: creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-09-11 16:32:23.105 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:32:23.114 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-09-11 16:32:23.114 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

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

2025-09-11 16:32:23.115 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:32:28.537 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2067, outputTokenCount = 523, totalTokenCount = 2590 }
2025-09-11 16:32:28.538 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:32:28.538 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:32:28.539 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-09-11 16:32:28.540 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:32:28.540 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }
}

2025-09-11 16:33:18.633 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:33:18.634 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-09-11 16:33:18.634 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
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

2025-09-11 16:33:18.634 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:33:23.014 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3848, outputTokenCount = 620, totalTokenCount = 4468 }
2025-09-11 16:33:23.014 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:33:23.014 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isNotNull();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:33:23.014 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-09-11 16:33:23.015 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:33:23.016 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isNotNull();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-09-11 16:34:11.291 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:34:11.292 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-09-11 16:34:11.292 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isNotNull();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
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

2025-09-11 16:34:11.292 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:34:15.162 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5823, outputTokenCount = 632, totalTokenCount = 6455 }
2025-09-11 16:34:15.163 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:34:15.163 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isNotNull();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-09-11 16:34:15.163 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-09-11 16:34:15.164 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-11 16:34:15.165 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isNotNull();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
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

2025-10-03 11:16:58.155 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:17:05.110 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 26316, outputTokenCount = 630, totalTokenCount = 26946 }
2025-10-03 11:17:05.110 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-10-03 11:17:05.110 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-03 11:17:05.111 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-10-03 11:17:05.111 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-10-03 11:17:05.111 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-03 11:17:59.308 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:17:59.309 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-10-03 11:17:59.309 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
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

2025-10-03 11:17:59.309 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:18:24.608 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 28285, outputTokenCount = 630, totalTokenCount = 28915 }
2025-10-03 11:18:24.609 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-10-03 11:18:24.609 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
2025-10-03 11:18:24.609 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-10-03 11:18:24.609 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-10-03 11:18:24.610 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}

2025-10-03 11:19:10.329 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:19:10.330 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-10-03 11:19:10.330 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenForbiddenException_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenForbiddenWithCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenForbiddenWithMessageAndCause_whenThrown_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
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

2025-10-03 11:19:10.330 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:19:10.973 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - AI ERROR - did not generate response
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
2025-10-03 11:19:10.976 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Failed to generate code
2025-10-03 11:19:10.976 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-10-03 11:19:10.976 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - No code to be used! Generated code is empty
*/
