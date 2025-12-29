package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastructure.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession mockCqlSession;

    @InjectMocks
    private CassandraInfoPersistentRepository repository;

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void shouldFindAllInfos() {
        // GIVEN
        List<Info> infos = new ArrayList<>();
        when(mockCqlSession.execute(any())).thenReturn(resultSet);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isEqualTo(infos);
    }

    @Test
    void shouldFindInfoById() {
        // GIVEN
        when(mockCqlSession.execute(any())).thenReturn(resultSet);

        // WHEN
        Info result = repository.findById(info.getId());

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldInsertInfo() {
        // GIVEN
        when(mockCqlSession.execute(any())).thenReturn(resultSet);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldUpdateInfo() {
        // GIVEN
        when(mockCqlSession.execute(any())).thenReturn(resultSet);

        // WHEN
        Info result = repository.update(info.getId(), info);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldDeleteInfoById() {
        // GIVEN
        when(mockCqlSession.execute(any())).thenReturn(resultSet);

        // WHEN
        boolean result = repository.deleteById(info.getId());

        // THEN
        assertThat(result).isTrue();
    }
}
