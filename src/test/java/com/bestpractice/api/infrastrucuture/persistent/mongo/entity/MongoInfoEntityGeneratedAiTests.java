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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testConvertFromInfoWithInvalidObjectIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromInfoWithNullInfoThrowsException() {
        // GIVEN
        Info info = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToInfoWithNullIdThrowsException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    // IMPROVEMENTS AND EDGE CASE TESTS

    @Test
    void testConvertFromInfoWithNullTitleAndDescription() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(null);
        info.setDescription(null);

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(null, entity.getTitle());
        assertEquals(null, entity.getDescription());
    }

    @Test
    void testConvertToInfoWithNullTitleAndDescription() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(id.toString(), info.getId());
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }

    @Test
    void testConvertFromInfoWithEmptyStrings() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("");
        info.setDescription("");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals("", entity.getTitle());
        assertEquals("", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithEmptyStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("");
        mongoInfoEntity.setDescription("");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithWhitespaceStrings() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("   ");
        info.setDescription("   ");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals("   ", entity.getTitle());
        assertEquals("   ", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithWhitespaceStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("   ");
        mongoInfoEntity.setDescription("   ");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals("   ", info.getTitle());
        assertEquals("   ", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithLongStrings() {
        // GIVEN
        String longTitle = "A".repeat(1000);
        String longDescription = "B".repeat(2000);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(longTitle);
        info.setDescription(longDescription);

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals(longTitle, entity.getTitle());
        assertEquals(longDescription, entity.getDescription());
    }

    @Test
    void testConvertToInfoWithLongStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        String longTitle = "C".repeat(1000);
        String longDescription = "D".repeat(2000);
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(longTitle);
        mongoInfoEntity.setDescription(longDescription);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals(longTitle, info.getTitle());
        assertEquals(longDescription, info.getDescription());
    }

    @Test
    void testConvertFromInfoWithSingleCharacterStrings() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("A");
        info.setDescription("B");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals("A", entity.getTitle());
        assertEquals("B", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithSingleCharacterStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("X");
        mongoInfoEntity.setDescription("Y");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals("X", info.getTitle());
        assertEquals("Y", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithMixedCaseStrings() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("TitleCase");
        info.setDescription("DescriptionCase");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals("TitleCase", entity.getTitle());
        assertEquals("DescriptionCase", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithMixedCaseStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("TitleCase");
        mongoInfoEntity.setDescription("DescriptionCase");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals("TitleCase", info.getTitle());
        assertEquals("DescriptionCase", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithSpecialCharacters() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("!@#$%^&*()");
        info.setDescription("<>{}[]");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertNotNull(entity);
        assertEquals("!@#$%^&*()", entity.getTitle());
        assertEquals("<>{}[]", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithSpecialCharacters() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("!@#$%^&*()");
        mongoInfoEntity.setDescription("<>{}[]");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertNotNull(info);
        assertEquals("!@#$%^&*()", info.getTitle());
        assertEquals("<>{}[]", info.getDescription());
    }
}
