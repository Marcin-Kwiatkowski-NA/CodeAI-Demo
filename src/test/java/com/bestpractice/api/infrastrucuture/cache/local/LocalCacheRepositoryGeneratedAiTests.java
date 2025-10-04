package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }
}

/*
2025-10-03 09:56:25.614 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:56:25.619 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 09:56:25.619 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 09:56:25.619 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:56:30.642 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1571, outputTokenCount = 355, totalTokenCount = 1926 }
2025-10-03 09:56:30.642 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:56:30.642 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        // THEN: We assert that no exception is thrown
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }
}
2025-10-03 09:56:30.642 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 09:56:30.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:56:30.643 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        // THEN: We assert that no exception is thrown
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }
}

2025-10-03 09:57:19.742 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:57:19.742 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 09:57:19.742 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        // THEN: We assert that no exception is thrown
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 09:57:19.742 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:57:23.183 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2964, outputTokenCount = 438, totalTokenCount = 3402 }
2025-10-03 09:57:23.183 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:57:23.183 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Assert that creating a new LocalCacheRepository does not throw any exception
        try {
            new LocalCacheRepository();
        } catch (Exception e) {
            throw new AssertionError("Constructor should not throw exception", e);
        }
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: We assert that the instance is created and no exception is thrown
        assertNotNull(instance);
    }
}
2025-10-03 09:57:23.183 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 09:57:23.184 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:57:23.184 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Assert that creating a new LocalCacheRepository does not throw any exception
        try {
            new LocalCacheRepository();
        } catch (Exception e) {
            throw new AssertionError("Constructor should not throw exception", e);
        }
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: We assert that the instance is created and no exception is thrown
        assertNotNull(instance);
    }
}

2025-10-03 09:58:11.477 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:58:11.478 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 09:58:11.478 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Assert that creating a new LocalCacheRepository does not throw any exception
        try {
            new LocalCacheRepository();
        } catch (Exception e) {
            throw new AssertionError("Constructor should not throw exception", e);
        }
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: We assert that the instance is created and no exception is thrown
        assertNotNull(instance);
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 09:58:11.478 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:58:15.937 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4523, outputTokenCount = 510, totalTokenCount = 5033 }
2025-10-03 09:58:15.937 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:58:15.937 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Assert that creating a new LocalCacheRepository does not throw any exception
        try {
            new LocalCacheRepository();
        } catch (Exception e) {
            throw new AssertionError("Constructor should not throw exception", e);
        }
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: We assert that the instance is created and no exception is thrown
        assertNotNull(instance);
    }

    @Test
    void testConstructorThrowsNoExceptionUsingAssertThrows() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Using assertThrows to verify no exception is thrown is not possible directly,
        // so instead we ensure that assertThrows fails if an exception is thrown
        assertNotNull(new LocalCacheRepository());
    }
}
2025-10-03 09:58:15.938 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 09:58:15.938 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 09:58:15.938 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testNoExceptionOnCreation() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = null;
        try {
            instance = new LocalCacheRepository();
        } catch (Exception e) {
            // THEN: Fail if any exception is thrown
            throw new AssertionError("Exception should not be thrown during creation", e);
        }

        // THEN: The instance should be created successfully
        assertNotNull(instance);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Assert that creating a new LocalCacheRepository does not throw any exception
        try {
            new LocalCacheRepository();
        } catch (Exception e) {
            throw new AssertionError("Constructor should not throw exception", e);
        }
    }

    @Test
    void testExceptionScenarioNotApplicable() {
        // GIVEN: The class has no public/protected methods that throw exceptions

        // WHEN: We attempt to simulate an exception scenario
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: We assert that the instance is created and no exception is thrown
        assertNotNull(instance);
    }

    @Test
    void testConstructorThrowsNoExceptionUsingAssertThrows() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Using assertThrows to verify no exception is thrown is not possible directly,
        // so instead we ensure that assertThrows fails if an exception is thrown
        assertNotNull(new LocalCacheRepository());
    }
}

2025-10-03 12:20:26.203 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:20:26.205 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 12:20:26.205 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 12:20:26.205 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:20:30.417 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5850, outputTokenCount = 397, totalTokenCount = 6247 }
2025-10-03 12:20:30.417 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:20:30.417 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Creating a new LocalCacheRepository should not throw any exception
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }

    @Test
    void testConstructorThrowsNoExceptionUsingAssertThrows() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Using assertThrows to verify no exception is thrown is not applicable,
        // so instead we ensure that creating the instance works without throwing
        assertNotNull(new LocalCacheRepository());
    }
}
2025-10-03 12:20:30.417 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 12:20:30.418 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:20:30.418 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Creating a new LocalCacheRepository should not throw any exception
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }

    @Test
    void testConstructorThrowsNoExceptionUsingAssertThrows() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Using assertThrows to verify no exception is thrown is not applicable,
        // so instead we ensure that creating the instance works without throwing
        assertNotNull(new LocalCacheRepository());
    }
}

2025-10-03 12:21:19.522 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:21:19.522 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 12:21:19.522 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Creating a new LocalCacheRepository should not throw any exception
        LocalCacheRepository instance = new LocalCacheRepository();
        assertNotNull(instance);
    }

    @Test
    void testConstructorThrowsNoExceptionUsingAssertThrows() {
        // GIVEN: No special preconditions

        // WHEN & THEN: Using assertThrows to verify no exception is thrown is not applicable,
        // so instead we ensure that creating the instance works without throwing
        assertNotNull(new LocalCacheRepository());
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 12:21:19.522 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:21:23.296 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7301, outputTokenCount = 408, totalTokenCount = 7709 }
2025-10-03 12:21:23.297 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:21:23.297 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: The instance should be created successfully without exceptions
        assertNotNull(instance);
    }

    @Test
    void testNewInstanceIsAlwaysFresh() {
        // GIVEN: A new LocalCacheRepository instance
        LocalCacheRepository firstInstance = new LocalCacheRepository();

        // WHEN: Another new instance is created
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // THEN: They should not be the same reference
        assertEquals(false, firstInstance == secondInstance);
    }
}
2025-10-03 12:21:23.297 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 12:21:23.298 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:21:23.298 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: The instance should be created successfully without exceptions
        assertNotNull(instance);
    }

    @Test
    void testNewInstanceIsAlwaysFresh() {
        // GIVEN: A new LocalCacheRepository instance
        LocalCacheRepository firstInstance = new LocalCacheRepository();

        // WHEN: Another new instance is created
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // THEN: They should not be the same reference
        assertEquals(false, firstInstance == secondInstance);
    }
}

2025-10-03 12:22:10.465 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:22:10.465 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-03 12:22:10.466 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.cache.local;

public class LocalCacheRepository {

}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: The instance should be created successfully without exceptions
        assertNotNull(instance);
    }

    @Test
    void testNewInstanceIsAlwaysFresh() {
        // GIVEN: A new LocalCacheRepository instance
        LocalCacheRepository firstInstance = new LocalCacheRepository();

        // WHEN: Another new instance is created
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // THEN: They should not be the same reference
        assertEquals(false, firstInstance == secondInstance);
    }
}


>> REQUIREMENTS:

1. The response must contain fully functional test code.
2. The response must be in plain text (no code block formatting like '''java ''').
3. Place the generated tests in the SAME PACKAGE as the input JAVA class.
4. Follow this naming convention for the test class: use the original class name and append "GeneratedAiTests".
  - Do not add another "s" if the class name already ends with "s".
  - Do not append "Tests" if the class name ends with "x", "ch", "sh", or "ss".
    Example: `HelloAction` becomes `HelloActionGeneratedAiTests`.
5. Use JUNIT5 for the test framework, MOCKITO for mocking, and ASSERTJ for assertions.
6. Exclude `DisplayName` annotations.
7. Include necessary imports for annotations like `@ExtendWith`.
8. Ensure each test method has at least one assertion.
9. Avoid generating tests for private methods—focus only on public and protected methods.
10. Ensure any modified state in the test is reset before each test with a `@BeforeEach` method.
11. Tests should be independent; no test should rely on the result of another.
12. If no mocks are needed, skip importing mock-related libraries.
13. Organize the test methods using the GIVEN WHEN THEN structure. Each test should begin with a GIVEN section that sets up the necessary preconditions or context, followed by a WHEN section that describes the action being tested, and concluding with a THEN section that specifies the expected outcome. Include comments for each section to clearly indicate their purpose.
14. If error compilation refers to 'reference to assertThat is ambiguous' please do not use org.assertj.core.api.Assertions.assertThat, apart that please use assertEquals(expected, actual) from org.junit.jupiter.api.Assertions.assertEquals
15. Please do not forget about necessary imports
16. Check if the class name matches the requirements, e.g. classWithUnitTests instead of classWithUnitTest
17. If a test fails, check it again to see if it's well written, is assertion correct

# SECURITY REQUIREMENTS:
1. Security Requirements are applicable to the all files, including those that are not security-sensitive
2. If you encounter code that handles security-critical operations, mark it as security-sensitive in the generated code.
3. Don't include any secrets, passwords, API keys, tokens, actual connection strings, authentication details, environment-specific configurations, sensitive configuration values, or personal and sensitive information in the generated code.

2025-10-03 12:22:10.466 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:22:14.087 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8784, outputTokenCount = 513, totalTokenCount = 9297 }
2025-10-03 12:22:14.087 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:22:14.087 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: The instance should be created successfully without exceptions
        assertNotNull(instance);
    }

    @Test
    void testNewInstanceIsAlwaysFresh() {
        // GIVEN: A new LocalCacheRepository instance
        LocalCacheRepository firstInstance = new LocalCacheRepository();

        // WHEN: Another new instance is created
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // THEN: They should not be the same reference
        assertEquals(false, firstInstance == secondInstance);
    }

    @Test
    void testInstancesAreOfSameClassType() {
        // GIVEN: Two instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We get their class types
        Class<?> firstClass = firstInstance.getClass();
        Class<?> secondClass = secondInstance.getClass();

        // THEN: They should be of the same type
        assertEquals(firstClass, secondClass);
    }
}
2025-10-03 12:22:14.087 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-03 12:22:14.088 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Done
2025-10-03 12:22:14.088 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository localCacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        localCacheRepository = new LocalCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: A LocalCacheRepository instance is created in setUp

        // WHEN: We check the instance
        LocalCacheRepository instance = localCacheRepository;

        // THEN: The instance should not be null
        assertNotNull(instance);
    }

    @Test
    void testMultipleInstancesAreIndependent() {
        // GIVEN: Two separate instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We compare their references
        boolean areSameReference = firstInstance == secondInstance;

        // THEN: They should not be the same reference
        assertEquals(false, areSameReference);
    }

    @Test
    void testConstructorDoesNotThrowException() {
        // GIVEN: No special preconditions

        // WHEN: Creating a new LocalCacheRepository
        LocalCacheRepository instance = new LocalCacheRepository();

        // THEN: The instance should be created successfully without exceptions
        assertNotNull(instance);
    }

    @Test
    void testNewInstanceIsAlwaysFresh() {
        // GIVEN: A new LocalCacheRepository instance
        LocalCacheRepository firstInstance = new LocalCacheRepository();

        // WHEN: Another new instance is created
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // THEN: They should not be the same reference
        assertEquals(false, firstInstance == secondInstance);
    }

    @Test
    void testInstancesAreOfSameClassType() {
        // GIVEN: Two instances of LocalCacheRepository
        LocalCacheRepository firstInstance = new LocalCacheRepository();
        LocalCacheRepository secondInstance = new LocalCacheRepository();

        // WHEN: We get their class types
        Class<?> firstClass = firstInstance.getClass();
        Class<?> secondClass = secondInstance.getClass();

        // THEN: They should be of the same type
        assertEquals(firstClass, secondClass);
    }
}
*/
