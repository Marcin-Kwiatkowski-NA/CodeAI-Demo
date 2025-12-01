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
    void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoInfoEntity.setId(expectedId);

        // THEN
        assertThat(mongoInfoEntity.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        mongoInfoEntity.setTitle(expectedTitle);

        // THEN
        assertThat(mongoInfoEntity.getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        mongoInfoEntity.setDescription(expectedDescription);

        // THEN
        assertThat(mongoInfoEntity.getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    void testConvertFrom() {
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
    void testConvertTo() {
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
        assertThat(result.getId()).isEqualTo(id.toString());
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getDescription()).isEqualTo(description);
    }
}
