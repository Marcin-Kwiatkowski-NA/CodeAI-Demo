package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    ServiceUnavailable exception = new ServiceUnavailable();
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new NullPointerException("Something went wrong");
    ServiceUnavailable exception = new ServiceUnavailable(cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new NullPointerException("Another error");
    ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
    assertEquals("Service unavailable", exception.getMessage());
  }
}

/*
2025-07-25 11:24:51.854 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-07-25 11:24:51.857 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-07-25 11:24:51.857 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[14,24] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/common/exception/ServiceUnavailableGeneratedAiTests.java:[14,24] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-07-25 11:24:51.857 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 11:25:17.943 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1710, outputTokenCount = 500, totalTokenCount = 2210 }
2025-07-25 11:25:17.943 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-07-25 11:25:17.943 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ServiceUnavailableGeneratedAiTests {

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    ServiceUnavailable exception = new ServiceUnavailable();
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new NullPointerException("Something went wrong");
    ServiceUnavailable exception = new ServiceUnavailable(cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new NullPointerException("Another error");
    ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
    assertEquals("Service unavailable", exception.getMessage());
  }
}

class MyExtension {}
```
2025-07-25 11:25:17.943 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-07-25 11:25:17.943 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-07-25 11:25:25.154 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 11:25:25.154 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generating code...
2025-07-25 11:25:25.154 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyExtension.class)` annotation from the `ServiceUnavailableGeneratedAiTests` class.
]

In this code:

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ServiceUnavailableGeneratedAiTests {

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    ServiceUnavailable exception = new ServiceUnavailable();
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new NullPointerException("Something went wrong");
    ServiceUnavailable exception = new ServiceUnavailable(cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new NullPointerException("Another error");
    ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
    assertEquals("Service unavailable", exception.getMessage());
  }
}

class MyExtension {}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 11:25:25.154 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 11:25:46.842 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2897, outputTokenCount = 470, totalTokenCount = 3367 }
2025-07-25 11:25:46.842 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
2025-07-25 11:25:46.842 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    ServiceUnavailable exception = new ServiceUnavailable();
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new NullPointerException("Something went wrong");
    ServiceUnavailable exception = new ServiceUnavailable(cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new NullPointerException("Another error");
    ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
    assertNotNull(exception);
    assertTrue(exception instanceof RuntimeException);
    assertSame(cause, exception.getCause());
    assertEquals("Service unavailable", exception.getMessage());
  }
}
```
2025-07-25 11:25:46.842 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Refining code...
2025-07-25 11:25:46.842 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.ServiceUnavailableGeneratedAiTests.java}] - Done
*/
