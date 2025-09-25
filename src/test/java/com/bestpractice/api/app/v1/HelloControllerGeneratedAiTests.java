package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnHelloWorldMap() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();
        // THEN: the result should contain the expected key-value pair
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_shouldNotThrowException() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = null;
        try {
            result = helloController.sample1();
        } catch (Exception e) {
            throw new AssertionError("sample1 method threw an unexpected exception", e);
        }
        // THEN: result should not be null and should contain expected data
        assertNotNull(result);
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_forcedExceptionScenario_shouldThrowRuntimeException() {
        // GIVEN: a HelloController instance overridden to throw exception
        HelloController controller = new HelloController() {
            @Override
            public Map<String, String> sample1() {
                throw new RuntimeException("Forced exception for testing");
            }
        };
        // WHEN & THEN: assert that RuntimeException is thrown
        assertThrows(RuntimeException.class, controller::sample1);
    }
}
