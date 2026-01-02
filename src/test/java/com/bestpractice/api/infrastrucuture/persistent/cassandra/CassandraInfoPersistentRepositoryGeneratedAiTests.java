package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit5.extension.PowerMockExtension;

@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CqlSession session;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Row row;
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        session = mock(CqlSession.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        row = mock(Row.class);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void newIdShouldReturnUniqueNonNullUuid() {
        // GIVEN
        // repository already set up in @BeforeEach
        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();
        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(id1).matches("[0-9a-fA-F-]{36}");
    }

    @Test
    void findAllShouldReturnAllInfosWhenRowsExist() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);

        Iterator<Row> iterator = mock(Iterator.class);
        when(resultSet.iterator()).thenReturn(iterator);

        Row row1 = mock(Row.class);
        Row row2 = mock(Row.class);
        when(iterator.hasNext()).thenReturn(true, true, false);
        when(iterator.next()).thenReturn(row1, row2);

        when(row1.getString("id")).thenReturn("id1");
        when(row1.getString("title")).thenReturn("title1");
        when(row1.getString("description")).thenReturn("desc1");
        when(row2.getString("id")).thenReturn("id2");
        when(row2.getString("title")).thenReturn("title2");
        when(row2.getString("description")).thenReturn("desc2");

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        assertThat(infos.get(0).getId()).isEqualTo("id1");
        assertThat(infos.get(0).getTitle()).isEqualTo("title1");
        assertThat(infos.get(0).getDescription()).isEqualTo("desc1");
        assertThat(infos.get(1).getId()).isEqualTo("id2");
        assertThat(infos.get(1).getTitle()).isEqualTo("title2");
        assertThat(infos.get(1).getDescription()).isEqualTo("desc2");
    }

    @Test
    void findAllShouldReturnEmptyListWhenNoRows() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.iterator()).thenReturn(Collections.emptyIterator());

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void findByIdShouldReturnInfoWhenRowExists() {
        // GIVEN
        String id = "id1";
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);

        when(row.getString("id")).thenReturn(id);
        when(row.getString("title")).thenReturn("title1");
        when(row.getString("description")).thenReturn("desc1");

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo("title1");
        assertThat(info.getDescription()).isEqualTo("desc1");
    }

    @Test
    void findByIdShouldReturnNullWhenRowDoesNotExist() {
        // GIVEN
        String id = "id1";
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void insertShouldReturnInfoWhenWasAppliedTrue() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("title1");
        info.setDescription("desc1");

        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void insertShouldReturnNullWhenWasAppliedFalse() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("title1");
        info.setDescription("desc1");

        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void replaceShouldReturnInfoWhenWasAppliedTrue() {
        // GIVEN
        String id = "id1";
        Info info = new Info();
        info.setTitle("newTitle");
        info.setDescription("newDesc");

        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), id))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void replaceShouldReturnNullWhenWasAppliedFalse() {
        // GIVEN
        String id = "id1";
        Info info = new Info();
        info.setTitle("newTitle");
        info.setDescription("newDesc");

        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), id))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void removeByIdShouldReturnTrueWhenWasAppliedTrue() {
        // GIVEN
        String id = "id1";
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeByIdShouldReturnFalseWhenWasAppliedFalse() {
        // GIVEN
        String id = "id1";
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }
}
