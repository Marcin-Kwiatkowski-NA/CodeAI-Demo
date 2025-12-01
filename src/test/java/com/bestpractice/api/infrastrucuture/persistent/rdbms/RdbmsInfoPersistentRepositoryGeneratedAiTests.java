package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.*;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(jdbcTemplate);
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_shouldReturnListOfInfos() {
        // GIVEN
        String sql = "SELECT * FROM infos";
        List<Info> mockInfos = List.of(new Info(), new Info());
        when(jdbcTemplate.query(eq(sql), any(BeanPropertyRowMapper.class))).thenReturn(mockInfos);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(jdbcTemplate, times(1)).query(eq(sql), any(BeanPropertyRowMapper.class));
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        String id = "test-id";
        String sql = "SELECT * FROM users WHERE id = ?";
        Info mockInfo = new Info();
        when(jdbcTemplate.queryForObject(eq(sql), any(DataClassRowMapper.class), eq(id))).thenReturn(mockInfo);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        verify(jdbcTemplate, times(1)).queryForObject(eq(sql), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void insert_shouldInsertInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("test-title");
        info.setDescription("test-description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        doAnswer(invocation -> {
            Connection connection = invocation.getArgument(0);
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, info.getId());
            ps.setString(2, info.getTitle());
            ps.setString(3, info.getDescription());
            return ps;
        }).when(jdbcTemplate).update(any(), eq(keyHolder));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("test-id");
        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
    }

    @Test
    void insert_shouldThrowConflictExceptionOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("test-title");
        info.setDescription("test-description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_shouldUpdateInfoSuccessfully() {
        // GIVEN
        String id = "test-id";
        Info info = new Info();
        info.setTitle("updated-title");
        info.setDescription("updated-description");
        String sql = "UPDATE infos SET title = ?, description = ? WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(sql),eq(info.getTitle()), eq(info.getDescription()), eq(id));

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("updated-title");
        assertThat(result.getDescription()).isEqualTo("updated-description");
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(info.getTitle()), eq(info.getDescription()), eq(id));
    }

    @Test
    void removeById_shouldDeleteInfoSuccessfully() {
        // GIVEN
        String id = "test-id";
        String sql = "DELETE FROM infos WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(sql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(id));
    }

    @Test
    void removeById_shouldReturnFalseOnFailure() {
        // GIVEN
        String id = "test-id";
        String sql = "DELETE FROM infos WHERE id = ?";
        doThrow(new RuntimeException("Deletion failed")).when(jdbcTemplate).update(eq(sql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(id));
    }
}
