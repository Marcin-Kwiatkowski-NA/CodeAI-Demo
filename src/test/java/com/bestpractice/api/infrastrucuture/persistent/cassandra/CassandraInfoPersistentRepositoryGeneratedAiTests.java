package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        when(session.prepare(anyString())).thenReturn(mock(PreparedStatement.class));
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.wasApplied()).thenReturn(true);
        when(session.execute((String) any())).thenReturn(resultSet);
    }

    @Test
    public void testNewId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
    }

    @Test
    public void testFindAll() {
        // GIVEN
        ResultSet resultSet = mock(ResultSet.class);
        when(session.execute((String) any())).thenReturn(resultSet);

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertEquals(new ArrayList<>(), infos);
    }

    @Test
    public void testFindById() {
        // GIVEN
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.one()).thenReturn(null);
        when(session.execute((String) any())).thenReturn(resultSet);

        // WHEN
        Info foundInfo = repository.findById("1");

        // THEN
        assertNull(foundInfo);
    }

    @Test
    public void testInsert() {
        // GIVEN

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertNotNull(insertedInfo);
    }

    @Test
    public void testReplace() {
        // GIVEN

        // WHEN
        Info replacedInfo = repository.replace("1", info);

        // THEN
        assertNotNull(replacedInfo);
    }

    @Test
    public void testRemoveById() {
        // GIVEN

        // WHEN
        boolean isRemoved = repository.removeById("1");

        // THEN
        assertEquals(true, isRemoved);
    }
}
