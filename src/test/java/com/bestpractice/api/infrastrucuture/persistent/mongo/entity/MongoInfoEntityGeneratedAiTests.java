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
    private MongoInfoEntity mongoInfoEntity;

    @Test
    void testConvertFromInfo() {
        // GIVEN: Create an Info object
        Info info = new Info();
        info.setId("12345");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert the Info object to a MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);

        // THEN: Assert that the converted MongoInfoEntity has the correct values
        assertEquals("12345", mongoInfoEntity.getId().toString());
        assertEquals("Test Title", mongoInfoEntity.getTitle());
        assertEquals("Test Description", mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertToJson() {
        // GIVEN: Create a MongoInfoEntity object
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();
        mongoInfoEntity.setId("67890");
        mongoInfoEntity.setTitle("Another Title");
        mongoInfoEntity.setDescription("Another Description");

        // WHEN: Convert the MongoInfoEntity to an Info object
        Info info = mongoInfoEntity.convertTo();

        // THEN: Assert that the converted Info object has the correct values
        assertEquals("67890", info.getId());
        assertEquals("Another Title", info.getTitle());
        assertEquals("Another Description", info.getDescription());
    }

    @Test
    void testSetId() {
        // GIVEN: Create a MongoInfoEntity object
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the ID
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);

        // THEN: Assert that the ID has been set correctly
        assertEquals(id, mongoInfoEntity.getId());
    }

    @Test
    void testSetTitle() {
        // GIVEN: Create a MongoInfoEntity object
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the title
        String title = "New Title";
        mongoInfoEntity.setTitle(title);

        // THEN: Assert that the title has been set correctly
        assertEquals(title, mongoInfoEntity.getTitle());
    }

    @Test
    void testSetDescription() {
        // GIVEN: Create a MongoInfoEntity object
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the description
        String description = "New Description";
        mongoInfoEntity.setDescription(description);

        // THEN: Assert that the description has been set correctly
        assertEquals(description, mongoInfoEntity.getDescription());
    }
}
