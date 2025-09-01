package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

/*
2025-09-01 11:10:08.475 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-01 11:10:08.480 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 11:10:08.480 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[13,13] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR]   symbol:   class MockitoExtension
[ERROR]   location: package org.junit.jupiter.api.extension
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[13,13] cannot find symbol
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

2025-09-01 11:10:08.480 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:10:19.301 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1918, outputTokenCount = 461, totalTokenCount = 2379 }
2025-09-01 11:10:19.301 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:19.301 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:19.302 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 11:10:19.302 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:19.302 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:25.260 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 11:10:25.260 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 11:10:25.260 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement `import org.junit.jupiter.api.extension.MockitoExtension;` from the class.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
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

2025-09-01 11:10:25.260 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:10:37.200 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3069, outputTokenCount = 441, totalTokenCount = 3510 }
2025-09-01 11:10:37.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:37.201 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:37.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 11:10:37.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:37.201 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:41.741 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 11:10:41.741 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 11:10:41.741 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement `import org.junit.jupiter.api.extension.MockitoExtension;` from the class.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
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

2025-09-01 11:10:41.741 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:10:52.748 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4195, outputTokenCount = 441, totalTokenCount = 4636 }
2025-09-01 11:10:52.748 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:52.749 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:52.749 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 11:10:52.749 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:10:52.749 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:10:57.430 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 11:10:57.431 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 11:10:57.431 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement `import org.junit.jupiter.api.extension.MockitoExtension;` from the class.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
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

2025-09-01 11:10:57.431 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 11:11:08.107 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5321, outputTokenCount = 421, totalTokenCount = 5742 }
2025-09-01 11:11:08.108 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:11:08.108 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}

2025-09-01 11:11:08.108 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 11:11:08.108 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 11:11:08.108 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RequestTimeout object is created with no message
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RequestTimeout object is created with the provided message
        String message = "Request timed out";
        RequestTimeout requestTimeoutWithMessage = new RequestTimeout(message);
        assertEquals(message, requestTimeoutWithMessage.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is passed to the constructor
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithCause = new RequestTimeout(cause);
        assertSame(cause, requestTimeoutWithCause.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        String message = "Request timed out";
        Throwable cause = new RuntimeException("Underlying error");
        RequestTimeout requestTimeoutWithBoth = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeoutWithBoth.getMessage());
        assertSame(cause, requestTimeoutWithBoth.getCause());
    }
}
*/
