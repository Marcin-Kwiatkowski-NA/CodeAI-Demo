package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: Prepare a fresh instance before each test
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a predefined id
        String expectedId = "123";

        // WHEN: Retrieving the id using getId()
        String actualId = infoResponse.getId();

        // THEN: The returned id should match the expected value
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a predefined title
        String expectedTitle = "Sample Title";

        // WHEN: Retrieving the title using getTitle()
        String actualTitle = infoResponse.getTitle();

        // THEN: The returned title should match the expected value
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a predefined description
        String expectedDescription = "Sample Description";

        // WHEN: Retrieving the description using getDescription()
        String actualDescription = infoResponse.getDescription();

        // THEN: The returned description should match the expected value
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConstructorHandlesNullValuesGracefully() {
        // GIVEN: Null values for all constructor parameters
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN: Retrieving values using getters
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN: All returned values should be null
        assertNull(id);
        assertNull(title);
        assertNull(description);
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // GIVEN: Empty string values for all constructor parameters
        InfoResponse emptyResponse = new InfoResponse("", "", "");

        // WHEN: Retrieving values using getters
        String id = emptyResponse.getId();
        String title = emptyResponse.getTitle();
        String description = emptyResponse.getDescription();

        // THEN: All returned values should be empty strings
        assertEquals("", id);
        assertEquals("", title);
        assertEquals("", description);
    }
}
