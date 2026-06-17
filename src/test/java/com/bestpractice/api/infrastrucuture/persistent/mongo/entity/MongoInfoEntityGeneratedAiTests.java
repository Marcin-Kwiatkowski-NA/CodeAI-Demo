package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
    void testConvertFromInfoToMongoInfoEntity() {
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
    void testConvertToInfoFromMongoInfoEntity() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("Mongo Title");
        mongoInfoEntity.setDescription("Mongo Description");
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals(id.toString(), result.getId());
        assertEquals("Mongo Title", result.getTitle());
        assertEquals("Mongo Description", result.getDescription());
    }

    @Test
    void testAllArgsConstructor() {
        ObjectId id = new ObjectId();
        String title = "Constructor Title";
        String description = "Constructor Description";
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);
        assertEquals(id, entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    void testConvertFromWithNullInfoThrowsException() {
        Info info = null;
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromWithInvalidObjectIdThrowsException() {
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromWithNullIdThrowsException() {
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToWithNullIdThrowsException() {
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    void testSetAndGetEmptyTitle() {
        String emptyTitle = "";
        mongoInfoEntity.setTitle(emptyTitle);
        assertEquals("", mongoInfoEntity.getTitle());
    }

    @Test
    void testSetAndGetWhitespaceTitle() {
        String whitespaceTitle = "   ";
        mongoInfoEntity.setTitle(whitespaceTitle);
        assertEquals("   ", mongoInfoEntity.getTitle());
    }

    @Test
    void testSetAndGetEmptyDescription() {
        String emptyDescription = "";
        mongoInfoEntity.setDescription(emptyDescription);
        assertEquals("", mongoInfoEntity.getDescription());
    }

    @Test
    void testSetAndGetWhitespaceDescription() {
        String whitespaceDescription = "   ";
        mongoInfoEntity.setDescription(whitespaceDescription);
        assertEquals("   ", mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertFromWithEmptyStrings() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("");
        info.setDescription("");
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);
        assertNotNull(result);
        assertEquals("", result.getTitle());
        assertEquals("", result.getDescription());
    }

    @Test
    void testConvertFromWithWhitespaceStrings() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("   ");
        info.setDescription("   ");
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);
        assertNotNull(result);
        assertEquals("   ", result.getTitle());
        assertEquals("   ", result.getDescription());
    }

    @Test
    void testConvertToWithEmptyStrings() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("");
        mongoInfoEntity.setDescription("");
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals("", result.getTitle());
        assertEquals("", result.getDescription());
    }

    @Test
    void testConvertToWithWhitespaceStrings() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("   ");
        mongoInfoEntity.setDescription("   ");
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals("   ", result.getTitle());
        assertEquals("   ", result.getDescription());
    }

    @Test
    void testConvertFromWithSingleCharacterStrings() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("A");
        info.setDescription("B");
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);
        assertNotNull(result);
        assertEquals("A", result.getTitle());
        assertEquals("B", result.getDescription());
    }

    @Test
    void testConvertToWithSingleCharacterStrings() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("X");
        mongoInfoEntity.setDescription("Y");
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals("X", result.getTitle());
        assertEquals("Y", result.getDescription());
    }

    @Test
    void testConvertFromWithLongStrings() {
        String longTitle = "T".repeat(1000);
        String longDescription = "D".repeat(2000);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(longTitle);
        info.setDescription(longDescription);
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);
        assertNotNull(result);
        assertEquals(longTitle, result.getTitle());
        assertEquals(longDescription, result.getDescription());
    }

    @Test
    void testConvertToWithLongStrings() {
        ObjectId id = new ObjectId();
        String longTitle = "T".repeat(1000);
        String longDescription = "D".repeat(2000);
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(longTitle);
        mongoInfoEntity.setDescription(longDescription);
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals(longTitle, result.getTitle());
        assertEquals(longDescription, result.getDescription());
    }

    @Test
    void testConvertFromWithNullTitleAndDescription() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(null);
        info.setDescription(null);
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);
        assertNotNull(result);
        assertEquals(null, result.getTitle());
        assertEquals(null, result.getDescription());
    }

    @Test
    void testConvertToWithNullTitleAndDescription() {
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);
        Info result = mongoInfoEntity.convertTo();
        assertNotNull(result);
        assertEquals(null, result.getTitle());
        assertEquals(null, result.getDescription());
    }
}
