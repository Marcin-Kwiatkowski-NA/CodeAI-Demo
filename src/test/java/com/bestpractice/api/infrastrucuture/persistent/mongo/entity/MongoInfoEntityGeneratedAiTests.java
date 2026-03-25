package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        ObjectId expectedId = new ObjectId();
        mongoInfoEntity.setId(expectedId);
        assertEquals(expectedId, mongoInfoEntity.getId());
    }

    @Test
    void testSetAndGetTitle() {
        String expectedTitle = "Sample Title";
        mongoInfoEntity.setTitle(expectedTitle);
        assertEquals(expectedTitle, mongoInfoEntity.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        String expectedDescription = "Sample Description";
        mongoInfoEntity.setDescription(expectedDescription);
        assertEquals(expectedDescription, mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertFromInfo() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Title From Info");
        info.setDescription("Description From Info");

        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        assertNotNull(result);
        assertEquals(info.getTitle(), result.getTitle());
        assertEquals(info.getDescription(), result.getDescription());
        assertNotNull(result.getId());
    }

    @Test
    void testConvertFromInfoThrowsExceptionWhenInvalidId() {
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToInfo() {
        ObjectId id = new ObjectId();
        String title = "Converted Title";
        String description = "Converted Description";
        mongoInfoEntity = new MongoInfoEntity(id, title, description);

        Info result = mongoInfoEntity.convertTo();

        assertNotNull(result);
        assertEquals(id.toString(), result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
    }

    @Test
    void testConstructorWithParameters() {
        ObjectId id = new ObjectId();
        String title = "Param Title";
        String description = "Param Description";

        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void testDefaultConstructor() {
        MongoInfoEntity entity = new MongoInfoEntity();
        assertNull(entity.getId());
        assertNull(entity.getTitle());
        assertNull(entity.getDescription());
    }

    @Test
    void testConvertToInfoWithNullFields() {
        mongoInfoEntity = new MongoInfoEntity();
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);

        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }
}
