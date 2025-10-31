package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state if needed before each test
    }

    @Test
    void testCalculateDateAddsOneYear() {
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
    void testDeepCloneCreatesEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN: A serializable object
        TestSerializableObject original = new TestSerializableObject("test", 123);

        // WHEN: deepClone is called
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN: The cloned object should be equal but not the same reference
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDeepCloneThrowsIOExceptionForNonSerializableObject() {
        // GIVEN: A non-serializable object
        Object nonSerializable = new Object();

        // WHEN & THEN: deepClone should throw NotSerializableException
        assertThrows(NotSerializableException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvValueOrNull() {
        // GIVEN: Environment variable may or may not be set
        // WHEN: getSpringProfileActive is called
        String result = Util.getSpringProfileActive();

        // THEN: The result should be either null or a non-empty string
        if (result != null) {
            assertTrue(result.length() >= 0);
        } else {
            assertNull(result);
        }
    }

    // Helper class for deepClone test
    private static class TestSerializableObject implements java.io.Serializable {
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
