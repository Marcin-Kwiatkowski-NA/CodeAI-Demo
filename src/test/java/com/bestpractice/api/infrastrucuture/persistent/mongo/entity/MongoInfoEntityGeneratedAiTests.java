package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity entity;

    @BeforeEach
    void setUp() {
        entity = new MongoInfoEntity();
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // WHEN
        MongoInfoEntity defaultEntity = new MongoInfoEntity();
        // THEN
        assertThat(defaultEntity.getId()).isNull();
        assertThat(defaultEntity.getTitle()).isNull();
        assertThat(defaultEntity.getDescription()).isNull();
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Sample Title";
        String description = "Sample Description";
        // WHEN
        MongoInfoEntity paramEntity = new MongoInfoEntity(id, title, description);
        // THEN
        assertThat(paramEntity.getId()).isEqualTo(id);
        assertThat(paramEntity.getTitle()).isEqualTo(title);
        assertThat(paramEntity.getDescription()).isEqualTo(description);
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";
        // WHEN
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        // THEN ожидаем, что значения корректно сохранены
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertFromValid() {
        // GIVEN
        Info info = new Info();
        String idString = new ObjectId().toHexString();
        info.setId(idString);
        info.setTitle("Info Title");
        info.setDescription("Info Description");
        // WHEN
        MongoInfoEntity converted = MongoInfoEntity.convertFrom(info);
        // THEN
        assertThat(converted.getId()).isNotNull();
        assertThat(converted.getId().toHexString()).isEqualTo(idString);
        assertThat(converted.getTitle()).isEqualTo(info.getTitle());
        assertThat(converted.getDescription()).isEqualTo(info.getDescription());
    }

    @Test
    void testConvertFromNullIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");
        // WHEN
        // THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertFromNullInfoThrowsException() {
        // GIVEN
        // WHEN
        // THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(null));
    }

    @Test
    void testConvertFromInvalidIdThrowsException() {
        // GIVEN
        Info info = new Info();
        info.setId("invalidhexstring");
        info.setTitle("Title");
        info.setDescription("Description");
        // WHEN
        // THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertToValid() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Mongo Title";
        String description = "Mongo Description";
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        // WHEN
        Info info = entity.convertTo();
        // THEN
        assertThat(info.getId()).isEqualTo(id.toString());
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertToNullIdThrowsException() {
        // GIVEN
        entity.setTitle("Title");
        entity.setDescription("Description");
        // WHEN
        // THEN
        assertThatThrownBy(entity::convertTo)
                .isInstanceOf(NullPointerException.class);
    }
}
