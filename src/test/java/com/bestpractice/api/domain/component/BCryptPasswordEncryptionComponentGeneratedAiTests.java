package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}

/*
2025-08-08 11:07:43.788 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-08-08 11:07:43.790 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-08-08 11:07:43.790 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:108)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-11809397148186840759/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[13,26] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyAnnotations> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-11809397148186840759/src/test/java/com/bestpractice/api/domain/component/BCryptPasswordEncryptionComponentGeneratedAiTests.java:[13,26] incompatible types: java.lang.Class<com.bestpractice.api.domain.component.MyAnnotations> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-08-08 11:07:43.790 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:112)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-08 11:07:55.169 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1717, outputTokenCount = 488, totalTokenCount = 2205 }
2025-08-08 11:07:55.169 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-08-08 11:07:55.170 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}

class MyAnnotations {}
```
2025-08-08 11:07:55.170 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-08-08 11:07:55.170 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-08-08 11:07:55.170 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}

class MyAnnotations {}

2025-08-08 11:08:01.161 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-08-08 11:08:01.161 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-08-08 11:08:01.161 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:108)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(MyAnnotations.class)` annotation.
]

In this code:

package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}

class MyAnnotations {}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-08-08 11:08:01.161 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:112)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-08-08 11:08:13.140 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2857, outputTokenCount = 453, totalTokenCount = 3310 }
2025-08-08 11:08:13.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-08-08 11:08:13.140 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}
```
2025-08-08 11:08:13.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-08-08 11:08:13.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-08-08 11:08:13.140 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should not be the same as the raw password");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for a wrong password");
  }
}
*/
