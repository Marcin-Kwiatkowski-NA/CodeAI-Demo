package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
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
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Row row;

    @Mock
    private BoundStatement boundStatement;

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        reset(session, preparedStatement, resultSet, row, boundStatement);
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(boundStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(preparedStatement.bind(any(), any(), any())).thenReturn(boundStatement);
    }

    @Test
    void testNewIdReturnsUuid() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull();
        assertThat(id).matches("^[0-9a-fA-F-]{36}$");
        assertThat(UUID.fromString(id)).isNotNull();
    }

    @Test
    void testFindAllReturnsList() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        Row r1 = mock(Row.class);
        when(r1.getString("id")).thenReturn("1");
        when(r1.getString("title")).thenReturn("Title1");
        when(r1.getString("description")).thenReturn("Desc1");
        Row r2 = mock(Row.class);
        when(r2.getString("id")).thenReturn("2");
        when(r2.getString("title")).thenReturn("Title2");
        when(r2.getString("description")).thenReturn("Desc2");
        Iterator<Row> iterator = List.of(r1, r2).iterator();
        when(resultSet.iterator()).thenReturn(iterator);

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title1");
        assertThat(infos.get(0).getDescription()).isEqualTo("Desc1");
        assertThat(infos.get(1).getId()).isEqualTo("2");
        assertThat(infos.get(1).getTitle()).isEqualTo("Title2");
        assertThat(infos.get(1).getDescription()).isEqualTo("Desc2");
    }

    @Test
    void testFindAllReturnsEmptyList() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.iterator()).thenReturn(List.<Row>of().iterator());

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("123");
        when(row.getString("title")).thenReturn("Sample");
        when(row.getString("description")).thenReturn("Description");

        // WHEN
        Info info = repository.findById("123");

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("123");
        assertThat(info.getTitle()).isEqualTo("Sample");
        assertThat(info.getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("nonexistent");

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info returned = repository.insert(info);

        // THEN
        assertThat(returned).isSameAs(info);
    }

    @Test
    void testInsertFailure() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info returned = repository.insert(info);

        // THEN
        assertThat(returned).isNull();
    }

    @Test
    void testReplaceSuccess() {
        // GIVEN
        Info info = new Info();
        info.setTitle("New Title");
        info.setDescription("New Desc");
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info returned = repository.replace("1", info);

        // THEN
        assertThat(returned).isSameAs(info);
    }

    @Test
    void testReplaceFailure() {
        // GIVEN
        Info info = new Info();
        info.setTitle("New Title");
        info.setDescription("New Desc");
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info returned = repository.replace("1", info);

        // THEN
        assertThat(returned).isNull();
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isFalse();
    }
}
