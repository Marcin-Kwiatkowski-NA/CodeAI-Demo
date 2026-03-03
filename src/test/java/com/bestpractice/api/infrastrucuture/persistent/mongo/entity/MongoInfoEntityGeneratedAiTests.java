package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity entity;

    @BeforeEach
    void setUp() {
        entity = new MongoInfoEntity();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        ObjectId id = new ObjectId();

        // WHEN
        entity.setId(id);

        // THEN
        assertEquals(id, entity.getId());
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String title = "Sample Title";

        // WHEN
        entity.setTitle(title);

        // THEN
        assertEquals(title, entity.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String description = "Sample Description";

        // WHEN
        entity.setDescription(description);

        // THEN
        assertEquals(description, entity.getDescription());
    }

    @Test
    void testConvertFromValidInfo() {
        // GIVEN
        String idStr = "507f1f77bcf86cd799439011";
        Info info = new Info();
        info.setId(idStr);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals(new ObjectId(idStr), result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void testConvertFromInvalidIdThrows() {
        // GIVEN
        Info info = new Info();
        info.setId("invalidObjectId");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertFromNullIdThrows() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void testConvertTo() {
        // GIVEN
        ObjectId id = new ObjectId();
        entity.setId(id);
        entity.setTitle("Title");
        entity.setDescription("Description");

        // WHEN
        Info result = entity.convertTo();

        // THEN
        assertEquals(id.toString(), result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void testConvertToWithNullIdThrows() {
        // GIVEN
        entity.setTitle("Title");
        entity.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> entity.convertTo());
    }

    @Test
    void testConvertFromAndToRoundTrip() {
        // GIVEN
        String idStr = "507f1f77bcf86cd799439011";
        Info original = new Info();
        original.setId(idStr);
        original.setTitle("Round Trip Title");
        original.setDescription("Round Trip Description");

        // WHEN
        MongoInfoEntity mongoEntity = MongoInfoEntity.convertFrom(original);
        Info roundTrip = mongoEntity.convertTo();

        // THEN
        assertEquals(original.getId(), roundTrip.getId());
        assertEquals(original.getTitle(), roundTrip.getTitle());
        assertEquals(original.getDescription(), roundTrip.getDescription());
    }
}
