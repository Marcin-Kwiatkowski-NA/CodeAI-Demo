package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.Map;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_returnsExpectedMap() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        Map<String, String> result = helloController.sample1();
        assert result != null;
        assert result.containsKey("key");
        assert result.get("key").equals("Hello world.");
    }
}
