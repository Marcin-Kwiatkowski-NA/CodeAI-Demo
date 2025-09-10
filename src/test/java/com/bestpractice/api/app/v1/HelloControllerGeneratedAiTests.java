package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MyTestExtension.class)
public class HelloControllerGeneratedAiTests {

    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTest() {
        // No preconditions are needed for this simple test.
    }

    @Test
    public void sample1Test() {
        // GIVEN: No preconditions are needed for this simple test.
        // WHEN: The sample1() method is called.
        Map<String, String> response = HelloController.sample1();
        // THEN: The response should contain "key" with the value "Hello world.".
        boolean result = response.containsKey("key") && response.get("key").equals("Hello world.");
        assert result;
    }
}
