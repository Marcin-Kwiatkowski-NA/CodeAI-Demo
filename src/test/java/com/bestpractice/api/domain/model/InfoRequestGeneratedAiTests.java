package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@Test
public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void testConvert_validInput() {
        // GIVEN: Setup the InfoRequest with valid title and description.
        String id = "123";
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");

        // WHEN: Convert the InfoRequest to an Info object.
        Info info = infoRequest.convert(id);

        // THEN: Verify that the Info object has the correct title and description.
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals(id, info.getId());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN: Setup the InfoRequest with an empty title and a description.
        String id = "456";
        infoRequest.setTitle("");
        infoRequest.setDescription("Another Description");

        // WHEN: Convert the InfoRequest to an Info object.
        Info info = infoRequest.convert(id);

        // THEN: Verify that the Info object has the correct title and description.
        assertEquals("Another Description", info.getDescription());
        assertEquals(id, info.getId());
        assertEquals("", info.getTitle());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN: Setup the InfoRequest with a title and an empty description.
        String id = "789";
        infoRequest.setTitle("Yet Another Title");
        infoRequest.setDescription("");

        // WHEN: Convert the InfoRequest to an Info object.
        Info info = infoRequest.convert(id);

        // THEN: Verify that the Info object has the correct title and description.
        assertEquals("Yet Another Title", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals(id, info.getId());
    }

    @Test
    void testConvert_bothEmpty() {
        // GIVEN: Setup the InfoRequest with empty title and description.
        String id = "111";
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN: Convert the InfoRequest to an Info object.
        Info info = infoRequest.convert(id);

        // THEN: Verify that the Info object has the correct title and description.
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals(id, info.getId());
    }
}
