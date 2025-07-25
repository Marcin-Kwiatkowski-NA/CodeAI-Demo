package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}

/*
2025-07-25 11:46:57.947 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-07-25 11:46:57.948 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-07-25 11:46:57.948 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[14,26] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyTestFactory> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[66,34] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyTestFactory.MyAnnotationFactory> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[14,26] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyTestFactory> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[66,34] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyTestFactory.MyAnnotationFactory> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-07-25 11:46:57.949 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 11:47:19.860 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2006, outputTokenCount = 514, totalTokenCount = 2520 }
2025-07-25 11:47:19.861 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-07-25 11:47:19.861 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotationFactory.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}

class MyAnnotationFactory {
}
```
2025-07-25 11:47:19.861 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-07-25 11:47:19.862 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-07-25 11:47:25.925 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 11:47:25.925 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-07-25 11:47:25.925 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyAnnotationFactory.class)` annotation from the `BCryptPasswordEncryptionComponentGeneratedAiTests` class.
]

In this code:

package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotationFactory.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}

class MyAnnotationFactory {
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 11:47:25.925 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 11:47:39.004 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3212, outputTokenCount = 483, totalTokenCount = 3695 }
2025-07-25 11:47:39.004 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-07-25 11:47:39.004 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}
```
2025-07-25 11:47:39.004 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-07-25 11:47:39.004 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-07-25 11:47:43.777 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 11:47:43.778 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-07-25 11:47:43.778 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyAnnotationFactory.class)` annotation from the `BCryptPasswordEncryptionComponentGeneratedAiTests` class.
]

In this code:

package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 11:47:43.778 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 11:47:55.499 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4371, outputTokenCount = 480, totalTokenCount = 4851 }
2025-07-25 11:47:55.500 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-07-25 11:47:55.500 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be a hashed version of the raw password
    assertNotNull(encryptedPassword);
    // The actual hashed value will vary, but the core is the non-null check
    // You can add more specific assertions if needed, but the core is the non-null check
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: A valid password string and an incorrect encrypted password
    String rawPassword = "password123";
    String incorrectEncryptedPassword = "wrongPassword";

    // WHEN: The matchedPassword method is called with the raw password and incorrect encrypted password
    boolean result = component.matchedPassword(rawPassword, incorrectEncryptedPassword);

    // THEN: The method should return false if the passwords do not match
    assertFalse(result);
  }
}
```
2025-07-25 11:47:55.500 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-07-25 11:47:55.500 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
*/
