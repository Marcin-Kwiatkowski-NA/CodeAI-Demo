package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoInfoEntityGeneratedAiTests {

    @Test
    public void testConstructor() {
        // GIVEN: Setup
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";

        // WHEN: Create a MongoInfoEntity
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN: Verify the entity's state
        assertNotNull(entity);
        assertEquals(id.toString(), entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    public void testSetters() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set the ID
        ObjectId id = new ObjectId();
        entity.setId(id);

        // THEN: Verify the ID is set
        assertEquals(id.toString(), entity.getId());

        // WHEN: Set the title
        entity.setTitle("New Title");

        // THEN: Verify the title is set
        assertEquals("New Title", entity.getTitle());

        // WHEN: Set the description
        entity.setDescription("New Description");

        // THEN: Verify the description is set
        assertEquals("New Description", entity.getDescription());
    }

    @Test
    public void testConvertFrom() {
        // GIVEN: Setup
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert Info to MongoInfoEntity
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN: Verify the entity's state
        assertNotNull(entity);
        assertEquals(new ObjectId("test_id"), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    public void testConvertTo() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();
        entity.setId(new ObjectId());
        entity.setTitle("Test Title");
        entity.setDescription("Test Description");

        // WHEN: Convert MongoInfoEntity to Info
        Info info = entity.convertTo();

        // THEN: Verify the info's state
        assertNotNull(info);
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @BeforeEach
    public void beforeEachTest() {
        // Reset the state of the entity before each test
        new MongoInfoEntity();
    }
}
