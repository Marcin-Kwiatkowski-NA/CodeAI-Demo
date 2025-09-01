package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @org.junit.jupiter.api.Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

/*
2025-09-01 11:13:15.716 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-01 11:13:15.720 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-09-01 11:13:15.720 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/ForbiddenGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/ForbiddenGeneratedAiTests.java:[13,13] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/ForbiddenGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR]   symbol:   class MockitoExtension
[ERROR]   location: package org.junit.jupiter.api.extension
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/ForbiddenGeneratedAiTests.java:[13,13] cannot find symbol
[ERROR]   symbol: class MockitoExtension
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

2025-09-01 11:13:15.720 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:13:26.912 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1946, outputTokenCount = 477, totalTokenCount = 2423 }
2025-09-01 11:13:26.913 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-01 11:13:26.913 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@MockitoExtension
public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

2025-09-01 11:13:26.913 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-09-01 11:13:26.913 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-01 11:13:26.913 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@MockitoExtension
public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

2025-09-01 11:13:32.653 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 11:13:32.653 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generating code...
2025-09-01 11:13:32.653 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement: `import org.junit.jupiter.api.extension.MockitoExtension;`

Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@MockitoExtension
public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}


# Instructions:

1. Focus exclusively on fixing the specified error.
2. Ensure the corrected code passes all assertions and tests.
3. Do not modify unrelated parts of the code or tests.
4. Adhere to existing project standards, including naming conventions and formatting.
5. Make sure add all missing import statements.
6. Provide the output as plain text only.
7. Include the entire source code as originally provided; do not omit any parts.

2025-09-01 11:13:32.653 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:13:45.394 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3141, outputTokenCount = 473, totalTokenCount = 3614 }
2025-09-01 11:13:45.394 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-01 11:13:45.394 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @org.junit.jupiter.api.Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

2025-09-01 11:13:45.394 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refining code...
2025-09-01 11:13:45.394 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Done
2025-09-01 11:13:45.394 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ForbiddenGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @org.junit.jupiter.api.Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message.
        assertNotNull(exception);
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message.
        String message = "Access Denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided cause.
        String causeMessage = "Underlying Problem";
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Forbidden Access";
        Exception cause = new Exception("Invalid Credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
*/
