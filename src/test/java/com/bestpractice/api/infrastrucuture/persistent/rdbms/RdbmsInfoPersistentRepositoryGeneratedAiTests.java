package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.*;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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
        reset(jdbcTemplate);
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No preconditions required

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_shouldReturnListOfInfos() {
        // GIVEN
        List<Info> expectedInfos = Arrays.asList(new Info(), new Info());
        String sql = "SELECT * FROM infos";
        when(jdbcTemplate.query(eq(sql), any())).thenReturn(expectedInfos);

        // WHEN
        List<Info> actualInfos = repository.findAll();

        // THEN
        assertThat(actualInfos).isNotNull();
        assertThat(actualInfos).hasSize(2);
        assertThat(actualInfos).isEqualTo(expectedInfos);
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        String id = "test-id";
        Info expectedInfo = new Info();
        expectedInfo.setId(id);
        String sql = "SELECT * FROM users WHERE id = ?";
        when(jdbcTemplate.queryForObject(eq(sql), any(), eq(id))).thenReturn(expectedInfo);

        // WHEN
        Info actualInfo = repository.findById(id);

        // THEN
        assertThat(actualInfo).isNotNull();
        assertThat(actualInfo.getId()).isEqualTo(id);
    }

    @Test
    void insert_shouldInsertInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        doNothing().when(jdbcTemplate).update(any(), eq(keyHolder));

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertThat(insertedInfo).isNotNull();
        assertThat(insertedInfo.getId()).isEqualTo("test-id");
        assertThat(insertedInfo.getTitle()).isEqualTo("Test Title");
        assertThat(insertedInfo.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void insert_shouldThrowConflictExceptionOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        doThrow(new DuplicateKeyException("Duplicate key")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .hasCauseInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void replace_shouldUpdateInfoSuccessfully() {
        // GIVEN
        String id = "test-id";
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        String sql = "UPDATE infos SET title = ?, description = ? WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(sql), eq(info.getTitle()), eq(info.getDescription()), eq(id));

        // WHEN
        Info updatedInfo = repository.replace(id, info);

        // THEN
        assertThat(updatedInfo).isNotNull();
        assertThat(updatedInfo.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedInfo.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void removeById_shouldDeleteInfoSuccessfully() {
        // GIVEN
        String id = "test-id";
        String deleteSql = "DELETE FROM infos WHERE id = ?";
        doNothing().when(jdbcTemplate).update(eq(deleteSql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldReturnFalseOnFailure() {
        // GIVEN
        String id = "test-id";
        String deleteSql = "DELETE FROM infos WHERE id = ?";
        doThrow(new RuntimeException("Delete failed")).when(jdbcTemplate).update(eq(deleteSql), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void preparedStatement_shouldCreatePreparedStatementSuccessfully() throws SQLException {
        // GIVEN
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        when(mockConnection.prepareStatement(eq(sql), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(mockPreparedStatement);

        // WHEN
        PreparedStatement preparedStatement = repository.preparedStatement(mockConnection, sql, info);

        // THEN
        assertThat(preparedStatement).isNotNull();
        verify(mockPreparedStatement).setString(1, "test-id");
        verify(mockPreparedStatement).setString(2, "Test Title");
        verify(mockPreparedStatement).setString(3, "Test Description");
    }

    @Test
    void preparedStatement_shouldThrowInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection mockConnection = mock(Connection.class);
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        when(mockConnection.prepareStatement(eq(sql), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(new SQLException("SQL error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.preparedStatement(mockConnection, sql, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessage("Failed to run sql.");
    }
}
