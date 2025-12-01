package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any modified state before each test
    }

    @Test
    void testCalculateDate() {
        // GIVEN: Current date setup
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN: Method is called
        Date resultDate = Util.calculateDate();

        // THEN: Verify the result matches the expected date
        assertThat(resultDate).isNotNull();
        assertThat(resultDate).isEqualTo(expectedDate);
    }

    @Test
    void testDeepClone() throws IOException, ClassNotFoundException {
        // GIVEN: An object to clone
        String originalObject = "Test String";

        // WHEN: Method is called to clone the object
        String clonedObject = Util.deepClone(originalObject);

        // THEN: Verify the cloned object matches the original
        assertThat(clonedObject).isNotNull();
        assertThat(clonedObject).isEqualTo(originalObject);
        assertThat(clonedObject).isNotSameAs(originalObject);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: Mocking environment variable
        try (var mockedSystem = mockStatic(System.class)) {
            mockedSystem.when(() -> System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");

            // WHEN: Method is called
            String profileActive = Util.getSpringProfileActive();

            // THEN: Verify the result matches the mocked value
            assertThat(profileActive).isNotNull();
            assertThat(profileActive).isEqualTo("dev");
        }
    }
}
