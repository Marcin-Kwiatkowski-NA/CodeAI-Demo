package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.entity.Info;

public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

  private RdbmsInfoPersistentRepository repository;
  private Info info;

  @BeforeEach
  void setUp() {
    repository = new RdbmsInfoPersistentRepository(new JdbcTemplate());
    info = new Info();
    info.setId(UUID.randomUUID().toString());
    info.setTitle("Test Title");
    info.setDescription("Test Description");
  }

  @Test
  void newId() {
    // GIVEN
    // WHEN
    String newId = repository.newId();
    // THEN
    assertNotNull(newId, "New ID should not be null");
    assert(!newId.isEmpty(), "New ID should not be empty");
  }

  @Test
  void findAll() {
    // GIVEN
    // WHEN
    List<Info> allInfos = repository.findAll();
    // THEN
    assertNotNull(allInfos, "List of infos should not be null");
    assertEquals(1, allInfos.size(), "Should return one info");
  }
}
