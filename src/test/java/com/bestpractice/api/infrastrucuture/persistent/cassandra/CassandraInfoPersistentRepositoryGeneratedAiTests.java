package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
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
        reset(session, preparedStatement, boundStatement, resultSet, row);
    }

    @Test
    void testNewId_ShouldReturnValidUUIDString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindAll_ShouldReturnListOfInfos() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        Iterator<Row> iterator = rows.iterator();
        when(resultSet.iterator()).thenReturn(iterator);
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title");
        assertThat(result.get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindById_ShouldReturnInfoWhenFound() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind("1")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        // WHEN
        Info result = repository.findById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindById_ShouldReturnNullWhenNotFound() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind("nonexistent")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info result = repository.findById("nonexistent");

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testInsert_ShouldReturnInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    void testInsert_ShouldReturnNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplace_ShouldReturnInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testReplace_ShouldReturnNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenApplied() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind("1")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldReturnFalseWhenNotApplied() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind("1")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
