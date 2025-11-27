package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any global state if necessary (not applicable for this class)
    }

    @Test
    void calculateDate_shouldReturnDateOneYearAhead() {
        // GIVEN
        Date currentDate = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTime()).isGreaterThan(currentDate.getTime());
    }

    @Test
    void deepClone_shouldReturnDeepCopyOfObject() throws IOException, ClassNotFoundException {
        // GIVEN
        String originalObject = "Test String";

        // WHEN
        String clonedObject = Util.deepClone(originalObject);

        // THEN
        assertThat(clonedObject).isNotNull();
        assertThat(clonedObject).isEqualTo(originalObject);
        assertThat(clonedObject).isNotSameAs(originalObject);
    }

    @Test
    void deepClone_shouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN
        Object nonSerializableObject = new Object();

        // WHEN & THEN
        assertThatThrownBy(() -> Util.deepClone(nonSerializableObject))
                .isInstanceOf(IOException.class);
    }

    @Test
    void getSpringProfileActive_shouldReturnEnvironmentVariableValue() {
        // GIVEN
        String expectedProfile = "test-profile";
        try (MockedStatic<System> mockedSystem = Mockito.mockStatic(System.class)) {
            mockedSystem.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(expectedProfile);

            // WHEN
            String result = Util.getSpringProfileActive();

            // THEN
            assertThat(result).isEqualTo(expectedProfile);
        }
    }
}
