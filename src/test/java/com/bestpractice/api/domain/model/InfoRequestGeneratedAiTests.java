package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @Test
    void testConvert_validInput() {
        // GIVEN: Setup the InfoRequest with valid title and description
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");

        // WHEN: Convert the InfoRequest to an Info object
        Info info = infoRequest.convert("123");

        // THEN: Verify that the Info object has the correct title and description
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN: Setup the InfoRequest with an empty title and description
        infoRequest.setTitle("");
        infoRequest.setDescription("Test Description");

        // WHEN: Convert the InfoRequest to an Info object
        Info info = infoRequest.convert("123");

        // THEN: Verify that the Info object has the correct title and description
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN: Setup the InfoRequest with a title and an empty description
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("");

        // WHEN: Convert the InfoRequest to an Info object
        Info info = infoRequest.convert("123");

        // THEN: Verify that the Info object has the correct title and description
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    void testConvert_bothEmpty() {
        // GIVEN: Setup the InfoRequest with empty title and description
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN: Convert the InfoRequest to an Info object
        Info info = infoRequest.convert("123");

        // THEN: Verify that the Info object has the correct title and description
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }
}
