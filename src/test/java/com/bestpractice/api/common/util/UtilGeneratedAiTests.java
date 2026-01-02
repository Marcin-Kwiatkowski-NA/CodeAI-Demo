package com.bestpractice.api.common.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.assertj.core.api.Assertions;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;

@ExtendWith(PowerMockExtension.class)
@PrepareForTest({System.class})
public class UtilGeneratedAiTests {

    @BeforeEach
    void resetMocks() {
        PowerMockito.reset(System.class);
    }

    @Test
    void calculateDate_ShouldReturnDateOneYearLater() {
        // GIVEN
        Date original = new Date();

        // WHEN
        Date result = Util.calculateDate();

        // THEN
        Assertions.assertThat(result).isAfter(original);
        Calendar cal = Calendar.getInstance();
        cal.setTime(original);
        cal.add(Calendar.YEAR, 1);
        Date expected = cal.getTime();
        Assertions.assertThat(result).isEqualToIgnoringHours(expected);
    }

    @Test
    void deepClone_ShouldReturnDeepCopyOfSerializableObject() throws Exception {
        // GIVEN
        TestBean original = new TestBean(1, "test");

        // WHEN
        TestBean clone = Util.deepClone(original);

        // THEN
        Assertions.assertThat(clone).isNotSameAs(original);
        Assertions.assertThat(clone.getId()).isEqualTo(original.getId());
        Assertions.assertThat(clone.getName()).isEqualTo(original.getName());
    }

    @Test
    void deepClone_ShouldReturnNullWhenInputIsNull() throws Exception {
        // GIVEN
        Object input = null;

        // WHEN
        Object result = Util.deepClone(input);

        // THEN
        Assertions.assertThat(result).isNull();
    }

    @Test
    void deepClone_ShouldThrowNotSerializableExceptionWhenObjectIsNotSerializable() {
        // GIVEN
        Object nonSerializable = new Object(); // Object is not serializable

        // WHEN
        Assertions.assertThatThrownBy(() -> Util.deepClone(nonSerializable))
                .isInstanceOf(NotSerializableException.class);
    }

    @Test
    void getSpringProfileActive_ShouldReturnEnvironmentVariableValue() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn("dev");

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        Assertions.assertThat(profile).isEqualTo("dev");
    }

    @Test
    void getSpringProfileActive_ShouldReturnNullWhenEnvironmentVariableNotSet() {
        // GIVEN
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv("SPRING_PROFILES_ACTIVE")).thenReturn(null);

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        Assertions.assertThat(profile).isNull();
    }

    private static class TestBean implements Serializable {
        private static final long serialVersionUID = 1L;
        private final int id;
        private final String name;

        TestBean(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }
    }
}
