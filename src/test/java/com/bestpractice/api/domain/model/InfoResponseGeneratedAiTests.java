package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for InfoResponse class.
 * This class verifies the correctness of public methods and constructor behavior.
 */
public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId, "getId() should return the expected ID value");
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle, "getTitle() should return the expected title value");
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription, "getDescription() should return the expected description value");
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
        assertNull(id, "ID should be null when constructed with null");
        assertNull(title, "Title should be null when constructed with null");
        assertNull(description, "Description should be null when constructed with null");
    }

    @Test
    void testConstructorAssignsValuesCorrectly() {
        // GIVEN
        String id = "456";
        String title = "Another Title";
        String description = "Another Description";

        // WHEN
        InfoResponse response = new InfoResponse(id, title, description);

        // THEN
        assertEquals(id, response.getId(), "Constructor should correctly assign ID");
        assertEquals(title, response.getTitle(), "Constructor should correctly assign title");
        assertEquals(description, response.getDescription(), "Constructor should correctly assign description");
    }
}
