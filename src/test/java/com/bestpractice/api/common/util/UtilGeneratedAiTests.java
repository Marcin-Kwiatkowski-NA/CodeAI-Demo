package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilGeneratedAiTests {

    private Util util;

    @BeforeEach
    void setUp() {
        util = new Util();
    }

    @AfterEach
    void tearDown() {
        util = null;
    }

    @Test
    void testCalculateDate() {
        // GIVEN: We have a current date.
        Date currentDate = util.calculateDate();

        // WHEN: We calculate the date one year in the future.
        Date futureDate = util.calculateDate();

        // THEN: The future date is one year after the current date.
        boolean isOneYearLater = (futureDate.getTime() - currentDate.getTime()) == (365 * 24 * 60 * 60 * 1000);
        org.junit.jupiter.api.Assertions.assertTrue(isOneYearLater);
    }

    @Test
    void testDeepClone() {
        // GIVEN: We have an object to be cloned.
        String originalString = "This is a test string";

        // WHEN: We deep clone the string.
        String clonedString = util.deepClone(originalString);

        // THEN: The cloned string is a deep copy of the original string.
        org.junit.jupiter.api.Assertions.assertEquals(originalString, clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: We set the SPRING_PROFILES_ACTIVE environment variable.
        System.getenv("SPRING_PROFILES_ACTIVE") = "test";

        // WHEN: We get the active Spring profile.
        String activeProfile = util.getSpringProfileActive();

        // THEN: The active Spring profile is "test".
        org.junit.jupiter.api.Assertions.assertEquals("test", activeProfile);
    }
}
