package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date and expected date one year ahead
        Date now = new Date();
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(now);
        expectedCalendar.add(Calendar.YEAR, 1);

        // WHEN: Calling calculateDate
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expectedCalendar.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
        assertThat(result).isNotNull();
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN: Deep cloning the object
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: Expect IOException when attempting to clone
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundExceptionForInvalidData() throws IOException {
        // GIVEN: A corrupted byte stream that cannot be deserialized
        byte[] invalidData = new byte[]{0x00, 0x01, 0x02};

        // WHEN & THEN: Expect ClassNotFoundException when attempting to deserialize invalid data
        assertThrows(ClassNotFoundException.class, () -> {
            Util.deepClone(invalidData);
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE may or may not be set
        String envValue = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: Calling getSpringProfileActive
        String result = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable value
        assertEquals(envValue, result);
    }

    private static class TestSerializableObject implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final int value;

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
            return name.hashCode() + value;
        }
    }
}
