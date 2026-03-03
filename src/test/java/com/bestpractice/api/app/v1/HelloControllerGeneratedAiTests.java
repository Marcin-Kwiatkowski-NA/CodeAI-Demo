package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelloControllerGeneratedAiTests {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void sample1ReturnsExpectedMap() {
        // GIVEN
        // (no preconditions needed)

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsKey("key");
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    void sample1ReturnsSingletonMapWithSingleEntry() {
        // GIVEN
        // (no preconditions needed)

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result).containsOnlyKeys("key");
    }

    @Test
    void sample1ReturnsImmutableMap() {
        // GIVEN
        Map<String, String> result = helloController.sample1();

        // WHEN & THEN
        assertThrows(UnsupportedOperationException.class, () -> result.put("newKey", "newValue"));
    }
}
