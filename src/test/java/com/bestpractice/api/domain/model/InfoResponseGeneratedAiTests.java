package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN a new InfoResponse instance with predefined values
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void testGetIdReturnsCorrectValue() {
        // WHEN retrieving the id
        String id = infoResponse.getId();

        // THEN the id should match the value passed to the constructor
        assertThat(id).isEqualTo("123");
    }

    @Test
    void testGetTitleReturnsCorrectValue() {
        // WHEN retrieving the title
        String title = infoResponse.getTitle();

        // THEN the title should match the value passed to the constructor
        assertThat(title).isEqualTo("Test Title");
    }

    @Test
    void testGetDescriptionReturnsCorrectValue() {
        // WHEN retrieving the description
        String description = infoResponse.getDescription();

        // THEN the description should match the value passed to the constructor
        assertThat(description).isEqualTo("Test Description");
    }

    @Test
    void testConstructorSetsAllFields() {
        // GIVEN a new InfoResponse instance
        InfoResponse newResponse = new InfoResponse("456", "Another Title", "Another Description");

        // WHEN accessing the fields via getters
        String id = newResponse.getId();
        String title = newResponse.getTitle();
        String description = newResponse.getDescription();

        // THEN all fields should be set correctly
        assertThat(id).isEqualTo("456");
        assertThat(title).isEqualTo("Another Title");
        assertThat(description).isEqualTo("Another Description");
    }

    @Test
    void testConstructorHandlesNullValues() {
        // GIVEN a new InfoResponse instance with null fields
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN accessing the fields via getters
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN all getters should return null
        assertThat(id).isNull();
        assertThat(title).isNull();
        assertThat(description).isNull();
    }
}
