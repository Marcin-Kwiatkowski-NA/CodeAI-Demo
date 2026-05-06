package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("Entity Title");
        mongoInfoEntity.setDescription("Entity Description");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals("Entity Title", info.getTitle());
        assertEquals("Entity Description", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithNullValues() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(null);
        info.setDescription(null);

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertNull(entity.getTitle());
        assertNull(entity.getDescription());
    }

    @Test
    void testConvertFromInfoThrowsExceptionWhenIdIsInvalid() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToThrowsExceptionWhenIdIsNull() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
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
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("");
        mongoInfoEntity.setDescription("");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
