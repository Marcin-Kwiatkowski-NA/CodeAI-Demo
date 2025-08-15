package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @org.junit.jupiter.api.Test
    void testConvert_validInput() {
        // GIVEN: A valid InfoRequest object with title and description
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: The convert method is called with "123" as the id
        Info info = infoRequest.convert("123");

        // THEN: The resulting Info object should have the correct title, description, and id
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
        assertEquals("123", info.getId());
    }

    @org.junit.jupiter.api.Test
    void testConvert_emptyInput() {
        // GIVEN: An InfoRequest object with empty title and description
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN: The convert method is called with "123" as the id
        Info info = infoRequest.convert("123");

        // THEN: The resulting Info object should have the correct title, description, and id
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }

    @org.junit.jupiter.api.Test
    void testConvert_nullInput() {
        // GIVEN: An InfoRequest object with null title and description
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN: The convert method is called with "123" as the id
        Info info = infoRequest.convert("123");

        // THEN: The resulting Info object should have the correct title, description, and id
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("123", info.getId());
    }
}
