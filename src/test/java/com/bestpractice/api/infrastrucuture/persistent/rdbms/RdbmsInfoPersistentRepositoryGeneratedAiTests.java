package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@ExtendWith(MockitoExtension.class)
public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

  @Mock private JdbcTemplate jdbcTemplate;

  @InjectMocks private RdbmsInfoPersistentRepository repository;

  @BeforeEach
  void setUp() {
    Mockito.reset(jdbcTemplate);
  }

  @Test
  void newId_returnsValidUuid() {
    // GIVEN
    // WHEN
    String id = repository.newId();
    // THEN
    Assertions.assertThat(id).isNotNull().matches(
        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
  }

  @Test
  void findAll_returnsAllInfos() {
    // GIVEN
    Info info1 = new Info();
    info1.setId("1");
    info1.setTitle("Title1");
    info1.setDescription("Desc1");
    Info info2 = new Info();
    info2.setId("2");
    info2.setTitle("Title2");
    info2.setDescription("Desc2");
    List<Info> expected = Arrays.asList(info1, info2);
    Mockito.when(jdbcTemplate.query(Mockito.eq("SELECT * FROM infos"),
        Mockito.any(BeanPropertyRowMapper.class)))
        .thenReturn(expected);

    // WHEN
    List<Info> result = repository.findAll();

    // THEN
    Assertions.assertThat(result).isEqualTo(expected);
    Mockito.verify(jdbcTemplate).query(Mockito.eq("SELECT * FROM infos"),
        Mockito.any(BeanPropertyRowMapper.class));
  }

  @Test
  void findById_returnsCorrectInfo() {
    // GIVEN
    Info expected = new Info();
    expected.setId("123");
    expected.setTitle("Test");
    expected.setDescription("Desc");
    Mockito.when(jdbcTemplate.queryForObject(
            Mockito.eq("SELECT * FROM users"),
            Mockito.any(DataClassRowMapper.class),
            Mockito.eq("123")))
        .thenReturn(expected);

    // WHEN
    Info result = repository.findById("123");

    // THEN
    Assertions.assertThat(result).isEqualTo(expected);
    Mockito.verify(jdbcTemplate).queryForObject(Mockito.eq("SELECT * FROM users"),
        Mockito.any(DataClassRowMapper.class), Mockito.eq("123"));
  }

  @Test
  void insert_successfulInsertsInfo() {
    // GIVEN
    Info info = new Info();
    info.setId("999");
    info.setTitle("Insert");
    info.setDescription("InsertDesc");
    ArgumentCaptor<PreparedStatementCreator> pscCaptor =
        ArgumentCaptor.forClass(PreparedStatementCreator.class);
    ArgumentCaptor<KeyHolder> keyHolderCaptor = ArgumentCaptor.forClass(KeyHolder.class);
    Mockito.when(jdbcTemplate.update(pscCaptor.capture(), keyHolderCaptor.capture()))
        .thenReturn(1);

    // WHEN
    Info result = repository.insert(info);

    // THEN
    Assertions.assertThat(result).isEqualTo(info);
    Mockito.verify(jdbcTemplate).update(Mockito.any(PreparedStatementCreator.class),
        Mockito.any(KeyHolder.class));
    Assertions.assertThat(keyHolderCaptor.getValue()).isInstanceOf(GeneratedKeyHolder.class);
  }

  @Test
  void insert_duplicateKeyThrowsConflict() {
    // GIVEN
    Info info = new Info();
    info.setId("dup");
    info.setTitle("Dup");
    info.setDescription("DupDesc");
    Mockito.when(jdbcTemplate.update(Mockito.any(PreparedStatementCreator.class),
            Mockito.any(KeyHolder.class)))
        .thenThrow(new DuplicateKeyException("duplicate key"));

    // WHEN & THEN
    Assertions.assertThatThrownBy(() -> repository.insert(info))
        .isInstanceOf(Conflict.class)
        .hasMessageContaining("duplicate key");
  }

  @Test
  void replace_updatesInfoAndReturnsIt() {
    // GIVEN
    Info info = new Info();
    info.setId("456");
    info.setTitle("NewTitle");
    info.setDescription("NewDesc");
    Mockito.when(jdbcTemplate.update(Mockito.anyString(),
            Mockito.eq(info.getId()),
            Mockito.eq(info.getTitle()),
            Mockito.eq(info.getDescription())))
        .thenReturn(1);

    // WHEN
    Info result = repository.replace("456", info);

    // THEN
    Assertions.assertThat(result).isEqualTo(info);
    Mockito.verify(jdbcTemplate).update(Mockito.eq(
            "UPDATE infos SET title=?, description=? WHERE id=?"),
        Mockito.eq(info.getTitle()),
        Mockito.eq(info.getDescription()),
        Mockito.eq("456"));
  }

  @Test
  void removeById_successfulDeletionReturnsTrue() {
    // GIVEN
    Mockito.when(jdbcTemplate.update(Mockito.eq("DELETE FROM infos WHERE id=?"),
        Mockito.eq("42")))
        .thenReturn(1);

    // WHEN
    boolean result = repository.removeById("42");

    // THEN
    Assertions.assertThat(result).isTrue();
    Mockito.verify(jdbcTemplate).update(Mockito.eq("DELETE FROM infos WHERE id=?"),
        Mockito.eq("42"));
  }

  @Test
  void removeById_failureDeletionReturnsFalse() {
    // GIVEN
    Mockito.when(jdbcTemplate.update(Mockito.eq("DELETE FROM infos WHERE id=?"),
            Mockito.eq("99")))
        .thenThrow(new RuntimeException("db error"));

    // WHEN
    boolean result = repository.removeById("99");

    // THEN
    Assertions.assertThat(result).isFalse();
    Mockito.verify(jdbcTemplate).update(Mockito.eq("DELETE FROM infos WHERE id=?"),
        Mockito.eq("99"));
  }
}
