package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

@ExtendWith(MockitoExtension.class)
public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
        Mockito.reset(jdbcTemplate);
    }

    @Test
    void newId_ShouldReturnUuidString() {
        // GIVEN
        // (No setup required)

        // WHEN
        String id = repository.newId();

        // THEN
        Assertions.assertThat(id)
                .isNotNull()
                .matches("^[0-9a-fA-F-]{36}$");
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
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any()))
                .thenReturn(expected);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        Assertions.assertThat(result).isEqualTo(expected);
        Mockito.verify(jdbcTemplate).query(Mockito.eq("SELECT * FROM infos"), Mockito.any());
    }

    @Test
    void findById_ShouldReturnInfoById() {
        // GIVEN
        String id = "123";
        Info expected = new Info();
        expected.setId(id);
        expected.setTitle("Title");
        expected.setDescription("Desc");
        Mockito.when(jdbcTemplate.queryForObject(
                        Mockito.anyString(),
                        Mockito.any(),
                        Mockito.eq(id)))
                .thenReturn(expected);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        Assertions.assertThat(result).isEqualTo(expected);
        Mockito.verify(jdbcTemplate).queryForObject(
                Mockito.eq("SELECT * FROM users WHERE id = ?"),
                Mockito.any(),
                Mockito.eq(id));
    }

    @Test
    void insert_ShouldReturnInfoWhenSuccessful() {
        // GIVEN
        Info info = new Info();
        info.setId("id-1");
        info.setTitle("Title");
        info.setDescription("Desc");
        KeyHolder keyHolder = Mockito.mock(KeyHolder.class);
        Mockito.doNothing().when(jdbcTemplate)
                .update(Mockito.any(), Mockito.eq(keyHolder));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        Assertions.assertThat(result).isSameAs(info);
        ArgumentCaptor<org.springframework.jdbc.core.PreparedStatementCreator> captor =
                ArgumentCaptor.forClass(org.springframework.jdbc.core.PreparedStatementCreator.class);
        Mockito.verify(jdbcTemplate).update(captor.capture(), Mockito.eq(keyHolder));
        // Verify that the PreparedStatementCreator will set the correct values
        // (the actual statement execution is not performed in this test)
    }

    @Test
    void insert_ShouldThrowConflictWhenDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("id-1");
        info.setTitle("Title");
        info.setDescription("Desc");
        KeyHolder keyHolder = Mockito.mock(KeyHolder.class);
        Mockito.doThrow(new DuplicateKeyException("duplicate"))
                .when(jdbcTemplate)
                .update(Mockito.any(), Mockito.eq(keyHolder));

        // WHEN & THEN
        Assertions.assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("duplicate");
    }

    @Test
    void replace_ShouldReturnUpdatedInfo() {
        // GIVEN
        String id = "id-1";
        Info info = new Info();
        info.setTitle("New Title");
        info.setDescription("New Description");

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        Assertions.assertThat(result).isSameAs(info);
        Mockito.verify(jdbcTemplate).update(
                Mockito.eq("UPDATE infos SET title = ?, description = ? WHERE id = ?"),
                Mockito.eq(info.getTitle()),
                Mockito.eq(info.getDescription()),
                Mockito.eq(id));
    }

    @Test
    void removeById_ShouldReturnTrueWhenDeletionSucceeds() {
        // GIVEN
        String id = "id-1";
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.eq(id))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isTrue();
        Mockito.verify(jdbcTemplate).update(
                Mockito.eq("DELETE FROM infos WHERE id = ?"),
                Mockito.eq(id));
    }

    @Test
    void removeById_ShouldReturnFalseWhenDeletionFails() {
        // GIVEN
        String id = "id-1";
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.eq(id)))
                .thenThrow(new RuntimeException("db error"));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isFalse();
    }
}
