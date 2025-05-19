package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoInfoEntityGeneratedAiTests {
    @Test
    void constructorTest() {
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
    void setIdTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set a new ID
        ObjectId newId = new ObjectId();
        entity.setId(newId);

        // THEN: Verify the ID has been updated
        assertEquals(newId.toString(), entity.getId());
    }

    @Test
    void setTitleTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set a new title
        String newTitle = "New Title";
        entity.setTitle(newTitle);

        // THEN: Verify the title has been updated
        assertEquals(newTitle, entity.getTitle());
    }

    @Test
    void setDescriptionTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set a new description
        String newDescription = "New Description";
        entity.setDescription(newDescription);

        // THEN: Verify the description has been updated
        assertEquals(newDescription, entity.getDescription());
    }

    @Test
    void convertFromTest() {
        // GIVEN: Setup
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert Info to MongoInfoEntity
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN: Verify the entity's state
        assertEquals(new ObjectId(), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    void convertToTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");

        // WHEN: Convert MongoInfoEntity to Info
        Info info = entity.convertTo();

        // THEN: Verify the Info's state
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
