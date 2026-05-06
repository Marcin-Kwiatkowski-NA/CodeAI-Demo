package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
        // GIVEN: Initialize InfoResponse with sample data before each test
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN: InfoResponse initialized in setUp()

        // WHEN: Retrieving the id
        String id = infoResponse.getId();

        // THEN: The id should match the expected value
        assertEquals("123", id, "Expected id should match the initialized value");
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN: InfoResponse initialized in setUp()

        // WHEN: Retrieving the title
        String title = infoResponse.getTitle();

        // THEN: The title should match the expected value
        assertEquals("Sample Title", title, "Expected title should match the initialized value");
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN: InfoResponse initialized in setUp()

        // WHEN: Retrieving the description
        String description = infoResponse.getDescription();

        // THEN: The description should match the expected value
        assertEquals("Sample Description", description, "Expected description should match the initialized value");
    }

    @Test
    void testConstructorHandlesNullValues() {
        // GIVEN: InfoResponse created with null values
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN: Retrieving fields
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN: All fields should be null
        assertNull(id, "Expected id to be null when initialized with null");
        assertNull(title, "Expected title to be null when initialized with null");
        assertNull(description, "Expected description to be null when initialized with null");
    }

    @Test
    void testConstructorAssignsValuesCorrectly() {
        // GIVEN: Specific values for constructor
        String expectedId = "456";
        String expectedTitle = "Another Title";
        String expectedDescription = "Another Description";

        // WHEN: Creating a new InfoResponse instance
        InfoResponse response = new InfoResponse(expectedId, expectedTitle, expectedDescription);

        // THEN: All fields should match the provided values
        assertEquals(expectedId, response.getId(), "Expected id should match the provided value");
        assertEquals(expectedTitle, response.getTitle(), "Expected title should match the provided value");
        assertEquals(expectedDescription, response.getDescription(), "Expected description should match the provided value");
    }
}
