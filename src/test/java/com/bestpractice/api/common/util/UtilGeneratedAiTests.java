package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import static org.assertj.core.api.Assertions.*;

public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No shared state to reset
    }

    @Test
    void testCalculateDate_incrementsYearByOne() {
        // GIVEN
        Date before = new Date();
        Calendar calBefore = Calendar.getInstance();
        calBefore.setTime(before);
        int yearBefore = calBefore.get(Calendar.YEAR);

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        int yearResult = calResult.get(Calendar.YEAR);
        assertThat(yearResult).isEqualTo(yearBefore + 1);
        assertThat(calResult.get(Calendar.MONTH)).isEqualTo(calBefore.get(Calendar.MONTH));
        assertThat(calResult.get(Calendar.DAY_OF_MONTH)).isEqualTo(calBefore.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testDeepClone_returnsDifferentInstanceWithSameState() throws IOException, ClassNotFoundException {
        // GIVEN
        Date original = new Date();

        // WHEN
        Date cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNotSameAs(original);
        assertThat(cloned).isEqualTo(original);
    }

    @Test
    void testDeepClone_nullInput_returnsNull() throws IOException, ClassNotFoundException {
        // GIVEN
        Object original = null;

        // WHEN
        Object cloned = Util.deepClone(original);

        // THEN
        assertThat(cloned).isNull();
    }

    @Test
    void testGetSpringProfileActive_returnsNullWhenNotSet() {
        // GIVEN
        // Ensure the environment variable is not set (cannot be set programmatically in Java)

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertThat(profile).isNull();
    }
}
