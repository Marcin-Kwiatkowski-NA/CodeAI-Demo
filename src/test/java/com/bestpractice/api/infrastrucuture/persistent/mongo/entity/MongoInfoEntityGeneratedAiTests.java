package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoInfoEntityGeneratedAiTests {
    private MongoInfoEntity entity;

    @Test
    void constructorTest() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");
        assertNotNull(entity.getId());
        assertEquals("Test Title", entity.getTitle());
        assertEquals("Test Description", entity.getDescription());
    }

    @Test
    void setIdTest() {
        MongoInfoEntity entity = new MongoInfoEntity();
        ObjectId newId = new ObjectId();
        entity.setId(newId);
        assertEquals(newId, entity.getId());
    }

    @Test
    void setTitleTest() {
        MongoInfoEntity entity = new MongoInfoEntity();
        entity.setTitle("New Title");
        assertEquals("New Title", entity.getTitle());
    }

    @Test
    void setDescriptionTest() {
        MongoInfoEntity entity = new MongoInfoEntity();
        entity.setDescription("New Description");
        assertEquals("New Description", entity.getDescription());
    }

    @Test
    void convertFromTest() {
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
        assertEquals(new ObjectId(), entity.getId());
        assertEquals("Test Title", entity.getTitle());
        assertEquals("Test Description", entity.getDescription());
    }

    @Test
    void convertToTest() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");
        Info info = entity.convertTo();
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
