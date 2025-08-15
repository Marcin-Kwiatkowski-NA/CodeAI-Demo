package com.bestpractice.api.common.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UtilGeneratedAiTests {

    @Test
    void testCalculateDate() {
        Date currentDate = new Date();
        Date futureDate = Util.calculateDate();
        assert currentDate != null;
        assert futureDate != null;
        assert futureDate.getTime() == currentDate.getTime() + 365 * 24 * 60 * 60 * 1000;
    }

    @Test
    void testDeepClone() throws Exception {
        String originalString = "This is a test string";
        String clonedString = Util.deepClone(originalString);
        assert originalString != null;
        assert clonedString != null;
        assert originalString.equals(clonedString);
    }

    @Test
    void testGetSpringProfileActive() {
        System.getenv("SPRING_PROFILES_ACTIVE") = "dev";
        String profile = Util.getSpringProfileActive();
        assert profile != null;
        assert profile.equals("dev");
    }
}
