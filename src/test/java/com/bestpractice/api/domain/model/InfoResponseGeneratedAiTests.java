package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: Initialize InfoResponse with sample data
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance initialized in setUp

        // WHEN: Calling getId()
        String result = infoResponse.getId();

        // THEN: The returned id should match the initialized value
        assertEquals("123", result);
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance initialized in setUp

        // WHEN: Calling getTitle()
        String result = infoResponse.getTitle();

        // THEN: The returned title should match the initialized value
        assertEquals("Sample Title", result);
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance initialized in setUp

        // WHEN: Calling getDescription()
        String result = infoResponse.getDescription();

        // THEN: The returned description should match the initialized value
        assertEquals("Sample Description", result);
    }

    @Test
    void testConstructorHandlesNullValues() {
        // GIVEN: Null values for all parameters
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN: Accessing fields via getters
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN: All returned values should be null
        assertNull(id);
        assertNull(title);
        assertNull(description);
    }
}
