package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}

/*
2025-09-19 12:48:03.996 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-19 12:48:03.998 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generating code...
2025-09-19 12:48:03.998 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-10602582860762743180/src/test/java/com/bestpractice/api/domain/model/InfoResponseGeneratedAiTests.java:[13,24] incompatible types: java.lang.Class<com.bestpractice.api.domain.model.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-10602582860762743180/src/test/java/com/bestpractice/api/domain/model/InfoResponseGeneratedAiTests.java:[13,24] incompatible types: java.lang.Class<com.bestpractice.api.domain.model.MyExtension> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
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

2025-09-19 12:48:03.998 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-19 12:48:12.162 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1591, outputTokenCount = 365, totalTokenCount = 1956 }
2025-09-19 12:48:12.162 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Done
2025-09-19 12:48:12.162 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}

class MyExtension {}
```
2025-09-19 12:48:12.162 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Refining code...
2025-09-19 12:48:12.162 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Done
2025-09-19 12:48:12.162 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}

class MyExtension {}

2025-09-19 12:48:18.007 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-19 12:48:18.007 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generating code...
2025-09-19 12:48:18.007 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the `@ExtendWith(MyExtension.class)` annotation from the `InfoResponseGeneratedAiTests` class.


In this code:

package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}

class MyExtension {}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-09-19 12:48:18.007 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-19 12:48:26.201 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2490, outputTokenCount = 325, totalTokenCount = 2815 }
2025-09-19 12:48:26.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Done
2025-09-19 12:48:26.201 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
```
2025-09-19 12:48:26.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Refining code...
2025-09-19 12:48:26.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Done
2025-09-19 12:48:26.201 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoResponseGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
*/
