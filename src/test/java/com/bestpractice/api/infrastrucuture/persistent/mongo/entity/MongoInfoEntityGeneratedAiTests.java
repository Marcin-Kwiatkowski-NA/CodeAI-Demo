package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Test
public class MongoInfoEntityGeneratedAiTests {

    @Test
    public void testConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String title = "Test Title";
        String description = "Test Description";

        // WHEN
        MongoInfoEntity entity = new MongoInfoEntity(id, title, description);

        // THEN
        assertEquals(id.toString(), entity.getId());
        assertEquals(title, entity.getTitle());
        assertEquals(description, entity.getDescription());
    }

    @Test
    public void testSetId() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN
        entity.setId(new ObjectId());

        // THEN
        assertEquals(new ObjectId().toString(), entity.getId());
    }

    @Test
    public void testSetTitle() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN
        entity.setTitle("New Title");

        // THEN
        assertEquals("New Title", entity.getTitle());
    }

    @Test
    public void testSetDescription() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity();

        // WHEN
        entity.setDescription("New Description");

        // THEN
        assertEquals("New Description", entity.getDescription());
    }

    @Test
    public void testConvertFrom() {
        // GIVEN
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals(new ObjectId(info.getId()), entity.getId());
        assertEquals(info.getTitle(), entity.getTitle());
        assertEquals(info.getDescription(), entity.getDescription());
    }

    @Test
    public void testConvertTo() {
        // GIVEN
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        InfoEntity infoEntity = MongoInfoEntity.convertTo(info);

        // THEN
        assertEquals("test_id", infoEntity.getId());
        assertEquals(info.getTitle(), infoEntity.getTitle());
        assertEquals(info.getDescription(), infoEntity.getDescription());
    }

    @BeforeEach
    public void beforeEach() {
        // Reset state before each test
    }
}
