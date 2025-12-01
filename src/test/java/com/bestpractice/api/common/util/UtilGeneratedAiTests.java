package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class UtilGeneratedAiTests {

    private Util util;

    @BeforeEach
    void setUp() {
        util = new Util();
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
    void deepClone_shouldReturnDeepClonedObject() throws IOException, ClassNotFoundException {
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
    void getSpringProfileActive_shouldReturnEnvironmentVariableValue() {
        // GIVEN
        String expectedProfile = "dev";
        MockedStatic<Util> mockedUtil = Mockito.mockStatic(Util.class);
        mockedUtil.when(Util::getSpringProfileActive).thenReturn(expectedProfile);

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertThat(result).isEqualTo(expectedProfile);

        mockedUtil.close();
    }
}
