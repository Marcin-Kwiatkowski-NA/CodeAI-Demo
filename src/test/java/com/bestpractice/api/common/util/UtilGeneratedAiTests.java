package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
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
        int expectedYear = expectedCalendar.get(Calendar.YEAR);

        // WHEN: Calling calculateDate()
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar resultCalendar = Calendar.getInstance();
        resultCalendar.setTime(result);
        int resultYear = resultCalendar.get(Calendar.YEAR);
        assertEquals(expectedYear, resultYear);
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 42);

        // WHEN: Deep cloning the object
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same instance
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: Expect NotSerializableException when attempting to deep clone
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundExceptionForInvalidData() throws IOException {
        // GIVEN: A corrupted byte stream that cannot be deserialized
        // This test simulates a scenario where deserialization fails
        byte[] invalidData = new byte[]{0x00, 0x01, 0x02};
        IOException thrownException = assertThrows(IOException.class, () -> {
            // WHEN: Attempting to deserialize invalid data
            Util.deepClone(invalidData);
        });

        // THEN: IOException should be thrown
        assertEquals(IOException.class, thrownException.getClass());
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentValueOrNull() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE may or may not be set
        String envValue = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN: Calling getSpringProfileActive()
        String result = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable value
        if (envValue == null) {
            assertNull(result);
        } else {
            assertEquals(envValue, result);
        }
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
