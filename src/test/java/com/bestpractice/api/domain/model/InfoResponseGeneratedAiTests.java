package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for InfoResponse class.
 * This class verifies that all public methods behave correctly under normal and edge conditions.
 */
public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN - Initialize a valid InfoResponse instance before each test
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId, "getId should return the correct id value");
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle, "getTitle should return the correct title value");
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription, "getDescription should return the correct description value");
    }

    @Test
    void testConstructorHandlesNullValuesGracefully() {
        // GIVEN
        InfoResponse responseWithNulls = new InfoResponse(null, null, null);

        // WHEN
        String id = responseWithNulls.getId();
        String title = responseWithNulls.getTitle();
        String description = responseWithNulls.getDescription();

        // THEN
        assertNull(id, "Id should be null when constructed with null");
        assertNull(title, "Title should be null when constructed with null");
        assertNull(description, "Description should be null when constructed with null");
    }

    @Test
    void testConstructorHandlesEmptyStrings() {
        // GIVEN
        InfoResponse responseWithEmptyStrings = new InfoResponse("", "", "");

        // WHEN
        String id = responseWithEmptyStrings.getId();
        String title = responseWithEmptyStrings.getTitle();
        String description = responseWithEmptyStrings.getDescription();

        // THEN
        assertEquals("", id, "Id should be empty string when constructed with empty string");
        assertEquals("", title, "Title should be empty string when constructed with empty string");
        assertEquals("", description, "Description should be empty string when constructed with empty string");
    }
}
