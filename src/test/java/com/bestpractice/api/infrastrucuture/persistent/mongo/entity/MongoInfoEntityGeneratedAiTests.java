package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(mongoInfoEntity.getId()).isEqualTo(id);
    }

    @Test
    void givenValidTitle_whenSetTitle_thenTitleShouldBeSetCorrectly() {
        // GIVEN
        String title = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(title);

        // THEN
        assertThat(mongoInfoEntity.getTitle()).isEqualTo(title);
    }

    @Test
    void givenValidDescription_whenSetDescription_thenDescriptionShouldBeSetCorrectly() {
        // GIVEN
        String description = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(description);

        // THEN
        assertThat(mongoInfoEntity.getDescription()).isEqualTo(description);
    }

    @Test
    void givenValidInfo_whenConvertFrom_thenMongoInfoEntityShouldBeCreatedCorrectly() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Info Title");
        info.setDescription("Info Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(result.getId().toString()).isEqualTo(info.getId());
        assertThat(result.getTitle()).isEqualTo(info.getTitle());
        assertThat(result.getDescription()).isEqualTo(info.getDescription());
    }

    @Test
    void givenValidMongoInfoEntity_whenConvertTo_thenInfoShouldBeCreatedCorrectly() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Mongo Title";
        String description = "Mongo Description";
        mongoInfoEntity.setId(id);
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);

        // WHEN
        Info result = mongoInfoEntity.convertTo();

        // THEN
        assertThat(result.getId()).isEqualTo(id.toString());
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getDescription()).isEqualTo(description);
    }
}
