package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        reset(session, preparedStatement, boundStatement, resultSet, row);
    }

    @Test
    void testNewIdGeneratesUUIDString() {
        // GIVEN - no preconditions

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
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
        assertNotNull(infos);
        assertEquals(1, infos.size());
        assertEquals("1", infos.get(0).getId());
    }

    @Test
    void testFindByIdReturnsInfoWhenFound() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind("1")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row);
        when(row.getString("id")).thenReturn("1");
        when(row.getString("title")).thenReturn("Title");
        when(row.getString("description")).thenReturn("Description");

        // WHEN
        Info info = repository.findById("1");

        // THEN
        assertNotNull(info);
        assertEquals("1", info.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind("1")).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("1");

        // THEN
        assertNull(info);
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNotNull(result);
        assertEquals(info, result);
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNull(result);
    }
