package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate() {
        // GIVEN: Current date setup
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN: Method is called
        Date resultDate = Util.calculateDate();

        // THEN: Verify the date is correctly calculated
        assertThat(resultDate).isNotNull();
        assertThat(resultDate).isCloseTo(expectedDate, 1000); // Allow slight time differences
    }

    @Test
    void testDeepCloneSuccess() throws IOException, ClassNotFoundException {
        // GIVEN: An object to clone
        String originalObject = "Test String";

        // WHEN: Method is called
        String clonedObject = Util.deepClone(originalObject);

        // THEN: Verify the cloned object is equal but not the same reference
        assertThat(clonedObject).isNotNull();
        assertThat(clonedObject).isEqualTo(originalObject);
        assertThat(clonedObject).isNotSameAs(originalObject);
    }

    @Test
    void testDeepCloneThrowsIOException() {
        // GIVEN: A non-serializable object
        Object nonSerializableObject = new Object();

        // WHEN: Method is called
        // THEN: Verify IOException is thrown
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializableObject));
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: Mocking environment variable
        try (MockedStatic<System> mockedSystem = Mockito.mockStatic(System.class)) {
            mockedSystem.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("test-profile");

            // WHEN: Method is called
            String result = Util.getSpringProfileActive();

            // THEN: Verify the correct profile is returned
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo("test-profile");
        }
    }
}
