package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN: a new HelloController instance before each test
        helloController = new HelloController();
    }

    @Test
    void givenHelloController_whenSample1Called_thenReturnsExpectedMap() {
        // GIVEN: a HelloController instance is initialized in setUp()

        // WHEN: calling the sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: the returned map should not be null and should contain the expected key-value pair
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void givenHelloController_whenSample1Called_thenDoesNotThrowException() {
        // GIVEN: a HelloController instance is initialized in setUp()

        // WHEN: calling the sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: result should not be null and should contain expected data
        assertNotNull(result);
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void givenHelloController_whenExpectingExceptionButNoneThrown_thenAssertionErrorIsThrown() {
        // GIVEN: a HelloController instance is initialized in setUp()

        // WHEN & THEN: simulate a scenario where we wrongly expect an exception
        AssertionError thrown = assertThrows(AssertionError.class, () -> {
            // No exception will be thrown by sample1, so we manually throw to simulate failure
            helloController.sample1();
            throw new AssertionError("Expected exception was not thrown");
        });

        // THEN: verify the thrown AssertionError message contains the expected text
        assertEquals(true, thrown.getMessage().contains("Expected exception was not thrown"));
    }
}
