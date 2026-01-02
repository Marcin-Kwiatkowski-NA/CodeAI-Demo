package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloControllerGeneratedAiTests {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    void testSample1ReturnsCorrectMap() {
        // GIVEN
        // controller is initialized in setUp

        // WHEN
        Map<String, String> result = controller.sample1();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).containsEntry("key", "Hello world.");
    }

    @Test
    void testSample1MapIsSingleton() {
        // GIVEN
        // controller is initialized in setUp

        // WHEN
        Map<String, String> firstCall = controller.sample1();
        Map<String, String> secondCall = controller.sample1();

        // THEN
        assertThat(firstCall).isSameAs(secondCall);
    }

    @Test
    void testSample1MapIsImmutable() {
        // GIVEN
        Map<String, String> result = controller.sample1();

        // WHEN
        // Attempt to modify the map

        // THEN
        assertThatThrownBy(() -> result.put("newKey", "newValue"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testSample1MapContainsOnlyExpectedKey() {
        // GIVEN
        // controller is initialized in setUp

        // WHEN
        Map<String, String> result = controller.sample1();

        // THEN
        assertThat(result.keySet()).containsExactly("key");
    }
}
