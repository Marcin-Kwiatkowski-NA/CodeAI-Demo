package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 123);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable value or be null if not set
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements java.io.Serializable {
        private String name;
        private int value;

        public TestSerializableObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return value == that.value && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, value);
        }
    }
}

/*
2025-10-06 14:24:07.094 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:24:07.096 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-06 14:24:07.096 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }

    public static <T> T deepClone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public static String getSpringProfileActive() {
        return System.getenv("SPRING_PROFILES_ACTIVE");
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
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

2025-10-06 14:24:07.096 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:24:11.742 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2753, outputTokenCount = 629, totalTokenCount = 3382 }
2025-10-06 14:24:11.742 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:24:11.743 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException (specifically NotSerializableException)
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }
}
2025-10-06 14:24:11.743 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-10-06 14:24:11.743 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:24:11.743 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException (specifically NotSerializableException)
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }
}

2025-10-06 14:24:30.772 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:24:30.772 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-06 14:24:30.772 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }

    public static <T> T deepClone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public static String getSpringProfileActive() {
        return System.getenv("SPRING_PROFILES_ACTIVE");
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException (specifically NotSerializableException)
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
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

2025-10-06 14:24:30.772 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:24:35.716 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4908, outputTokenCount = 765, totalTokenCount = 5673 }
2025-10-06 14:24:35.717 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:24:35.717 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepCloneThrowsClassNotFoundException() {
        // GIVEN: A serializable object with manipulated stream to cause ClassNotFoundException
        // WHEN & THEN: We simulate by creating a custom ObjectInputStream that throws ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            byte[] invalidData = new byte[]{0, 1, 2, 3};
            Util.deepClone(new InvalidSerializableObject(invalidData));
        });
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }

    private static class InvalidSerializableObject implements Serializable {
        private byte[] data;

        public InvalidSerializableObject(byte[] data) {
            this.data = data;
        }
    }
}
2025-10-06 14:24:35.717 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-10-06 14:24:35.718 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:24:35.718 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepCloneThrowsClassNotFoundException() {
        // GIVEN: A serializable object with manipulated stream to cause ClassNotFoundException
        // WHEN & THEN: We simulate by creating a custom ObjectInputStream that throws ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            byte[] invalidData = new byte[]{0, 1, 2, 3};
            Util.deepClone(new InvalidSerializableObject(invalidData));
        });
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }

    private static class InvalidSerializableObject implements Serializable {
        private byte[] data;

        public InvalidSerializableObject(byte[] data) {
            this.data = data;
        }
    }
}

2025-10-06 14:24:56.076 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-10-06 14:24:56.076 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-06 14:24:56.076 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

>> INPUT JAVA here you can find original code of CLASS:

package com.bestpractice.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }

    public static <T> T deepClone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public static String getSpringProfileActive() {
        return System.getenv("SPRING_PROFILES_ACTIVE");
    }
}


>> TASK: Below is the class with the originally generated tests. Please review the tests and check if exceptions are correctly handled. If methods in the original class can throw exceptions, ensure there are dedicated tests for those scenarios using assertThrows. Add or improve tests to cover exception handling, fix any related compilation errors, and suggest corrections to enhance quality. If there are logical errors in exception testing, address them.


package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException (specifically NotSerializableException)
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
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

2025-10-06 14:24:56.076 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:25:00.721 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7199, outputTokenCount = 751, totalTokenCount = 7950 }
2025-10-06 14:25:00.722 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:25:00.722 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepCloneThrowsClassNotFoundException() {
        // GIVEN: A serializable object that will cause ClassNotFoundException when deserialized
        BrokenSerializableObject brokenObject = new BrokenSerializableObject();

        // WHEN & THEN: deepClone should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> Util.deepClone(brokenObject));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }

    private static class BrokenSerializableObject implements Serializable {
        private Object readResolve() throws ClassNotFoundException {
            throw new ClassNotFoundException("Simulated ClassNotFoundException during deserialization");
        }
    }
}
2025-10-06 14:25:00.723 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-10-06 14:25:00.723 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-06 14:25:00.723 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepCloneThrowsClassNotFoundException() {
        // GIVEN: A serializable object that will cause ClassNotFoundException when deserialized
        BrokenSerializableObject brokenObject = new BrokenSerializableObject();

        // WHEN & THEN: deepClone should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> Util.deepClone(brokenObject));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
        private String value;
        private int number;

        public TestSerializableObject(String value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestSerializableObject)) return false;
            TestSerializableObject that = (TestSerializableObject) o;
            return number == that.number && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode() * 31 + number;
        }
    }

    private static class BrokenSerializableObject implements Serializable {
        private Object readResolve() throws ClassNotFoundException {
            throw new ClassNotFoundException("Simulated ClassNotFoundException during deserialization");
        }
    }
}
*/
