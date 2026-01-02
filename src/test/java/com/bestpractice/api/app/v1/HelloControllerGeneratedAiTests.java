package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
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
        // helloController is initialized in setUp

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsKey("key");
        assertThat(result.get("key")).isEqualTo("Hello world.");
        assertThat(result).hasSize(1);
    }

    @Test
    void sample1IsStateless() {
        // GIVEN
        // helloController is initialized in setUp

        // WHEN
        Map<String, String> firstCall = helloController.sample1();
        Map<String, String> secondCall = helloController.sample1();

        // THEN
        assertThat(firstCall).isEqualTo(secondCall);
        assertThat(firstCall).isNotSameAs(secondCall);
    }

    @Test
    void sample1ReturnsImmutableMap() {
        // GIVEN
        // helloController is initialized in setUp

        // WHEN
        Map<String, String> result = helloController.sample1();

        // THEN
        assertThrows(UnsupportedOperationException.class, () -> result.put("newKey", "newValue"));
    }
}
