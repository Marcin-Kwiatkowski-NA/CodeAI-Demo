package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.KeyHolder;

@ExtendWith(MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void testNewId() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertNotNull(id);
    }

    @Test
    void testFindAll() {
        // GIVEN
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(List.of(info));
        // WHEN
        List<Info> result = repository.findAll();
        // THEN
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
    }

    @Test
    void testFindById() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(info);
        // WHEN
        Info result = repository.findById("1");
        // THEN
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    void testInsert() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), any(PreparedStatementSetter.class), any(KeyHolder.class));
        // WHEN
        Info result = repository.insert(info);
        // THEN
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    void testReplace() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), any(String.class), any(String.class), anyString());
        // WHEN
        Info result = repository.replace("1", info);
        // THEN
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    void testRemoveById() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), anyString());
        // WHEN
        boolean result = repository.removeById("1");
        // THEN
        assertTrue(result);
    }
}
