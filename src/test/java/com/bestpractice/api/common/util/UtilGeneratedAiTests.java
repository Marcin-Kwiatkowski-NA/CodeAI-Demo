package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        calNow.add(Calendar.YEAR, 1);

        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);

        assertEquals(calNow.get(Calendar.YEAR), calResult.get(Calendar.YEAR));
        assertEquals(calNow.get(Calendar.MONTH), calResult.get(Calendar.MONTH));
        assertEquals(calNow.get(Calendar.DAY_OF_MONTH), calResult.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDifferentObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestObject original = new TestObject("test", 123);

        // WHEN: deepClone is called
        TestObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertNotSame(original, cloned);
        assertEquals(original, cloned);
    }

    @Test
    void testDeepClone_ShouldThrowNotSerializableException_WhenObjectNotSerializable() {
        // GIVEN: A non-serializable object
        NonSerializableObject original = new NonSerializableObject("test");

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(original));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundException_WhenClassNotFound() {
        // GIVEN: A serializable object with manipulated readObject to cause ClassNotFoundException
        SerializableBrokenObject brokenObject = new SerializableBrokenObject();

        // WHEN & THEN: deepClone should throw ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> Util.deepClone(brokenObject));
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvValue() {
        // GIVEN: The environment variable SPRING_PROFILES_ACTIVE is set externally
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile);
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
        private String name;

        public NonSerializableObject(String name) {
            this.name = name;
        }
    }

    private static class SerializableBrokenObject implements Serializable {
        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new ClassNotFoundException("Forced ClassNotFoundException for testing");
        }
    }
}
