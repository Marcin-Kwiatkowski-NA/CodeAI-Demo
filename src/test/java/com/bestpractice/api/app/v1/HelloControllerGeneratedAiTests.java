package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Map;

@Test
public class HelloControllerGeneratedAiTests {

    @Test
    public void sample1Test() {
        // GIVEN: The context is set up. No preconditions are needed for this simple test.
        // WHEN: The sample1() method is called.
        Map<String, String> result = HelloController.sample1();
        // THEN: The response map should contain the expected key-value pair.
        assertEquals("Hello world.", result.get("key"));
    }
}
