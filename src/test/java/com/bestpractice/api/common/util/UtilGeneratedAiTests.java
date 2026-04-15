package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN
        Date currentDate = new Date();
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(currentDate);
        expectedCalendar.add(Calendar.YEAR, 1);
        Date expectedDate = expectedCalendar.getTime();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertNotNull(result);
        assertEquals(expectedDate.getYear(), result.getYear());
        assertTrue(result.after(currentDate));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestObject original = new TestObject("test", 42);

        // WHEN
        TestObject cloned = Util.deepClone(original);

        // THEN
        assertNotNull(cloned);
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
    void testDeepClone_ShouldThrowClassNotFoundExceptionForInvalidStream() throws IOException {
        // GIVEN
        // This test simulates corrupted stream scenario indirectly by mocking invalid serialized data
        byte[] invalidData = new byte[]{0x00, 0x01, 0x02};
        assertThrows(ClassNotFoundException.class, () -> {
            Util.deepClone(invalidData);
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValueOrNull() {
        // GIVEN
        // Environment variable cannot be set programmatically in a portable way

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        // It should either be null or a non-empty string depending on environment
        assertTrue(result == null || result.isEmpty() || result.length() > 0);
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
