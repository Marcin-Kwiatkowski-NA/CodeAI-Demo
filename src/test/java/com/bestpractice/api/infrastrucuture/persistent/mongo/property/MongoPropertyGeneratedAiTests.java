package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN
        String expectedPlatformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "testUser";

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // Security-sensitive

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

/*
2025-10-03 10:37:28.744 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:37:28.754 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 10:37:28.754 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 10:37:28.754 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:37:32.556 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2825, outputTokenCount = 528, totalTokenCount = 3353 }
2025-10-03 10:37:32.556 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:37:32.557 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 10:37:32.557 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 10:37:32.557 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:37:32.557 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

2025-10-03 10:38:33.378 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:38:33.378 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 10:38:33.379 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 10:38:33.379 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:38:37.052 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4823, outputTokenCount = 528, totalTokenCount = 5351 }
2025-10-03 10:38:37.052 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:38:37.052 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 10:38:37.052 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 10:38:37.053 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:38:37.053 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

2025-10-03 10:39:34.383 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:39:34.384 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 10:39:34.384 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 10:39:34.384 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:39:37.402 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6821, outputTokenCount = 528, totalTokenCount = 7349 }
2025-10-03 10:39:37.402 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:39:37.402 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 10:39:37.402 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 10:39:37.403 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 10:39:37.403 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

2025-10-03 12:56:25.883 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:56:25.885 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 12:56:25.885 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 12:56:25.885 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:56:29.940 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8793, outputTokenCount = 528, totalTokenCount = 9321 }
2025-10-03 12:56:29.940 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:56:29.940 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 12:56:29.941 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 12:56:29.941 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:56:29.941 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

2025-10-03 12:57:17.699 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:57:17.699 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 12:57:17.699 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 12:57:17.699 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:57:21.211 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 10765, outputTokenCount = 528, totalTokenCount = 11293 }
2025-10-03 12:57:21.211 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:57:21.211 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 12:57:21.211 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 12:57:21.212 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:57:21.212 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}

2025-10-03 12:58:08.112 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:58:08.112 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generating code...
2025-10-03 12:58:08.112 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongo")
public class MongoProperty {
    private String host;
    private int port;
    private String authDatabase;
    private String platformDatabase;
    private String user;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthDatabase() {
        return authDatabase;
    }

    public void setAuthDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
    }

    public String getPlatformDatabase() {
        return platformDatabase;
    }

    public void setPlatformDatabase(String platformDatabase) {
        this.platformDatabase = platformDatabase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
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

2025-10-03 12:58:08.112 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:58:11.708 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 12737, outputTokenCount = 528, totalTokenCount = 13265 }
2025-10-03 12:58:11.708 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:58:11.708 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
2025-10-03 12:58:11.709 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refining code...
2025-10-03 12:58:11.709 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Done
2025-10-03 12:58:11.709 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoPropertyGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the retrieved auth database should match the expected value
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the retrieved platform database should match the expected value
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a user value
        String expectedUser = "mongoUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the retrieved user should match the expected value
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
*/
