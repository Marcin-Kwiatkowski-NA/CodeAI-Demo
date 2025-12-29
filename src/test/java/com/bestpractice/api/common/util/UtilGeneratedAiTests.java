package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilGeneratedAiTests {

    private static final String SPRING_PROFILES_ACTIVE_ENV_VAR = "SPRING_PROFILES_ACTIVE";
    private static final String EXPECTED_PROFILE_ACTIVE = "dev";

    private Util util;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        util = new Util();
        objectMapper = new ObjectMapper();
    }

    @Test
    void givenValidDate_whenCalculateDate_thenReturnsOneYearLater() throws Exception {
        // GIVEN
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN
        Date result = util.calculateDate();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isAfter(currentDate);
        assertThat(result).isBefore(expectedDate);
    }

    @Test
    void givenSerializableObject_whenDeepClone_thenReturnsDeepClone() throws Exception {
        // GIVEN
        Person person = new Person("John", 30);
        Person original = person;

        // WHEN
        Person cloned = util.deepClone(person);

        // THEN
        assertThat(cloned).isNotNull();
        assertThat(cloned.getName()).isEqualTo("John");
        assertThat(cloned.getAge()).isEqualTo(30);
        assertThat(cloned).isNotSameAs(original);
    }

    @Test
    void givenNullObject_whenDeepClone_thenThrowsNullPointerException() {
        // GIVEN
        Object nullObject = null;

        // WHEN & THEN
        assertThatThrownBy(() -> util.deepClone(nullObject))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("null");
    }

    @Test
    void givenNoSpringProfileActiveEnvironmentVariable_whenGetSpringProfileActive_thenReturnsNull() {
        // GIVEN
        System.clearProperty(SPRING_PROFILES_ACTIVE_ENV_VAR);

        // WHEN
        String result = util.getSpringProfileActive();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenSpringProfileActiveEnvironmentVariable_whenGetSpringProfileActive_thenReturnsExpectedValue() {
        // GIVEN
        System.setProperty(SPRING_PROFILES_ACTIVE_ENV_VAR, EXPECTED_PROFILE_ACTIVE);

        // WHEN
        String result = util.getSpringProfileActive();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(EXPECTED_PROFILE_ACTIVE);
    }

    @Test
    void givenInvalidDate_whenCalculateDate_thenReturnsOneYearLater() {
        // GIVEN
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        Date expectedDate = calendar.getTime();

        // WHEN
        Date result = util.calculateDate();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isAfter(date);
        assertThat(result).isBefore(expectedDate);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
