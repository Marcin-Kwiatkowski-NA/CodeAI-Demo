package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@ExtendWith(MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

  @Mock private JdbcTemplate jdbcTemplate;

  @InjectMocks private RdbmsInfoPersistentRepository repository;

  @BeforeEach
  void setUp() {
    Mockito.reset(jdbcTemplate);
  }

  @Test
  void newId_ShouldReturnUuidString() {
    // GIVEN
    // (no setup needed)

    // WHEN
    String id = repository.newId();

    // THEN
    Assertions.assertThat(id).isNotNull();
    Assertions.assertThat(id).matches(
        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$");
  }

  @Test
  void findAll_ShouldReturnAllInfos() {
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
    Mockito.when(jdbcTemplate.query(
            Mockito.eq("SELECT * FROM infos"),
            Mockito.any(BeanPropertyRowMapper.class)))
        .thenReturn(expected);

    // WHEN
    List<Info> result = repository.findAll();

    // THEN
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void findById_ShouldReturnInfo() {
    // GIVEN
    String id = "123";
    Info expected = new Info();
    expected.setId(id);
    expected.setTitle("Title");
    expected.setDescription("Desc");
    Mockito.when(
            jdbcTemplate.queryForObject(
                Mockito.eq("SELECT * FROM users WHERE id = ?"),
                Mockito.any(DataClassRowMapper.class),
                Mockito.eq(id)))
        .thenReturn(expected);

    // WHEN
    Info result = repository.findById(id);

    // THEN
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void insert_ShouldReturnInfo_WhenSuccessful() {
    // GIVEN
    Info info = new Info();
    info.setId("id1");
    info.setTitle("Title");
    info.setDescription("Desc");
    Mockito.doAnswer(
            invocation -> {
              KeyHolder keyHolder = invocation.getArgument(1);
              keyHolder.getKeyList().add("id1");
              return 1;
            })
        .when(jdbcTemplate)
        .update(Mockito.any(), Mockito.any(KeyHolder.class));

    // WHEN
    Info result = repository.insert(info);

    // THEN
    Assertions.assertThat(result).isEqualTo(info);
    ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
    Mockito.verify(jdbcTemplate).update(sqlCaptor.capture(), Mockito.any(KeyHolder.class));
    Assertions.assertThat(sqlCaptor.getValue())
        .isEqualTo("INSERT INTO infos (id, title, description) VALUES (?, ?, ?)");
  }

  @Test
  void insert_ShouldThrowConflict_WhenDuplicateKey() {
    // GIVEN
    Info info = new Info();
    info.setId("id1");
    info.setTitle("Title");
    info.setDescription("Desc");
    Mockito.doThrow(new DuplicateKeyException("duplicate"))
        .when(jdbcTemplate)
        .update(Mockito.any(), Mockito.any(KeyHolder.class));

    // WHEN & THEN
    Assertions.assertThatThrownBy(() -> repository.insert(info))
        .isInstanceOf(Conflict.class)
        .hasMessageContaining("duplicate");
  }

  @Test
  void replace_ShouldUpdateInfoAndReturnIt() {
    // GIVEN
    String id = "id1";
    Info info = new Info();
    info.setTitle("New Title");
    info.setDescription("New Desc");
    Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any()))
        .thenReturn(1);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    Assertions.assertThat(result).isEqualTo(info);
    Mockito.verify(jdbcTemplate)
        .update(
            Mockito.eq("UPDATE infos SET title = ?, description = ? WHERE id = ?"),
            Mockito.eq(info.getTitle()),
            Mockito.eq(info.getDescription()),
            Mockito.eq(id));
  }

  @Test
  void removeById_ShouldReturnTrue_WhenDeleteSuccessful() {
    // GIVEN
    String id = "id1";
    Mockito.when(jdbcTemplate.update(Mockito.eq("DELETE FROM infos WHERE id = ?"), Mockito.eq(id)))
        .thenReturn(1);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    Assertions.assertThat(result).isTrue();
    Mockito.verify(jdbcTemplate)
        .update(Mockito.eq("DELETE FROM infos WHERE id = ?"), Mockito.eq(id));
  }

  @Test
  void removeById_ShouldReturnFalse_WhenExceptionThrown() {
    // GIVEN
    String id = "id1";
    Mockito.when(jdbcTemplate.update(Mockito.eq("DELETE FROM infos WHERE id = ?"), Mockito.eq(id)))
        .thenThrow(new RuntimeException("db error"));

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    Assertions.assertThat(result).isFalse();
  }
}
