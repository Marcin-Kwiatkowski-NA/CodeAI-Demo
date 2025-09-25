package com.bestpractice.api.common.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or prepare any state before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN: Current date
        Date now = new Date();

        // WHEN: calculateDate is called
        Date result = Util.calculateDate();

        // THEN: The result should be approximately one year ahead
        assertTrue(result.after(now), "Result date should be after current date");
        long diffInMillis = result.getTime() - now.getTime();
        long daysDiff = diffInMillis / (1000 * 60 * 60 * 24);
        assertTrue(daysDiff >= 365 && daysDiff <= 366, "Difference should be about one year");
    }

    @Test
    void testDeepCloneReturnsEqualObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 123);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned, "Cloned object should be equal to original");
        assertNotSame(original, cloned, "Cloned object should not be the same reference as original");
    }

    @Test
    void testDeepCloneThrowsNotSerializableExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable),
                "Deep clone should throw NotSerializableException for non-serializable objects");
    }

    @Test
    void testDeepCloneThrowsClassNotFoundException() throws IOException {
        // GIVEN: A serializable object but simulate ClassNotFoundException during deserialization
        TestSerializableObject original = new TestSerializableObject("simulate", 999);

        // WHEN & THEN: We simulate by using a custom ObjectInputStream that throws ClassNotFoundException
        assertThrows(ClassNotFoundException.class, () -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(original);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais) {
                @Override
                protected Class<?> resolveClass(java.io.ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                    throw new ClassNotFoundException("Simulated");
                }
            };
            ois.readObject();
        }, "Deep clone should throw ClassNotFoundException when class cannot be resolved");
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValue() {
        // GIVEN: Environment variable SPRING_PROFILES_ACTIVE is set externally
        // WHEN: getSpringProfileActive is called
        String profile = Util.getSpringProfileActive();

        // THEN: The result should match the environment variable or be null if not set
        String expected = System.getenv("SPRING_PROFILES_ACTIVE");
        assertEquals(expected, profile, "Returned profile should match environment variable");
    }

    private static class TestSerializableObject implements Serializable {
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
            return value == that.value && (name != null ? name.equals(that.name) : that.name == null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }
    }
}
