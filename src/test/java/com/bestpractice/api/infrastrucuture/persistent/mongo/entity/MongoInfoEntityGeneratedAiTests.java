package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class MongoInfoEntityGeneratedAiTests {

    private MongoInfoEntity mongoInfoEntity;

    @Test
    void testConstructor() {
        // GIVEN: A new MongoInfoEntity object is created.
        // WHEN: The constructor is called with specific values.
        // THEN: The object's id, title, and description fields are set to the provided values.
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");
        assertEquals("Test Title", entity.getTitle());
        assertEquals("Test Description", entity.getDescription());
    }

    @Test
    void testSetId() {
        // GIVEN: A MongoInfoEntity object is created.
        // WHEN: The setId method is called with a new ObjectId.
        // THEN: The object's id field is updated to the provided ObjectId.
        ObjectId newId = new ObjectId();
        mongoInfoEntity.setId(newId);
        assertEquals(newId, mongoInfoEntity.getId());
    }

    @Test
    void testSetTitleAndDescription() {
        // GIVEN: A MongoInfoEntity object is created.
        // WHEN: The setTitle and setDescription methods are called with new values.
        // THEN: The object's title and description fields are set to the provided values.
        String newTitle = "New Title";
        String newDescription = "New Description";
        mongoInfoEntity.setTitle(newTitle);
        mongoInfoEntity.setDescription(newDescription);
        assertEquals(newTitle, mongoInfoEntity.getTitle());
        assertEquals(newDescription, mongoInfoEntity.getDescription());
    }

    @Test
    void testGetId() {
        // GIVEN: A MongoInfoEntity object is created.
        // WHEN: The getId method is called.
        // THEN: The object's id field is returned.
        ObjectId id = new ObjectId();
        mongoInfoEntity.setId(id);
        assertEquals(id, mongoInfoEntity.getId());
    }

    @Test
    void testGetTitleAndDescription() {
        // GIVEN: A MongoInfoEntity object is created.
        // WHEN: The getTitle and getDescription methods are called.
        // THEN: The object's title and description fields are returned.
        String title = "Test Title";
        String description = "Test Description";
        mongoInfoEntity.setTitle(title);
        mongoInfoEntity.setDescription(description);
        assertEquals(title, mongoInfoEntity.getTitle());
        assertEquals(description, mongoInfoEntity.getDescription());
    }

    @Test
    void testConvertFrom() {
        // GIVEN: An Info object is created.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: The convertFrom method is called with the Info object.
        // THEN: A new MongoInfoEntity object is created with the same values as the Info object.
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        assertEquals("testId", entity.getId().toString());
        assertEquals("Test Title", entity.getTitle());
        assertEquals("Test Description", entity.getDescription());
    }

    @Test
    void testConvertTo() {
        // GIVEN: A MongoInfoEntity object is created.
        ObjectId id = new ObjectId();
        MongoInfoEntity entity = new MongoInfoEntity(id, "Test Title", "Test Description");

        // WHEN: The convertTo method is called.
        // THEN: An Info object is created with the same values as the MongoInfoEntity object.
        Info info = entity.convertTo();

        assertEquals("testId", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
