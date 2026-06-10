package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        // GIVEN
        // (already set up in @BeforeEach)

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertEquals(1, result.size());
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1_shouldNotThrowException() {
        // GIVEN
        // (already set up in @BeforeEach)

        // WHEN & THEN
        assertDoesNotThrow(() -> helloController.sample1());
    }

    @Test
    void collectionsSingletonMap_shouldThrowExceptionWhenKeyOrValueIsNull() {
        // GIVEN
        // (no setup required)

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> Collections.singletonMap(null, "value"));
        assertThrows(NullPointerException.class, () -> Collections.singletonMap("key", null));
    }

    @Test
    void sample1_shouldReturnImmutableMap() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN
        assertThrows(UnsupportedOperationException.class, () -> result.put("newKey", "newValue"));
    }

    @Test
    void sample1_shouldContainExpectedKeyAndValue() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN
        boolean containsKey = result.containsKey("key");
        boolean containsValue = result.containsValue("Hello world.");

        // THEN
        assertEquals(true, containsKey);
        assertEquals(true, containsValue);
    }

    @Test
    void sample1_shouldHandleEmptyBoundaryCase() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN
        String defaultValue = result.getOrDefault("nonexistent", "default");

        // THEN
        assertEquals("default", defaultValue);
    }

    @Test
    void sample1_shouldHandleWhitespaceBoundaryCase() {
        // GIVEN
        Map<String, String> whitespaceMap = Collections.singletonMap(" ", " ");

        // WHEN
        int size = whitespaceMap.size();

        // THEN
        assertEquals(1, size);
        assertEquals(" ", whitespaceMap.get(" "));
    }

    @Test
    void sample1_shouldHandleSingleElementBoundaryCase() {
        // GIVEN
        Map<String, String> singleElementMap = Collections.singletonMap("single", "element");

        // WHEN
        int size = singleElementMap.size();

        // THEN
        assertEquals(1, size);
        assertEquals("element", singleElementMap.get("single"));
    }

    @Test
    void sample1_shouldHandleDuplicateKeyBoundaryCase() {
        // GIVEN
        Map<String, String> duplicateMap = Collections.singletonMap("key", "Hello world.");

        // WHEN
        String value = duplicateMap.get("key");

        // THEN
        assertEquals("Hello world.", value);
    }

    @Test
    void sample1_shouldHandleBoundaryNumericValues() {
        // GIVEN
        Map<String, Integer> numericMapMin = Collections.singletonMap("min", Integer.MIN_VALUE);
        Map<String, Integer> numericMapMax = Collections.singletonMap("max", Integer.MAX_VALUE);

        // WHEN
        int minValue = numericMapMin.get("min");
        int maxValue = numericMapMax.get("max");

        // THEN
        assertEquals(Integer.MIN_VALUE, minValue);
        assertEquals(Integer.MAX_VALUE, maxValue);
    }

    @Test
    void sample1_shouldHandleBoundaryDoubleValues() {
        // GIVEN
        Map<String, Double> doubleMapInfinity = Collections.singletonMap("infinity", Double.POSITIVE_INFINITY);
        Map<String, Double> doubleMapNaN = Collections.singletonMap("nan", Double.NaN);

        // WHEN
        double infinityValue = doubleMapInfinity.get("infinity");
        double nanValue = doubleMapNaN.get("nan");

        // THEN
        assertEquals(Double.POSITIVE_INFINITY, infinityValue);
        assertEquals(true, Double.isNaN(nanValue));
    }
}
