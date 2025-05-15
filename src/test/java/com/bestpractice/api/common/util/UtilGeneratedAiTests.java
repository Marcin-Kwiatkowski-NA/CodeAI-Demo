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
        // WHEN: We calculate the date one year in the future.
        Date futureDate = util.calculateDate();
    }

    @Test
    void testDeepClone() {
        // GIVEN: We have an object to be cloned.
        String originalString = "This is a test string";
        
        // WHEN: We deep clone the string.
        String clonedString = util.deepClone(originalString);

        // THEN: The cloned string is a deep copy of the original string.
        assert originalString != null;
        assert clonedString != null;
        assert !originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: We set the SPRING_PROFILES_ACTIVE environment variable.
        System.getenv("SPRING_PROFILES_ACTIVE") = "development";

        // WHEN: We retrieve the active Spring profile.
        String activeProfile = util.getSpringProfileActive();

        // THEN: The active Spring profile is "development".
        assert activeProfile != null;
        assert activeProfile.equals("development");
    }
}
