package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.datastax.cql.Session;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CassandraInfoPersistentRepository repository;
  private CqlSession mockSession;
  private String testId;

  @BeforeEach
  void setUp() {
    mockSession = mock(CqlSession.class);
    repository = new CassandraInfoPersistentRepository(mockSession);
    testId = UUID.randomUUID().toString();
  }

  @Test
  @DisplayName("findAll returns all infos")
  void findAll() {
    // Arrange
    List<Info> infos = new ArrayList<>();
    Info info1 = new Info();
    info1.setId(testId);
    info1.setTitle("Test Title");
    info1.setDescription("Test Description");
    infos.add(info1);

    // Act
    List<Info> result = repository.findAll();

    // Assert
    assertEquals(1, result.size());
    assertEquals(testId, result.get(0).getId());
    assertEquals("Test Title", result.get(0).getTitle());
    assertEquals("Test Description", result.get(0).getDescription());
  }

  @Test
  @DisplayName("findById returns info by id")
  void findById() {
    // Arrange
    Info info = new Info();
    info.setId(testId);
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // Act
    Info result = repository.findById(testId);

    // Assert
    assertNotNull(result);
    assertEquals(testId, result.getId());
    assertEquals("Test Title", result.getTitle());
    assertEquals("Test Description", result.getDescription());
  }

  @Test
  @DisplayName("insert returns new info")
  void insert() {
    // Arrange
    Info info = new Info();
    info.setId(testId);
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // Act
    Info result = repository.insert(info);

    // Assert
    assertNotNull(result);
    assertEquals(testId, result.getId());
    assertEquals("Test Title", result.getTitle());
    assertEquals("Test Description", result.getDescription());
  }

  @Test
  @DisplayName("replace returns updated info")
  void replace() {
    // Arrange
    Info info = new Info();
    info.setId(testId);
    info.setTitle("New Title");
    info.setDescription("New Description");

    // Act
    Info result = repository.replace(testId, info);

    // Assert
    assertNotNull(result);
    assertEquals(testId, result.getId());
    assertEquals("New Title", result.getTitle());
    assertEquals("New Description", result.getDescription());
  }

  @Test
  @DisplayName("removeById returns true if info is removed")
  void removeById() {
    // Arrange
    boolean result = repository.removeById(testId);

    // Assert
    assertTrue(result);
  }
}
