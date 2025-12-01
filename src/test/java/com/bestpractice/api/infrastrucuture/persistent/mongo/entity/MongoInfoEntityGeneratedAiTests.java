package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MongoInfoEntityGeneratedAiTests {

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
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertFrom() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals(info.getId(), result.getId().toString());
        assertEquals(info.getTitle(), result.getTitle());
        assertEquals(info.getDescription(), result.getDescription());
    }

    @Test
    void testConvertFromWithInvalidId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalidObjectId");
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertTo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info result = mongoInfoEntity.convertTo();

        // THEN
        assertEquals(id.toString(), result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
    }

    @Test
    void testConvertToWithNullId() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Entity Title");
        mongoInfoEntity.setDescription("Entity Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    void testConvertFromWithNullInfo() {
        // GIVEN
        Info info = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromWithEmptyFields() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("");
        info.setDescription("");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals(info.getId(), result.getId().toString());
        assertEquals("", result.getTitle());
        assertEquals("", result.getDescription());
    }

    @Test
    void testConvertFromWithNullIdField() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }
}
