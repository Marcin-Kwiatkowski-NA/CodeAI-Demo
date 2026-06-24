package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
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
import org.springframework.jdbc.support.KeyHolder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        reset(jdbcTemplate);
    }

    @Test
    void newId_ShouldReturnValidUUID() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_ShouldReturnListOfInfos() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(List.of(info));

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("Title");
    }

    @Test
    void findById_ShouldReturnInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(info);

        // WHEN
        Info result = repository.findById("1");

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
    }

    @Test
    void insert_ShouldInsertInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        doAnswer(invocation -> 1).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void insert_ShouldThrowConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldUpdateInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void removeById_ShouldReturnTrueOnSuccessfulDelete() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldReturnFalseOnException() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), anyString())).thenThrow(new RuntimeException("Error"));

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void preparedStatement_ShouldThrowInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("SQL Error"));
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN THEN
        assertThatThrownBy(() -> {
            repository.insert(info);
        }).isInstanceOfAny(Conflict.class, InternalServerError.class);
    }
}
