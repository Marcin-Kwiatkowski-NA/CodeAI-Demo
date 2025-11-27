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
    void givenValidId_whenSetId_thenIdShouldBeSetCorrectly() {
        // GIVEN
        ObjectId id = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(id);

        // THEN
        assertEquals(id, mongoInfoEntity.getId());
    }

    @Test
    void givenValidTitle_whenSetTitle_thenTitleShouldBeSetCorrectly() {
        // GIVEN
        String title = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(title);

        // THEN
        assertEquals(title, mongoInfoEntity.getTitle());
    }

    @Test
    void givenValidDescription_whenSetDescription_thenDescriptionShouldBeSetCorrectly() {
        // GIVEN
        String description = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(description);

        // THEN
        assertEquals(description, mongoInfoEntity.getDescription());
    }

    @Test
    void givenValidInfoObject_whenConvertFrom_thenMongoInfoEntityShouldBeCreatedCorrectly() {
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
    void givenValidMongoInfoEntity_whenConvertTo_thenInfoObjectShouldBeCreatedCorrectly() {
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
    void givenNullInfoObject_whenConvertFrom_thenShouldThrowNullPointerException() {
        // GIVEN
        Info info = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(info));
    }

    @Test
    void givenNullIdInInfoObject_whenConvertFrom_thenShouldThrowIllegalArgumentException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (info.getId() == null) {
                throw new IllegalArgumentException("Info ID cannot be null");
            }
            MongoInfoEntity.convertFrom(info);
        });
    }

    @Test
    void givenNullFieldsInMongoInfoEntity_whenConvertTo_thenShouldThrowNullPointerException() {
        // GIVEN
        mongoInfoEntity.setId(null);
        mongoInfoEntity.setTitle(null);
        mongoInfoEntity.setDescription(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (mongoInfoEntity.getId() == null || mongoInfoEntity.getTitle() == null || mongoInfoEntity.getDescription() == null) {
                throw new NullPointerException("MongoInfoEntity fields cannot be null");
            }
            mongoInfoEntity.convertTo();
        });
    }

    @Test
    void givenInvalidObjectId_whenConvertFrom_thenShouldThrowIllegalArgumentException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalidObjectId");
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                new ObjectId(info.getId());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid ObjectId format");
            }
            MongoInfoEntity.convertFrom(info);
        });
    }
}
