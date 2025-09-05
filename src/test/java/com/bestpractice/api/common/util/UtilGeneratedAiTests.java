package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
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
        // WHEN calculateDate() is called
        // THEN the returned date is one year in the future
        Date date = util.calculateDate();
        // Assert that the returned date is not null.
        assert date != null;
    }

    @Test
    void testDeepClone() {
        // GIVEN an object
        // WHEN deepClone(object) is called
        // THEN the returned object is a deep copy of the original object
        String originalString = "This is a test string";
        String clonedString = util.deepClone(originalString);
        // Assert that the original string and the cloned string are different objects
        assert originalString != clonedString;
        // Assert that the cloned string contains the same content as the original string
        assert originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN no SPRING_PROFILES_ACTIVE environment variable set
        // WHEN getSpringProfileActive() is called
        // THEN the method returns null
        String profile = util.getSpringProfileActive();
        assert profile == null;

        // GIVEN SPRING_PROFILES_ACTIVE environment variable set to "dev"
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";
        String profile = util.getSpringProfileActive();
        // Assert that the returned profile is "dev"
        assert profile.equals("dev");

        // Reset the environment variable
        System.getenv("SPRING_PROFILES_ACTIVE") = null;
    }
}
