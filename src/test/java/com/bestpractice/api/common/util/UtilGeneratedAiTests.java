package com.bestpractice.api.common.util;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        // THEN the future date is one year after the current date
        assert currentDate != null;
        assert futureDate != null;
    }

    @Test
    void testDeepClone() throws Exception {
        // GIVEN an object
        String originalString = "This is a test string";
        // WHEN cloning the object
        String clonedString = util.deepClone(originalString);
        // THEN the cloned object is a deep copy of the original
        assert !originalString.equals(clonedString);
        assert originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN no SPRING_PROFILES_ACTIVE environment variable set
        String springProfile = util.getSpringProfileActive();
        // WHEN retrieving the spring profile
        // THEN the method returns null
        assert springProfile == null;

        // GIVEN SPRING_PROFILES_ACTIVE environment variable set to "dev"
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";
        String springProfile = util.getSpringProfileActive();
        // WHEN retrieving the spring profile
        // THEN the method returns "dev"
        assert springProfile == "dev";

        // GIVEN SPRING_PROFILES_ACTIVE environment variable set to "prod"
        System.getenv("SPRING_PROFILES_ACTIVE") = "prod";
        String springProfile = util.getSpringProfileActive();
        // WHEN retrieving the spring profile
        // THEN the method returns "prod"
        assert springProfile == "prod";
    }
}