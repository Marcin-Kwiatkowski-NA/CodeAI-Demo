package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}

/*
2025-10-03 11:06:58.761 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-03 11:06:58.763 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generating code...
2025-10-03 11:06:58.764 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[38,70] cannot find symbol
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[15,36] incompatible types: java.lang.Class<com.bestpractice.api.app.v1.MyTestsGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[40,5] method does not override or implement a method from a supertype
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[42,9] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[38,70] cannot find symbol
[ERROR]   symbol:   class InternalExtensionTest
[ERROR]   location: package org.junit.jupiter.api.extension
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[15,36] incompatible types: java.lang.Class<com.bestpractice.api.app.v1.MyTestsGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[40,5] method does not override or implement a method from a supertype
[ERROR] /tmp/codeai-test-7646536852238106506/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[42,9] cannot find symbol
[ERROR]   symbol:   variable super
[ERROR]   location: class com.bestpractice.api.app.v1.MyTestsGeneratedAiTests
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

2025-10-03 11:06:58.764 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:07:06.682 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2022, outputTokenCount = 319, totalTokenCount = 2341 }
2025-10-03 11:07:06.682 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-10-03 11:07:06.682 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MyTestsGeneratedAiTests.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}

// Custom JUnit extension to handle naming conventions
class MyTestsGeneratedAiTests extends org.junit.jupiter.api.extension.InternalExtensionTest {

    @Override
    public void beforeAll() {
        super.beforeAll();
        // No specific setup needed for this extension
    }
}
```
2025-10-03 11:07:06.682 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refining code...
2025-10-03 11:07:06.683 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-10-03 11:07:06.683 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MyTestsGeneratedAiTests.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}

// Custom JUnit extension to handle naming conventions
class MyTestsGeneratedAiTests extends org.junit.jupiter.api.extension.InternalExtensionTest {

    @Override
    public void beforeAll() {
        super.beforeAll();
        // No specific setup needed for this extension
    }
}

2025-10-03 11:07:13.278 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-03 11:07:13.278 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generating code...
2025-10-03 11:07:13.278 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the `@ExtendWith(MyTestsGeneratedAiTests.class)` annotation from the `HelloControllerGeneratedAiTests` class.


In this code:

package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MyTestsGeneratedAiTests.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}

// Custom JUnit extension to handle naming conventions
class MyTestsGeneratedAiTests extends org.junit.jupiter.api.extension.InternalExtensionTest {

    @Override
    public void beforeAll() {
        super.beforeAll();
        // No specific setup needed for this extension
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-03 11:07:13.278 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:07:20.436 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2832, outputTokenCount = 241, totalTokenCount = 3073 }
2025-10-03 11:07:20.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-10-03 11:07:20.436 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}
```
2025-10-03 11:07:20.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refining code...
2025-10-03 11:07:20.436 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-10-03 11:07:20.436 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The HelloController is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}
*/
