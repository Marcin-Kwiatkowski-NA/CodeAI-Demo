package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({})
public class UtilGeneratedAiTests {

    private Date originalDate;
    private String springProfileActive;

    @BeforeEach
    public void setUp() {
        // Reset any modified state before each test
        originalDate = new Date();
        springProfileActive = System.getenv("SPRING_PROFILES_ACTIVE");
    }

    @Test
    public void calculateDate_shouldAddOneYearToCurrentDate() {
        // GIVEN
        Date currentDate = new Date();

        // WHEN
        Date futureDate = Util.calculateDate();

        // THEN
        assertTrue(futureDate.after(currentDate), "The calculated date should be in the future");
    }

    @Test
    public void deepClone_shouldReturnACopyOfAnObject() throws IOException, ClassNotFoundException {
        // GIVEN
        Date original = new Date();

        // WHEN
        Date cloned = Util.deepClone(original);

        // THEN
        assertNotSame(original, cloned, "The cloned object should not be the same instance as the original");
    }

    @Test
    public void getSpringProfileActive_shouldReturnCorrectProfile() {
        // GIVEN
        String expectedProfile = springProfileActive;

        // WHEN
        String actualProfile = Util.getSpringProfileActive();

        // THEN
        assertEquals(expectedProfile, actualProfile, "The active Spring profile should match the system environment variable");
    }
}
