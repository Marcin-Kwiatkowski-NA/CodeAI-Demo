package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testConvert_validInput() {
        // GIVEN: Setup the InfoRequest object with valid title and description.
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");

        // WHEN: The convert method is called with the ID "123".
        Info info = infoRequest.convert("123");

        // THEN: Verify that the resulting Info object has the correct title, description, and ID.
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    public void testConvert_emptyTitle() {
        // GIVEN: Setup the InfoRequest object with an empty title and a description.
        infoRequest.setTitle("");
        infoRequest.setDescription("Test Description");

        // WHEN: The convert method is called with the ID "123".
        Info info = infoRequest.convert("123");

        // THEN: Verify that the resulting Info object has the correct title, description, and ID.
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    public void testConvert_emptyDescription() {
        // GIVEN: Setup the InfoRequest object with a title and an empty description.
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("");

        // WHEN: The convert method is called with the ID "123".
        Info info = infoRequest.convert("123");

        // THEN: Verify that the resulting Info object has the correct title, description, and ID.
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    public void testConvert_nullTitle() {
        // GIVEN: Setup the InfoRequest object with a null title and a description.
        infoRequest.setTitle(null);
        infoRequest.setDescription("Test Description");

        // WHEN: The convert method is called with the ID "123".
        Info info = infoRequest.convert("123");

        // THEN: Verify that the resulting Info object has the correct title, description, and ID.
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @Test
    public void testConvert_nullDescription() {
        // GIVEN: Setup the InfoRequest object with a title and a null description.
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription(null);

        // WHEN: The convert method is called with the ID "123".
        Info info = infoRequest.convert("123");

        // THEN: Verify that the resulting Info object has the correct title, description, and ID.
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }
}
