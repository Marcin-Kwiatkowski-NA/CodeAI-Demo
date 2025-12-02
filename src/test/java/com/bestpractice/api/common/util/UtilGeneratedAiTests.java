package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UtilGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any global state if necessary (not applicable for this class)
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
    void deepClone_shouldThrowIOExceptionForNonSerializableObject() {
        // GIVEN
        Object nonSerializableObject = new Object();

        // WHEN & THEN
        assertThatThrownBy(() -> Util.deepClone(nonSerializableObject))
                .isInstanceOf(IOException.class);
    }

    @Test
    void getSpringProfileActive_shouldReturnEnvironmentVariableValue() {
        // GIVEN
        String expectedProfile = "test-profile";
        System.setProperty("SPRING_PROFILES_ACTIVE", expectedProfile);

        // WHEN
        String result = Util.getSpringProfileActive();

        // THEN
        assertThat(result).isEqualTo(expectedProfile);

        // Cleanup
        System.clearProperty("SPRING_PROFILES_ACTIVE");
    }
}

package com.bestpractice.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }

    public static <T> T deepClone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public static String getSpringProfileActive() {
        return System.getProperty("SPRING_PROFILES_ACTIVE");
    }
}
