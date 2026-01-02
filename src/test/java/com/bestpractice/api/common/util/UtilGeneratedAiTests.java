package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.assertj.core.api.Assertions;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;

@ExtendWith(PowerMockExtension.class)
@PrepareForTest({System.class})
public class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        PowerMockito.reset(System.class);
    }

    @Test
    void calculateDate_shouldReturnDateOneYearLater() {
        // GIVEN
        Date now = new Date();
        // WHEN
        Date result = Util.calculateDate();
        // THEN
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        Calendar calResult = Calendar.getInstance();
        calResult.setTime(result);
        Assertions.assertThat(calResult.get(Calendar.YEAR))
                .isEqualTo(calNow.get(Calendar.YEAR) + 1);
        Assertions.assertThat(calResult.get(Calendar.DAY_OF_YEAR))
                .isEqualTo(calNow.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    void deepClone_shouldReturnEqualButDifferentInstance() throws IOException, ClassNotFoundException {
        // GIVEN
        Date original = new Date();
        // WHEN
        Date clone = Util.deepClone(original);
        // THEN
        Assertions.assertThat(clone).isNotSameAs(original);
        Assertions.assertThat(clone).isEqualTo(original);
    }

    @Test
    void deepClone_shouldThrowExceptionForNonSerializableObject() {
        // GIVEN
        NonSerializable obj = new NonSerializable("test");
        // WHEN
        Assertions.assertThatThrownBy(() -> Util.deepClone(obj))
                .isInstanceOf(IOException.class);
    }

    @Test
    void getSpringProfileActive_shouldReturnEnvironmentVariable() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");
        // WHEN
        String profile = Util.getSpringProfileActive();
        // THEN
        Assertions.assertThat(profile).isEqualTo("dev");
    }

    @Test
    void getSpringProfileActive_shouldReturnNullWhenEnvNotSet() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(null);
        // WHEN
        String profile = Util.getSpringProfileActive();
        // THEN
        Assertions.assertThat(profile).isNull();
    }

    private static class NonSerializable {
        private String value;
        NonSerializable(String value) {
            this.value = value;
        }
    }
}
