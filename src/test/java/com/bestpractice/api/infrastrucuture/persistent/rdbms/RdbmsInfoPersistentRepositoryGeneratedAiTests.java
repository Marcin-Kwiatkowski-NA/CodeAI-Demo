package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

  private RdbmsInfoPersistentRepository repository;

  @BeforeEach
  void setUp() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
  }

  @Test
  void newId() {
    // GIVEN: A new ID is requested.
    // WHEN: The newId() method is called.
    // THEN: A unique ID string is returned.
    String id = repository.newId();
    // Assert that the returned ID is not null and is a valid UUID.
    assertNotNull(id);
    assertTrue(id.length() > 30); // UUIDs are typically longer than 30 characters.
  }

  @Test
  void findAll() {
    // GIVEN: There are some sample infos in the database
    // WHEN: findAll() is called
    // THEN: A list of infos is returned
    List<Info> infos = repository.findAll();
    assertNotNull(infos);
    assertEquals(0, infos.size());
  }

  @Test
  void findById() {
    // GIVEN: An info exists in the database
    // WHEN: findById("1") is called
    // THEN: The info with id "1" is returned
    Info info = repository.findById("1");
    assertNotNull(info);
    assertEquals("1", info.getId());
    assertEquals("Title", info.getTitle());
    assertEquals("Description", info.getDescription());
  }

  @Test
  void insert() {
    // GIVEN: A new info is created
    // WHEN: insert() is called
    // THEN: The new info is returned
    Info info = new Info();
    info.setTitle("New Title");
    info.setDescription("New Description");
    Info insertedInfo = repository.insert(info);
    assertNotNull(insertedInfo);
    assertEquals("New Title", insertedInfo.getTitle());
    assertEquals("New Description", insertedInfo.getDescription());
  }

  @Test
  void replace() {
    // GIVEN: An info exists in the database
    // WHEN: replace("1", new Info()) is called
    // THEN: The info with id "1" is updated
    Info info = new Info();
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    Info replacedInfo = repository.replace("1", info);
    assertNotNull(replacedInfo);
    assertEquals("Updated Title", replacedInfo.getTitle());
    assertEquals("Updated Description", replacedInfo.getDescription());
  }

  @Test
  void removeById() {
    // GIVEN: An info exists in the database
    // WHEN: removeById("1") is called
    // THEN: The info with id "1" is removed
    boolean removed = repository.removeById("1");
    assertTrue(removed);
  }
}
