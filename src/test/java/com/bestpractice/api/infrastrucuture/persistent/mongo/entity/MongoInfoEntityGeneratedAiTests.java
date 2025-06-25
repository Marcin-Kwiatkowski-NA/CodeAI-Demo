package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoInfoEntityGeneratedAiTests.class)
class MongoInfoEntityGeneratedAiTests {
    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity(new ObjectId("64f4d314359428683784898b"), new Info());
    }

    @Test
    void testConvertFrom() {
        // GIVEN: Create an Info object
        Info info = new Info();
        info.setId("someId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert the Info object to a MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);

        // THEN: Assert that the converted MongoInfoEntity has the correct values
        assertEquals("someId", mongoInfoEntity.getId().toString());
        assertEquals("Test Title", mongoInfoEntity.getTitle());
        assertEquals("Test Description", mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertAndConvertBack() {
        // GIVEN: Create an Info object
        Info info = new Info();
        info.setId("anotherId");
        info.setTitle("Another Title");
        info.setDescription("Another Description");

        // WHEN: Convert the Info object to a MongoInfoEntity and back to Info
        MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);
        Info convertedInfo = mongoInfoEntity.convertTo();

        // THEN: Assert that the converted Info object has the correct values
        assertEquals("anotherId", convertedInfo.getId().toString());
        assertEquals("Another Title", convertedInfo.getTitle());
        assertEquals("Another Description", convertedInfo.getDescription());
    }

    @Test
    void testSetIdAndGetId() {
        // GIVEN: Initialize a MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the ID
        mongoInfoEntity.setId(new ObjectId("newId"));

        // THEN: Assert that the ID has been set correctly
        assertEquals("newId", mongoInfoEntity.getId().toString());
    }

    @Test
    void testSetTitleAndGetTitle() {
        // GIVEN: Initialize a MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the title
        mongoInfoEntity.setTitle("New Title");

        // THEN: Assert that the title has been set correctly
        assertEquals("New Title", mongoInfoEntity.getTitle());
    }

    @Test
    void testSetDescriptionAndGetDescription() {
        // GIVEN: Initialize a MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();

        // WHEN: Set the description
        mongoInfoEntity.setDescription("New Description");

        // THEN: Assert that the description has been set correctly
        assertEquals("New Description", mongoInfoEntity.getDescription());
    }
}
