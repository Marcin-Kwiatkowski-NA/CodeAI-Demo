package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.clearInvocations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

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

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        clearInvocations(session, preparedStatement, boundStatement, resultSet, row);
    }

    @Test
    void testNewIdGeneratesUniqueUUID() {
        // GIVEN

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        List<Row> rows = new ArrayList<>();
        rows.add(row);
        when(resultSet.iterator()).thenReturn(rows.iterator());
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isNotEmpty();
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title");
        assertThat(infos.get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdReturnsInfoWhenExists() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("123");
        when(row.getString("title")).thenReturn("Sample Title");
        when(row.getString("description")).thenReturn("Sample Description");

        // WHEN
        Info info = repository.findById("123");

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("123");
        assertThat(info.getTitle()).isEqualTo("Sample Title");
        assertThat(info.getDescription()).isEqualTo("Sample Description");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("999");

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any(), any(), any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any(), any(), any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplaceReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any(), any(), any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void testReplaceReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any(), any(), any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        // GIVEN
        when(session.prepare(any(com.datastax.oss.driver.api.core.cql.SimpleStatement.class))).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
