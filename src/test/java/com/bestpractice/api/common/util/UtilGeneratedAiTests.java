package com.bestpractice.api.common.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testCalculateDate() {
        // GIVEN: We have a current date.
        Date currentDate = new Date();
        // WHEN: We call the calculateDate() method.
        Date futureDate = util.calculateDate();
        // THEN: The returned date is one year after the current date.
        assert currentDate != null;
        assert futureDate != null;
        assert futureDate.getYear() == currentDate.getYear();
        assert futureDate.getMonth() == currentDate.getMonth();
        assert futureDate.getDate() == currentDate.getDate();
    }

    @Test
    void testDeepClone() throws Exception {
        // GIVEN: We have an object to clone.
        String originalString = "This is a test string";
        // WHEN: We call the deepClone() method.
        String clonedString = util.deepClone(originalString);
        // THEN: The cloned object is a deep copy of the original object.
        assert !originalString.equals(clonedString);
        assert originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: The SPRING_PROFILES_ACTIVE environment variable is set to "test".
        System.getenv("SPRING_PROFILES_ACTIVE") = "test";
        // WHEN: We call the getSpringProfileActive() method.
        String profile = util.getSpringProfileActive();
        // THEN: The method returns the value of the SPRING_PROFILES_ACTIVE environment variable.
        assert profile != null;
        assert profile.equals("test");
    }
}
