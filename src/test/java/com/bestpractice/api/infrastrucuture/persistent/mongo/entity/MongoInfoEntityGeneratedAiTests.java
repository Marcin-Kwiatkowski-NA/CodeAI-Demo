package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MongoInfoEntityGeneratedAiTests {

    private Info info;
    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    public void setUp() {
        // Reset state before each test
        info = new Info();
        info.setId("60b71e24c98f5d3a1c6f1b2c");  // Valid ObjectId string
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");

        mongoInfoEntity = new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
    }

    @Test
    public void testConvertFrom() {
        // GIVEN
        Info inputInfo = new Info();
        inputInfo.setId("60b71e24c98f5d3a1c6f1b2c");  // Valid ObjectId string
        inputInfo.setTitle("Sample Title");
        inputInfo.setDescription("Sample Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(inputInfo);

        // THEN
        assertNotNull(result);
        assertEquals(new ObjectId(inputInfo.getId()), result.getId());
        assertEquals(inputInfo.getTitle(), result.getTitle());
        assertEquals(inputInfo.getDescription(), result.getDescription());
    }

    @Test
    public void testConvertTo() {
        // GIVEN
        Info expectedInfo = new Info();
        expectedInfo.setId(mongoInfoEntity.getId().toString());
        expectedInfo.setTitle("Sample Title");
        expectedInfo.setDescription("Sample Description");

        // WHEN
        Info result = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(result);
        assertEquals(expectedInfo.getId(), result.getId());
        assertEquals(expectedInfo.getTitle(), result.getTitle());
        assertEquals(expectedInfo.getDescription(), result.getDescription());
    }

    @Test
    public void testSettersAndGetters() {
        // GIVEN
        ObjectId newId = new ObjectId();
        String newTitle = "New Title";
        String newDescription = "New Description";

        // WHEN
        mongoInfoEntity.setId(newId);
        mongoInfoEntity.setTitle(newTitle);
        mongoInfoEntity.setDescription(newDescription);

        // THEN
        assertEquals(newId, mongoInfoEntity.getId());
        assertEquals(newTitle, mongoInfoEntity.getTitle());
        assertEquals(newDescription, mongoInfoEntity.getDescription());
    }
}
