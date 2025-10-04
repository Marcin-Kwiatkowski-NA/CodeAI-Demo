package com.bestpractice.api.common.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        String original = "TestString";

        // WHEN: deepClone is called
        String cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Set environment variable simulation (cannot set real env in Java easily)
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }
}

/*
2025-10-03 11:35:50.384 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:35:50.393 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-03 11:35:50.393 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        String original = "TestString";

        // WHEN: deepClone is called
        String cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Set environment variable simulation (cannot set real env vars in Java easily)
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }
}

/*
2025-09-11 16:51:19.092 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:51:19.102 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar now = Calendar.getInstance();
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE is not set in test environment
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable (may be null if not set)
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2837, outputTokenCount = 622, totalTokenCount = 3459 }
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.137 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:52:12.911 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:52:12.912 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:52:19.152 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4988, outputTokenCount = 848, totalTokenCount = 5836 }
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.153 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:52:19.154 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.154 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:53:11.825 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:53:11.826 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7365, outputTokenCount = 842, totalTokenCount = 8207 }
2025-09-11 16:53:17.374 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:53:17.375 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:53:17.376 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.376 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
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

2025-10-03 11:35:50.396 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:35:51.143 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - AI ERROR - did not generate response
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
2025-10-03 11:35:51.146 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Failed to generate code
2025-10-03 11:35:51.146 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-03 11:35:51.146 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-03 11:36:38.678 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:36:38.679 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-03 11:36:38.679 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        String original = "TestString";

        // WHEN: deepClone is called
        String cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Set environment variable simulation (cannot set real env vars in Java easily)
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }
}

/*
2025-09-11 16:51:19.092 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:51:19.102 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar now = Calendar.getInstance();
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE is not set in test environment
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable (may be null if not set)
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2837, outputTokenCount = 622, totalTokenCount = 3459 }
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.137 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:52:12.911 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:52:12.912 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:52:19.152 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4988, outputTokenCount = 848, totalTokenCount = 5836 }
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.153 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:52:19.154 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.154 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:53:11.825 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:53:11.826 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7365, outputTokenCount = 842, totalTokenCount = 8207 }
2025-09-11 16:53:17.374 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:53:17.375 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:53:17.376 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.376 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
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

2025-10-03 11:36:38.682 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:36:39.527 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - AI ERROR - did not generate response
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
2025-10-03 11:36:39.529 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Failed to generate code
2025-10-03 11:36:39.529 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-03 11:36:39.529 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-03 11:37:25.737 INFO [main] [io.github.adamw7.testing.generator.prompt.ImproveUnitTestsPromptProvider.getPromptMessages(ImproveUnitTestsPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for improving unit tests generation...
2025-10-03 11:37:25.737 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-10-03 11:37:25.739 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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


>> TASK: Below is the class with the originally generated tests, please review the following test class, fix any compilation error, improve the tests,
 and suggest corrections that could enhance their quality. If there are any logical errors or issues with the tests themselves, please address them.


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
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        String original = "TestString";

        // WHEN: deepClone is called
        String cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Set environment variable simulation (cannot set real env vars in Java easily)
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }
}

/*
2025-09-11 16:51:19.092 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:51:19.102 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar now = Calendar.getInstance();
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE is not set in test environment
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable (may be null if not set)
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:51:19.102 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2837, outputTokenCount = 622, totalTokenCount = 3459 }
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.136 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:51:24.136 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:51:24.137 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:52:12.911 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:52:12.911 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:52:12.912 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:52:19.152 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4988, outputTokenCount = 848, totalTokenCount = 5836 }
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.153 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:52:19.153 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:52:19.154 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:52:19.154 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassIsMissing() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException by manipulating stream
        byte[] invalidData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            invalidData = baos.toByteArray();
        }
        // Corrupt the stream to cause ClassNotFoundException
        invalidData[invalidData.length - 1] = 0;

        // WHEN & THEN: Reading invalid data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(invalidData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}

2025-09-11 16:53:11.825 INFO [main] [io.github.adamw7.testing.generator.prompt.ExceptionsHandlingPromptProvider.getPromptMessages(ExceptionsHandlingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Building a prompt for exceptions handling in unit tests...
2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generating code...
2025-09-11 16:53:11.826 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Using prompt:

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

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw IOException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
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

2025-09-11 16:53:11.826 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7365, outputTokenCount = 842, totalTokenCount = 8207 }
2025-09-11 16:53:17.374 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.374 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
2025-09-11 16:53:17.375 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refining code...
2025-09-11 16:53:17.376 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-09-11 16:53:17.376 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any shared state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.YEAR, 1);

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The returned date should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expected.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: deepClone is called
        TestSerializableObject clone = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassDefinitionMissing() throws IOException {
        // GIVEN: Corrupted serialized data to simulate ClassNotFoundException
        byte[] corruptedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestSerializableObject("data", 1));
            corruptedData = baos.toByteArray();
        }
        // Corrupt the data intentionally
        corruptedData[corruptedData.length - 1] = 0;

        // WHEN & THEN: Reading corrupted data should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(corruptedData);
                 java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ois.readObject();
            }
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The returned value should match the environment variable
        assertEquals(expected, profile);
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
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

2025-10-03 11:37:25.741 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-03 11:37:26.590 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - AI ERROR - did not generate response
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
2025-10-03 11:37:26.593 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Failed to generate code
2025-10-03 11:37:26.593 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - Done
2025-10-03 11:37:26.593 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.common.util.UtilGeneratedAiTests.java}] - No code to be used! Generated code is empty
*/
