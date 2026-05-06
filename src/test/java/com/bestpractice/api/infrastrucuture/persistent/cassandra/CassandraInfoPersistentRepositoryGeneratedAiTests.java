package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
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
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

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

    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(session, preparedStatement, boundStatement, resultSet, row);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testNewIdGeneratesUniqueUUID() {
        String id1 = repository.newId();
        String id2 = repository.newId();

        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind()).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        Mockito.when(resultSet.iterator()).thenReturn(rows.iterator());
        Mockito.when(row.getString("id")).thenReturn("1");
        Mockito.when(row.getString("title")).thenReturn("Title");
        Mockito.when(row.getString("description")).thenReturn("Description");

        List<Info> infos = repository.findAll();

        assertThat(infos).isNotEmpty();
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title");
        assertThat(infos.get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdReturnsInfoWhenExists() {
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("123"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.one()).thenReturn(row);
        Mockito.when(row.getString("id")).thenReturn("123");
        Mockito.when(row.getString("title")).thenReturn("Test Title");
        Mockito.when(row.getString("description")).thenReturn("Test Description");

        Info info = repository.findById("123");

        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("123");
        assertThat(info.getTitle()).isEqualTo("Test Title");
        assertThat(info.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("999"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.one()).thenReturn(null);

        Info info = repository.findById("999");

        assertThat(info).isNull();
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("1"), eq("Title"), eq("Description"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(true);

        Info result = repository.insert(info);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        Info info = new Info();
        info.setId("2");
        info.setTitle("Title2");
        info.setDescription("Description2");
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("2"), eq("Title2"), eq("Description2"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(false);

        Info result = repository.insert(info);

        assertThat(result).isNull();
    }

    @Test
    void testReplaceReturnsInfoWhenApplied() {
        Info info = new Info();
        info.setId("3");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("Updated Title"), eq("Updated Description"), eq("3"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(true);

        Info result = repository.replace("3", info);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void testReplaceReturnsNullWhenNotApplied() {
        Info info = new Info();
        info.setId("4");
        info.setTitle("Title4");
        info.setDescription("Description4");
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("Title4"), eq("Description4"), eq("4"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(false);

        Info result = repository.replace("4", info);

        assertThat(result).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("5"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(true);

        boolean result = repository.removeById("5");

        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        Mockito.when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.bind(eq("6"))).thenReturn(boundStatement);
        Mockito.when(session.execute(eq(boundStatement))).thenReturn(resultSet);
        Mockito.when(resultSet.wasApplied()).thenReturn(false);

        boolean result = repository.removeById("6");

        assertThat(result).isFalse();
    }
}
