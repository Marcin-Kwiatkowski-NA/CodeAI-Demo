package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void testConvert_validInput() {
        // GIVEN: A valid InfoRequest object with title and description
        String id = "123";
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals(id, info.getId());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN: A valid InfoRequest object with an empty title
        String id = "456";
        infoRequest.setTitle("");
        infoRequest.setDescription("Empty Title Description");

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("", info.getTitle());
        assertEquals("Empty Title Description", info.getDescription());
        assertEquals(id, info.getId());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN: A valid InfoRequest object with an empty description
        String id = "789";
        infoRequest.setTitle("Empty Description Title");
        infoRequest.setDescription("");

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("Empty Description Title", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals(id, info.getId());
    }

    @Test
    void testConvert_bothEmpty() {
        // GIVEN: A valid InfoRequest object with empty title and description
        String id = "112";
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals(id, info.getId());
    }
}
