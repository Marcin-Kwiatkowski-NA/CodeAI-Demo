package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.clearInvocations;

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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
        clearInvocations(jdbcTemplate);
    }

    @Test
    void newId_ShouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_ShouldReturnListOfInfos() {
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
    void findById_ShouldReturnInfo() {
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
    void insert_ShouldInsertInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        when(jdbcTemplate.update(any(), eq(keyHolder))).thenAnswer(invocation -> {
            Connection connection = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            when(connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(ps);
            return 1;
        });

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("test-id");
        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
    }

    @Test
    void insert_ShouldThrowConflictExceptionOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("Duplicate key");
    }

    @Test
    void replace_ShouldUpdateInfoSuccessfully() {
        // GIVEN
        String id = "test-id";
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        String sql = "UPDATE infos SET title =?, description = ? WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(sql), eq(info.getTitle()), eq(info.getDescription()), eq(id));

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(info.getTitle()), eq(info.getDescription()), eq(id));
    }

    @Test
    void removeById_ShouldDeleteInfoSuccessfully() {
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
    void removeById_ShouldReturnFalseOnFailure() {
        // GIVEN
        String id = "test-id";
        String sql = "DELETE FROM infos WHERE id = ?";
        doThrow(new RuntimeException("Delete failed")).when(jdbcTemplate).update(eq(sql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(id));
    }

    @Test
    void preparedStatement_ShouldThrowInternalServerErrorOnSQLException() {
        // GIVEN
        Connection connection = mock(Connection.class);
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        try {
            when(connection.prepareStatement(eq(sql), eq(PreparedStatement.RETURN_GENERATED_KEYS)))
                    .thenThrow(new SQLException("SQL error"));
        } catch (SQLException ignored) {
        }

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to run sql.");
    }
}
