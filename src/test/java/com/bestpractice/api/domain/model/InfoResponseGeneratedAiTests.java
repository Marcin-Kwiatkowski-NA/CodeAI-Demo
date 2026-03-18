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

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void shouldReturnCorrectId() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void shouldReturnCorrectTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void shouldReturnCorrectDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN
        InfoResponse nullResponse = new InfoResponse(null, null, null);

        // WHEN
        String id = nullResponse.getId();
        String title = nullResponse.getTitle();
        String description = nullResponse.getDescription();

        // THEN
        assertNull(id);
        assertNull(title);
        assertNull(description);
    }

    @Test
    void shouldCreateObjectWithAllFields() {
        // GIVEN
        String id = "456";
        String title = "Another Title";
        String description = "Another Description";

        // WHEN
        InfoResponse response = new InfoResponse(id, title, description);

        // THEN
        assertEquals(id, response.getId());
        assertEquals(title, response.getTitle());
        assertEquals(description, response.getDescription());
    }
}
