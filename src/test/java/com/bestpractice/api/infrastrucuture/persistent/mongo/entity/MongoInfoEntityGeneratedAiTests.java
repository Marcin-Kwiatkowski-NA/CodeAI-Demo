package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link MongoInfoEntity}.
 */
public class MongoInfoEntityGeneratedAiTests {

    /** The entity under test. */
    private MongoInfoEntity entity;

    @BeforeEach
    void setUp() {
        entity = null;
    }

    /* --------------------------------------------------------------------- */
    /* 1. Constructors & basic accessors                                      */
    /* --------------------------------------------------------------------- */

    @Test
    void testDefaultConstructor() {
        // GIVEN
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
        String title = "Title";
        String description = "Description";

        // WHEN
        entity = new MongoInfoEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);

        // THEN
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getDescription()).isEqualTo(description);
    }

    @Test
    void testSettersAllowNullValues() {
        // WHEN
        entity = new MongoInfoEntity();
        entity.setId(null);
        entity.setTitle(null);
        entity.setDescription(null);

        // THEN
        assertThat(entity.getId()).isNull();
        assertThat(entity.getTitle()).isNull();
        assertThat(entity.getDescription()).isNull();
    }

    /* --------------------------------------------------------------------- */
    /* 2. Conversion from Info DTO                                            */
    /* --------------------------------------------------------------------- */

    @Test
    void testConvertFromValidId() {
        // GIVEN
        String validHex = new ObjectId().toHexString();
        Info info = new Info();
        info.setId(validHex);
        info.setTitle("Valid Title");
        info.setDescription("Valid Description");

        // WHEN
        MongoInfoEntity resultEntity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(resultEntity).isNotNull();
        assertThat(resultEntity.getId()).isEqualTo(new ObjectId(validHex));
        assertThat(resultEntity.getTitle()).isEqualTo("Valid Title");
        assertThat(resultEntity.getDescription()).isEqualTo("Valid Description");
    }

    @Test
    void testConvertFromWithNullTitleDescription() {
        // GIVEN
        String validHex = new ObjectId().toHexString();
        Info info = new Info();
        info.setId(validHex);
        info.setTitle(null);
        info.setDescription(null);

        // WHEN
        MongoInfoEntity resultEntity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertThat(resultEntity).isNotNull();
        assertThat(resultEntity.getId()).isEqualTo(new ObjectId(validHex));
        assertThat(resultEntity.getTitle()).isNull();
        assertThat(resultEntity.getDescription()).isNull();
    }

    @Test
    void testConvertFromNullIdThrows() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertFromInvalidHexThrows() {
        // GIVEN
        Info info = new Info();
        info.setId("zzzzzzzzzzzzzzzzzzzzzzzz"); // 24 chars but invalid hex
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & TH
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertFromInvalidLengthThrows() {
        // GIVEN
        Info info = new Info();
        info.setId("12345"); // too short
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN
        assertThatThrownBy(() -> MongoInfoEntity.convertFrom(info))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /* --------------------------------------------------------------------- */
    /* 3. Conversion to Info DTO                                              */
    /* --------------------------------------------------------------------- */

    @Test
    void testConvertToValidEntity() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Sample Title";
        String description = "Sample Description";

        MongoInfoEntity entityToConvert = new MongoInfoEntity(id, title, description);

        // WHEN
        Info resultInfo = entityToConvert.convertTo();

        // THEN
        assertThat(resultInfo).isNotNull();
        assertThat(resultInfo.getId()).isEqualTo(id.toHexString());
        assertThat(resultInfo.getTitle()).isEqualTo(title);
        assertThat(resultInfo.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertToWithNullIdThrows() {
        // GIVEN
        entity = new MongoInfoEntity();
        entity.setTitle("Title");
        entity.setDescription("Description");

        // WHEN & THEN
        assertThatThrownBy(() -> entity.convertTo())
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConvertFromNullInfoThrows() {
        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoInfoEntity.convertFrom(null));
    }
}
