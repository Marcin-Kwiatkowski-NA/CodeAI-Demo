package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        new InfoRequest();
    }

    @Test
    void testConvert() {
        // GIVEN a valid InfoRequest
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");

        // WHEN the convert method is called
        Info info = request.convert("123");

        // THEN the resulting Info object should have the correct values
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN a new InfoRequest
        InfoRequest request = new InfoRequest();

        // WHEN the title is set to a new value
        request.setTitle("New Title");

        // THEN the title should be updated
        assertEquals("New Title", request.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN a new InfoRequest
        InfoRequest request = new InfoRequest();

        // WHEN the description is set to a new value
        request.setDescription("New Description");

        // THEN the description should be updated
        assertEquals("New Description", request.getDescription());
    }
}
