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
        // GIVEN: Setup preconditions
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";

        // WHEN: Create a MongoInfoEntity object
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN: Verify that the object's fields are set correctly
        assertEquals(id.toString(), entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void setIdTest() {
        // GIVEN: Setup preconditions
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set the ID
        entity.setId(new ObjectId());

        // THEN: Verify that the ID is set correctly
        assertEquals(new ObjectId().toString(), entity.getId());
    }

    @Test
    void setTitleTest() {
        // GIVEN: Setup preconditions
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set the title
        entity.setTitle("New Title");

        // THEN: Verify that the title is set correctly
        assertEquals("New Title", entity.getTitle());
    }

    @Test
    void setDescriptionTest() {
        // GIVEN: Setup preconditions
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN: Set the description
        entity.setDescription("New Description");

        // THEN: Verify that the description is set correctly
        assertEquals("New Description", entity.getDescription());
    }

    @Test
    void convertFromTest() {
        // GIVEN: Setup preconditions
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert Info to MongoInfoEntity
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN: Verify that the entity's fields are set correctly
        assertEquals(new ObjectId().toString(), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    void convertToTest() {
        // GIVEN: Setup preconditions
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");

        // WHEN: Convert MongoInfoEntity to Info
        Info info = entity.convertTo();

        // THEN: Verify that the info object's fields are set correctly
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
