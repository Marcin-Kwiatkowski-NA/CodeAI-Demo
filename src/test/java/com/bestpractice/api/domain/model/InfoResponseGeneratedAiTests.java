package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void getId_shouldReturnCorrectId() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void getTitle_shouldReturnCorrectTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void constructor_shouldInitializeAllFieldsCorrectly() {
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

    @Test
    void constructor_shouldHandleNullValuesGracefully() {
        // GIVEN
        String id = null;
        String title = null;
        String description = null;

        // WHEN
        InfoResponse response = new InfoResponse(id, title, description);

        // THEN
        assertEquals(null, response.getId());
        assertEquals(null, response.getTitle());
        assertEquals(null, response.getDescription());
    }

    @Test
    void constructor_shouldNotThrowExceptionForValidInputs() {
        // GIVEN
        String id = "789";
        String title = "Valid Title";
        String description = "Valid Description";

        // WHEN
        InfoResponse response = new InfoResponse(id, title, description);

        // THEN
        assertEquals("789", response.getId());
        assertEquals("Valid Title", response.getTitle());
        assertEquals("Valid Description", response.getDescription());
    }

    @Test
    void constructor_shouldNotThrowExceptionForNullInputs() {
        // GIVEN
        String id = null;
        String title = null;
        String description = null;

        // WHEN
        InfoResponse response = new InfoResponse(id, title, description);

        // THEN
        assertEquals(null, response.getId());
        assertEquals(null, response.getTitle());
        assertEquals(null, response.getDescription());
    }
}
