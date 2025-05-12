package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisCacheRepository;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.List;

@Test
class InfrastructureBeanGeneratedAiTests {

  private RedisCacheRepository redisCacheRepository;
  private InfoPersistentRepository infoRepository;
  private MongoClient mongoClient;
  private MongoDatabase mongoDatabase;

  @BeforeEach
  void setUp() {
    redisCacheRepository = new RedisCacheRepository(new RedisProperty());
    infoRepository = new InfoPersistentRepository(mongoClient, mongoDatabase);
    mongoClient = MongoClient.builder().build();
    mongoDatabase = mongoClient.getDatabase("test_database");
  }

  @Test
  void testInsertInfo() {
    // GIVEN: A new Info object
    Info info = new Info();
    info.setId("new_id");
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN: The infoRepository.insert(info) method is called
    Info insertedInfo = infoRepository.insert(info);

    // THEN: The insertedInfo object should match the original info object
    assertEquals("new_id", insertedInfo.getId());
    assertEquals("Test Title", insertedInfo.getTitle());
    assertEquals("Test Description", insertedInfo.getDescription());
  }

  @Test
  void testFindById() {
    // GIVEN: An existing Info object
    Info info = new Info();
    info.setId("existing_id");
    info.setTitle("Existing Title");
    info.setDescription("Existing Description");

    // WHEN: The infoRepository.findById("existing_id") method is called
    Info foundInfo = infoRepository.findById("existing_id");

    // THEN: The foundInfo object should match the original info object
    assertEquals("existing_id", foundInfo.getId());
    assertEquals("Existing Title", foundInfo.getTitle());
    assertEquals("Existing Description", foundInfo.getDescription());
  }

  @Test
  void testReplaceInfo() {
    // GIVEN: An existing Info object
    Info info = new Info();
    info.setId("existing_id");
    info.setTitle("Existing Title");
    info.setDescription("Existing Description");

    // WHEN: The infoRepository.replace("existing_id", info) method is called
    Info replacedInfo = infoRepository.replace("existing_id", info);

    // THEN: The replacedInfo object should match the original info object
    assertEquals("existing_id", replacedInfo.getId());
    assertEquals("Existing Title", replacedInfo.getTitle());
    assertEquals("Existing Description", replacedInfo.getDescription());
  }

  @Test
  void testFindAllInfo() {
    // GIVEN: An existing Info object
    Info info = new Info();
    info.setId("existing_id");
    info.setTitle("Existing Title");
    info.setDescription("Existing Description");

    // WHEN: The infoRepository.findAll() method is called
    List<Info> allInfo = infoRepository.findAll();

    // THEN: The allInfo list should contain one element
    assertEquals(1, allInfo.size());

    // AND: The first element in the list should match the original info object
    assertEquals("existing_id", allInfo.get(0).getId());
    assertEquals("Existing Title", allInfo.get(0).getTitle());
    assertEquals("Existing Description", allInfo.get(0).getDescription());
  }
}
