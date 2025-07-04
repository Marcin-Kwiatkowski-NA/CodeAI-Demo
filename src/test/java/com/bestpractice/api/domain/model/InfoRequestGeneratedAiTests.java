package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class InfoRequestGeneratedAiTests {

    @Test
    void testConvert_validInput() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");

        // WHEN
        Info info = request.convert("123");

        // THEN
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyTitle() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("Test Description");

        // WHEN
        Info info = request.convert("123");

        // THEN
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyDescription() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("");

        // WHEN
        Info info = request.convert("123");

        // THEN
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvert_emptyTitleAndDescription() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("");

        // WHEN
        Info info = request.convert("123");

        // THEN
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
