package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    // No need for clearInvocations as it is not defined
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
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.iterator()).thenReturn(List.of(row).iterator());
    Mockito.when(row.getString(Mockito.anyString())).thenReturn("1", "Test Title", "Test Description");

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
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.one()).thenReturn(row);
    Mockito.when(row.getString(Mockito.anyString())).thenReturn(id, "Test Title", "Test Description");

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
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.one()).thenReturn(null);

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
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info insertedInfo = repository.insert(info);

    // THEN
    assertThat(insertedInfo).isNotNull();
    assertThat(insertedInfo.getId()).isEqualTo("1");
    assertThat(insertedInfo.getTitle()).isEqualTo("Test Title");
    assertThat(insertedInfo.getDescription()).isEqualTo("Test Description");
  }

  @Test
  void insert_shouldReturnNullWhenFailed() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info insertedInfo = repository.insert(info    // THEN
    assertThat(insertedInfo).isNull();
  }

  @Test
  void replace_shouldReturnInfoWhenSuccessful() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    Info replacedInfo = repository.replace("1", info);

    // THEN
    assertThat(replacedInfo).isNotNull();
    assertThat(replacedInfo.getId()).isEqualTo("1");
    assertThat(replacedInfo.getTitle()).isEqualTo("Updated Title");
    assertThat(replacedInfo.getDescription()).isEqualTo("Updated Description");
  }

  @Test
  void replace_shouldReturnNullWhenFailed() {
    // GIVEN
    Info info = new Info();
    info.setId("1");
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    Info replacedInfo = repository.replace("1", info);

    // THEN
    assertThat(replacedInfo).isNull();
  }

  @Test
  void removeById_shouldReturnTrueWhenSuccessful() {
    // GIVEN
    String id = "1";
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(true);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldReturnFalseWhenFailed() {
    // GIVEN
    String id = "1";
    Mockito.when(session.prepare(Mockito.anyString())).thenReturn(preparedStatement);
    Mockito.when(session.execute(Mockito.any())).thenReturn(resultSet);
    Mockito.when(resultSet.wasApplied()).thenReturn(false);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isFalse();
  }
}
