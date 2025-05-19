package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CassandraInfoPersistentRepository repository;
  private CqlSession session;

  @BeforeEach
  void setUp() {
    session = CqlSession.builder().build();
    repository = new CassandraInfoPersistentRepository(session);
  }

  @Test
  void testNewId() {
    // GIVEN: A new repository instance is created.
    // WHEN: The newId() method is called.
    // THEN: A new UUID string is returned.
    String id = repository.newId();
    assertNotNull(id, "New ID should not be null");
    assert(!id.isEmpty(), "New ID should not be empty");
  }

  @Test
  void testFindAll() {
    // GIVEN: The repository is initialized with a session.
    // WHEN: findAll() is called.
    // THEN: A list of Info objects is returned, each with a generated ID, title, and description.
    List<Info> infos = repository.findAll();
    assertNotNull(infos, "List of Info objects should not be null");
    assertEquals(1, infos.size(), "Should return at least one Info object");
    Info info = infos.get(0);
    assertNotNull(info.getId(), "Info object should have an ID");
    assertEquals("TestTitle", info.getTitle(), "Title should be 'TestTitle'");
    assertEquals("TestDescription", info.getDescription(), "Description should be 'TestDescription'");
  }

  @Test
  void testFindById() {
    // GIVEN: A new Info object is created and inserted into the database.
    Info info = new Info();
    info.setId("testId");
    info.setTitle("TestTitle");
    info.setDescription("TestDescription");
    repository.insert(info);

    // WHEN: findById("testId") is called.
    // THEN: An Info object with the specified ID is returned.

    Info foundInfo = repository.findById("testId");

    assertNotNull(foundInfo, "Info object should not be null");
    assertEquals("testId", foundInfo.getId(), "ID should match");
    assertEquals("TestTitle", foundInfo.getTitle(), "Title should match");
    assertEquals("TestDescription", foundInfo.getDescription(), "Description should match");
  }

  @Test
  void testInsert() {
    // GIVEN: A new Info object is created.
    Info info = new Info();
    info.setId("insertTestId");
    info.setTitle("TestTitle");
    info.setDescription("TestDescription");

    // WHEN: insert(info) is called.
    // THEN: The Info object is returned, and the object is inserted into the database.

    Info insertedInfo = repository.insert(info);

    assertNotNull(insertedInfo, "Inserted Info object should not be null");
    assertEquals("insertTestId", insertedInfo.getId(), "ID should match");
    assertEquals("TestTitle", insertedInfo.getTitle(), "Title should match");
    assertEquals("TestDescription", insertedInfo.getDescription(), "Description should match");
  }
}
