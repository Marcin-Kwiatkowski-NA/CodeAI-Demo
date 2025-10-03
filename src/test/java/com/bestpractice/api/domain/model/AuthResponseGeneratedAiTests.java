package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void testGetTokenType() {
        // GIVEN: an AuthResponse instance with a specific token type
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the token type
        String result = authResponse.getTokenType();

        // THEN: the token type should match the expected value
        assertEquals(tokenType, result);
    }

    @Test
    void testGetToken() {
        // GIVEN: an AuthResponse instance with a specific token
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the token
        String result = authResponse.getToken();

        // THEN: the token should match the expected value
        assertEquals(token, result);
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN: an AuthResponse instance with a specific refresh token
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the refresh token
        String result = authResponse.getRefreshToken();

        // THEN: the refresh token should match the expected value
        assertEquals(refreshToken, result);
    }

    @Test
    void testGetExpiresAt() {
        // GIVEN: an AuthResponse instance with a specific expiration date
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the expiration date
        Date result = authResponse.getExpiresAt();

        // THEN: the expiration date should match the expected value
        assertEquals(expiresAt, result);
    }

    @Test
    void testExpiresAtIsNull() {
        // GIVEN: an AuthResponse instance with null expiration date
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN: retrieving the expiration date
        Date result = authResponse.getExpiresAt();

        // THEN: the expiration date should be null
        assertNull(result);
    }
}

/*
2025-09-12 10:46:15.989 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 10:46:16.022 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generating code...
2025-09-12 10:46:16.023 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class AuthResponse {

  @NotNull
  @JsonProperty("token_type")
  private final String tokenType;

  @NotNull
  @JsonProperty("token")
  private final String token;

  @NotNull
  @JsonProperty("refresh_token")
  private final String refreshToken;

  @JsonProperty("expired_at")
  private final Date expiresAt;

  public AuthResponse(String tokenType, String token, String refreshToken, Date expiresAt) {
    this.tokenType = tokenType;
    this.token = token;
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
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

2025-09-12 10:46:16.024 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 10:46:26.405 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2643, outputTokenCount = 722, totalTokenCount = 3365 }
2025-09-12 10:46:26.406 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:46:26.407 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}
2025-09-12 10:46:26.408 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refining code...
2025-09-12 10:46:26.410 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:46:26.411 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}

2025-09-12 10:48:32.773 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 10:48:32.775 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generating code...
2025-09-12 10:48:32.776 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class AuthResponse {

  @NotNull
  @JsonProperty("token_type")
  private final String tokenType;

  @NotNull
  @JsonProperty("token")
  private final String token;

  @NotNull
  @JsonProperty("refresh_token")
  private final String refreshToken;

  @JsonProperty("expired_at")
  private final Date expiresAt;

  public AuthResponse(String tokenType, String token, String refreshToken, Date expiresAt) {
    this.tokenType = tokenType;
    this.token = token;
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenNoExceptionThrownAndValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
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

2025-09-12 10:48:32.777 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 10:48:39.853 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4969, outputTokenCount = 700, totalTokenCount = 5669 }
2025-09-12 10:48:39.854 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:48:39.854 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}
2025-09-12 10:48:39.855 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refining code...
2025-09-12 10:48:39.858 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:48:39.859 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}

2025-09-12 10:51:06.382 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 10:51:06.384 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generating code...
2025-09-12 10:51:06.384 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class AuthResponse {

  @NotNull
  @JsonProperty("token_type")
  private final String tokenType;

  @NotNull
  @JsonProperty("token")
  private final String token;

  @NotNull
  @JsonProperty("refresh_token")
  private final String refreshToken;

  @JsonProperty("expired_at")
  private final Date expiresAt;

  public AuthResponse(String tokenType, String token, String refreshToken, Date expiresAt) {
    this.tokenType = tokenType;
    this.token = token;
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
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

2025-09-12 10:51:06.385 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 10:51:12.471 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7261, outputTokenCount = 700, totalTokenCount = 7961 }
2025-09-12 10:51:12.472 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:51:12.472 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}
2025-09-12 10:51:12.473 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refining code...
2025-09-12 10:51:12.476 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Done
2025-09-12 10:51:12.476 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.AuthResponseGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidConstructorArguments_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidConstructorArguments_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertSame(expiresAt, result);
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNull(result);
    }

    @Test
    void givenNullTokenType_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenCreatingAuthResponse_thenValueIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertNull(authResponse.getRefreshToken());
    }
}
*/
