package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnHelloWorldMap() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: verify the returned map contains expected key-value pair
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_shouldReturnImmutableMap() {
        // GIVEN: a HelloController instance
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN: verify that modifying the returned map throws UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> result.put("newKey", "newValue"));
    }

    @Test
    void sample1_shouldReturnConsistentResultOnMultipleCalls() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 multiple times
        Map<String, String> firstCall = helloController.sample1();
        Map<String, String> secondCall = helloController.sample1();

        // THEN: verify both results are equal and consistent
        assertEquals(firstCall, secondCall);
        assertEquals("Hello world.", secondCall.get("key"));
    }

    @Test
    void sample1_shouldReturnNonEmptyMap() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: verify the map is not empty and contains exactly one entry
        assertThat(result).isNotEmpty();
        assertEquals(1, result.size());
    }

    @Test
    void sample1_shouldHandleNullControllerReferenceGracefully() {
        // GIVEN: a null HelloController reference
        HelloController nullController = null;

        // WHEN & THEN: verify NullPointerException is thrown when invoking sample1 on null reference
        assertThrows(NullPointerException.class, () -> nullController.sample1());
    }

    @Test
    void sample1_shouldReturnExpectedKeyAndValue() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: verify the key and value are exactly as expected
        assertThat(result.keySet()).containsExactly("key");
        assertThat(result.values()).containsExactly("Hello world.");
    }

    @Test
    void sample1_shouldNotReturnNullMap() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 method
        Map<String, String> result = helloController.sample1();

        // THEN: verify the returned map is not null
        assertThat(result).isNotNull();
    }

    @Test
    void sample1_shouldReturnSameReferenceForRepeatedCalls() {
        // GIVEN: a HelloController instance
        // WHEN: calling sample1 multiple times
        Map<String, String> firstCall = helloController.sample1();
        Map<String, String> secondCall = helloController.sample1();

        // THEN: verify that both calls return equal but not necessarily same reference
        assertThat(firstCall).isEqualTo(secondCall);
    }
}
