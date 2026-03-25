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
        infoResponse = new InfoResponse("123", "Sample Title", "Sample Description");
    }

    @Test
    void testGetIdReturnsExpectedValue() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTitleReturnsExpectedValue() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetDescriptionReturnsExpectedValue() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConstructorHandlesNullValues() {
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
    void testConstructorWithEmptyStrings() {
        // GIVEN
        InfoResponse emptyResponse = new InfoResponse("", "", "");

        // WHEN
        String id = emptyResponse.getId();
        String title = emptyResponse.getTitle();
        String description = emptyResponse.getDescription();

        // THEN
        assertEquals("", id);
        assertEquals("", title);
        assertEquals("", description);
    }
}
