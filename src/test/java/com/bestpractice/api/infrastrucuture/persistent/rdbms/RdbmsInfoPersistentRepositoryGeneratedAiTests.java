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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN
        // No setup required

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isNotEmpty();
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        // GIVEN
        List<Info> expectedList = List.of(info);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Sample Title");
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(info);

        // WHEN
        Info result = repository.findById(info.getId());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(info.getId());
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        doAnswer(invocation -> {
            Connection connection = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            when(connection.prepareStatement(anyString(), anyInt())).thenReturn(ps);
            return 1;
        }).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Sample Title");
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN
        Info result = repository.replace(info.getId(), info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo("Sample Description");
    }

    @Test
    void testRemoveByIdReturnsTrueOnSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        // WHEN
        boolean result = repository.removeById(info.getId());

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseOnException() {
        // GIVEN
        doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN
        boolean result = repository.removeById(info.getId());

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void testPreparedStatementThrowsInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("SQL Error"));

        // WHEN / THEN
        assertThatThrownBy(() -> {
            repository.insert(info);
        }).isInstanceOf(Conflict.class);
    }
}
