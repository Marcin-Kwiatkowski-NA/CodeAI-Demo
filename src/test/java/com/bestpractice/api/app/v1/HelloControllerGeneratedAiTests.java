package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1() {
        // GIVEN a new HelloController instance
        // WHEN the sample1() method is called
        // THEN a Map<String, String> is returned with the key "key" and the value "Hello world."
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}
