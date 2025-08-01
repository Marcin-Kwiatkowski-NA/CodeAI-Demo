package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}

/*
2025-08-01 12:32:29.785 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-08-01 12:32:29.787 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-08-01 12:32:29.787 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-13556531916225010714/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[14,48] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-13556531916225010714/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[14,48] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-08-01 12:32:29.787 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:32:42.839 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1715, outputTokenCount = 542, totalTokenCount = 2257 }
2025-08-01 12:32:42.839 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:32:42.839 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ServiceUnavailableGeneratedAiTests.class})
class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A throwable object is provided to the constructor.
        // WHEN: The constructor is called with a throwable object.
        // THEN: A RuntimeException is created with the provided throwable object.
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a throwable object are provided to the constructor.
        // WHEN: The constructor is called with a message and a throwable object.
        // THEN: A RuntimeException is created with the provided message and throwable object.
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-08-01 12:32:42.839 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-08-01 12:32:42.840 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:32:49.871 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-01 12:32:49.871 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-08-01 12:32:49.871 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith({ServiceUnavailableGeneratedAiTests.class})` annotation.
]

In this code:

package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ServiceUnavailableGeneratedAiTests.class})
class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A throwable object is provided to the constructor.
        // WHEN: The constructor is called with a throwable object.
        // THEN: A RuntimeException is created with the provided throwable object.
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a throwable object are provided to the constructor.
        // WHEN: The constructor is called with a message and a throwable object.
        // THEN: A RuntimeException is created with the provided message and throwable object.
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-01 12:32:49.871 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:33:08.371 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2967, outputTokenCount = 299, totalTokenCount = 3266 }
2025-08-01 12:33:08.371 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:33:08.371 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-08-01 12:33:08.371 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-08-01 12:33:08.371 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:33:13.231 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-01 12:33:13.231 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-08-01 12:33:13.231 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith({ServiceUnavailableGeneratedAiTests.class})` annotation.
]

In this code:

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-01 12:33:13.231 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:33:28.000 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3761, outputTokenCount = 299, totalTokenCount = 4060 }
2025-08-01 12:33:28.000 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:33:28.000 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-08-01 12:33:28.000 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-08-01 12:33:28.001 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:33:33.098 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-01 12:33:33.099 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-08-01 12:33:33.099 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith({ServiceUnavailableGeneratedAiTests.class})` annotation.
]

In this code:

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-01 12:33:33.099 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:33:44.906 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4555, outputTokenCount = 296, totalTokenCount = 4851 }
2025-08-01 12:33:44.906 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-08-01 12:33:44.907 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        Throwable cause = new NullPointerException("Test cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-08-01 12:33:44.907 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-08-01 12:33:44.907 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
*/
