package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
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
    public void testNewIdGeneratesUUID() {
        // GIVEN

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

    @Test
    public void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNotNull(result);
        assertEquals(info, result);
    }

   @Test
    public void testInsertReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind(info.getId(), info.getTitle(), info.getDescription())).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNull(result);
    }

    @Test
    public void testReplaceReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("New Title");
        info.setDescription("New Description");
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertNotNull(result);
        assertEquals(info, result);
    }

    @Test
    public void testReplaceReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("New Title");
        info.setDescription("New Description");
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind(info.getTitle(), info.getDescription(), info.getId())).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace("1", info);

        // THEN
        assertNull(result);
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenApplied() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind("1")).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertTrue(result);
    }

    @Test
    public void testRemoveByIdReturnsFalseWhenNotApplied() {
        // GIVEN
        PreparedStatement mockPrepared = mock(PreparedStatement.class);
        BoundStatement mockBound = mock(BoundStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockSession.prepare(anyString())).thenReturn(mockPrepared);
        when(mockPrepared.bind("1")).thenReturn(mockBound);
        when(mockSession.execute(mockBound)).thenReturn(mockResultSet);
        when(mockResultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertFalse(result);
    }
}