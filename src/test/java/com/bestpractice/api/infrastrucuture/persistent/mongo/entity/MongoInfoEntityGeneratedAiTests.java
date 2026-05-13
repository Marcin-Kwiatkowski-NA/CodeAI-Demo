package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
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
    void testConstructorWithParameters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Title";
        String description = "Description";

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
        info.setTitle("Converted Title");
        info.setDescription("Converted Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNotNull();
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    void testConvertToInfo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Entity Title";
        String description = "Entity Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertThat(info).isNotNull();
        assertEquals(id.toString(), info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    void testConvertFromWithNullValues() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(null);
        info.setDescription(null);

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(entity).isNotNull();
        assertEquals(null, entity.getTitle());
        assertEquals(null, entity.getDescription());
    }

    @Test
    void testConvertToWithNullValues() {
        // GIVEN
        mongoInfoEntity.setId(new ObjectId());
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertThat(info).isNotNull();
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }

    @Test
    void testConvertFromThrowsExceptionWhenInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromThrowsExceptionWhenIdIsNull() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }
}
