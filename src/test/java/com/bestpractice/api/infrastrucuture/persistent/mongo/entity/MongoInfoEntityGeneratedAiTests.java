package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;
    private Info info;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
        info = new Info();
        info.setId("existing-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void shouldConvertFromInfoWithValidData() {
        // GIVEN
        Info validInfo = new Info();
        validInfo.setId("1234567890");
        validInfo.setTitle("Valid Title");
        validInfo.setDescription("Valid Description");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(validInfo);

        // THEN
        assertThat(result.getId()).isEqualTo(new ObjectId("1234567890"));
        assertThat(result.getTitle()).isEqualTo("Valid Title");
        assertThat(result.getDescription()).isEqualTo("Valid Description");
    }

    @Test
    void shouldConvertToInfoWithValidData() {
        // GIVEN
        ObjectId id = new ObjectId("0123456789abcdef");
        String title = "Converted Title";
        String description = "Converted Description";

        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity(id, title, description);

        // WHEN
        Info convertedInfo = mongoInfoEntity.convertTo();

        // THEN
        assertThat(convertedInfo.getId()).isEqualTo("0123456789abcdef");
        assertThat(convertedInfo.getTitle()).isEqualTo(title);
        assertThat(convertedInfo.getDescription()).isEqualTo(description);
    }

    @Test
    void shouldHandleNullTitleAndDescriptionInConversion() {
        // GIVEN
        ObjectId id = new ObjectId("0123456789abcdef");
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity(id, null, null);

        // WHEN
        Info convertedInfo = mongoInfoEntity.convertTo();

        // THEN
        assertThat(convertedInfo.getId()).isEqualTo("0123456789abcdef");
        assertThat(convertedInfo.getTitle()).isNull();
        assertThat(convertedInfo.getDescription()).isNull();
    }

    @Test
    void shouldThrowExceptionWhenIdIsInvalidInConversion() {
        // GIVEN
        Info info = new Info();
        info.setId("invalid-id-format");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN & THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id");
    }

    @Test
    void shouldConvertFromInfoWithEmptyTitleAndDescription() {
        // GIVEN
        Info info = new Info();
        info.setId("empty-id");
        info.setTitle("");
        info.setDescription("");

        // WHEN
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(result.getTitle()).isEmpty();
        assertThat(result.getDescription()).isEqualTo("");
    }

    @Test
    void shouldNotAllowNullIdInConvertFrom() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN & THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id");
    }

    @Test
    void shouldHandleEmptyIdInConvertFrom() {
        // GIVEN
        Info info = new Info();
        info.setId("");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN & THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id");
    }
}
