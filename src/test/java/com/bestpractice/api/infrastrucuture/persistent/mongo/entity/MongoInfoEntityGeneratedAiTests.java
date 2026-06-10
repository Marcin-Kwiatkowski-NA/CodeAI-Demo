package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertEquals(id.toString(), info.getId());
        assertEquals("Mongo Title", info.getTitle());
        assertEquals("Mongo Description", info.getDescription());
    }

    @Test
    void testConvertFromInfoThrowsIllegalArgumentExceptionWhenInvalidObjectId() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid_object_id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromInfoThrowsNullPointerExceptionWhenInfoIsNull() {
        // GIVEN
        Info info = null;

        // WHEN THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertToThrowsNullPointerExceptionWhenIdIsNull() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle("Title");
        mongoInfoEntity.setDescription("Description");

        // WHEN THEN
        assertThrows(NullPointerException.class, () -> mongoInfoEntity.convertTo());
    }

    @Test
    void testConvertFromInfoThrowsIllegalArgumentExceptionWhenInfoIdIsNull() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromInfoThrowsIllegalArgumentExceptionWhenEmptyId() {
        // GIVEN
        Info info = new Info();
        info.setId("");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid hexadecimal representation");
    }

    @Test
    void testConvertFromInfoWithValidMinimalData() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("");
        info.setDescription("");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals("", entity.getTitle());
        assertEquals("", entity.getDescription());
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
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithWhitespaceFields() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("   ");
        info.setDescription("   ");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals("   ", entity.getTitle());
        assertEquals("   ", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithWhitespaceFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("   ");
        mongoInfoEntity.setDescription("   ");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertEquals("   ", info.getTitle());
        assertEquals("   ", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithLongStrings() {
        // GIVEN
        String longText = "a".repeat(1000);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle(longText);
        info.setDescription(longText);

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals(longText, entity.getTitle());
        assertEquals(longText, entity.getDescription());
    }

    @Test
    void testConvertToInfoWithLongStrings() {
        // GIVEN
        String longText = "a".repeat(1000);
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(longText);
        mongoInfoEntity.setDescription(longText);

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertEquals(longText, info.getTitle());
        assertEquals(longText, info.getDescription());
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
        assertEquals("!@#$%^&*()", info.getTitle());
        assertEquals("<>{}[]", info.getDescription());
    }

    @Test
    void testConvertFromInfoWithSingleCharacterFields() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("A");
        info.setDescription("B");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals("A", entity.getTitle());
        assertEquals("B", entity.getDescription());
    }

    @Test
    void testConvertToInfoWithSingleCharacterFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle("A");
        mongoInfoEntity.setDescription("B");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertEquals("A", info.getTitle());
        assertEquals("B", info.getDescription());
    }

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
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }
}
