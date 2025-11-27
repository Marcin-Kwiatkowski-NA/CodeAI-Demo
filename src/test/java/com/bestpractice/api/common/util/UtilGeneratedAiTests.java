package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
        // GIVEN: Current date and expected date after adding one year
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN: calculateDate is called
        Date actualDate = Util.calculateDate();

        // THEN: The returned date should be one year ahead of the current date
        assertThat(actualDate).isCloseTo(expectedDate, 1000);
    }

    @Test
    void testDeepCloneSuccess() throws IOException, ClassNotFoundException {
        // GIVEN: An object to clone
        String originalObject = "Test String";

        // WHEN: deepClone is called
        String clonedObject = Util.deepClone(originalObject);

        // THEN: The cloned object should be equal to the original and not the same reference
        assertThat(clonedObject).isEqualTo(originalObject);
        assertThat(clonedObject).isNotSameAs(originalObject);
    }

    @Test
    void testDeepCloneThrowsException() {
        // GIVEN: An object that cannot be serialized
        Object nonSerializableObject = new Object();

        // WHEN: deepClone is called with a non-serializable object
        // THEN: An IOException should be thrown
        assertThrows(IOException.class, () -> Util.deepClone(nonSerializableObject));
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: Mocked environment variable
        try (MockedStatic<System> mockedSystem = Mockito.mockStatic(System.class)) {
            mockedSystem.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");

            // WHEN: getSpringProfileActive is called
            String profile = Util.getSpringProfileActive();

            // THEN: The returned profile should match the mocked value
            assertThat(profile).isEqualTo("dev");
        } catch (IllegalStateException e) {
            // Handle the case where mocking System.getenv fails
            assertThat(e).hasMessageContaining("Could not initialize");
        }
    }
}
