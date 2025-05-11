package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoInfoEntityGeneratedAiTests {

    @Test
    void testConvertFrom() {
        // GIVEN a Info object
        Info info = new Info();
        info.setId("123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN convertFrom is called
        MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);

        // THEN verify the converted object
        assertEquals("123", mongoInfoEntity.getId().toString());
        assertEquals("Test Title", mongoInfoEntity.getTitle());
        assertEquals("Test Description", mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertTo() {
        // GIVEN a MongoInfoEntity object
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity(new ObjectId("456"), "Another Title", "Another Description");

        // WHEN convertTo is called
        Info info = mongoInfoEntity.convertTo();

        // THEN verify the converted object
        assertEquals("456", info.getId());
        assertEquals("Another Title", info.getTitle());
        assertEquals("Another Description", info.getDescription());
    }
}
