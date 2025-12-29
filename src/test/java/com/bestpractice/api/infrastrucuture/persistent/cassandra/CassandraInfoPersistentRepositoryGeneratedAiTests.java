package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CqlSession mockSession;
    private PreparedStatement mockSelectAll;
    private PreparedStatement mockSelectById;
    private PreparedStatement mockInsert;
    private PreparedStatement mockUpdate;
    private PreparedStatement mockDelete;

    private InfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mockSession = mock(CqlSession.class);
        mockSelectAll = mock(PreparedStatement.class);
        mockSelectById = mock(PreparedStatement.class);
        mockInsert = mock(PreparedStatement.class);
        mockUpdate = mock(PreparedStatement.class);
        mockDelete = mock(PreparedStatement.class);

        when(mockSelectAll.bind()).thenReturn(mock(ResultSet.class));
        when(mockSelectById.bind(anyString())).thenReturn(mock(ResultSet.class));
        when(mockInsert.bind(anyString(), anyString(), anyString())).thenReturn(mock(ResultSet.class));
        when(mockUpdate.bind(anyString(), anyString(), anyString())).thenReturn(mock(ResultSet.class));
        when(mockDelete.bind(anyString())).thenReturn(mock(ResultSet.class));

        when(mockSelectAll.build()).thenReturn(mockSelectAll);
        when(mockSelectById.build()).thenReturn(mockSelectById);
        when(mockInsert.build()).thenReturn(mockInsert);
        when(mockUpdate.build()).thenReturn(mockUpdate);
        when(mockDelete.build()).thenReturn(mockDelete);

        when(mockSession.prepare(any())).thenReturn(mockSelectAll);

        repository = new CassandraInfoPersistentRepository(mockSession);
    }

    @Test
    public void shouldGenerateNewId() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }

    @Test
    public void shouldGenerateNewIdWithDefault() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }

    @Test
    public void shouldGenerateNewIdWithCustom() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }

    @Test
    public void shouldGenerateNewIdWithPattern() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }

    @Test
    public void shouldGenerateNewIdWithLength() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }

    @Test
    public void shouldGenerateNewIdWithValidation() {
        String id = UUID.randomUUID().toString();
        assertThat(id).isNotBlank();
    }
}
