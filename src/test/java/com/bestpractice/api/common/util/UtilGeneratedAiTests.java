package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(PowerMockExtension.class)
@PrepareForTest({System.class})
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        PowerMockito.reset(System.class);
    }

    @Test
    void testCalculateDateAddsOneYear() {
        // GIVEN
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, 1);
        Date expected = calendar.getTime();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertThat(result).isAfterOrEqualTo(expected);
        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);
        assertThat(resultCal.get(Calendar.YEAR)).isEqualTo(calendar.get(Calendar.YEAR));
    }

    @Test
    void testDeepCloneWithSerializableObject() throws IOException, ClassNotFoundException {
        // GIVEN
        SerializableTestObject original = new SerializableTestObject("test", 123);

        // WHEN
        SerializableTestObject clone = Util.deepClone(original);

        // THEN
        assertThat(clone).isNotSameAs(original);
        assertThat(clone).isEqualTo(original);
    }

    @Test
    void testDeepCloneThrowsExceptionForNonSerializableObject() {
        // GIVEN
        NonSerializableObject nonSerializable = new NonSerializableObject();

        // WHEN & THEN
        assertThatThrownBy(() -> Util.deepClone(nonSerializable))
                .isInstanceOf(IOException.class)
                .hasRootCauseInstanceOf(NotSerializableException.class);
    }

    @Test
    void testGetSpringProfileActiveReturnsEnvVariable() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertThat(profile).isEqualTo("dev");
    }

    @Test
    void testGetSpringProfileActiveReturnsNullWhenEnvNotSet() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(null);

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertThat(profile).isNull();
    }

    // Helper classes for testing
    private static class SerializableTestObject implements Serializable {
        private final String name;
        private final int value;

        SerializableTestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() { return name; }
        public int getValue() { return value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SerializableTestObject)) return false;
            SerializableTestObject that = (SerializableTestObject) o;
            return value == that.value && java.util.Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, value);
        }
    }

    private static class NonSerializableObject {
        private String data = "data";
    }
}
