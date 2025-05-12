package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;

@ExtendWith(CassandraInfoPersistentRepositoryGeneratedAiTests.class)
class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CassandraInfoPersistentRepository repository;
  private CqlSession session;
  private Info info;

  @BeforeEach
  void setUp() {
    session = CqlSession.builder().build();
    repository = new CassandraInfoPersistentRepository(session);
    info = new Info();
  }

  @Test
  void testNewId() {
    // GIVEN: A new instance of the repository is created.
    // WHEN: The newId() method is called.
    // THEN: A new UUID string is returned.
    String id = repository.newId();
    Assertions.assertNotNull(id, "New ID should not be null");
    Assertions.assertFalse(id.isEmpty(), "New ID should not be empty");
  }

  @Test
  void testFindAll() {
    // GIVEN: The repository is initialized with a CqlSession.
    // WHEN: findAll() is called.
    // THEN: A list of Info objects is returned, each with a generated ID, title, and description.
    List<Info> infos = repository.findAll();
    Assertions.assertNotNull(infos, "List of Info objects should not be null");
    Assertions.assertEquals(1, infos.size(), "Should return at least one Info object");
    Info info = infos.get(0);
    Assertions.assertNotNull(info.getId(), "Info object should have an ID");
    Assertions.assertEquals("TestTitle", info.getTitle(), "Title should be 'TestTitle'");
    Assertions.assertEquals("TestDescription", info.getDescription(), "Description should be 'TestDescription'");
  }

  @Test
  void testFindById() {
    // GIVEN: An Info object is inserted into the Cassandra database.
    // WHEN: findById("testId") is called.
    // THEN: An Info object with the specified ID is returned.
    Info info = repository.findById("testId");
    Assertions.assertNotNull(info, "Info object should not be null");
    Assertions.assertEquals("testId", info.getId(), "ID should match");
    Assertions.assertEquals("TestTitle", info.getTitle(), "Title should be 'TestTitle'");
    Assertions.assertEquals("TestDescription", info.getDescription(), "Description should be 'TestDescription'");
  }

  @Test
  void testInsert() {
    // GIVEN: The repository is initialized with a CqlSession.
    // WHEN: insert(new Info()) is called.
    // THEN: An Info object is returned, and the Info object is inserted into the database.
    info.setTitle("NewTitle");
    info.setDescription("NewDescription");
    Info insertedInfo = repository.insert(info);
    Assertions.assertNotNull(insertedInfo, "Inserted Info object should not be null");
    Assertions.assertEquals("NewTitle", insertedInfo.getTitle(), "Title should be 'NewTitle'");
    Assertions.assertEquals("NewDescription", insertedInfo.getDescription(), "Description should be 'NewDescription'");
  }

  @Test
  void testReplace() {
    // GIVEN: An Info object is inserted into the Cassandra database.
    // WHEN: replace("testId", new Info()) is called.
    // THEN: The Info object is updated in the database, and the updated Info object is returned.
    info.setTitle("UpdatedTitle");
    info.setDescription("UpdatedDescription");
    Info replacedInfo = repository.replace("testId", info);
    Assertions.assertNotNull(replacedInfo, "Replaced Info object should not be null");
    Assertions.assertEquals("UpdatedTitle", replacedInfo.getTitle(), "Title should be 'UpdatedTitle'");
    Assertions.assertEquals("UpdatedDescription", replacedInfo.getDescription(), "Description should be 'UpdatedDescription'");
  }
}
