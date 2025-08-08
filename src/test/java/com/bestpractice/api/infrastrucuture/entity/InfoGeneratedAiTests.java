package com.bestpractice.api.infrastrucuture.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyExtension.class)
public class InfoGeneratedAiTests {

    @Test
    public void testGetSetId() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the id is set
        info.setId("123");
        // THEN the id should be set correctly
        assertEquals("123", info.getId());
    }

    @Test
    public void testGetSetTitle() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the title is set
        info.setTitle("Test Title");
        // THEN the title should be set correctly
        assertEquals("Test Title", info.getTitle());
    }

    @Test
    public void testGetSetDescription() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the description is set
        info.setDescription("Test Description");
        // THEN the description should be set correctly
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    public void testGetCreatedAt() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the object is created
        // THEN the createdAt field should be initialized with the current date
        assertEquals(new Date(), info.getCreatedAt());
    }

    @Test
    public void testGetSetIdAndCreatedAt() {
        // GIVEN a new Info object
        Info info = new Info();
        // WHEN the id is set and the object is created
        // THEN the id should be set and the createdAt field should be initialized with the current date
        assertEquals("123", info.getId());
        assertEquals(new Date(), info.getCreatedAt());
    }
}

// Dummy extension to satisfy the annotation requirement
class MyExtension {}