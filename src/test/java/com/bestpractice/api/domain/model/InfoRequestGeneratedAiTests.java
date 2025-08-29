package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.entity.Info;

public class InfoRequestGeneratedAiTests {

    @Test
    public void testConvert_validInput() {
        // GIVEN: A new InfoRequest object is created with title and description.
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("Test Description");

        // WHEN: The convert method is called with "123" as the id.
        Info info = request.convert("123");

        // THEN: The resulting Info object has the correct title and description, and the id is "123".
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    public void testConvert_emptyTitle() {
        // GIVEN: A new InfoRequest object is created with an empty title and description.
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("Test Description");

        // WHEN: The convert method is called with "123" as the id.
        Info info = request.convert("123");

        // THEN: The resulting Info object has the correct id, but the title and description are empty.
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvert_emptyDescription() {
        // GIVEN: A new InfoRequest object is created with a title and an empty description.
        InfoRequest request = new InfoRequest();
        request.setTitle("Test Title");
        request.setDescription("");

        // WHEN: The convert method is called with "123" as the id.
        Info info = request.convert("123");

        // THEN: The resulting Info object has the correct id, but the title and description are empty.
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvert_emptyTitleAndDescription() {
        // GIVEN: A new InfoRequest object is created with empty title and description.
        InfoRequest request = new InfoRequest();
        request.setTitle("");
        request.setDescription("");

        // WHEN: The convert method is called with "123" as the id.
        Info info = request.convert("123");

        // THEN: The resulting Info object has the correct id, but the title and description are empty.
        assertEquals("123", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    public void testConvert_nullTitle() {
        // GIVEN: A new InfoRequest object is created with a null title and description.
        InfoRequest request = new InfoRequest();
        request.setTitle(null);
        request.setDescription("Test Description");

        // WHEN: The convert method is called with "123" as the id.
        Info info = request.convert("123");

        // THEN: The resulting Info object has the correct id, but the title and description are empty.
        assertEquals("123", info.getId());
        assertEquals(null, info.getTitle());
        assertEquals("", info.getDescription());
    }
}