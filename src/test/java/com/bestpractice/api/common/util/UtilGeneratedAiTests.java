package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state if necessary
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN
        Date now = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        assertEquals(calNow.get(Calendar.YEAR) + 1, calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestObject original = new TestObject("test", 42);

        // WHEN
        TestObject cloned = Util.deepClone(original);

        // THEN
        assertNotSame(original, cloned);
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
    void testDeepClone_ShouldThrowClassNotFoundExceptionForUnknownClass() throws IOException {
        // GIVEN
        byte[] serializedData;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(new TestObject("test", 1));
            serializedData = baos.toByteArray();
        }

        // WHEN / THEN
        assertThrows(ClassNotFoundException.class, () -> {
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(serializedData);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais) {
                @Override
                protected Class<?> resolveClass(java.io.ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                    throw new ClassNotFoundException("Simulated missing class");
                }
            };
            ois.readObject();
        });
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValueOrNull() {
        // GIVEN
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertEquals(expected, result);
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
