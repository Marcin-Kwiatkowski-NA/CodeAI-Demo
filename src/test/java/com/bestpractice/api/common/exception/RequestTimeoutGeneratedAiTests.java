package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

/*
2025-09-01 20:33:40.490 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-01 20:33:40.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 20:33:40.493 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-14466646958197756183/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR] /tmp/codeai-test-14466646958197756183/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[13,13] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-14466646958197756183/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR]   symbol:   class MockitoExtension
[ERROR]   location: package org.junit.jupiter.api.extension
[ERROR] /tmp/codeai-test-14466646958197756183/src/test/java/com/bestpractice/api/common/exception/RequestTimeoutGeneratedAiTests.java:[13,13] cannot find symbol
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

2025-09-01 20:33:40.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 20:33:51.461 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1941, outputTokenCount = 466, totalTokenCount = 2407 }
2025-09-01 20:33:51.461 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:33:51.461 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:33:51.461 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 20:33:51.462 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:33:51.462 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:33:57.492 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 20:33:57.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 20:33:57.493 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement: `import org.junit.jupiter.api.extension.MockitoExtension;` from the class `RequestTimeoutGeneratedAiTests`.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
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

2025-09-01 20:33:57.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 20:34:09.619 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3109, outputTokenCount = 447, totalTokenCount = 3556 }
2025-09-01 20:34:09.622 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:09.622 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:34:09.623 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 20:34:09.623 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:09.623 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:34:14.160 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 20:34:14.161 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 20:34:14.161 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement: `import org.junit.jupiter.api.extension.MockitoExtension;` from the class `RequestTimeoutGeneratedAiTests`.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
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

2025-09-01 20:34:14.161 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 20:34:25.204 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4254, outputTokenCount = 447, totalTokenCount = 4701 }
2025-09-01 20:34:25.204 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:25.204 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:34:25.204 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 20:34:25.205 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:25.205 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:34:29.795 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 20:34:29.795 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generating code...
2025-09-01 20:34:29.795 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement: `import org.junit.jupiter.api.extension.MockitoExtension;` from the class `RequestTimeoutGeneratedAiTests`.


Failing code:
  
  package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension(MockitoExtension.class)
public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = new RequestTimeout();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
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

2025-09-01 20:34:29.795 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 20:34:40.674 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5399, outputTokenCount = 426, totalTokenCount = 5825 }
2025-09-01 20:34:40.675 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:40.675 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Generated code:
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}

2025-09-01 20:34:40.675 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refining code...
2025-09-01 20:34:40.675 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Done
2025-09-01 20:34:40.675 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.RequestTimeoutGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
        // GIVEN: A new RequestTimeout object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The object is initialized with no specific message or cause.
        assertNotNull(requestTimeout);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new RequestTimeout object is created with a message.
        // WHEN: The constructor is called with a message.
        String message = "Request timed out";
        requestTimeout = new RequestTimeout(message);
        assertEquals(message, requestTimeout.getMessage());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new RequestTimeout object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Request timed out";
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(message, cause);
        assertEquals(message, requestTimeout.getMessage());
        assertSame(cause, requestTimeout.getCause());
    }

    @Test
    void constructor_withCauseOnly() {
        // GIVEN: A new RequestTimeout object is created with only a cause.
        // WHEN: The constructor is called with only a cause.
        Throwable cause = new Throwable();
        requestTimeout = new RequestTimeout(cause);
        assertSame(cause, requestTimeout.getCause());
    }
}
*/
