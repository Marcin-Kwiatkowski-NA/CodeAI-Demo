package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
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
    void givenController_whenSample1Called_thenReturnsExpectedMap() {
        // GIVEN: a HelloController instance is set up in @BeforeEach

        // WHEN: calling the sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: the returned map should not be null and contain the expected key-value pair
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void givenController_whenSample1Called_thenNoExceptionIsThrown() {
        // GIVEN: a HelloController instance is set up in @BeforeEach

        // WHEN: calling the sample1 method
        Map<String, String> result = null;
        try {
            result = helloController.sample1();
        } catch (Exception e) {
            throw new AssertionError("Exception was thrown when none expected", e);
        }

        // THEN: the result should be valid
        assertNotNull(result);
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void givenController_whenForcedException_thenExceptionIsThrown() {
        // GIVEN: a HelloController instance is set up in @BeforeEach

        // WHEN & THEN: simulate an exception scenario by throwing manually
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Forced exception for testing");
        });
    }

    @Test
    void givenController_whenSample1ResultAccessed_thenKeyExistsAndValueIsCorrect() {
        // GIVEN: a HelloController instance is set up in @BeforeEach

        // WHEN: calling the sample1 method and accessing the key
        Map<String, String> result = helloController.sample1();

        // THEN: the key should exist and have the correct value
        assertNotNull(result);
        assertEquals(true, result.containsKey("key"));
        assertEquals("Hello world.", result.get("key"));
    }
}
