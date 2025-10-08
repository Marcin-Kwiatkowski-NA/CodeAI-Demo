package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private JdbcTemplate jdbcTemplate;
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
    }

    @Test
    void testNewIdGeneratesUUID() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(info1));

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString()))
                .thenReturn(info);

        // WHEN
        Info result = repository.findById("123");

        // THEN
        assertNotNull(result);
        assertEquals("123", result.getId());
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(java.util.function.Function.class), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNotNull(result);
        assertEquals("id1", result.getId());
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(java.util.function.Function.class), any(GeneratedKeyHolder.class)))
                .thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(eq("UPDATE infos SET title = ?, description = ? WHERE id = ?"), any(Object.class), any(Object.class), any(Object.class))).thenReturn(1);

        // WHEN
        Info result = repository.replace("id1", info);

        // THEN
        assertNotNull(result);
        assertEquals("NewTitle", result.getTitle());
    }

    @Test
    void testRemoveByIdReturnsTrueOnSuccess() {
        // GIVEN
        when(jdbcTemplate.update(eq("DELETE FROM infos WHERE id = ?"), any(Object.class))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("id1");

        // THEN
        assertTrue(result);
    }

    @Test
    void testRemoveByIdReturnsFalseOnException() {
        // GIVEN
        when(jdbcTemplate.update(eq("DELETE FROM infos WHERE id = ?"), any(Object.class))).thenThrow(new RuntimeException("error"));

        // WHEN
        boolean result = repository.removeById("id1");

        // THEN
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("fail"));
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> {
            var method = RdbmsInfoPersistentRepository.class.getDeclaredMethod("preparedStatement", Connection.class, String.class, Info.class);
            method.setAccessible(true);
            method.invoke(repository, connection, "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)", info);
        });
    }
}
