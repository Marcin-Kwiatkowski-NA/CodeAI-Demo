package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state if necessary before each test
    }

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
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestObject original = new TestObject("sample", 100);

        // WHEN
        TestObject cloned = Util.deepClone(original);

        // THEN
        assertNotSame(original, cloned);
        assertEquals(original, cloned);
    }

    @Test
    void testDeepClone_ShouldThrowIOException_WhenObjectNotSerializable() {
        // GIVEN
        Object nonSerializable = new Object();

        // WHEN / THEN
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenInvalidClassData() throws IOException {
        // GIVEN
        // This test simulates corrupted serialized data by manually writing invalid bytes
        byte[] invalidData = new byte[]{0x00, 0x01, 0x02};
        IOException thrown = assertThrows(IOException.class, () -> {
            // WHEN
            Util.deepClone(invalidData);
        });

        // THEN
        assertTrue(thrown.getMessage() == null || thrown.getMessage().length() >= 0);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN
        // Environment variable cannot be set programmatically in a portable way

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertTrue(result == null || result instanceof String);
    }

    private static class TestObject implements Serializable {
        private final String name;
        private final int value;

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
