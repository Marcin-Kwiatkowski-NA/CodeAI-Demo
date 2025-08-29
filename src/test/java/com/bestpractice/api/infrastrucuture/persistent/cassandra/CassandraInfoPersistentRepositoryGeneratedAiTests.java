package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CqlSession session;
  private CassandraInfoPersistentRepository repository;
  private Info info;

  @BeforeEach
  void setUp() {
    session = CqlSession.builder().withCassandraContactAddress("127.0.0.1").build();
    repository = new CassandraInfoPersistentRepository(session);
    info = new Info();
  }

  @Test
  void findAll_shouldReturnAllInfos() {
    // GIVEN
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    List<Info> allInfos = repository.findAll();

    // THEN
    assertEquals(1, allInfos.size());
    assertEquals("Test Title", allInfos.get(0).getTitle());
    assertEquals("Test Description", allInfos.get(0).getDescription());
  }

  @Test
  void findById_shouldReturnInfoById() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    Info foundInfo = repository.findById(id);

    // THEN
    assertNotNull(foundInfo);
    assertEquals("Test Title", foundInfo.getTitle());
    assertEquals("Test Description", foundInfo.getDescription());
    assertEquals(id, foundInfo.getId());
  }

  @Test
  void insert_shouldInsertInfo() {
    // GIVEN
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN
    Info insertedInfo = repository.insert(info);

    // THEN
    assertNotNull(insertedInfo);
    assertEquals("Test Title", insertedInfo.getTitle());
    assertEquals("Test Description", insertedInfo.getDescription());
    assertEquals(repository.newId(), insertedInfo.getId());
  }

  @Test
  void replace_shouldReplaceInfo() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    Info replacedInfo = repository.replace(id, info);

    // THEN
    assertNotNull(replacedInfo);
    assertEquals("Test Title", replacedInfo.getTitle());
    assertEquals("Test Description", replacedInfo.getDescription());
    assertEquals(id, replacedInfo.getId());
  }

  @Test
  void removeById_shouldRemoveInfo() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    boolean removed = repository.removeById(id);

    // THEN
    assertTrue(removed);
  }
}