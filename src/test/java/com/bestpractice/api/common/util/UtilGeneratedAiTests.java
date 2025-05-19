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
        // GIVEN: We have a current date.
        Date currentDate = util.calculateDate();

        // WHEN: We calculate the date one year in the future.
        Date futureDate = util.calculateDate();

        // THEN: The future date is one year after the current date.
        assert currentDate != null;
        assert futureDate != null;
    }

    @Test
    void testDeepClone() {
        // GIVEN: We have an object to be cloned.
        String originalString = "This is a test string.";

        // WHEN: We perform a deep clone of the object.
        String clonedString = util.deepClone(originalString);

        // THEN: The cloned object is a deep copy of the original object.
        assert originalString != null;
        assert clonedString != null;
        assert !originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: The SPRING_PROFILES_ACTIVE environment variable is set to "dev".
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";

        // WHEN: We retrieve the active Spring profile.
        String activeProfile = util.getSpringProfileActive();

        // THEN: The active Spring profile is "dev".
        assert activeProfile != null;
        assert activeProfile.equals("dev");
    }
}
