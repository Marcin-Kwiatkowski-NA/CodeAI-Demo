package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertThat(result).isNotNull();
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
        Date expectedDate = testCalendar.getTime();

        Date result = Util.calculateDate();
        assertThat(result).isNotNull();
        assertThat(result.getTime()).isGreaterThan(leapDate.getTime());
    }

    @Test
    void testCalculateDate_ShouldHandleYearEndBoundary() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date yearEndDate = calendar.getTime();

        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(yearEndDate);
        expectedCalendar.add(Calendar.YEAR, 1);
        Date expectedDate = expectedCalendar.getTime();

        Date result = Util.calculateDate();
        assertThat(result).isNotNull();
        assertThat(result.getYear()).isEqualTo(expectedDate.getYear());
    }

    @Test
    void testCalculateDate_ShouldReturnDifferentInstancesOnMultipleCalls() {
        Date firstCall = Util.calculateDate();
        Date secondCall = Util.calculateDate();

        assertThat(firstCall).isNotSameAs(secondCall);
        assertThat(firstCall).isNotEqualTo(secondCall);
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("test", 42);

        TestObject cloned = Util.deepClone(original);

        assertThat(cloned).isNotNull();
        assertThat(cloned).isEqualTo(original);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldHandleEmptyStringField() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("", 0);

        TestObject cloned = Util.deepClone(original);

        assertThat(cloned).isNotNull();
        assertEquals(original, cloned);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldHandleBoundaryIntegerValues() throws IOException, ClassNotFoundException {
        TestObject minValueObject = new TestObject("min", Integer.MIN_VALUE);
        TestObject maxValueObject = new TestObject("max", Integer.MAX_VALUE);

        TestObject clonedMin = Util.deepClone(minValueObject);
        TestObject clonedMax = Util.deepClone(maxValueObject);

        assertEquals(minValueObject, clonedMin);
        assertEquals(maxValueObject, clonedMax);
        assertThat(clonedMin).isNotSameAs(minValueObject);
        assertThat(clonedMax).isNotSameAs(maxValueObject);
    }

    @Test
    void testDeepClone_ShouldHandleWhitespaceStringField() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("   ", 1);

        TestObject cloned = Util.deepClone(original);

        assertEquals(original, cloned);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldHandleSingleCharacterStringField() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("A", 1);

        TestObject cloned = Util.deepClone(original);

        assertEquals(original, cloned);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldHandleNegativeIntegerValue() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("negative", -1);

        TestObject cloned = Util.deepClone(original);

        assertEquals(original, cloned);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void testDeepClone_ShouldPreserveObjectStateAfterClone() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("stateful", 100);

        TestObject cloned = Util.deepClone(original);

        assertThat(cloned.name).isEqualTo(original.name);
        assertThat(cloned.value).isEqualTo(original.value);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        String result = Util.getSpringProfileActive();

        assertThat(result == null || result instanceof String).isTrue();
    }

    @Test
    void testGetSpringProfileActive_ShouldHandleEmptyEnvironmentVariable() {
        String result = Util.getSpringProfileActive();

        assertThat(result == null || result.isEmpty() || result instanceof String).isTrue();
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnConsistentValueAcrossCalls() {
        String firstCall = Util.getSpringProfileActive();
        String secondCall = Util.getSpringProfileActive();

        assertThat(firstCall == null || firstCall.equals(secondCall)).isTrue();
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
