package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String title = "Sample Title";

        // WHEN
        infoRequest.setTitle(title);

        // THEN
        assertEquals(title, infoRequest.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String description = "Sample Description";

        // WHEN
        infoRequest.setDescription(description);

        // THEN
        assertEquals(description, infoRequest.getDescription());
    }

    @Test
    public void testConvert() {
        // GIVEN
        String id = "123";
        String title = "Sample Title";
        String description = "Sample Description";

        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }
}