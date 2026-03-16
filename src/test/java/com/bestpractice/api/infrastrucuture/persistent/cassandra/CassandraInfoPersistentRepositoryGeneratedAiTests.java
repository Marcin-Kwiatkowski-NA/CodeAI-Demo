package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Row row;

    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(session, preparedStatement, resultSet, row);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        String id1 = repository.newId();
        String id2 = repository.newId();

        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        when(resultSet.iterator()).thenReturn(rows.iterator());
        when(row.getString("id")).thenReturn("123");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        List<Info> infos = repository.findAll();

        assertThat(infos).isNotEmpty();
        assertThat(infos.get(0).getId()).isEqualTo("123");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title");
        assertThat(infos.get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdReturnsInfoWhenExists() {
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("123");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        Info info = repository.findById("123");

        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("123");
        assertThat(info.getTitle()).isEqualTo("Title");
        assertThat(info.getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        Info info = repository.findById("999");

        assertThat(info).isNull();
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        Info result = repository.insert(info);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        Info result = repository.insert(info);

        assertThat(result).isNull();
    }

    @Test
    void testReplaceReturnsInfoWhenApplied() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        Info result = repository.replace("123", info);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testReplaceReturnsNullWhenNotApplied() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        Info result = repository.replace("123", info);

        assertThat(result).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        boolean result = repository.removeById("123");

        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(session.execute(any(com.datastax.oss.driver.api.core.cql.BoundStatement.class))).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        boolean result = repository.removeById("123");

        assertThat(result).isFalse();
    }
}
