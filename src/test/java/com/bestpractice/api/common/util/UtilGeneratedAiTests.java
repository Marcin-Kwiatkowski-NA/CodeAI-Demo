package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No need to mock System.getenv as it is accessible directly
    }

    @Test
    void calculateDate_shouldReturnDateOneYearAhead() {
        // GIVEN
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertThat(result).isCloseTo(expectedDate, 1000); // Allowing a 1-second margin for execution time
    }

    @Test
    void deepClone_shouldReturnDeepClonedObject() throws IOException, ClassNotFoundException {
        // GIVEN
        String originalObject = "Test String";

        // WHEN
        String clonedObject = Util.deepClone(originalObject);

        // THEN
        assertThat(clonedObject).isNotSameAs(originalObject);
        assertThat(clonedObject).isEqualTo(originalObject);
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
        String expectedProfile = "dev";
        try (MockedStatic<System> mockedStatic = Mockito.mockStatic(System.class)) {
            mockedStatic.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(expectedProfile);

            // WHEN
            String result = Util.getSpringProfileActive();

            // THEN
            assertThat(result).isEqualTo(expectedProfile);
        }
    }

    @Test
    void getSpringProfileActive_shouldReturnNullIfEnvironmentVariableNotSet() {
        // GIVEN
        try (MockedStatic<System> mockedStatic = Mockito.mockStatic(System.class)) {
            mockedStatic.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(null);

            // WHEN
            String result = Util.getSpringProfileActive();

            // THEN
            assertThat(result).isNull();
        }
    }
}
