package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CqlSession session;
    private PreparedStatement preparedStatement;
    private BoundStatement boundStatement;
    private ResultSet resultSet;
    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        session = mock(CqlSession.class);
        preparedStatement = mock(PreparedStatement.class);
        boundStatement = mock(BoundStatement.class);
        resultSet = mock(ResultSet.class);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById("123");

        // THEN
        assertThat(result).isTrue();
        verify(session).prepare(anyString());
        verify(session).execute(boundStatement);
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.bind(any())).thenReturn(boundStatement);
        when(session.execute(boundStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById("123");

        // THEN
        assertThat(result).isFalse();
        verify(session).prepare(anyString());
        verify(session).execute(boundStatement);
    }
}
