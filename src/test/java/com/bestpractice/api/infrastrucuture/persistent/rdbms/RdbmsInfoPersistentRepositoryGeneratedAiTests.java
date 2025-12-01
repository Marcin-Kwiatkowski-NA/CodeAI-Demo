package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void findAll_ShouldReturnListOfInfo() {
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
    void findById_ShouldReturnInfo_WhenIdExists() {
        // GIVEN
        String id = "123";
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
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        // GIVEN
        String id = "123";
        String sql = "SELECT * FROM users WHERE id = ?";
        when(jdbcTemplate.queryForObject(eq(sql), any(DataClassRowMapper.class), eq(id))).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(org.springframework.dao.EmptyResultDataAccessException.class);
        verify(jdbcTemplate, times(1)).queryForObject(eq(sql), any(DataClassRowMapper.class), eq(id));
    }

    @Test
    void insert_ShouldInsertInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        doAnswer(invocation -> {
            Connection connection = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            when(connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)).thenReturn(ps);
            return ps;
        }).when(jdbcTemplate).update(any(), eq(keyHolder));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
    }

    @Test
    void insert_ShouldThrowConflictException_WhenDuplicateKeyOccurs() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?,?, ?)";
        doThrow(new org.springframework.dao.DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(org.springframework.dao.DuplicateKeyException.class);
        verify(jdbcTemplate, times(1)).update(any(), any(KeyHolder.class));
    }

    @Test
    void replace_ShouldUpdateInfoSuccessfully() {
        // GIVEN
        String id = "123";
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        String sql = "UPDATE infos SET title = ?, description = ? WHERE id = ?";
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
        String id = "123";
        String sql = "DELETE FROM infos WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(sql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(id));
    }

    @Test
    void removeById_ShouldReturnFalse_WhenDeletionFails() {
        // GIVEN
        String id = "123";
        String sql = "DELETE FROM infos WHERE id = ?";
        doThrow(new RuntimeException("Deletion failed")).when(jdbcTemplate).update(eq(sql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(eq(sql), eq(id));
    }
}