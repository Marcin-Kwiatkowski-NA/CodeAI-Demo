package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
        // GIVEN a connection that throws SQLException
        java.sql.Connection connection = mock(java.sql.Connection.class);
package com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
package com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
package com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
        // GIVEN a connection thatpackage com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
        // GIVEN a connection thatpackage com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }

    @Test
    void testPreparedStatementThrowsInternalServerError() throws Exception {
        // GIVEN a connection thatpackage com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
import java.sql.Statement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos returned by jdbcTemplate
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN calling findAll
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info returned by jdbcTemplate
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN calling findById
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN calling insert
        Info result = repository.insert(info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN jdbcTemplate update throws DuplicateKeyException
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN & THEN calling insert should throw Conflict
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN jdbcTemplate update succeeds
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN calling replace
        Info result = repository.replace("id1", info);

        // THEN the returned info should be the same as provided
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN jdbcTemplate update succeeds
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN jdbcTemplate update throws exception
        when(jdbcTemplate.update(anyString(), any())).thenThrow(new RuntimeException("error"));

        // WHEN calling removeById
        boolean result = repository.removeById("id1");

        // THEN the result should be false
        assertFalse(result);
    }
