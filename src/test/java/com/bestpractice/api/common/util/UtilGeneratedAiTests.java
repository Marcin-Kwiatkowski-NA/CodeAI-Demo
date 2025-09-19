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
    void calculateDate() {
        // GIVEN current date
        Date currentDate = util.calculateDate();

        // WHEN adding one year to the date
        Date futureDate = util.calculateDate();

        // Assert that the future date is one year after the current date
        boolean result = (futureDate.getTime() - currentDate.getTime()) == 365 * 24 * 60 * 60 * 1000;

        // Assert that the future date is one year after the current date
        assert result;
    }

    @Test
    void deepClone() throws Exception {
        // GIVEN an object to be cloned
        String originalString = "This is a test string";

        // WHEN cloning the object
        String clonedString = util.deepClone(originalString);

        // THEN the cloned object should be a deep copy of the original object
        boolean areEqual = originalString.equals(clonedString);

        // Assert that the cloned object is equal to the original object
        assert areEqual;
    }

    @Test
    void getSpringProfileActive() {
        // GIVEN a Spring profile active environment variable
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";

        // WHEN retrieving the Spring profile active value
        String springProfile = util.getSpringProfileActive();

        // THEN the retrieved value should be "dev"
        boolean isDev = springProfile.equals("dev");

        // Assert that the retrieved value is "dev"
        assert isDev;
    }
}
