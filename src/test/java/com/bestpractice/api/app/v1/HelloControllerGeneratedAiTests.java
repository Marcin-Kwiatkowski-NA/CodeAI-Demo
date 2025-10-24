package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnNonNullMapWithExpectedKeyValue() {
        // GIVEN: A HelloController instance
        // WHEN: Calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: The result should be non-null and contain the expected key-value pair
        assertNotNull(result, "Result map should not be null");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should match expected");
    }

    @Test
    void sample1_shouldNotThrowAnyException() {
        // GIVEN: A HelloController instance
        // WHEN: Calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: The result should be non-null and contain the expected key-value pair without throwing exceptions
        assertNotNull(result, "Result map should not be null");
        assertEquals("Hello world.", result.get("key"), "Value for 'key' should match expected");
    }

    @Test
    void sample1_mockedController_shouldThrowRuntimeException() {
        // GIVEN: A mocked HelloController that throws RuntimeException
        HelloController mockedController = Mockito.mock(HelloController.class);
        Mockito.when(mockedController.sample1()).thenThrow(new RuntimeException("Simulated failure"));
        // WHEN & THEN: Calling sample1 should throw RuntimeException
        assertThrows(RuntimeException.class, mockedController::sample1, "Expected RuntimeException to be thrown");
    }

    @Test
    void sample1_mockedController_shouldThrowIllegalStateException() {
        // GIVEN: A mocked HelloController that throws IllegalStateException
        HelloController mockedController = Mockito.mock(HelloController.class);
        Mockito.when(mockedController.sample1()).thenThrow(new IllegalStateException("Illegal state"));
        // WHEN & THEN: Calling sample1 should throw IllegalStateException
        assertThrows(IllegalStateException.class, mockedController::sample1, "Expected IllegalStateException to be thrown");
    }

    @Test
    void sample1_mockedController_shouldReturnCustomMap() {
        // GIVEN: A mocked HelloController that returns a custom map
        HelloController mockedController = Mockito.mock(HelloController.class);
        Mockito.when(mockedController.sample1()).thenReturn(Map.of("customKey", "customValue"));
        // WHEN: Calling sample1 method
        Map<String, String> result = mockedController.sample1();
        // THEN: The result should contain the custom key-value pair
        assertNotNull(result, "Result map should not be null");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertEquals("customValue", result.get("customKey"), "Value for 'customKey' should match expected");
    }
}
