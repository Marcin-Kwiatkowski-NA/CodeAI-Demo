package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Map;

@SuppressWarnings("unused")
public class HelloControllerGeneratedAiTests {

    @Test
    public void sample1() {
        // GIVEN: No preconditions are needed for this simple method.
        // WHEN: The sample1() method is called.
        // THEN: A map with the key "key" and the value "Hello world." is returned.
        HelloController controller = new HelloController();
        Map<String, String> result = controller.sample1();
        assertEquals("Hello world.", result.get("key"));
    }
}
