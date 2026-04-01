package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.sql.Connection;
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

    @BeforeEach
    void setUp() {
        reset(jdbcTemplate);
    }

    @Test
    void testNewIdGeneratesUniqueUUID() {
        // GIVEN
        // No setup required

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindAllReturnsListOfInfos() {
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
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(info);

        // WHEN
        Info result = repository.findById("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Sample");
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"));
    }

    @Test
    void testInsertSuccessful() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        doAnswer(invocation -> null).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(jdbcTemplate, times(1)).update(any(), any(KeyHolder.class));
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
        verify(jdbcTemplate, times(1)).update(any(), any(KeyHolder.class));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN
        Info info = new Info();
        info.setTitle("UpdatedTitle");
        info.setDescription("UpdatedDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN
        Info result = repository.replace("id1", info);

        // THEN
        assertThat(result.getTitle()).isEqualTo("UpdatedTitle");
        assertThat(result.getDescription()).isEqualTo("UpdatedDesc");
        verify(jdbcTemplate, times(1)).update(anyString(), eq("UpdatedTitle"), eq("UpdatedDesc"), eq("id1"));
    }

    @Test
    void testRemoveByIdReturnsTrueOnSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("id1");

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(anyString(), eq("id1"));
    }

    @Test
    void testRemoveByIdReturnsFalseOnException() {
        // GIVEN
        doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN
        boolean result = repository.removeById("id1");

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(anyString(), eq("id1"));
    }

    @Test
    void testPreparedStatementThrowsInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("SQL Error"));
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
    }
}
