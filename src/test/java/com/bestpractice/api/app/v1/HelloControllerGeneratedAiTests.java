package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1_shouldReturnHelloWorldMap() {
        Map<String, String> result = helloController.sample1();
        assertNotNull(result);
        assertEquals("Hello world.", result.get("key"));
        assertEquals(1, result.size());
    }

    @Test
    void sample1_shouldReturnImmutableMap() {
        Map<String, String> result = helloController.sample1();
        assertThrows(UnsupportedOperationException.class, () -> result.put("anotherKey", "value"));
    }

    @Test
    void sample1_shouldContainExpectedKeyAndValue() {
        Map<String, String> result = helloController.sample1();
        assertTrue(result.containsKey("key"));
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_shouldReturnNonEmptyMap() {
        Map<String, String> result = helloController.sample1();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void sample1_shouldReturnConsistentValueAcrossMultipleCalls() {
        Map<String, String> firstCall = helloController.sample1();
        Map<String, String> secondCall = helloController.sample1();
        assertEquals(firstCall.get("key"), secondCall.get("key"));
        assertEquals("Hello world.", secondCall.get("key"));
    }

    @Test
    void sample1_shouldHandleTrimmedValueCorrectly() {
        Map<String, String> result = helloController.sample1();
        String trimmedValue = result.get("key").trim();
        assertEquals("Hello world.", trimmedValue);
        assertFalse(trimmedValue.isEmpty());
    }

    @Test
    void sample1_shouldHandleBoundaryCaseWithLengthCheck() {
        Map<String, String> result = helloController.sample1();
        String value = result.get("key");
        assertEquals(12, value.length());
        assertTrue(value.startsWith("H"));
        assertTrue(value.endsWith("."));
    }

    @Test
    void sample1_shouldNotContainUnexpectedKeys() {
        Map<String, String> result = helloController.sample1();
        assertFalse(result.containsKey("unexpected"));
    }

    @Test
    void sample1_shouldReturnSameMapInstanceForConsistency() {
        Map<String, String> firstResult = helloController.sample1();
        Map<String, String> secondResult = helloController.sample1();
        assertEquals(firstResult, secondResult);
        assertEquals("Hello world.", firstResult.get("key"));
    }
}
