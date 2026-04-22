package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Sample Title";
        String description = "Sample Description";

        // WHEN
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // THEN
        assertEquals(id, mongoInfoEntity.getId());
        assertEquals(title, mongoInfoEntity.getTitle());
        assertEquals(description, mongoInfoEntity.getDescription());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals("Info Title", entity.getTitle());
        assertEquals("Info Description", entity.getDescription());
    }

    @Test
    void testConvertFromInfoThrowsExceptionWhenInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Invalid Title");
        info.setDescription("Invalid Description");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("Mongo Title");
        mongoInfoEntity.setDescription("Mongo Description");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals("Mongo Title", info.getTitle());
        assertEquals("Mongo Description", info.getDescription());
    }

    @Test
    void testConvertFromAndConvertToConsistency() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId(new ObjectId().toString());
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(originalInfo);
        Info convertedBack = entity.convertTo();

        // THEN
        assertEquals(originalInfo.getTitle(), convertedBack.getTitle());
        assertEquals(originalInfo.getDescription(), convertedBack.getDescription());
        assertEquals(entity.getId().toString(), convertedBack.getId());
    }

    @Test
    void testConvertToInfoThrowsExceptionWhenIdIsNull() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title Without ID");
        mongoInfoEntity.setDescription("Description Without ID");

        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }
}
