package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}

/*
2025-09-10 09:08:58.392 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-10 09:08:58.395 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-09-10 09:08:58.395 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-7265805542967110913/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[13,48] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-7265805542967110913/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[13,48] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-09-10 09:08:58.395 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-10 09:09:11.265 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1703, outputTokenCount = 545, totalTokenCount = 2248 }
2025-09-10 09:09:11.266 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-09-10 09:09:11.266 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
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
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and Throwable object.
        // THEN: A RuntimeException is created with the message and Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-09-10 09:09:11.266 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-09-10 09:09:11.267 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-09-10 09:09:11.267 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and Throwable object.
        // THEN: A RuntimeException is created with the message and Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}

2025-09-10 09:09:17.223 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-10 09:09:17.224 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-09-10 09:09:17.224 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the `@ExtendWith({ServiceUnavailableGeneratedAiTests.class})` annotation.


In this code:

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and Throwable object.
        // THEN: A RuntimeException is created with the message and Throwable object as the cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
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
2025-09-10 09:09:17.224 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-10 09:09:26.231 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2956, outputTokenCount = 314, totalTokenCount = 3270 }
2025-09-10 09:09:26.232 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-09-10 09:09:26.232 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
```
2025-09-10 09:09:26.232 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-09-10 09:09:26.232 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-09-10 09:09:26.233 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable.");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        Throwable cause = new NullPointerException("NullPointerException occurred");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
*/
