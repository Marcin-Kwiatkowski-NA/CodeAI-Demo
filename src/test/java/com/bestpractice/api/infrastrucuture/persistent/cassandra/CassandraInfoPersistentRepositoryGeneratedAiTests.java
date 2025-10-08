package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CqlSession mockSession;
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mockSession = mock(CqlSession.class);
        repository = new CassandraInfoPersistentRepository(mockSession);
    }

    @Test
    public void testNewIdGeneratesUUIDString() {
        // GIVEN - no preconditions

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    public void testFindAllReturnsListOfInfos() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Row mockRow = mock(Row.class);
        when(mockSession.prepare(any())).thenReturn(mockPrepared);
        when(mockSession.execute(any(PreparedStatement.class).bind())).thenReturn(mockResultSet);
        when(mockResultSet.iterator()).thenReturn(Arrays.asList(mockRow).iterator());
        when(mockRow.getString("id")).thenReturn("1");
        when(mockRow.getString("title")).thenReturn("Title");
        when(mockRow.getString("description")).thenReturn("Description");

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertNotNull(infos);
        assertEquals(1, infos.size());
        assertEquals("1", infos.get(0).getId());
    }

    @Test
    public void testFindByIdReturnsInfoWhenFound() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Row mockRow = mock(Row.class);
        when(mockSession.prepare(any())).thenReturn(mockPrepared);
        when(mockSession.execute(any(PreparedStatement.class).bind(any()))).thenReturn(mockResultSet);
        when(mockResultSet.one()).thenReturn(mockRow);
        when(mockRow.getString("id")).thenReturn("1");
        when(mockRow.getString("title")).thenReturn("Title");
        when(mockRow.getString("description")).thenReturn("Description");

        // WHEN
        Info info = repository.findById("1");

        // THEN
        assertNotNull(info);
        assertEquals("1", info.getId());
    }

    @Test
    public void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(any())).thenReturn(mockPrepared);
        when(mockSession.execute(any(PreparedStatement.class).bind(any()))).thenReturn(mockResultSet);
        when(mockResultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("1");

        // THEN
        assertNull(info);
    }

    @Test
    public void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(mockSession.prepare(any())).thenReturn(mockPrepared);
        when(mockSession.execute(any(PreparedStatement.class).bind(any(), any(), any()))).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNotNull(result);
        assertEquals(info, result);
    }
}