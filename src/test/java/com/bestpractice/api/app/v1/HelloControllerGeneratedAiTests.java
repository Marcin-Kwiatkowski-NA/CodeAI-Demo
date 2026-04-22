package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_ShouldReturnExpectedMap() {
        // GIVEN
        // A HelloController instance is initialized in setUp()

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_ShouldNotThrowAnyException() {
        // GIVEN
        // A HelloController instance is initialized in setUp()

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            Map<String, String> result = helloController.sample1();
            assertNotNull(result);
            assertEquals("Hello world.", result.get("key"));
        });
    }
}
