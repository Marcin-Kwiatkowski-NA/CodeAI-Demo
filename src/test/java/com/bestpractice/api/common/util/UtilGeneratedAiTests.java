package com.bestpractice.api.common.util;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class UtilGeneratedAiTests {

    private Util util;

    @BeforeEach
    void setUp() {
        util = new Util();
    }

    @Test
    void testCalculateDate() {
        // GIVEN
        Date currentDate = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isAfter(currentDate);
    }

    @Test
    void testDeepClone() throws IOException, ClassNotFoundException {
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
    void testGetSpringProfileActive() {
        // GIVEN
        String expectedProfile = "dev";
        try (MockedStatic<Util> mockedStatic = Mockito.mockStatic(Util.class)) {
            mockedStatic.when(Util::getSpringProfileActive).thenReturn(expectedProfile);

            // WHEN
            String result = Util.getSpringProfileActive();

            // THEN
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(expectedProfile);
        }
    }
}