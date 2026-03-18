package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertFromInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(info.getTitle(), result.getTitle());
        assertEquals(info.getDescription(), result.getDescription());
    }

    @Test
    void testConvertFromInfoThrowsExceptionWhenInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Invalid Title");
        info.setDescription("Invalid Description");

        // WHEN & THEN
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
        Info result = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(result);
        assertEquals(id.toString(), result.getId());
        assertEquals("Mongo Title", result.getTitle());
        assertEquals("Mongo Description", result.getDescription());
    }

    @Test
    void testConvertToInfoThrowsExceptionWhenIdIsNull() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    void testAllArgsConstructor() {
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
    void testConvertFromInfoThrowsExceptionWhenInfoIsNull() {
        // GIVEN
        Info info = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToInfoWithEmptyFields() {
        // GIVEN
        mongoInfoEntity.setId(new ObjectId());
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);

        // WHEN
        Info result = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(result);
        assertEquals(mongoInfoEntity.getId().toString(), result.getId());
        assertEquals(null, result.getTitle());
        assertEquals(null, result.getDescription());
    }
}
