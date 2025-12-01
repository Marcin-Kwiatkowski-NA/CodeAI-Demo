package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetId() {
        // GIVEN: An InfoResponse object initialized with an ID
        String expectedId = "123";

        // WHEN: Retrieving the ID
        String actualId = infoResponse.getId();

        // THEN: The ID should match the expected value
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitle() {
        // GIVEN: An InfoResponse object initialized with a title
        String expectedTitle = "Test Title";

        // WHEN: Retrieving the title
        String actualTitle = infoResponse.getTitle();

        // THEN: The title should match the expected value
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetDescription() {
        // GIVEN: An InfoResponse object initialized with a description
        String expectedDescription = "Test Description";

        // WHEN: Retrieving the description
        String actualDescription = infoResponse.getDescription();

        // THEN: The description should match the expected value
        assertEquals(expectedDescription, actualDescription);
    }
}
