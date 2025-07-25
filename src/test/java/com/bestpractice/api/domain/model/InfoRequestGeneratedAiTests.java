package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Test
class InfoRequestGeneratedAiTests {

    @Test
    void testConvert_validInput() {
        // GIVEN: Setup
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");

        // WHEN: Convert InfoRequest to Info
        Info info = request.convert("123");

        // THEN: Assertions
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN: Setup
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("Test Description");

        // WHEN: Convert InfoRequest to Info
        Info info = request.convert("123");

        // THEN: Assertions
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN: Setup
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("");

        // WHEN: Convert InfoRequest to Info
        Info info = request.convert("123");

        // THEN: Assertions
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvert_emptyTitleAndDescription() {
        // GIVEN: Setup
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("");

        // WHEN: Convert InfoRequest to Info
        Info info = request.convert("123");

        // THEN: Assertions
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
