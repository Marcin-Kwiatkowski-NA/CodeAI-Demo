package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate_ShouldReturnDateOneYearAhead() {
        // GIVEN
        Date currentDate = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertNotNull(result);
        Calendar calCurrent = Calendar.getInstance();
        calCurrent.setTime(currentDate);
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        assertEquals(calCurrent.get(Calendar.YEAR) + 1, calResult.get(Calendar.YEAR));
    }

    @Test
    void testDeepClone_ShouldReturnEqualButDistinctObject() throws IOException, ClassNotFoundException {
        // GIVEN
        TestSerializableObject original = new TestSerializableObject("testValue", 42);

        // WHEN
        TestSerializableObject cloned = Util.deepClone(original);

        // THEN
        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertEquals(original.getName(), cloned.getName());
        assertEquals(original.getValue(), cloned.getValue());
    }

    @Test
    void testDeepClone_ShouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN
        Object nonSerializable = new Object();

        // WHEN / THEN
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializable));
    }

    @Test
    void testDeepClone_ShouldThrowClassNotFoundExceptionForInvalidData() throws IOException {
        // GIVEN
        byte[] invalidData = new byte[]{0x00, 0x01, 0x02};
        // WHEN / THEN
        assertThatThrownBy(() -> {
            Util.deepClone(invalidData);
        }).isInstanceOf(IOException.class);
    }

    @Test
    void testGetSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN
        String expectedProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertEquals(expectedProfile, result);
    }

    private static class TestSerializableObject implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final int value;

        public TestSerializableObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }
}
