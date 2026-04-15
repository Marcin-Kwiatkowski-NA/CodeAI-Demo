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
        // GIVEN: Prepare a fresh instance before each test
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known id
        String expectedId = "123";

        // WHEN: Retrieving the id
        String actualId = infoResponse.getId();

        // THEN: The id should match the expected value
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known title
        String expectedTitle = "Sample Title";

        // WHEN: Retrieving the title
        String actualTitle = infoResponse.getTitle();

        // THEN: The title should match the expected value
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known description
        String expectedDescription = "Sample Description";

        // WHEN: Retrieving the description
        String actualDescription = infoResponse.getDescription();

        // THEN: The description should match the expected value
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConstructorHandlesNullValues() {
        // GIVEN: Null values for all fields
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN: Retrieving each field
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN: Each field should be null
        assertNull(id);
        assertNull(title);
        assertNull(description);
    }

    @Test
    void testConstructorHandlesEmptyStrings() {
        // GIVEN: Empty string values for all fields
        InfoResponse emptyResponse = new InfoResponse("", "", "");

        // WHEN: Retrieving each field
        String id = emptyResponse.getId();
        String title = emptyResponse.getTitle();
        String description = emptyResponse.getDescription();

        // THEN: Each field should be an empty string
        assertEquals("", id);
        assertEquals("", title);
        assertEquals("", description);
    }
}
