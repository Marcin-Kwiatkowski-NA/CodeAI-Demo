package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    public InfoRequestGeneratedAiTests() {
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
        assertEquals("123", info.getId());
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
        assertEquals("Empty Title", info.getTitle());
        assertEquals("Empty Title Description", info.getDescription());
        assertEquals("456", info.getId());
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
        assertEquals("Empty Title Description", info.getDescription());
        assertEquals("789", info.getId());
    }

    @Test
    void testConvert_nullTitle() {
        // GIVEN: A valid InfoRequest object with a null title
        String id = "101";
        infoRequest.setTitle(null);
        infoRequest.setDescription("Null Title Description");

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("Null Title", info.getTitle());
        assertEquals("Null Title Description", info.getDescription());
        assertEquals("101", info.getId());
    }

    @Test
    void testConvert_nullDescription() {
        // GIVEN: A valid InfoRequest object with a null description
        String id = "112";
        infoRequest.setTitle("Null Description Title");
        infoRequest.setDescription(null);

        // WHEN: The InfoRequest is converted to an Info object
        Info info = infoRequest.convert(id);

        // THEN: The Info object should have the correct title and description, and the id
        assertEquals("Null Description Title", info.getTitle());
        assertEquals("Null Title Description", info.getDescription());
        assertEquals("112", info.getId());
    }
}
