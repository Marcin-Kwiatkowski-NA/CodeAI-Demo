package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnNonNullMapWithExpectedKeyValue() {
        // GIVEN: a HelloController instance is initialized in setUp()

        // WHEN: calling the sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: the result should not be null and should contain the expected key-value pair
        assertNotNull(result, "Result map should not be null");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should be 'Hello world.'");
    }

    @Test
    void sample1_shouldNotThrowAnyException() {
        // GIVEN: a HelloController instance is initialized in setUp()

        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: the result should be valid and no exception should be thrown
        assertNotNull(result, "Result map should not be null");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should be 'Hello world.'");
    }

    @Test
    void sample1_shouldThrowNullPointerExceptionWhenControllerIsNull() {
        // GIVEN: a null HelloController reference
        helloController = null;

        // WHEN & THEN: calling sample1 on null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> {
            helloController.sample1();
        }, "Calling sample1 on a null HelloController should throw NullPointerException");
    }
}
