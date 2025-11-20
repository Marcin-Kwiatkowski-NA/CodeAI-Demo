package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_shouldReturnListOfInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        List<Info> expectedInfos = Arrays.asList(info1, info2);
        when(jdbcTemplate.query(anyString(), any())).thenReturn(expectedInfos);

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
        String id = "1";
        Info expectedInfo = new Info();
        expectedInfo.setId(id);
        expectedInfo.setTitle("Title1");
        expectedInfo.setDescription("Description1");

        when(jdbcTemplate.queryForObject(anyString(), any(), eq(id))).thenReturn(expectedInfo);

        // WHEN
        Info actualInfo = repository.findById(id);

        // THEN
        assertThat(actualInfo).isNotNull();
        assertThat(actualInfo).isEqualTo(expectedInfo);
    }

    @Test
    void insert_shouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        doNothing().when(jdbcTemplate).update(any(), eq(keyHolder));

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertThat(insertedInfo).isNotNull();
        assertThat(insertedInfo).isEqualTo(info);
    }

    @Test
    void insert_shouldThrowConflictOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        doThrow(DuplicateKeyException.class).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_shouldUpdateInfo() {
        // GIVEN
        String id = "1";
        Info info = new Info();
        info.setTitle("UpdatedTitle");
        info.setDescription("UpdatedDescription");

        doNothing().when(jdbcTemplate).update(anyString(), eq(info.getTitle()), eq(info.getDescription()), eq(id));

        // WHEN
        Info updatedInfo = repository.replace(id, info);

        // THEN
        assertThat(updatedInfo).isNotNull();
        assertThat(updatedInfo).isEqualTo(info);
    }

    @Test
    void removeById_shouldDeleteInfo() {
        // GIVEN
        String id = "1";
        doNothing().when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldReturnFalseOnFailure() {
        // GIVEN
        String id = "1";
        doThrow(RuntimeException.class).when(jdbcTemplate).update(anyString(), eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void preparedStatement_shouldPrepareStatementSuccessfully() throws SQLException {
        // GIVEN
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        when(mockConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(mockPreparedStatement);

        // WHEN
        PreparedStatement preparedStatement = repository.preparedStatement(mockConnection, sql, info);

        // THEN
        assertThat(preparedStatement).isNotNull();
        verify(mockPreparedStatement).setString(1, info.getId());
        verify(mockPreparedStatement).setString(2, info.getTitle());
        verify(mockPreparedStatement).setString(3, info.getDescription());
    }

    @Test
    void preparedStatement_shouldThrowInternalServerErrorOnSQLException() throws SQLException {
        // GIVEN
        Connection mockConnection = mock(Connection.class);
        String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        when(mockConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)).thenThrow(SQLException.class);

        // WHEN THEN
        assertThatThrownBy(() -> repository.preparedStatement(mockConnection, sql, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessage("Failed to run sql.");
    }
}