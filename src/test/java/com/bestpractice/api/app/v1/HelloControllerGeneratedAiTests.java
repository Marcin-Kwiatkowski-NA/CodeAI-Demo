package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        // GIVEN: Reset state before each test
        helloController = new HelloController();
    }

    @Test
    void testSample1ReturnsNonNullMap() {
        // GIVEN: A HelloController instance
        // WHEN: Calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: The result should not be null
        assertNotNull(result, "Result map should not be null");
    }

    @Test
    void testSample1ReturnsExpectedKeyValue() {
        // GIVEN: A HelloController instance
        // WHEN: Calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: The result should contain the expected key and value
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should match expected string");
    }

    @Test
    void testSample1DoesNotThrowException() {
        // GIVEN: A HelloController instance
        // WHEN: Calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: The method should execute without throwing exceptions and return correct data
        assertNotNull(result, "Result map should not be null");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should match expected string");
    }

    @Test
    void testSample1ThrowsExceptionWhenControllerIsNull() {
        // GIVEN: A null HelloController reference
        HelloController nullController = null;
        // WHEN & THEN: Calling sample1 on null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullController.sample1(), "Calling method on null reference should throw NullPointerException");
    }
}
