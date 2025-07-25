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

        // WHEN: Create a MongoInfoEntity instance
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN: Verify the instance properties
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
        entity.setId(new ObjectId());

        // THEN: Verify the ID is updated
        assertEquals(new ObjectId().toString(), entity.getId());
    }

    @Test
    void setTitleTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set a new title
        entity.setTitle("New Title");

        // THEN: Verify the title is updated
        assertEquals("New Title", entity.getTitle());
    }

    @Test
    void setDescriptionTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set a new description
        entity.setDescription("New Description");

        // THEN: Verify the description is updated
        assertEquals("New Description", entity.getDescription());
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

        // THEN: Verify the converted entity
        assertEquals(new ObjectId().toString(), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    void convertToTest() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");

        // WHEN: Convert MongoInfoEntity to Info
        Info info = entity.convertTo();

        // THEN: Verify the converted Info
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
