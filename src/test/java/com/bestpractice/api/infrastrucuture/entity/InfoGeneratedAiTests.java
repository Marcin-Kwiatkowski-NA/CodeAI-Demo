package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class InfoGeneratedAiTests {

    @BeforeEach
    public void setUp() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the object is created
        // THEN the createdAt field should be initialized with the current timestamp
        info.setCreatedAt(new Date());
    }

    @Test
    public void testGetSetId() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the id is set
        info.setId("123");
        // THEN the id should be set to "123"
        assertEquals("123", info.getId());
    }

    @Test
    public void testGetSetTitle() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the title is set
        info.setTitle("Test Title");
        // THEN the title should be set to "Test Title"
        assertEquals("Test Title", info.getTitle());
    }

    @Test
    public void testGetSetDescription() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the description is set
        info.setDescription("Test Description");
        // THEN the description should be set to "Test Description"
        assertEquals("Test Description", info.getDescription());
    }
}
