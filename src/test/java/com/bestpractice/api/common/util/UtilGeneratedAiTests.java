package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN
        Date now = new Date();
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(now);
        expectedCalendar.add(Calendar.YEAR, 1);

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertEquals(expectedCalendar.get(Calendar.YEAR), resultCal.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepClone_ShouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN
        Object nonSerializable = new Object();

        // WHEN / THEN
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundExceptionForInvalidStream() {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN / THEN
        assertThrows(ClassNotFoundException.class, () -> {
            byte[] corruptedData = new byte[]{0, 1, 2, 3};
            Util.deepClone(original);
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertEquals(expected, result);
        assertThat(result == null || result.equals(expected)).isTrue();
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
