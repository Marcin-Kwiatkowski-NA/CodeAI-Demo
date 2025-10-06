package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "TestPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted version
        String rawPassword = "TestPassword123";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted version of a different password
        String rawPassword = "TestPassword123";
        String differentPassword = "DifferentPassword456";
        String encryptedPassword = encryptionComponent.encodePassword(differentPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "TestPassword123";
        String encryptedPassword = null;

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }
}

/*
2025-10-06 14:25:54.130 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:25:54.134 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-10-06 14:25:54.134 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncryptionComponent {
  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncryptionComponent() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String encodePassword(String rawPassword) {
    return this.passwordEncoder.encode(rawPassword);
  }

  public boolean matchedPassword(String rawPassword, String encryptedPassword) {
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnHashedPassword() {
        // GIVEN
        String rawPassword = "testPassword";

        // WHEN
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN
        String rawPassword = "testPassword";
        String encodedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncodedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN
        String rawPassword = "testPassword";
        String encodedPassword = encryptionComponent.encodePassword("differentPassword");

        // WHEN
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullValues_whenMatchedPassword_thenThrowException() {
        // GIVEN
        String rawPassword = null;
        String encodedPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.matchedPassword(rawPassword, encodedPassword));
    }
}

/*
2025-10-03 12:02:20.623 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-03 12:02:20.626 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-10-03 12:02:20.626 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.441 s <<< FAILURE! - in com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests
[ERROR] givenNullEncryptedPassword_whenMatchedPassword_thenThrowException  Time elapsed: 0.022 s  <<< FAILURE!
[ERROR] givenNullRawPassword_whenEncodePassword_thenThrowException  Time elapsed: 0.001 s  <<< FAILURE!
[ERROR] Failures: 
[ERROR]   BCryptPasswordEncryptionComponentGeneratedAiTests.givenNullEncryptedPassword_whenMatchedPassword_thenThrowException:78 Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
[ERROR]   BCryptPasswordEncryptionComponentGeneratedAiTests.givenNullRawPassword_whenEncodePassword_thenThrowException:68 Unexpected exception type thrown ==> expected: <java.lang.NullPointerException> but was: <java.lang.IllegalArgumentException>
[ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.2:test (default-test) on project demo-code-ai: There are test failures.
[ERROR] 
[ERROR] Please refer to C:\Users\mrckw\AppData\Local\Temp\codeai-test-5962228733721397868\target\surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
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

2025-10-03 12:02:20.626 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:02:24.363 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1781, outputTokenCount = 584, totalTokenCount = 2365 }
2025-10-03 12:02:24.364 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-03 12:02:24.364 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
        assertTrue(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }
}
2025-10-03 12:02:24.364 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-10-03 12:02:24.365 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-03 12:02:24.366 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
        assertTrue(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$"));
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }
}
* /


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

2025-10-06 14:25:54.135 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:25:57.915 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4769, outputTokenCount = 615, totalTokenCount = 5384 }
2025-10-06 14:25:57.915 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:25:57.915 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }
}
2025-10-06 14:25:57.915 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-10-06 14:25:57.916 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:25:57.916 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }
}

2025-10-06 14:26:17.813 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:26:17.814 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-10-06 14:26:17.814 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncryptionComponent {
  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncryptionComponent() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String encodePassword(String rawPassword) {
    return this.passwordEncoder.encode(rawPassword);
  }

  public boolean matchedPassword(String rawPassword, String encryptedPassword) {
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
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

2025-10-06 14:26:17.814 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:26:22.747 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6784, outputTokenCount = 709, totalTokenCount = 7493 }
2025-10-06 14:26:22.747 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:26:22.748 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw a NullPointerException because BCryptPasswordEncoder does not accept null
        assertThrows(NullPointerException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because BCryptPasswordEncoder does not accept null encrypted password
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }

    @Test
    void givenNullRawAndEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: null raw password and null encrypted password
        String rawPassword = null;
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because both inputs are null
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}
2025-10-06 14:26:22.748 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-10-06 14:26:22.750 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:26:22.750 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw a NullPointerException because BCryptPasswordEncoder does not accept null
        assertThrows(NullPointerException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because BCryptPasswordEncoder does not accept null encrypted password
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }

    @Test
    void givenNullRawAndEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: null raw password and null encrypted password
        String rawPassword = null;
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because both inputs are null
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}

2025-10-06 14:26:44.376 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:26:44.376 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-10-06 14:26:44.376 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncryptionComponent {
  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncryptionComponent() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String encodePassword(String rawPassword) {
    return this.passwordEncoder.encode(rawPassword);
  }

  public boolean matchedPassword(String rawPassword, String encryptedPassword) {
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN: checking if the raw password matches the null encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
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

2025-10-06 14:26:44.377 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:26:48.694 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8893, outputTokenCount = 709, totalTokenCount = 9602 }
2025-10-06 14:26:48.694 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:26:48.694 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw a NullPointerException because BCryptPasswordEncoder does not accept null
        assertThrows(NullPointerException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because BCryptPasswordEncoder does not accept null encrypted password
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }

    @Test
    void givenNullRawAndEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: null raw password and null encrypted password
        String rawPassword = null;
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because both inputs are null
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}
2025-10-06 14:26:48.694 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-10-06 14:26:48.694 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 14:26:48.695 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "testPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertThat(encryptedPassword).isNotNull();
        assertThat(encryptedPassword).isNotEqualTo(rawPassword);
        assertThat(encryptedPassword.startsWith("$2a$") || encryptedPassword.startsWith("$2b$") || encryptedPassword.startsWith("$2y$")).isTrue();
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted form
        String rawPassword = "securePassword456";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertThat(matches).isTrue();
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted password from a different raw password
        String rawPassword = "passwordOne";
        String differentRawPassword = "passwordTwo";
        String encryptedPassword = encryptionComponent.encodePassword(differentRawPassword);

        // WHEN: checking if the raw password matches the encrypted password
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertThat(matches).isFalse();
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw a NullPointerException because BCryptPasswordEncoder does not accept null
        assertThrows(NullPointerException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "somePassword";
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because BCryptPasswordEncoder does not accept null encrypted password
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }

    @Test
    void givenNullRawAndEncryptedPassword_whenMatchedPassword_thenThrowException() {
        // GIVEN: null raw password and null encrypted password
        String rawPassword = null;
        String encryptedPassword = null;

        // WHEN & THEN: matching should throw a NullPointerException because both inputs are null
        assertThrows(NullPointerException.class, () -> encryptionComponent.matchedPassword(rawPassword, encryptedPassword));
    }
}

2025-10-06 15:59:17.368 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 15:59:17.371 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:63)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generating code...
2025-10-06 15:59:17.371 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:114)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.439 s <<< FAILURE! - in com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests
[ERROR] givenNullEncryptedPassword_whenMatchedPassword_thenThrowException  Time elapsed: 0.015 s  <<< FAILURE!
[ERROR] givenNullRawPassword_whenEncodePassword_thenThrowException  Time elapsed: 0.003 s  <<< FAILURE!
[ERROR] Failures: 
[ERROR]   BCryptPasswordEncryptionComponentGeneratedAiTests.givenNullEncryptedPassword_whenMatchedPassword_thenThrowException:77 Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
[ERROR]   BCryptPasswordEncryptionComponentGeneratedAiTests.givenNullRawPassword_whenEncodePassword_thenThrowException:67 Unexpected exception type thrown ==> expected: <java.lang.NullPointerException> but was: <java.lang.IllegalArgumentException>
[ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.2:test (default-test) on project demo-code-ai: There are test failures.
[ERROR] 
[ERROR] Please refer to C:\Users\mrckw\AppData\Local\Temp\codeai-test-6611502173495608718\target\surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
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

2025-10-06 15:59:17.371 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:118)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 15:59:20.838 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1738, outputTokenCount = 535, totalTokenCount = 2273 }
2025-10-06 15:59:20.839 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:81)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 15:59:20.839 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "TestPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted version
        String rawPassword = "TestPassword123";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted version of a different password
        String rawPassword = "TestPassword123";
        String differentPassword = "DifferentPassword456";
        String encryptedPassword = encryptionComponent.encodePassword(differentPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "TestPassword123";
        String encryptedPassword = null;

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }
}
2025-10-06 15:59:20.839 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:88)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refining code...
2025-10-06 15:59:20.840 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Done
2025-10-06 15:59:20.841 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void givenRawPassword_whenEncodePassword_thenReturnEncryptedPassword() {
        // GIVEN: a raw password
        String rawPassword = "TestPassword123";

        // WHEN: encoding the password
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // THEN: the encrypted password should not be null and should not equal the raw password
        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }

    @Test
    void givenMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnTrue() {
        // GIVEN: a raw password and its encrypted version
        String rawPassword = "TestPassword123";
        String encryptedPassword = encryptionComponent.encodePassword(rawPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be true
        assertTrue(matches);
    }

    @Test
    void givenNonMatchingRawAndEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and an encrypted version of a different password
        String rawPassword = "TestPassword123";
        String differentPassword = "DifferentPassword456";
        String encryptedPassword = encryptionComponent.encodePassword(differentPassword);

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }

    @Test
    void givenNullRawPassword_whenEncodePassword_thenThrowException() {
        // GIVEN: a null raw password
        String rawPassword = null;

        // WHEN & THEN: encoding should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encryptionComponent.encodePassword(rawPassword));
    }

    @Test
    void givenNullEncryptedPassword_whenMatchedPassword_thenReturnFalse() {
        // GIVEN: a raw password and null encrypted password
        String rawPassword = "TestPassword123";
        String encryptedPassword = null;

        // WHEN: checking if they match
        boolean matches = encryptionComponent.matchedPassword(rawPassword, encryptedPassword);

        // THEN: the match result should be false
        assertFalse(matches);
    }
}
*/
