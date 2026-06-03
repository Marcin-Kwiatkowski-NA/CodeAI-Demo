package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;


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

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        // No reset needed
    }

    @Test
    void testNewIdGeneratesUUID() {
        // GIVEN
        // No setup required

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThatCode(() -> UUID.fromString(id)).doesNotThrowAnyException();
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Mockito.doReturn(List.of(info)).when(jdbcTemplate).query(anyString(), any(BeanPropertyRowMapper.class));

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo("1");
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        Mockito.doReturn(info).when(jdbcTemplate).queryForObject(anyString(), any(DataClassRowMapper.class), anyString());

        // WHEN
        Info result = repository.findById("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Mockito.doReturn(1).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Mockito.doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
    }

    @Test
    void testReplaceUpdatesRecord() {
        // GIVEN
        Info info = new Info();
        info.setTitle("Updated");
        info.setDescription("Updated Desc");
        Mockito.doReturn(1).when(jdbcTemplate).update(anyString(), any(), any(), any());

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated");
    }

    @Test
    void testRemoveByIdReturnsTrueOnSuccess() {
        // GIVEN
        Mockito.doReturn(1).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseOnException() {
        // GIVEN
        Mockito.doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void testPreparedStatementThrowsInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection connection = mock(Connection.class);
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOfAny(Conflict.class, InternalServerError.class);
    }
}
