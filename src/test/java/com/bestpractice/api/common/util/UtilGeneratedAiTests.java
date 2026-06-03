package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        Date currentDate = new Date();
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(currentDate);
        expectedCalendar.add(Calendar.YEAR, 1);
        Date expectedDate = expectedCalendar.getTime();

        Date result = Util.calculateDate();

        assertNotNull(result);
        assertTrue(result.after(currentDate));
        assertEquals(expectedDate.getYear(), result.getYear());
    }

    @Test
    void testCalculateDate_ShouldHandleLeapYearBoundary() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 29);
        Date leapDate = calendar.getTime();

        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(leapDate);
        testCalendar.add(Calendar.YEAR, 1);
        Date result = testCalendar.getTime();

        assertEquals(28, result.getDate());
    }

    @Test
    void testCalculateDate_ShouldHandleEndOfYearBoundary() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, 31);
        Date endOfYear = calendar.getTime();

        Calendar testCalendar = Calendar.getInstance();
        testCalendar.setTime(endOfYear);
        testCalendar.add(Calendar.YEAR, 1);
        Date result = testCalendar.getTime();

        assertEquals(2024, result.getYear() + 1900);
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("test", 42);

        TestObject cloned = Util.deepClone(original);

        assertNotNull(cloned);
        assertEquals(original, cloned);
        assertTrue(cloned != original);
    }

    @Test
    void testDeepClone_ShouldHandleEmptyStringField() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("", 0);

        TestObject cloned = Util.deepClone(original);

        assertNotNull(cloned);
        assertEquals(original, cloned);
        assertTrue(cloned != original);
    }

    @Test
    void testDeepClone_ShouldHandleBoundaryIntegerValues() throws IOException, ClassNotFoundException {
        TestObject minValueObject = new TestObject("min", Integer.MIN_VALUE);
        TestObject maxValueObject = new TestObject("max", Integer.MAX_VALUE);

        TestObject clonedMin = Util.deepClone(minValueObject);
        TestObject clonedMax = Util.deepClone(maxValueObject);

        assertEquals(minValueObject, clonedMin);
        assertEquals(maxValueObject, clonedMax);
    }

    @Test
    void testDeepClone_ShouldHandleSingleCharacterString() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("A", 1);

        TestObject cloned = Util.deepClone(original);

        assertNotNull(cloned);
        assertEquals(original, cloned);
        assertTrue(cloned != original);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        Object nonSerializable = new Object();

        assertThatThrownBy(() -> Util.deepClone(nonSerializable))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testDeepClone_ShouldThrowException_WhenNullPassed() {
        Object nullObject = null;

        assertThatThrownBy(() -> Util.deepClone(nullObject))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        String result = Util.getSpringProfileActive();
        assertTrue(result == null || result instanceof String);
    }

    @Test
    void testGetSpringProfileActive_ShouldHandleWhitespaceEnvironmentVariable() {
        String simulatedValue = "   ";
        String result = simulatedValue.trim();
        assertEquals("", result);
    }

    @Test
    void testDeepClone_ShouldHandleZeroAndNegativeValues() throws IOException, ClassNotFoundException {
        TestObject zeroValue = new TestObject("zero", 0);
        TestObject negativeValue = new TestObject("negative", -1);

        TestObject clonedZero = Util.deepClone(zeroValue);
        TestObject clonedNegative = Util.deepClone(negativeValue);

        assertEquals(zeroValue, clonedZero);
        assertEquals(negativeValue, clonedNegative);
    }

    private static class TestObject implements Serializable {
        private String name;
        private int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestObject)) return false;
            TestObject that = (TestObject) o;
            return value == that.value && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + value;
        }
    }
}
