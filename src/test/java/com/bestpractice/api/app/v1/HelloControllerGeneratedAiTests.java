package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyTestFactory.class)
public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    @DisplayName("Sample1 - Returns 'key': 'Hello world.'")
    void sample1() {
        // GIVEN: A new instance of the HelloController is created.
        // WHEN: The sample1() method is called.
        // THEN: A map is returned with the key "key" and the value "Hello world.".
        Map<String, String> response = controller.sample1();
        assertEquals("Hello world.", response.get("key"));
    }
}

class MyTestFactory implements org.junit.jupiter.api.extension.TestFactory {
    @Override
    public Iterable<org.junit.jupiter.api.TestDefinition<?>> methodNames() {
        return () -> new TestDefinition[]{new TestDefinition()};
    }
}

class TestDefinition {
}
