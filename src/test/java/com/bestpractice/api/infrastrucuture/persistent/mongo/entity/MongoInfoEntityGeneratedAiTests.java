package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoInfoEntityGeneratedAiTests {

  @Test
  void testConvertFrom() {
    // GIVEN a Info object
    Info info = new Info();
    info.setId("testId");
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN convertFrom is called
    MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);

    // THEN the resulting MongoInfoEntity should have the correct values
    assertEquals("testId", mongoInfoEntity.getId());
    assertEquals("Test Title", mongoInfoEntity.getTitle());
    assertEquals("Test Description", mongoInfoEntity.getDescription());
  }

  @Test
  void testConvertTo() {
    // GIVEN a MongoInfoEntity object
    MongoInfoEntity mongoInfoEntity = new MongoInfoEntity(new ObjectId("testId"), "Test Title", "Test Description");

    // WHEN convertTo is called
    Info info = mongoInfoEntity.convertTo();

    // THEN the resulting Info object should have the correct values
    assertEquals("testId", info.getId());
    assertEquals("Test Title", info.getTitle());
    assertEquals("Test Description", info.getDescription());
  }
}
