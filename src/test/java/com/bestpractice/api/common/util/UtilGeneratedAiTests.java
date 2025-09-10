package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
    void calculateDate() {
        // GIVEN current date
        Date currentDate = util.calculateDate();

        // WHEN adding one year
        Date futureDate = util.calculateDate();

        // THEN verify that the future date is one year after the current date
        boolean isOneYearLater = (futureDate.getTime() - currentDate.getTime()) == 365 * 24 * 60 * 60 * 1000;
        assertTrue(isOneYearLater);
    }

    @Test
    void deepClone() throws Exception {
        // GIVEN an object to clone
        String originalString = "This is a test string";

        // WHEN cloning the object
        String clonedString = util.deepClone(originalString);

        // THEN verify that the cloned object is the same as the original object
        boolean areEqual = originalString.equals(clonedString);
        assertTrue(areEqual);
    }

    @Test
    void getSpringProfileActive() {
        // GIVEN a Spring profile active environment variable
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";

        // WHEN retrieving the Spring profile active value
        String springProfile = util.getSpringProfileActive();

        // THEN verify that the returned value is "dev"
        boolean isDev = springProfile.equals("dev");
        assertTrue(isDev);
    }
}
