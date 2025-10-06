package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void givenDomainBean_whenPasswordEncoderCalled_thenReturnBCryptPasswordEncoder() {
        // GIVEN
        // DomainBean instance is already set up in @BeforeEach

        // WHEN
        PasswordEncoder encoder = domainBean.passwordEncoder();

        // THEN
        assertNotNull(encoder, "PasswordEncoder should not be null");
        assertTrue(encoder.matches("testPassword", encoder.encode("testPassword")),
                "Encoded password should match the raw password");
    }
}

/*
2025-10-06 14:28:24.402 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:28:24.404 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generating code...
2025-10-06 14:28:24.405 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DomainBean {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
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

2025-10-06 14:28:24.405 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:28:28.290 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1942, outputTokenCount = 609, totalTokenCount = 2551 }
2025-10-06 14:28:28.290 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:28:28.290 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}
2025-10-06 14:28:28.292 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refining code...
2025-10-06 14:28:28.293 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:28:28.293 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}

2025-10-06 14:28:49.381 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:28:49.381 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generating code...
2025-10-06 14:28:49.381 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DomainBean {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
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

2025-10-06 14:28:49.381 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:28:53.273 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3580, outputTokenCount = 609, totalTokenCount = 4189 }
2025-10-06 14:28:53.273 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:28:53.274 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}
2025-10-06 14:28:53.274 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refining code...
2025-10-06 14:28:53.275 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:28:53.275 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}

2025-10-06 14:29:14.663 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:29:14.665 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generating code...
2025-10-06 14:29:14.665 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DomainBean {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
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

2025-10-06 14:29:14.665 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:29:17.942 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5218, outputTokenCount = 609, totalTokenCount = 5827 }
2025-10-06 14:29:17.943 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:29:17.943 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}
2025-10-06 14:29:17.943 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refining code...
2025-10-06 14:29:17.944 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Done
2025-10-06 14:29:17.944 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.DomainBeanGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DomainBeanGeneratedAiTests {

    private DomainBean domainBean;

    @BeforeEach
    void setUp() {
        domainBean = new DomainBean();
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // GIVEN: a DomainBean instance
        // WHEN: calling passwordEncoder method
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // THEN: the returned PasswordEncoder should not be null and should be an instance of BCryptPasswordEncoder
        assertNotNull(encoder);
        assertTrue(encoder instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderFunctionality() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN: encoding a raw password
        String encodedPassword = encoder.encode(rawPassword);
        // THEN: the encoded password should not be null, should not equal the raw password, and should match when verified
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderThrowsExceptionForNullInput() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        // WHEN & THEN: encoding a null password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullEncodedPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String rawPassword = "testPassword";
        // WHEN & THEN: matching with null encoded password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(rawPassword, null));
    }

    @Test
    void testPasswordEncoderMatchesThrowsExceptionForNullRawPassword() {
        // GIVEN: a PasswordEncoder bean from DomainBean
        PasswordEncoder encoder = domainBean.passwordEncoder();
        String encodedPassword = encoder.encode("testPassword");
        // WHEN & THEN: matching with null raw password should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> encoder.matches(null, encodedPassword));
    }
}
*/
