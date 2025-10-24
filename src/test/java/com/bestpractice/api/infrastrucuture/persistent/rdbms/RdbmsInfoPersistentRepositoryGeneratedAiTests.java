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
import java.sql.PreparedStatement;
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
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id = repository.newId();

        // THEN the ID should be a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN a list of infos in the database
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        List<Info> expectedList = Arrays.asList(info1);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        // WHEN finding all infos
        List<Info> result = repository.findAll();

        // THEN the result should match the expected list
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN an info with a specific ID
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"))).thenReturn(info);

        // WHEN finding by ID
        Info result = repository.findById("123");

        // THEN the result should match the expected info
        assertEquals(info, result);
    }

    @Test
    void testInsertSuccess() {
        // GIVEN a valid info to insert
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // WHEN inserting the info
        Info result = repository.insert(info);

        // THEN the returned info should be the same as inserted
        assertEquals(info, result);
    }

    @Test
    void testInsertDuplicateKeyThrowsConflict() {
        // GIVEN a duplicate key scenario
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class))).thenThrow(new DuplicateKeyException("duplicate"));

        // WHEN inserting the info
        // THEN a Conflict exception should be thrown
        assertThrows(Conflict.class, () -> repository.insert(info));
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN an info to replace
        Info info = new Info();
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN replacing the info
        Info result = repository.replace("id1", info);

        // THEN the returned info should match the updated info
        assertEquals(info, result);
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN an existing ID
        when(jdbcTemplate.update(anyString(), any())).thenReturn(1);

        // WHEN removing by ID
        boolean result = repository.removeById("id1");

        // THEN the result should be true
        assertTrue(result);
    }