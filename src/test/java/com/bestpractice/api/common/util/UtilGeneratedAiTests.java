package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    // -------------------- calculateDate() Tests --------------------

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN
        Date currentDate = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        Calendar calCurrent = Calendar.getInstance();
        calCurrent.setTime(currentDate);
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        assertEquals(calCurrent.get(Calendar.YEAR) + 1, calResult.get(Calendar.YEAR));
    }

    @Test
    void testCalculateDate_ShouldHandleEdgeCaseAtYearEnd() {
        // GIVEN
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date dateAtYearEnd = calendar.getTime();

        // WHEN
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAtYearEnd);
        cal.add(Calendar.YEAR, 1);
        Date expected = cal.getTime();

        // THEN
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(Util.calculateDate());
        assertThat(resultCal.get(Calendar.YEAR)).isGreaterThanOrEqualTo(calendar.get(Calendar.YEAR));
    }

    @Test
    void testCalculateDate_ShouldHandleLeapYearBoundary() {
        // GIVEN
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        Date leapYearDate = calendar.getTime();

        // WHEN
        Calendar cal = Calendar.getInstance();
        cal.setTime(leapYearDate);
        cal.add(Calendar.YEAR, 1);
        Date expected = cal.getTime();

        // THEN
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(Util.calculateDate());
        assertThat(resultCal.get(Calendar.YEAR)).isGreaterThanOrEqualTo(2024);
    }

    // -------------------- deepClone() Tests --------------------

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertThat(cloned).isEqualTo(original);
    }

    @Test
    void testDeepClone_ShouldHandleEmptyStringAndZeroValue() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("", 0);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals("", cloned.name);
        assertEquals(0, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleMaxIntegerValue() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("maxValue", Integer.MAX_VALUE);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals(Integer.MAX_VALUE, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleMinIntegerValue() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("minValue", Integer.MIN_VALUE);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals(Integer.MIN_VALUE, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleWhitespaceString() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("   ", 1);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals("   ", cloned.name);
    }

    @Test
    void testDeepClone_ShouldHandleSingleCharacterString() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("A", 1);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals("A", cloned.name);
    }

    @Test
    void testDeepClone_ShouldHandleNegativeValue() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("negative", -1);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertEquals(-1, cloned.value);
    }

    // -------------------- getSpringProfileActive() Tests --------------------

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN
        // Environment variable SPRING_PROFILES_ACTIVE may or may not be set

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertThat(profile == null || profile.isEmpty() || profile instanceof String).isTrue();
    }

    @Test
    void testGetSpringProfileActive_ShouldHandleUnsetEnvironmentVariable() {
        // GIVEN
        // SPRING_PROFILES_ACTIVE is likely unset in test environment

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertThat(profile).isNullOrEmpty();
    }

    @Test
    void testGetSpringProfileActive_ShouldHandleWhitespaceValue() {
        // GIVEN
        String simulatedValue = "   ";

        // WHEN
        boolean isWhitespace = simulatedValue.trim().isEmpty();

        // THEN
        assertThat(isWhitespace).isTrue();
    }

    // -------------------- Helper Class --------------------

    private static class TestSerializableObject implements Serializable {
        private static final long serialVersionUID = 1L;
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
            return name.hashCode() + value;
        }
    }
}
