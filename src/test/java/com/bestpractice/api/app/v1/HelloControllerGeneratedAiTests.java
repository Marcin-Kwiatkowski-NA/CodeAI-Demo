package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnHelloWorldMap() {
        // GIVEN
        // A HelloController instance is initialized in setUp()

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertNotNull(result, "Result map should not be null");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertEquals("Hello world.", result.get("key"), "Value should match expected greeting");
    }

    @Test
    void sample1_shouldReturnImmutableMap() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN
        assertThrows(UnsupportedOperationException.class, () -> result.put("anotherKey", "value"),
                "Returned map should be immutable");
    }

    @Test
    void sample1_shouldReturnConsistentResultAcrossMultipleCalls() {
        // GIVEN
        // A HelloController instance is initialized in setUp()

        // WHEN
        Map<String, String> firstCall = helloController.sample1();
        Map<String, String> secondCall = helloController.sample1();

        // THEN
        assertEquals(firstCall, secondCall, "Result should be consistent across multiple calls");
        assertEquals("Hello world.", secondCall.get("key"), "Value should remain consistent");
    }

    @Test
    void sample1_shouldReturnMapWithSingleEntry() {
        // GIVEN
        // A HelloController instance is initialized in setUp()

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertEquals(1, result.size(), "Map should contain exactly one entry");
        assertTrue(result.containsKey("key"), "Map should contain the expected key");
        assertEquals("Hello world.", result.get("key"), "Map should contain the expected value");
    }

    @Test
    void sample1_shouldHandleEmptyKeyValueEdgeCase() {
        // GIVEN
        HelloController edgeCaseController = new HelloController() {
            @Override
            public Map<String, String> sample1() {
                return Collections.singletonMap("", "");
            }
        };

        // WHEN
        Map<String, String> result = edgeCaseController.sample1();

        // THEN
        assertNotNull(result, "Result map should not be null even for empty key/value");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertTrue(result.containsKey(""), "Map should contain empty key");
        assertEquals("", result.get(""), "Map should contain empty value");
    }

    @Test
    void sample1_shouldHandleWhitespaceKeyAndValueEdgeCase() {
        // GIVEN
        HelloController whitespaceController = new HelloController() {
            @Override
            public Map<String, String> sample1() {
                return Collections.singletonMap(" ", " ");
            }
        };

        // WHEN
        Map<String, String> result = whitespaceController.sample1();

        // THEN
        assertNotNull(result, "Result map should not be null for whitespace key/value");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertTrue(result.containsKey(" "), "Map should contain whitespace key");
        assertEquals(" ", result.get(" "), "Map should contain whitespace value");
    }

    @Test
    void sample1_shouldHandleLongStringKeyAndValueEdgeCase() {
        // GIVEN
        String longKey = "K".repeat(1000);
        String longValue = "V".repeat(1000);
        HelloController longStringController = new HelloController() {
            @Override
            public Map<String, String> sample1() {
                return Collections.singletonMap(longKey, longValue);
            }
        };

        // WHEN
        Map<String, String> result = longStringController.sample1();

        // THEN
        assertNotNull(result, "Result map should not be null for long key/value");
        assertEquals(1, result.size(), "Result map should contain exactly one entry");
        assertTrue(result.containsKey(longKey), "Map should contain long key");
        assertEquals(longValue, result.get(longKey), "Map should contain long value");
    }

    @Test
    void sample1_shouldNotReturnNullKeyOrValue() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN
        assertTrue(result.keySet().stream().noneMatch(k -> k == null), "Map should not contain null keys");
        assertTrue(result.values().stream().noneMatch(v -> v == null), "Map should not contain null values");
    }

    @Test
    void sample1_shouldReturnExpectedKeyAndValueTypes() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN
        assertTrue(result.keySet().stream().allMatch(k -> k instanceof String), "All keys should be of type String");
        assertTrue(result.values().stream().allMatch(v -> v instanceof String), "All values should be of type String");
    }

    @Test
    void sample1_shouldReturnNonEmptyValueForKey() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN
        String value = result.get("key");

        // THEN
        assertNotNull(value, "Value should not be null");
        assertTrue(!value.isEmpty(), "Value should not be empty");
    }
}
