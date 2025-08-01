package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestGeneratedAiTests {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @org.junit.jupiter.api.Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @org.junit.jupiter.api.Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

/*
2025-08-01 12:45:13.602 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-08-01 12:45:13.604 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generating code...
2025-08-01 12:45:13.604 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-13556531916225010714/src/test/java/com/bestpractice/api/common/exception/BadRequestGeneratedAiTests.java:[14,24] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-13556531916225010714/src/test/java/com/bestpractice/api/common/exception/BadRequestGeneratedAiTests.java:[14,24] incompatible types: java.lang.Class<com.bestpractice.api.common.exception.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-08-01 12:45:13.604 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:45:25.968 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1661, outputTokenCount = 530, totalTokenCount = 2191 }
2025-08-01 12:45:25.968 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-08-01 12:45:25.968 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

class MyExtension {}
```
2025-08-01 12:45:25.968 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Refining code...
2025-08-01 12:45:25.968 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-08-01 12:45:31.893 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-01 12:45:31.893 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generating code...
2025-08-01 12:45:31.893 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyExtension.class)` annotation.
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

@ExtendWith(MyExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

class MyExtension {}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-01 12:45:31.893 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:45:46.104 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2885, outputTokenCount = 526, totalTokenCount = 3411 }
2025-08-01 12:45:46.104 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-08-01 12:45:46.104 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Test
class BadRequestGeneratedAiTests {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @org.junit.jupiter.api.Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @org.junit.jupiter.api.Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}
```
2025-08-01 12:45:46.104 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Refining code...
2025-08-01 12:45:46.105 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-08-01 12:45:51.068 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-01 12:45:51.069 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generating code...
2025-08-01 12:45:51.069 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyExtension.class)` annotation.
]

In this code:

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Test
class BadRequestGeneratedAiTests {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @org.junit.jupiter.api.Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @org.junit.jupiter.api.Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-01 12:45:51.069 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-01 12:46:18.288 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4141, outputTokenCount = 515, totalTokenCount = 4656 }
2025-08-01 12:46:18.288 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
2025-08-01 12:46:18.288 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestGeneratedAiTests {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @org.junit.jupiter.api.Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @org.junit.jupiter.api.Test
  void constructor_withThrowable() {
    // GIVEN: A throwable is passed to the constructor.
    // WHEN: The constructor is called with the throwable.
    // THEN: A RuntimeException is created with the throwable.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @org.junit.jupiter.api.Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable are passed to the constructor.
    // WHEN: The constructor is called with the message and throwable.
    // THEN: A RuntimeException is created with the message and throwable.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}
```
2025-08-01 12:46:18.288 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Refining code...
2025-08-01 12:46:18.288 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.common.exception.BadRequestGeneratedAiTests.java}] - Done
*/
