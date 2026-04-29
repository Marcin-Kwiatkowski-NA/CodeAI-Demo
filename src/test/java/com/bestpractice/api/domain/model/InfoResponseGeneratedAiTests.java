package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: Prepare a fresh instance before each test
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known id
        String expectedId = "123";

        // WHEN: Calling getId()
        String actualId = infoResponse.getId();

        // THEN: The returned id should match the expected value
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known title
        String expectedTitle = "Test Title";

        // WHEN: Calling getTitle()
        String actualTitle = infoResponse.getTitle();

        // THEN: The returned title should match the expected value
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN: An InfoResponse instance with a known description
        String expectedDescription = "Test Description";

        // WHEN: Calling getDescription()
        String actualDescription = infoResponse.getDescription();

        // THEN: The returned description should match the expected value
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConstructorHandlesNullValuesGracefully() {
        // GIVEN: Null values for all constructor parameters
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN: Accessing fields through getters
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN: All returned values should be null
        assertNull(id);
        assertNull(title);
        assertNull(description);
    }
}
