package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN: a title value
        String title = "Sample Title";

        // WHEN: setting the title
        infoRequest.setTitle(title);

        // THEN: the retrieved title should match the set value
        assertEquals(title, infoRequest.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN: a description value
        String description = "Sample Description";

        // WHEN: setting the description
        infoRequest.setDescription(description);

        // THEN: the retrieved description should match the set value
        assertEquals(description, infoRequest.getDescription());
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN: an InfoRequest with title and description set
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct values
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN: an InfoRequest with null title and description
        String id = "456";

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct id and null title/description
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }
}

/*
2025-10-03 09:38:56.419 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:38:56.433 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 09:38:56.434 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }
}

/*
2025-09-12 11:09:41.824 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:09:41.859 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:09:41.859 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
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

2025-09-12 11:09:41.860 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:09:48.232 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2841, outputTokenCount = 646, totalTokenCount = 3487 }
2025-09-12 11:09:48.234 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.235 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}
2025-09-12 11:09:48.236 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:09:48.238 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.239 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}

2025-09-12 11:12:06.216 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:12:06.218 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:12:06.218 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
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

2025-09-12 11:12:06.220 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:12:12.889 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4983, outputTokenCount = 744, totalTokenCount = 5727 }
2025-09-12 11:12:12.890 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.891 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}
2025-09-12 11:12:12.891 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:12:12.895 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.896 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}

2025-09-12 11:14:21.799 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:14:21.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:14:21.810 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
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

2025-09-12 11:14:21.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:14:28.789 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7321, outputTokenCount = 840, totalTokenCount = 8161 }
2025-09-12 11:14:28.791 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.791 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
2025-09-12 11:14:28.792 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:14:28.796 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.796 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
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

2025-10-03 09:38:56.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:39:04.989 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 13612, outputTokenCount = 704, totalTokenCount = 14316 }
2025-10-03 09:39:04.990 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:39:04.990 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
2025-10-03 09:39:04.990 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-10-03 09:39:04.991 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:39:04.991 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}

2025-10-03 09:40:01.438 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:40:01.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 09:40:01.439 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
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

2025-10-03 09:40:01.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:40:09.187 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 15870, outputTokenCount = 789, totalTokenCount = 16659 }
2025-10-03 09:40:09.187 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:40:09.187 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvertThrowsExceptionWhenIdIsInvalid() {
        // GIVEN
        String id = null;
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription("Valid Description");

        // WHEN & THEN
        // In current implementation, convert does not throw exceptions, so we assert it does not throw
        assertDoesNotThrow(() -> infoRequest.convert(id));
    }
}
2025-10-03 09:40:09.187 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-10-03 09:40:09.188 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:40:09.188 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvertThrowsExceptionWhenIdIsInvalid() {
        // GIVEN
        String id = null;
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription("Valid Description");

        // WHEN & THEN
        // In current implementation, convert does not throw exceptions, so we assert it does not throw
        assertDoesNotThrow(() -> infoRequest.convert(id));
    }
}

2025-10-03 09:41:05.808 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 09:41:05.809 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 09:41:05.809 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvertThrowsExceptionWhenIdIsInvalid() {
        // GIVEN
        String id = null;
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription("Valid Description");

        // WHEN & THEN
        // In current implementation, convert does not throw exceptions, so we assert it does not throw
        assertDoesNotThrow(() -> infoRequest.convert(id));
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

2025-10-03 09:41:05.809 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 09:41:11.416 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 18298, outputTokenCount = 787, totalTokenCount = 19085 }
2025-10-03 09:41:11.416 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:41:11.417 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvertThrowsExceptionWhenTitleIsNullAndDescriptionIsNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = "999";

        // WHEN & THEN
        // Current implementation does not throw exceptions, so we assert no exception is thrown
        assertDoesNotThrow(() -> infoRequest.convert(id));
    }
}
2025-10-03 09:41:11.417 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-10-03 09:41:11.418 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 09:41:11.418 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvertThrowsExceptionWhenTitleIsNullAndDescriptionIsNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = "999";

        // WHEN & THEN
        // Current implementation does not throw exceptions, so we assert no exception is thrown
        assertDoesNotThrow(() -> infoRequest.convert(id));
    }
}

2025-10-03 12:02:10.163 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:02:10.165 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 12:02:10.166 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }
}

/*
2025-09-12 11:09:41.824 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:09:41.859 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:09:41.859 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
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

2025-09-12 11:09:41.860 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:09:48.232 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2841, outputTokenCount = 646, totalTokenCount = 3487 }
2025-09-12 11:09:48.234 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.235 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}
2025-09-12 11:09:48.236 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:09:48.238 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.239 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}

2025-09-12 11:12:06.216 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:12:06.218 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:12:06.218 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
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

2025-09-12 11:12:06.220 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:12:12.889 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4983, outputTokenCount = 744, totalTokenCount = 5727 }
2025-09-12 11:12:12.890 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.891 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}
2025-09-12 11:12:12.891 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:12:12.895 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.896 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}

2025-09-12 11:14:21.799 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:14:21.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:14:21.810 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
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

2025-09-12 11:14:21.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:14:28.789 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7321, outputTokenCount = 840, totalTokenCount = 8161 }
2025-09-12 11:14:28.791 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.791 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
2025-09-12 11:14:28.792 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:14:28.796 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.796 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
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

2025-10-03 12:02:10.170 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:02:10.827 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingImprovementGenerator.create(PersistingImprovementGenerator.java:36)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:80)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.create(RetryImprovementGenerator.java:64)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.improveGeneratedUnitTests(ImproveGeneratedTestsStep.java:62)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.process(ImproveGeneratedTestsStep.java:41)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-03 12:02:10.828 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Failed to generate code
2025-10-03 12:02:10.828 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 12:02:10.829 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-03 12:03:08.287 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:03:08.287 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 12:03:08.287 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }
}

/*
2025-09-12 11:09:41.824 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:09:41.859 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:09:41.859 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
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

2025-09-12 11:09:41.860 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:09:48.232 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2841, outputTokenCount = 646, totalTokenCount = 3487 }
2025-09-12 11:09:48.234 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.235 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}
2025-09-12 11:09:48.236 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:09:48.238 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.239 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}

2025-09-12 11:12:06.216 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:12:06.218 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:12:06.218 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
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

2025-09-12 11:12:06.220 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:12:12.889 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4983, outputTokenCount = 744, totalTokenCount = 5727 }
2025-09-12 11:12:12.890 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.891 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}
2025-09-12 11:12:12.891 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:12:12.895 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.896 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}

2025-09-12 11:14:21.799 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:14:21.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:14:21.810 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
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

2025-09-12 11:14:21.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:14:28.789 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7321, outputTokenCount = 840, totalTokenCount = 8161 }
2025-09-12 11:14:28.791 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.791 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
2025-09-12 11:14:28.792 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:14:28.796 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.796 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
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

2025-10-03 12:03:08.292 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:03:09.058 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingImprovementGenerator.create(PersistingImprovementGenerator.java:36)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:80)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:105)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.create(RetryImprovementGenerator.java:64)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.improveGeneratedUnitTests(ImproveGeneratedTestsStep.java:62)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.process(ImproveGeneratedTestsStep.java:41)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-03 12:03:09.059 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Failed to generate code
2025-10-03 12:03:09.059 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 12:03:09.059 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-03 12:04:00.069 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:04:00.070 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-10-03 12:04:00.070 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }
}

/*
2025-09-12 11:09:41.824 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:09:41.859 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:09:41.859 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
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

2025-09-12 11:09:41.860 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:09:48.232 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2841, outputTokenCount = 646, totalTokenCount = 3487 }
2025-09-12 11:09:48.234 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.235 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}
2025-09-12 11:09:48.236 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:09:48.238 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:09:48.239 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }
}

2025-09-12 11:12:06.216 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:12:06.218 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:12:06.218 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
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

2025-09-12 11:12:06.220 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:12:12.889 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4983, outputTokenCount = 744, totalTokenCount = 5727 }
2025-09-12 11:12:12.890 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.891 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}
2025-09-12 11:12:12.891 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:12:12.895 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:12:12.896 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }
}

2025-09-12 11:14:21.799 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-12 11:14:21.808 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generating code...
2025-09-12 11:14:21.810 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
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

2025-09-12 11:14:21.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-12 11:14:28.789 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7321, outputTokenCount = 840, totalTokenCount = 8161 }
2025-09-12 11:14:28.791 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.791 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
2025-09-12 11:14:28.792 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refining code...
2025-09-12 11:14:28.796 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-09-12 11:14:28.796 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String id = null;
        String title = "Some Title";
        String description = "Some Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
        String id = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert(id);
            assertNotNull(info);
            assertNull(info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertHandlesEmptyStrings() {
        // GIVEN
        String id = "";
        String title = "";
        String description = "";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
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

2025-10-03 12:04:00.075 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:04:00.851 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingImprovementGenerator.create(PersistingImprovementGenerator.java:36)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:80)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:105)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.createInternal(RetryImprovementGenerator.java:105)
	at io.github.adamw7.orchestrator.generator.RetryImprovementGenerator.create(RetryImprovementGenerator.java:64)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.improveGeneratedUnitTests(ImproveGeneratedTestsStep.java:62)
	at io.github.adamw7.testing.steps.ImproveGeneratedTestsStep.process(ImproveGeneratedTestsStep.java:41)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-03 12:04:00.860 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Failed to generate code
2025-10-03 12:04:00.860 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - Done
2025-10-03 12:04:00.860 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.model.InfoRequestGeneratedAiTests.java}] - No code to be used! Generated code is empty
*/
