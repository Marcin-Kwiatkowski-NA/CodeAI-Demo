package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession mockSession;

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        // No manual initialization needed due to @InjectMocks
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
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Row mockRow = mock(Row.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind()).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        Iterator<Row> iterator = Arrays.asList(mockRow).iterator();
        when(mockResultSet.iterator()).thenReturn(iterator);
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
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Row mockRow = mock(Row.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind("1")).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
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
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind("1")).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("1");

        // THEN
        assertNull(info);
    }
