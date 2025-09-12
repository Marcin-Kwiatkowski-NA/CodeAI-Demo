package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyCustomExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Error message", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Error message", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

// Custom JUnit extension to satisfy the requirement.
class MyCustomExtension implements Extension {}

/*
2025-09-12 10:08:27.599 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-12 10:08:27.602 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 10:08:27.602 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-13047852883889560876/src/test/java/com/bestpractice/api/common/exception/BadRequestGeneratedAiTests.java:[13,30] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyCustomExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-13047852883889560876/src/test/java/com/bestpractice/api/common/exception/BadRequestGeneratedAiTests.java:[13,30] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyCustomExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-09-12 10:08:27.603 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 10:08:40.365 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1719, outputTokenCount = 564, totalTokenCount = 2283 }
2025-09-12 10:08:40.365 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-09-12 10:08:40.365 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyCustomExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Error message", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Error message", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

// Custom JUnit extension to satisfy the requirement.
class MyCustomExtension implements Extension {}
```
2025-09-12 10:08:40.366 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 10:08:40.366 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-09-12 10:08:40.366 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyCustomExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Error message", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Error message", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

// Custom JUnit extension to satisfy the requirement.
class MyCustomExtension implements Extension {}
*/
