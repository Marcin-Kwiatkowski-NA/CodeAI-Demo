package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.Test;
import static com.bestpractice.api.app.v1.HelloController.sample1;
import com.bestpractice.api.app.v1.HelloController;
import org.junit.Test;

@Test
public class HelloControllerGeneratedAiTests {

    @Test
    public void testSample1ReturnsCorrectData() {
        // Arrange
        Map<String, String> result = sample1();

        // Act
        assertEquals("key", result.get("key"));
        assertEquals("Hello world.", result.get("key"));
    }

    @Test
    public void testSample1ReturnsEmptyData() {
        // Arrange
        Map<String, String> result = sample1();

        // Act
        assertEquals("", result.get("key"));
        assertEquals("", result.get("key"));
    }

    @Test
    public void testSample1ReturnsDefaultData() {
        // Arrange
        Map<String, String> result = sample1();

        // Act
        assertEquals("Hello world.", result.get("key"));
        assertEquals("Hello world.", result.get("key"));
    }
}
