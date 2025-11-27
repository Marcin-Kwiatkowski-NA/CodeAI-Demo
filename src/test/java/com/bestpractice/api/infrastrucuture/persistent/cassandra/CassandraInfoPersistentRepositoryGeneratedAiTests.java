package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CassandraInfoPersistentRepositoryGeneratedAiTests {

  @Mock
  private CqlSession session;

  @Mock
  private PreparedStatement preparedStatement;

  @Mock
  private ResultSet resultSet;

  @Mock
  private Row row;

  @InjectMocks
  private CassandraInfoPersistentRepository repository;

  @BeforeEach
  void setUp() {
    repository = new CassandraInfoPersistentRepository(session);
  }

  @Test
  void newId_ShouldGenerateUniqueId() {
    // GIVEN
    // No specific setup required

    // WHEN
    String id1 = repository.newId();
    String id2 = repository.newId();

    // THEN
    assertThat(id1).isNotNull();
    assertThat(id2).isNotNull();
    assertThat(id1).isNotEqualTo(id2);
  }

  @Test
  void findAll_ShouldReturnListOfInfos() {
    // GIVEN
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.iterator()).thenReturn(List.of(row).iterator());
    when(row.getString("id")).thenReturn("1");
    when(row.getString("title")).thenReturn("Test Title");
    when(row.getString("description")).thenReturn("Test Description");

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
  void findById_ShouldReturnInfoWhenExists() {
    // GIVEN
    String id = "1";
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.one()).thenReturn(row);
    when(row.getString("id")).thenReturn(id);
    when(row.getString("title")).thenReturn("Test Title");
    when(row.getString("description")).thenReturn("Test Description");

    // WHEN
    Info info = repository.findById(id);

    // THEN
    assertThat(info).isNotNull();
    assertThat(info.getId()).isEqualTo(id);
    assertThat(info.getTitle()).isEqualTo("Test Title");
    assertThat(info.getDescription()).isEqualTo("Test Description");
  }

  @Test
  void findById_ShouldReturnNullWhenNotExists() {
    // GIVEN
    String id = "1";
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.one()).thenReturn(null);

    // WHEN
    Info info = repository.findById(id);

    // THEN
    assertThat(info).isNull();
  }

  @Test
  void insert_ShouldInsertInfoSuccessfully() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info insertedInfo = repository.insert(info);

    // THEN
    assertThat(insertedInfo).isNotNull();
    assertThat(insertedInfo.getId()).isEqualTo("1");
    assertThat(insertedInfo.getTitle()).isEqualTo("Test Title");
    assertThat(insertedInfo.getDescription()).isEqualTo("Test Description");
  }

  @Test
  void insert_ShouldReturnNullWhenInsertFails() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info insertedInfo = repository.insert(info);

    // THEN
    assertThat(insertedInfo).isNull();
  }

  @Test
  void replace_ShouldUpdateInfoSuccessfully() {
    // GIVEN
    String id = "1";
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info updatedInfo = repository.replace(id, info);

    // THEN
    assertThat(updatedInfo).isNotNull();
    assertThat(updatedInfo.getId()).isEqualTo(id);
    assertThat(updatedInfo.getTitle()).isEqualTo("Updated Title");
    assertThat(updatedInfo.getDescription()).isEqualTo("Updated Description");
  }

  @Test
  void replace_ShouldReturnNullWhenUpdateFails() {
    // GIVEN
    String id = "1";
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info updatedInfo = repository.replace(id, info);

    // THEN
    assertThat(updatedInfo).isNull();
  }

  @Test
  void removeById_ShouldRemoveInfoSuccessfully() {
    // GIVEN
    String id = "1";
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    boolean isRemoved = repository.removeById(id);

    // THEN
    assertThat(isRemoved).isTrue();
  }

  @Test
  void removeById_ShouldReturnFalseWhenRemoveFails() {
    // GIVEN
    String id = "1";
    when(session.prepare(any())).thenReturn(preparedStatement);
    when(session.execute(any(PreparedStatement.class))).thenReturn(resultSet);
    when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    boolean isRemoved = repository.removeById(id);

    // THEN
    assertThat(isRemoved).isFalse();
  }
}