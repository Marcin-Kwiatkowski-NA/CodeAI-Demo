package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        ObjectId objectId = new ObjectId();
        info.setId(objectId.toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(objectId, entity.getId());
        assertEquals("Info Title", entity.getTitle());
        assertEquals("Info Description", entity.getDescription());
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId objectId = new ObjectId();
        mongoInfoEntity.setId(objectId);
        mongoInfoEntity.setTitle("Entity Title");
        mongoInfoEntity.setDescription("Entity Description");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(objectId.toString(), info.getId());
        assertEquals("Entity Title", info.getTitle());
        assertEquals("Entity Description", info.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId objectId = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(objectId, title, description);

        // THEN
        assertEquals(objectId, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

/*
2025-10-03 10:26:05.344 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:26:05.350 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:26:05.350 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 10:26:05.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:26:11.355 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3440, outputTokenCount = 758, totalTokenCount = 4198 }
2025-10-03 10:26:11.355 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:26:11.355 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 10:26:11.355 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:26:11.356 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:26:11.356 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

2025-10-03 10:27:01.784 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:27:01.785 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:27:01.785 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 10:27:01.785 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:27:06.459 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5903, outputTokenCount = 858, totalTokenCount = 6761 }
2025-10-03 10:27:06.460 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:27:06.460 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 10:27:06.460 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:27:06.461 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:27:06.461 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

2025-10-03 10:28:03.098 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:28:03.099 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 10:28:03.099 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 10:28:03.100 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:28:08.379 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8556, outputTokenCount = 858, totalTokenCount = 9414 }
2025-10-03 10:28:08.379 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:28:08.379 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 10:28:08.380 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 10:28:08.380 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 10:28:08.380 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

2025-10-03 12:47:41.368 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:47:41.369 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:47:41.370 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
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
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 12:47:41.370 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:47:46.354 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 10943, outputTokenCount = 858, totalTokenCount = 11801 }
2025-10-03 12:47:46.354 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:47:46.354 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 12:47:46.354 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:47:46.355 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:47:46.355 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

2025-10-03 12:48:33.222 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:48:33.222 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:48:33.222 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 12:48:33.222 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:48:38.019 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 13570, outputTokenCount = 858, totalTokenCount = 14428 }
2025-10-03 12:48:38.020 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:48:38.020 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 12:48:38.020 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:48:38.021 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:48:38.021 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}

2025-10-03 12:49:25.354 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:49:25.354 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generating code...
2025-10-03 12:49:25.354 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
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

2025-10-03 12:49:25.354 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:49:30.268 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 16197, outputTokenCount = 858, totalTokenCount = 17055 }
2025-10-03 12:49:30.269 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:49:30.269 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
2025-10-03 12:49:30.269 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refining code...
2025-10-03 12:49:30.270 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Done
2025-10-03 12:49:30.270 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntityGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    public void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
        assertEquals(info.getId(), entity.getId().toString());
    }

    @Test
    public void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertFromInfoWithNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    public void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    public void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }
}
*/
