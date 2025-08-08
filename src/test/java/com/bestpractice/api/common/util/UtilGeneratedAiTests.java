package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class UtilGeneratedAiTests {

    private Util util;

    @BeforeEach
    void setUp() {
        util = new Util();
    }

    @Test
    void testCalculateDate() {
        // GIVEN current date
        Date currentDate = util.calculateDate();
        // WHEN adding one year
        Date futureDate = util.calculateDate();
        // THEN the future date should be one year after the current date
        boolean isOneYearLater = (futureDate.getTime() - currentDate.getTime()) == 365 * 24 * 60 * 60 * 1000;
        assertTrue(isOneYearLater);
    }

    @Test
    void testDeepClone() throws Exception {
        // GIVEN an object to clone
        String originalString = "This is a test string";
        // WHEN cloning the object
        String clonedString = util.deepClone(originalString);
        // THEN the cloned object should be a deep copy of the original
        boolean areEqual = originalString.equals(clonedString);
        assertTrue(areEqual);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN a Spring profile active environment variable
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";
        // WHEN retrieving the Spring profile active value
        String springProfile = util.getSpringProfileActive();
        // THEN the retrieved value should be "dev"
        boolean isDev = springProfile.equals("dev");
        assertTrue(isDev);
    }
}
