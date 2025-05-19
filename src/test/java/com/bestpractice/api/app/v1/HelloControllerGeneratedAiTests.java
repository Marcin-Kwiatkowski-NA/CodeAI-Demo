package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;

class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: The controller is instantiated.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}
