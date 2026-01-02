package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity entity;

    @BeforeEach
    void setUp() {
        entity = new MongoInfoEntity();
    }

    @Test
    void testDefaultConstructorAndGetters() {
        // GIVEN
        // entity is initialized in @BeforeEach

        // WHEN
        ObjectId id = entity.getId();
        String title = entity.getTitle();
        String description = entity.getDescription();

        // THEN
        assertThat(id).isNull();
        assertThat(title).isNull();
        assertThat(description).isNull();
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";

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
        String title = "New Title";
        String description = "New Description";

        // WHEN
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);

        // THEN
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertFrom() {
        // GIVEN
        String idStr = new ObjectId().toString();
        String title = "Convert Title";
        String description = "Convert Description";
        Info info = new Info();
        info.setId(idStr);
        info.setTitle(title);
        info.setDescription(description);

        // WHEN
        MongoInfoEntity converted = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(converted.getId()).isEqualTo(new ObjectId(idStr));
        assertThat(converted.getTitle()).isEqualTo(title);
        assertThat(converted.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertTo() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "ToInfo Title";
        String description = "ToInfo Description";
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
    void testConvertFromNullInfo() {
        // GIVEN
        Info nullInfo = null;

        // WHEN / THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(nullInfo))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConvertFromNullId() {
        // GIVEN
        Info infoWithNullId = new Info();
        infoWithNullId.setId(null);
        infoWithNullId.setTitle("Title");
        infoWithNullId.setDescription("Description");

        // WHEN / THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(infoWithNullId))
                .isInstanceOf(NullPointerException.class);
    }
}
