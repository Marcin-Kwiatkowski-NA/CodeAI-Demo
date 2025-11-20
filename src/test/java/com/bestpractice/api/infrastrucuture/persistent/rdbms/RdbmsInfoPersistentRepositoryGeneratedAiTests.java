package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import com.bestpractice.api.common.exception.Conflict;
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
        Mockito.clearInvocations(jdbcTemplate);
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No specific setup required

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
        when(jdbcTemplate.query(Mockito.eq(sql), Mockito.any(BeanPropertyRowMapper.class))).thenReturn(mockInfos);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(jdbcTemplate, Mockito.times(1)).query(Mockito.eq(sql), Mockito.any(BeanPropertyRowMapper.class));
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        String id = "test-id";
        String sql = "SELECT * FROM users WHERE id = ?";
        Info mockInfo = new Info();
        mockInfo.setId(id);
        when(jdbcTemplate.queryForObject(Mockito.eq(sql), Mockito.any(DataClassRowMapper.class), Mockito.eq(id))).thenReturn(mockInfo);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(jdbcTemplate, Mockito.times(1)).queryForObject(Mockito.eq(sql), Mockito.any(DataClassRowMapper.class), Mockito.eq(id));
    }

    @Test
    void insert_shouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        when(jdbcTemplate.update(Mockito.any(), Mockito.eq(keyHolder))).thenAnswer(invocation -> {
            Connection connection = invocation.getArgument(0);
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, info.getId());
            ps.setString(2, info.getTitle());
            ps.setString(3, info.getDescription());
            return ps;
        });

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(info.getId());
        verify(jdbcTemplate, Mockito.times(1)).update(Mockito.any(), Mockito.eq(keyHolder));
    }

    @Test
    void insert_shouldThrowConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Mockito.doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(Mockito.any(), Mockito.eq(keyHolder));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
        verify(jdbcTemplate, Mockito.times(1)).update(Mockito.any(), Mockito.eq(keyHolder));
    }

    @Test
    void replace_shouldUpdateInfo() {
        // GIVEN
        String id = "test-id        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        String sql = "UPDATE infos SET title = ?, description = ? WHERE id = ?";
        Mockito.doNothing().when(jdbcTemplate).update(Mockito.eq(sql), Mockito.eq(info.getTitle()), Mockito.eq(info.getDescription()), Mockito.eq(id));

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(info.getTitle());
        assertThat(result.getDescription()).isEqualTo(info.getDescription());
        verify(jdbcTemplate, Mockito.times(1)).update(Mockito.eq(sql), Mockito.eq(info.getTitle()), Mockito.eq(info.getDescription()), Mockito.eq(id));
    }

    @Test
    void removeById_shouldDeleteInfo() {
        // GIVEN
        String id = "test-id";
        String sql = "DELETE FROM infos WHERE id = ?";
        Mockito.doNothing().when(jdbcTemplate).update(Mockito.eq(sql), Mockito.eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, Mockito.times(1)).update(Mockito.eq(sql), Mockito.eq(id));
    }

    @Test
    void removeById_shouldReturnFalseOnException() {
        // GIVEN
        String id = "test-id";
        String sql = "DELETE FROM infos WHERE id = ?";
        Mockito.doThrow(new RuntimeException("Delete failed")).when(jdbcTemplate).update(Mockito.eq(sql), Mockito.eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, Mockito.times(1)).update(Mockito.eq(sql), Mockito.eq(id));
    }
}
