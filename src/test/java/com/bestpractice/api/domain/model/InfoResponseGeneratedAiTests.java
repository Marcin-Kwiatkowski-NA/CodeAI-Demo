package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

    private String id;
    private String title;
    private String description;
    private InfoResponse infoResponse;

    @BeforeEach
    public void setUp() {
        id = "123";
        title = "Sample Title";
        description = "Sample Description";
        infoResponse = new InfoResponse(id, title, description);
    }

    @Test
    public void testGetId() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        String actualId = infoResponse.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        String actualTitle = infoResponse.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        String actualDescription = infoResponse.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }
}
