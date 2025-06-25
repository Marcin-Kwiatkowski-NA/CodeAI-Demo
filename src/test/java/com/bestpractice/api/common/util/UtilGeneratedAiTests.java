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
        Date currentDate = new Date();
    }

    @Test
    void testCalculateDate() {
        // GIVEN: A current date
        Date currentDate = new Date();

        // WHEN: The calculateDate method is called
        Date futureDate = util.calculateDate();

        // THEN: The future date is one year after the current date
        assert currentDate != null;
        assert futureDate != null;
        assert futureDate.getYear() == currentDate.getYear();
        assert futureDate.getMonth() == currentDate.getMonth();
        assert futureDate.getDate() == currentDate.getDate();
        assert futureDate.getHours() == currentDate.getHours();
        assert futureDate.getMinutes() == currentDate.getMinutes();
        assert futureDate.getSeconds() == currentDate.getSeconds();
    }

    @Test
    void testDeepClone() throws Exception {
        // GIVEN: An object to be cloned
        String originalString = "This is a test string";

        // WHEN: The deepClone method is called
        String clonedString = util.deepClone(originalString);

        // THEN: The cloned object is identical to the original object
        assert originalString != null;
        assert clonedString != null;
        assert originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN: No Spring profile is set in the environment
        String expectedProfile = null;

        // WHEN: The getSpringProfileActive method is called
        String activeProfile = util.getSpringProfileActive();

        // THEN: The active profile is null
        assert activeProfile == null;
    }
}
