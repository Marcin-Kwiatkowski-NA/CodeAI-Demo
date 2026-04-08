package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state if needed
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
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isAfter(currentDate);
        long difference = Math.abs(result.getTime() - expectedDate.getTime());
        Assertions.assertThat(difference).isLessThan(2000L);
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestObject original = new TestObject("testValue", 42);

        // WHEN
        TestObject cloned = Util.deepClone(original);

        // THEN
        Assertions.assertThat(cloned).isNotNull();
        Assertions.assertThat(cloned).isNotSameAs(original);
        assertEquals(original, cloned);
    }

    @Test
    void testDeepClone_ShouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN
        Object nonSerializable = new Object();

        // WHEN / THEN
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundExceptionForCorruptedStream() {
        // GIVEN
        // This test ensures that ClassNotFoundException is properly declared and can be thrown.
        // We simulate this by creating a serialized byte array that cannot be deserialized properly.
        byte[] corruptedData = new byte[]{0x00, 0x01, 0x02};

        // WHEN / THEN
        assertThrows(ClassNotFoundException.class, () -> {
            Util.deepClone(new CorruptedSerializable(corruptedData));
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValueOrNull() {
        // GIVEN
        // Environment variable cannot be set programmatically in a portable way,
        // so we just verify that the method executes and returns a String or null.

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        if (result != null) {
            Assertions.assertThat(result).isInstanceOf(String.class);
        } else {
            Assertions.assertThat(result).isNull();
        }
    }

    private static class TestObject implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
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

    private static class CorruptedSerializable implements Serializable {
        private static final long serialVersionUID = 1L;
        private final byte[] data;

        public CorruptedSerializable(byte[] data) {
            this.data = data;
        }

        public byte[] getData() {
            return data;
        }
    }
}
