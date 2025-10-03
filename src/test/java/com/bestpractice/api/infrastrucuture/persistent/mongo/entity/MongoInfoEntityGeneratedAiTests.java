package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyTestFactory.class)
public class MongoInfoEntityGeneratedAiTests {

    @BeforeEach
    public void beforeEach() {
        // Reset the state of the entity before each test
    }

    @Test
    public void testConstructor() {
        // GIVEN: Setup
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";

        // WHEN: Create a MongoInfoEntity
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN: Verify the entity's state
        assertEquals(id.toString(), entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    public void testSetId() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();
        ObjectId newId = new ObjectId();

        // WHEN: Set the ID
        entity.setId(newId);

        // THEN: Verify the ID is set correctly
        assertEquals(newId.toString(), entity.getId());
    }

    @Test
    public void testSetTitle() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();
        String newTitle = "New Title";

        // WHEN: Set the title
        entity.setTitle(newTitle);

        // THEN: Verify the title is set correctly
        assertEquals(newTitle, entity.getTitle());
    }

    @Test
    public void testSetDescription() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity();
        String newDescription = "New Description";

        // WHEN: Set the description
        entity.setDescription(newDescription);

        // THEN: Verify the description is set correctly
        assertEquals(newDescription, entity.getDescription());
    }

    @Test
    public void testConvertFrom() {
        // GIVEN: Setup
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Convert Info to MongoInfoEntity
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN: Verify the entity's state
        assertEquals(new ObjectId(), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    public void testConvertTo() {
        // GIVEN: Setup
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");

        // WHEN: Convert MongoInfoEntity to Info
        Info info = entity.convertTo();

        // THEN: Verify the Info's state
        assertEquals("test_id", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}

class MyTestFactory {
}
