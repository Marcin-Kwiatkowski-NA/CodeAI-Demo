package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoInfoEntity {
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

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}

class MongoInfoEntityTest {

    private MongoInfoEntity mongoInfoEntity;

    @BeforeEach
    void setUp() {
        mongoInfoEntity = new MongoInfoEntity();
    }

    @Test
    void testConvertFrom() {
        // GIVEN
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        MongoInfoEntity convertedEntity = MongoInfoEntity.convertFrom(info);

        // THEN
        assertEquals("testId", convertedEntity.getId().toString());
        assertEquals("Test Title", convertedEntity.getTitle());
        assertEquals("Test Description", convertedEntity.getDescription());
    }

    @Test
    void testConvertTo() {
        // GIVEN
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity(new ObjectId(), "Test Title", "Test Description");

        // WHEN
        Info info = mongoInfoEntity.convertTo();

        // THEN
        assertEquals("testId", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}
