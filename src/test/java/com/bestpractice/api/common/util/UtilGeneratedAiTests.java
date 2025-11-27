package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate() {
        // GIVEN: Current date and expected behavior
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN: Calling calculateDate method
        Date actualDate = Util.calculateDate();

        // THEN: Verify the returned date is one year ahead
        assertThat(actualDate).isCloseTo(expectedDate, 1000);
    }

    @Test
    void testDeepCloneWithValidObject() throws IOException, ClassNotFoundException {
        // GIVEN: A valid object to clone
        String originalObject = "Test String";

        // WHEN: Calling deepClone method
        String clonedObject = Util.deepClone(originalObject);

        // THEN: Verify the cloned object is equal but not the same reference
        assertThat(clonedObject).isEqualTo(originalObject);
        assertThat(clonedObject).isNotSameAs(originalObject);
    }

    @Test
    void testDeepCloneWithNullObject() {
        // GIVEN: A null object
        Object originalObject = null;

        // WHEN: Calling deepClone method
        // THEN: Verify that an exception is thrown
        assertThatThrownBy(() -> Util.deepClone(originalObject)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: Mocking environment variable
        try (MockedStatic<System> mockedSystem = Mockito.mockStatic(System.class)) {
            mockedSystem.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");

            // WHEN: Calling getSpringProfileActive method
            String profile = Util.getSpringProfileActive();

            // THEN: Verify the returned profile matches the mocked value
            assertThat(profile).isEqualTo("dev");
        }
    }
}
