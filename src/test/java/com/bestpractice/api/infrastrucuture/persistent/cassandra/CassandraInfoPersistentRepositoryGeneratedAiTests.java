package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CassandraInfoPersistentRepository repository;
  private CqlSession mockSession;
  private Select selectInfo;
  private RegularInsert insertInfo;
  private Update updateInfo;
  private Delete deleteInfo;

  @DisplayName("Test findAll")
  @Test
  void testFindAll() {
    List<Info> expectedInfos = new ArrayList<>();
    Info info1 = new Info();
    info1.setId("id1");
    info1.setTitle("title1");
    info1.setDescription("description1");
    expectedInfos.add(info1);

    when(mockSession.prepare(selectInfo.build())).thenReturn(preparedSelectInfo);
    when(preparedSelectInfo.bind()).thenReturn(resultSet);
    when(resultSet.one()).thenReturn(row);
    when(row.getString("id")).thenReturn("id1");
    when(row.getString("title")).thenReturn("title1");
    when(row.getString("description")).thenReturn("description1");

    List<Info> actualInfos = repository.findAll();
    assertEquals(1, actualInfos.size());
    assertEquals("id1", actualInfos.get(0).getId());
    assertEquals("title1", actualInfos.get(0).getTitle());
    assertEquals("description1", actualInfos.get(0).getDescription());
  }

  @DisplayName("Test findById")
  @Test
  void testFindById() {
    Info expectedInfo = new Info();
    expectedInfo.setId("id1");
    expectedInfo.setTitle("title1");
    expectedInfo.setDescription("description1");

    when(mockSession.prepare(selectInfoWithWhere.build())).thenReturn(preparedSelectInfo);
    when(preparedSelectInfo.bind("id1")).thenReturn(resultSet);
    when(resultSet.one()).thenReturn(row);
    when(row.getString("id")).thenReturn("id1");
    when(row.getString("title")).thenReturn("title1");
    when(row.getString("description")).thenReturn("description1");

    Info actualInfo = repository.findById("id1");

    assertEquals("id1", actualInfo.getId());
    assertEquals("title1", actualInfo.getTitle());
    assertEquals("description1", actualInfo.getDescription());
  }

  @DisplayName("Test insert")
  @Test
  void testInsert() {
    Info expectedInfo = new Info();
    expectedInfo.setId("id1");
    expectedInfo.setTitle("title1");
    expectedInfo.setDescription("description1");

    when(mockSession.prepare(insertInfo.build())).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    Info actualInfo = repository.insert(expectedInfo);

    assertEquals(expectedInfo, actualInfo);
  }

  @DisplayName("Test replace")
  @Test
  void testReplace() {
    Info expectedInfo = new Info();
    expectedInfo.setId("id1");
    expectedInfo.setTitle("title1");
    expectedInfo.setDescription("description1");

    when(mockSession.prepare(updateInfo.build())).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    Info actualInfo = repository.replace("id1", expectedInfo);

    assertEquals(expectedInfo, actualInfo);
  }

  @DisplayName("Test removeById")
  @Test
  void testRemoveById() {
    when(mockSession.prepare(deleteInfo.build())).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    assertTrue(repository.removeById("id1"));
  }
}
