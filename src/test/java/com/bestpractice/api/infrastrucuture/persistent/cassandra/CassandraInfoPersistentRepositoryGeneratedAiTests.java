package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private BoundStatement boundStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Row row;

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void newId_shouldReturnUniqueId() {
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
        when(session.prepare("SELECT * FROM infos")).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.iterator()).thenReturn(List.of(row).iterator());
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Test Title");
        when(row.getString("description")).thenReturn("Test Description");

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isNotEmpty();
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(infos.get(0).getDescription()).isEqualTo("Test Description");
    }

    @Test
    void findById_shouldReturnInfoWhenExists() {
        // GIVEN
        String id = "1";
        when(session.prepare("SELECT * FROM infos WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Test Title");
        when(row.getString("description")).thenReturn("Test Description");

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("1");
        assertThat(info.getTitle()).isEqualTo("Test Title");
        assertThat(info.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void findById_shouldReturnNullWhenNotExists() {
        // GIVEN
        String id = "1";
        when(session.prepare("SELECT * FROM infos WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void insert_shouldReturnInfoWhenSuccessful() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        when(session.prepare("INSERT INTO infos (id, title, description) VALUES (?, ?, ?)")).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void insert_shouldReturnNullWhenFailed() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        when(session.prepare("INSERT INTO infos (id, title, description) VALUES (?, ?, ?)")).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void replace_shouldReturnInfoWhenSuccessful() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        when(session.prepare("UPDATE infos SET title = ?, description = ? WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace(info.getId(), info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void replace_shouldReturnNullWhenFailed() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        when(session.prepare("UPDATE infos SET title = ?, description = ? WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace(info.getId(), info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void removeById_shouldReturnTrueWhenSuccessful() {
        // GIVEN
        String id = "1";

        when(session.prepare("DELETE FROM infos WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldReturnFalseWhenFailed() {
        // GIVEN
        String id = "1";

        when(session.prepare("DELETE FROM infos WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }
}
