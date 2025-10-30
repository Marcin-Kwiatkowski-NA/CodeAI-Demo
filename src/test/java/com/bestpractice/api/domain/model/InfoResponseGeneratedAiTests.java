package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        id = "123";
        title = "Sample Title";
        description = "Sample Description";
    }

    @Test
    void givenValidParameters_whenGetId_thenReturnsCorrectId() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN
        String result = infoResponse.getId();

        // THEN
        assertEquals(id, result);
    }

    @Test
    void givenValidParameters_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN
        String result = infoResponse.getTitle();

        // THEN
        assertEquals(title, result);
    }

    @Test
    void givenValidParameters_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(id, title, description);

        // WHEN
        String result = infoResponse.getDescription();

        // THEN
        assertEquals(description, result);
    }

    @Test
    void givenNullValues_whenConstructor_thenFieldsAreNull() {
        // GIVEN
        String nullId = null;
        String nullTitle = null;
        String nullDescription = null;

        // WHEN
        InfoResponse infoResponse = new InfoResponse(nullId, nullTitle, nullDescription);

        // THEN
        assertEquals(nullId, infoResponse.getId());
        assertEquals(nullTitle, infoResponse.getTitle());
        assertEquals(nullDescription, infoResponse.getDescription());
    }

    @Test
    void givenEmptyStrings_whenConstructor_thenFieldsAreEmpty() {
        // GIVEN
        String emptyId = "";
        String emptyTitle = "";
        String emptyDescription = "";

        // WHEN
        InfoResponse infoResponse = new InfoResponse(emptyId, emptyTitle, emptyDescription);

        // THEN
        assertEquals(emptyId, infoResponse.getId());
        assertEquals(emptyTitle, infoResponse.getTitle());
        assertEquals(emptyDescription, infoResponse.getDescription());
    }

    @Test
    void givenNullId_whenGetId_thenReturnsNull() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(null, title, description);

        // WHEN
        String result = infoResponse.getId();

        // THEN
        assertEquals(null, result);
    }

    @Test
    void givenNullTitle_whenGetTitle_thenReturnsNull() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(id, null, description);

        // WHEN
        String result = infoResponse.getTitle();

        // THEN
        assertEquals(null, result);
    }

    @Test
    void givenNullDescription_whenGetDescription_thenReturnsNull() {
        // GIVEN
        InfoResponse infoResponse = new InfoResponse(id, title, null);

        // WHEN
        String result = infoResponse.getDescription();

        // THEN
        assertEquals(null, result);
    }
}
