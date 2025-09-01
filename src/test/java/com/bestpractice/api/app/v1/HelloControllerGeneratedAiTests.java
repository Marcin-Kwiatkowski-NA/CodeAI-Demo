package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}

/*
2025-09-01 12:27:14.185 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-01 12:27:14.186 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generating code...
2025-09-01 12:27:14.187 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[16,13] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[10,39] cannot find symbol
[ERROR]   symbol:   class MockitoExtension
[ERROR]   location: package org.junit.jupiter.api.extension
[ERROR] /tmp/codeai-test-1511655724442698549/src/test/java/com/bestpractice/api/app/v1/HelloControllerGeneratedAiTests.java:[16,13] cannot find symbol
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

2025-09-01 12:27:14.187 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 12:27:19.893 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1757, outputTokenCount = 232, totalTokenCount = 1989 }
2025-09-01 12:27:19.893 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-09-01 12:27:19.893 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}

2025-09-01 12:27:19.893 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refining code...
2025-09-01 12:27:19.893 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-09-01 12:27:19.893 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}

2025-09-01 12:27:25.801 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-09-01 12:27:25.801 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generating code...
2025-09-01 12:27:25.801 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Using prompt:

There is an issue in the following code that needs to be fixed. Provide only the complete, well-formed source class with the corrected parts included, without any explanations:

Remove the import statement: `import org.junit.jupiter.api.extension.MockitoExtension;`


Failing code:
  
  package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
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

2025-09-01 12:27:25.801 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-01 12:27:32.643 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2462, outputTokenCount = 238, totalTokenCount = 2700 }
2025-09-01 12:27:32.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-09-01 12:27:32.643 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}

2025-09-01 12:27:32.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refining code...
2025-09-01 12:27:32.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Done
2025-09-01 12:27:32.644 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.HelloControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}
*/
