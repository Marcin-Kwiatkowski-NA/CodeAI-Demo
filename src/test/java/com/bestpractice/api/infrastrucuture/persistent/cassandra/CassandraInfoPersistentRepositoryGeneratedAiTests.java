package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  @Mock
  private CqlSession mockSession;

  @Mock
  private PreparedStatement mockPreparedStatement;

  @Mock
  private ResultSet mockResultSet;

  @Mock
  private Row mockRow;

  private CassandraInfoPersistentRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    repository = new CassandraInfoPersistentRepository(mockSession);
  }

  @Test
  void newId_shouldGenerateUniqueId() {
    // GIVEN

    // WHEN
    String id1 = repository.newId();
    String id2 = repository.newId();

    // THEN
    assertThat(id1).isNotNull();
    assertThat(id2).isNotNull();
    assertThat(id1).isNotEqualTo(id2);
  }

  @Test
  void findAll_shouldReturnListOfInfos() {
    // GIVEN
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.iterator()).thenReturn(List.of(mockRow).iterator());
    when(mockRow.getString("id")).thenReturn("1");
    when(mockRow.getString("title")).thenReturn("Test Title");
    when(mockRow.getString("description")).thenReturn("Test Description");

    // WHEN
    List<Info> infos = repository.findAll();

    // THEN
    assertThat(infos).isNotNull();
    assertThat(infos).hasSize(1);
    assertThat(infos.get(0).getId()).isEqualTo("1");
    assertThat(infos.get(0).getTitle()).isEqualTo("Test Title");
    assertThat(infos.get(0).getDescription()).isEqualTo("Test Description");
  }

  @Test
  void findById_shouldReturnInfoWhenExists() {
    // GIVEN
    String id = "1";
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.one()).thenReturn(mockRow);
    when(mockRow.getString("id")).thenReturn(id);
    when(mockRow.getString("title")).thenReturn("Test Title");
    when(mockRow.getString("description")).thenReturn("Test Description");

    // WHEN
    Info info = repository.findById(id);

    // THEN
    assertThat(info).isNotNull();
    assertThat(info.getId()).isEqualTo(id);
    assertThat(info.getTitle()).isEqualTo("Test Title");
    assertThat(info.getDescription()).isEqualTo("Test Description");
  }

  @Test
  void findById_shouldReturnNullWhenNotExists() {
    // GIVEN
    String id = "1";
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.one()).thenReturn(null);

    // WHEN
    Info info = repository.findById(id);

    // THEN
    assertThat(info).isNull();
  }

  @Test
  void insert_shouldReturnInfoWhenSuccessful() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info result = repository.insert(info);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo("1");
    assertThat(result.getTitle()).isEqualTo("Test Title");
    assertThat(result.getDescription()).isEqualTo("Test Description");
  }

  @Test
  void insert_shouldReturnNullWhenNotSuccessful() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info result = repository.insert(info);

    // THEN
    assertThat(result).isNull();
  }

  @Test
  void replace_shouldReturnInfoWhenSuccessful() {
    // GIVEN
    String id = "1";
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(id);
    assertThat(result.getTitle()).isEqualTo("Updated Title");
    assertThat(result.getDescription()).isEqualTo("Updated Description");
  }

  @Test
  void replace_shouldReturnNullWhenNotSuccessful() {
    // GIVEN
    String id = "1";
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    assertThat(result).isNull();
  }

  @Test
  void removeById_shouldReturnTrueWhenSuccessful() {
    // GIVEN
    String id = "1";
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(true);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldReturnFalseWhenNotSuccessful() {
    // GIVEN
    String id = "1";
    when(mockSession.prepare(any())).thenReturn(mockPreparedStatement);
    when(mockSession.execute(any(PreparedStatement.class))).thenReturn(mockResultSet);
    when(mockResultSet.wasApplied()).thenReturn(false);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isFalse();
  }
}
