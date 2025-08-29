package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    void testConvertFrom_validInfo() {
        // GIVEN a valid Info object
        Info info = new Info();
        info.setId("12345");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN the convertFrom method is called
        // THEN a MongoInfoEntity should be created with the correct values
        MongoInfoEntity result = MongoInfoEntity.convertFrom(info);

        // ASSERT
        assertEquals("12345", result.getId());
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void testConvertTo_validMongoInfoEntity() {
        // GIVEN a valid MongoInfoEntity
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();
        mongoInfoEntity.setId("67890");
        mongoInfoEntity.setTitle("Another Title");
        mongoInfoEntity.setDescription("Another Description");

        // WHEN the convertTo method is called
        // THEN an Info object should be created with the correct values
        Info result = mongoInfoEntity.convertTo();

        // ASSERT
        assertEquals("67890", result.getId());
        assertEquals("Another Title", result.getTitle());
        assertEquals("Another Description", result.getDescription());
    }

    @Test
    void testSetId_validId() {
        // GIVEN a MongoInfoEntity
        ObjectId id = new ObjectId();
        id.toString();

        // WHEN the setId method is called with a valid ObjectId
        // THEN the id field should be updated
        mongoInfoEntity.setId(id);

        // ASSERT
        assertEquals(id, mongoInfoEntity.getId());
    }

    @Test
    void testSetTitle_validTitle() {
        // GIVEN a MongoInfoEntity
        String title = "New Title";

        // WHEN the setTitle method is called with a valid title
        // THEN the title field should be updated
        mongoInfoEntity.setTitle(title);

        // ASSERT
        assertEquals(title, mongoInfoEntity.getTitle());
    }

    @Test
    void testSetDescription_validDescription() {
        // GIVEN a MongoInfoEntity
        String description = "New Description";

        // WHEN the setDescription method is called with a valid description
        // THEN the description field should be updated
        mongoInfoEntity.setDescription(description);

        // ASSERT
        assertEquals(description, mongoInfoEntity.getDescription());
    }
}