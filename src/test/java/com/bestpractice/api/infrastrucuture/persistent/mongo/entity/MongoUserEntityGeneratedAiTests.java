package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "username", "email@example.com", "password");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
        assertEquals(new ObjectId(user.getId()), entity.getId());
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "username";
        String email = "email@example.com";
        String password = "password";
        mongoUserEntity = new MongoUserEntity(id, username, email, password);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }
}

/*
2025-10-03 10:29:23.429 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:29:23.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:29:23.439 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
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

2025-10-03 10:29:23.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:29:29.407 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3737, outputTokenCount = 797, totalTokenCount = 4534 }
2025-10-03 10:29:29.407 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:29:29.408 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
2025-10-03 10:29:29.408 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:29:29.409 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:29:29.409 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}

2025-10-03 10:30:46.610 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:30:46.611 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:30:46.611 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
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

2025-10-03 10:30:46.611 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:30:52.122 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6316, outputTokenCount = 864, totalTokenCount = 7180 }
2025-10-03 10:30:52.122 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:30:52.122 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }
}
2025-10-03 10:30:52.122 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:30:52.123 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:30:52.123 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }
}

2025-10-03 10:31:46.855 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:31:46.857 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:31:46.857 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
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

2025-10-03 10:31:46.857 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:31:55.079 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 9029, outputTokenCount = 864, totalTokenCount = 9893 }
2025-10-03 10:31:55.079 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:31:55.079 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
2025-10-03 10:31:55.080 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:31:55.080 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 10:31:55.080 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}

2025-10-03 12:50:17.567 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:50:17.568 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:50:17.568 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
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

2025-10-03 12:50:17.568 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:50:23.206 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 11521, outputTokenCount = 864, totalTokenCount = 12385 }
2025-10-03 12:50:23.206 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:50:23.206 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
2025-10-03 12:50:23.206 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:50:23.207 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:50:23.207 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}

2025-10-03 12:51:12.111 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:51:12.111 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:51:12.112 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
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

2025-10-03 12:51:12.112 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:51:16.864 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 14208, outputTokenCount = 864, totalTokenCount = 15072 }
2025-10-03 12:51:16.864 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:51:16.864 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
2025-10-03 12:51:16.864 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:51:16.865 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:51:16.865 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}

2025-10-03 12:52:04.139 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:52:04.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:52:04.140 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
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

2025-10-03 12:52:04.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:52:13.729 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 16895, outputTokenCount = 864, totalTokenCount = 17759 }
2025-10-03 12:52:13.729 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:52:13.729 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
2025-10-03 12:52:13.729 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:52:13.730 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Done
2025-10-03 12:52:13.730 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
*/
