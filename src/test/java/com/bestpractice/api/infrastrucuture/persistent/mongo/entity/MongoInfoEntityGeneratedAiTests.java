package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
        ObjectId id = new ObjectId();
        String title = "Sample Title";
        String description = "Sample Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);
        assertEquals(id, mongoInfoEntity.getId());
        assertEquals(title, mongoInfoEntity.getTitle());
        assertEquals(description, mongoInfoEntity.getDescription());
    }

    @Test
    void testConstructorWithParameters() {
        ObjectId id = new ObjectId();
        String title = "Title";
        String description = "Description";
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void testConvertFromInfo() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Converted Title");
        info.setDescription("Converted Description");
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
        assertThat(entity.getId()).isNotNull();
        assertEquals("Converted Title", entity.getTitle());
        assertEquals("Converted Description", entity.getDescription());
    }

    @Test
    void testConvertToInfo() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("Title To Convert");
        mongoInfoEntity.setDescription("Description To Convert");
        Info info = mongoInfoEntity.convertTo();
        assertEquals(id.toString(), info.getId());
        assertEquals("Title To Convert", info.getTitle());
        assertEquals("Description To Convert", info.getDescription());
    }

    @Test
    void testConvertFromHandlesNullFieldsGracefully() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(null);
        info.setDescription(null);
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
        assertThat(entity.getId()).isNotNull();
        assertEquals(null, entity.getTitle());
        assertEquals(null, entity.getDescription());
    }

    @Test
    void testConvertToHandlesNullFieldsGracefully() {
        mongoInfoEntity.setId(new ObjectId());
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);
        Info info = mongoInfoEntity.convertTo();
        assertEquals(mongoInfoEntity.getId().toString(), info.getId());
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }

    @Test
    void testConvertFromThrowsExceptionWhenInfoIsNull() {
        Info info = null;
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromThrowsExceptionWhenInfoIdIsNull() {
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromThrowsExceptionWhenInfoIdIsInvalid() {
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToThrowsExceptionWhenIdIsNull() {
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    void testConvertFromWithEmptyStrings() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("");
        info.setDescription("");
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
        assertEquals("", entity.getTitle());
        assertEquals("", entity.getDescription());
    }

    @Test
    void testConvertToWithEmptyStrings() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("");
        mongoInfoEntity.setDescription("");
        Info info = mongoInfoEntity.convertTo();
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvertFromWithSpecialCharacters() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("!@#$%^&*()");
        info.setDescription("<>{}[]|\\/~`");
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
        assertEquals("!@#$%^&*()", entity.getTitle());
        assertEquals("<>{}[]|\\/~`", entity.getDescription());
    }

    @Test
    void testConvertToWithSpecialCharacters() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("!@#$%^&*()");
        mongoInfoEntity.setDescription("<>{}[]|\\/~`");
        Info info = mongoInfoEntity.convertTo();
        assertEquals("!@#$%^&*()", info.getTitle());
        assertEquals("<>{}[]|\\/~`", info.getDescription());
    }
}
