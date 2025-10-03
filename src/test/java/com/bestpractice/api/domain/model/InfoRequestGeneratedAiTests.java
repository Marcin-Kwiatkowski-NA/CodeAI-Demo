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
*/
