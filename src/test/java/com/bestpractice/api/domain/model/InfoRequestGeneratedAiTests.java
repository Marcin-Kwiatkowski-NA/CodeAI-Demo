package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InfoRequestGeneratedAiTests {

    @Test
    void testConvert_validInput() {
        // GIVEN: A valid InfoRequest object
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");

        // WHEN: The convert method is called with a valid ID
        Info info = request.convert("123");

        // THEN: The resulting Info object should have the correct values
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN: An InfoRequest object with an empty title
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("Test Description");

        // WHEN: The convert method is called with a valid ID
        Info info = request.convert("123");

        // THEN: The resulting Info object should have the correct values
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN: An InfoRequest object with an empty description
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("");

        // WHEN: The convert method is called with a valid ID
        Info info = request.convert("123");

        // THEN: The resulting Info object should have the correct values
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvert_emptyTitleAndDescription() {
        // GIVEN: An InfoRequest object with empty title and description
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("");

        // WHEN: The convert method is called with a valid ID
        Info info = request.convert("123");

        // THEN: The resulting Info object should have the correct values
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
