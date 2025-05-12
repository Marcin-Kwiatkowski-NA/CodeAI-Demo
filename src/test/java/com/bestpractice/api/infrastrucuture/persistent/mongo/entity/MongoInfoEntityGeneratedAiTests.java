package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.Objects;

public class MongoInfoEntity {

    private ObjectId id;
    private String title;
    private String description;

    public MongoInfoEntity() {
    }

    public MongoInfoEntity(ObjectId id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public static MongoInfoEntity convertFrom(Info info) {
        return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
    }

    @Override
    public Info convertTo() {
        Info info = new Info();
        info.setId(this.id.toString());
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }

    @Override
    public void testSetId() {
        // Basic test to verify id is set
        ObjectId id = ObjectId.valueOf(12345);
        MongoInfoEntity entity = new MongoInfoEntity(id, "My Entity", "This is a test");
        assertEquals(12345, entity.getId());
        assertEquals("My Entity", entity.getTitle());
        assertEquals("This is a test", entity.getDescription());
    }

    @Override
    public void testTitle() {
        // Test case 1: Set title to "My Entity"
        MongoInfoEntity entity = new MongoInfoEntity();
        entity.setTitle("My Entity");
        assertEquals("My Entity", entity.getTitle());
        assertEquals("This is a test", entity.getDescription());
    }

    @Override
    public void testDescription() {
        // Test case 2: Set description to "This is a test"
        MongoInfoEntity entity = new MongoInfoEntity();
        entity.setDescription("This is a test");
        assertEquals("This is a test", entity.getTitle());
        assertEquals("This is a test", entity.getDescription());
    }
}