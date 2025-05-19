package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import java.util.Date;

class InfoGeneratedAiTests {

    @Test
    void testGetAndSetId() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the id is set to "123"
        info.setId("123");
        // THEN the id should be "123"
        assertEquals("123", info.getId());
    }

    @Test
    void testGetAndSetTitle() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the title is set to "My Title"
        info.setTitle("My Title");
        // THEN the title should be "My Title"
        assertEquals("My Title", info.getTitle());
    }

    @Test
    void testGetAndSetDescription() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the description is set to "My Description"
        info.setDescription("My Description");
        // THEN the description should be "My Description"
        assertEquals("My Description", info.getDescription());
    }
}
