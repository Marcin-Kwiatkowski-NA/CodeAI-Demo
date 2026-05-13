package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for InfoResponse class.
 * This class verifies the correctness of public methods and ensures proper handling of null values.
 */
public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN - Initialize a valid InfoResponse instance before each test
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void shouldReturnCorrectId() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId, "The ID returned should match the expected value");
    }

    @Test
    void shouldReturnCorrectTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle, "The title returned should match the expected value");
    }

    @Test
    void shouldReturnCorrectDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription, "The description returned should match the expected value");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN
        assertNull(id, "ID should be null when initialized with null");
        assertNull(title, "Title should be null when initialized with null");
        assertNull(description, "Description should be null when initialized with null");
    }
}
