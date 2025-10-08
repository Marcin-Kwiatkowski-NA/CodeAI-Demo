package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

    private String id;
    private String title;
    private String description;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        id = "123";
        title = "Sample Title";
        description = "Sample Description";
    }

    @Test
    void testGetIdReturnsCorrectValue() {
        // GIVEN: an InfoResponse instance with a specific id
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN: getId is called
        String result = infoResponse.getId();

        // THEN: the returned id should match the initialized value
        assertEquals(id, result);
    }

    @Test
    void testGetTitleReturnsCorrectValue() {
        // GIVEN: an InfoResponse instance with a specific title
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN: getTitle is called
        String result = infoResponse.getTitle();

        // THEN: the returned title should match the initialized value
        assertEquals(title, result);
    }

    @Test
    void testGetDescriptionReturnsCorrectValue() {
        // GIVEN: an InfoResponse instance with a specific description
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN: getDescription is called
        String result = infoResponse.getDescription();

        // THEN: the returned description should match the initialized value
        assertEquals(description, result);
    }

    @Test
    void testConstructorAllowsNullValues() {
        // GIVEN: null values for all parameters
        String nullId = null;
        String nullTitle = null;
        String nullDescription = null;

        // WHEN: creating InfoResponse with null values
        InfoResponse infoResponse = new InfoResponse(nullId, nullTitle, nullDescription);

        // THEN: getters should return null
        assertEquals(nullId, infoResponse.getId());
        assertEquals(nullTitle, infoResponse.getTitle());
        assertEquals(nullDescription, infoResponse.getDescription());
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // GIVEN: empty string values for all parameters
        String emptyId = "";
        String emptyTitle = "";
        String emptyDescription = "";

        // WHEN: creating InfoResponse with empty strings
        InfoResponse infoResponse = new InfoResponse(emptyId, emptyTitle, emptyDescription);

        // THEN: getters should return empty strings
        assertEquals(emptyId, infoResponse.getId());
        assertEquals(emptyTitle, infoResponse.getTitle());
        assertEquals(emptyDescription, infoResponse.getDescription());
    }

    @Test
    void testConstructorWithMixedValues() {
        // GIVEN: mixed null and non-null values
        String mixedId = "456";
        String mixedTitle = null;
        String mixedDescription = "Some description";

        // WHEN: creating InfoResponse with mixed values
        InfoResponse infoResponse = new InfoResponse(mixedId, mixedTitle, mixedDescription);

        // THEN: getters should return the respective values
        assertEquals(mixedId, infoResponse.getId());
        assertEquals(mixedTitle, infoResponse.getTitle());
        assertEquals(mixedDescription, infoResponse.getDescription());
    }
}
