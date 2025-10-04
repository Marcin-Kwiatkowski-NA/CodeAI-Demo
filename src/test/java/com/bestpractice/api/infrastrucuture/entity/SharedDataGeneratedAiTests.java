package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 1000);
    }
}

/*
2025-10-03 10:07:10.669 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:07:10.679 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 10:07:10.679 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
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

2025-10-03 10:07:10.679 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:07:14.528 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2317, outputTokenCount = 534, totalTokenCount = 2851 }
2025-10-03 10:07:14.529 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:07:14.529 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }
}
2025-10-03 10:07:14.529 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 10:07:14.529 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:07:14.529 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }
}

2025-10-03 10:08:02.519 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:08:02.520 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 10:08:02.520 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
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

2025-10-03 10:08:02.520 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:08:09.575 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4224, outputTokenCount = 718, totalTokenCount = 4942 }
2025-10-03 10:08:09.575 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:08:09.575 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }
}
2025-10-03 10:08:09.575 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 10:08:09.576 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:08:09.576 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }
}

2025-10-03 10:09:02.060 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-03 10:09:02.060 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 10:09:02.061 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
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

2025-10-03 10:09:02.061 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 10:09:07.533 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6499, outputTokenCount = 804, totalTokenCount = 7303 }
2025-10-03 10:09:07.534 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:09:07.534 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtThrowsExceptionForUnsupportedType() {
        // GIVEN: an unsupported type for createdAt (simulated via casting)
        // WHEN & THEN: setting createdAt with invalid type should throw ClassCastException
        assertThrows(ClassCastException.class, () -> {
            Object invalidDate = new Object();
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}
2025-10-03 10:09:07.534 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 10:09:07.536 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 10:09:07.537 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtThrowsExceptionForUnsupportedType() {
        // GIVEN: an unsupported type for createdAt (simulated via casting)
        // WHEN & THEN: setting createdAt with invalid type should throw ClassCastException
        assertThrows(ClassCastException.class, () -> {
            Object invalidDate = new Object();
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}

2025-10-03 12:31:16.247 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:31:16.248 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 12:31:16.248 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
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

2025-10-03 12:31:16.248 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:31:21.148 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8493, outputTokenCount = 718, totalTokenCount = 9211 }
2025-10-03 12:31:21.148 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:31:21.149 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }
}
2025-10-03 12:31:21.149 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 12:31:21.149 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:31:21.150 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }
}

2025-10-03 12:32:10.255 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:32:10.256 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 12:32:10.256 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
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

2025-10-03 12:32:10.256 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:32:16.180 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 10742, outputTokenCount = 827, totalTokenCount = 11569 }
2025-10-03 12:32:16.181 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:32:16.181 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
    }
}
2025-10-03 12:32:16.181 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 12:32:16.182 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:32:16.182 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
    }
}

2025-10-03 12:33:06.541 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 12:33:06.541 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generating code...
2025-10-03 12:33:06.541 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
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

2025-10-03 12:33:06.542 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 12:33:17.040 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 13209, outputTokenCount = 915, totalTokenCount = 14124 }
2025-10-03 12:33:17.040 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:33:17.040 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
    }

    @Test
    void testSetCreatedAtWithInvalidTypeThrowsClassCastException() {
        // GIVEN: an invalid type for createdAt (simulated via casting)
        Object invalidDate = new Object();

        // WHEN & THEN: setting createdAt with invalid type should throw ClassCastException
        assertThrows(ClassCastException.class, () -> {
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}
2025-10-03 12:33:17.040 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refining code...
2025-10-03 12:33:17.041 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Done
2025-10-03 12:33:17.041 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
    }

    @Test
    void testSetCreatedAtWithInvalidTypeThrowsClassCastException() {
        // GIVEN: an invalid type for createdAt (simulated via casting)
        Object invalidDate = new Object();

        // WHEN & THEN: setting createdAt with invalid type should throw ClassCastException
        assertThrows(ClassCastException.class, () -> {
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}
*/
