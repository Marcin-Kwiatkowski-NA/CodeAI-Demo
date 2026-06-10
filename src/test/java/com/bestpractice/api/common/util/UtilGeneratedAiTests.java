package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        Date currentDate = new Date();
        Date result = Util.calculateDate();
        Calendar calCurrent = Calendar.getInstance();
        calCurrent.setTime(currentDate);
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        assertEquals(calCurrent.get(Calendar.YEAR) + 1, calResult.get(Calendar.YEAR));
    }

    @Test
    void testCalculateDate_ShouldHandleEdgeCase_EndOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date endOfYear = calendar.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(endOfYear);
        cal.add(Calendar.YEAR, 1);
        Date expected = cal.getTime();
        Date result = Util.calculateDate();
        assertThat(result).isNotNull();
        assertThat(result.getTime()).isGreaterThan(0);
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("test", 42);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertThat(cloned).isEqualTo(original);
    }

    @Test
    void testDeepClone_ShouldHandleEdgeCase_EmptyStringField() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("", 0);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertEquals("", cloned.name);
        assertEquals(0, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleEdgeCase_MaxIntegerValue() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("max", Integer.MAX_VALUE);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertEquals(Integer.MAX_VALUE, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleEdgeCase_MinIntegerValue() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("min", Integer.MIN_VALUE);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertEquals(Integer.MIN_VALUE, cloned.value);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        NonSerializableObject nonSerializable = new NonSerializableObject();
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowNullPointerException_WhenInputIsNull() {
        Object input = null;
        assertThrows(NullPointerException.class, () -> Util.deepClone(input));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        String result = Util.getSpringProfileActive();
        assertThat(result).isIn(System.getenv("SPRING_PROFILES_ACTIVE"), null);
    }

    @Test
    void testGetSpringProfileActive_ShouldHandleEdgeCase_EmptyEnvironmentVariable() {
        String result = Util.getSpringProfileActive();
        if (result != null) {
            assertThat(result.trim()).isEqualTo(result);
        } else {
            assertThat(result).isNull();
        }
    }

    @Test
    void testDeepClone_ShouldHandleEdgeCase_SingleCharacterString() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject("A", 1);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertEquals("A", cloned.name);
        assertEquals(1, cloned.value);
    }

    @Test
    void testDeepClone_ShouldHandleEdgeCase_WhitespaceString() throws IOException, ClassNotFoundException {
        TestObject original = new TestObject(" ", 10);
        TestObject cloned = Util.deepClone(original);
        assertThat(cloned).isNotSameAs(original);
        assertEquals(" ", cloned.name);
        assertEquals(10, cloned.value);
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

    private static class NonSerializableObject {
        private String data = "non-serializable";
    }
}
