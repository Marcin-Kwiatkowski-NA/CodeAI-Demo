package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.MockitoExtension;

@MockitoExtension
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void sample1() {
        // GIVEN a new HelloController instance
        // WHEN the sample1() method is called
        // THEN the method should return a map with the key "key" and the value "Hello world."
        Map<String, String> response = controller.sample1();
        assertEquals("Hello world.", response.get("key"));
    }
}
