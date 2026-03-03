package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Row row;

    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testNewIdReturnsUuidString() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull().isNotEmpty();
        assertThat(UUID.fromString(id)).isNotNull();
    }

    @Test
    void testFindAllReturnsListOfInfo() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.iterator()).thenReturn(Arrays.asList(
                createRow("1", "Title1", "Desc1"),
                createRow("2", "Title2", "Desc2")
        ).iterator());

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title1");
        assertThat(infos.get(0).getDescription()).isEqualTo("Desc1");
    }

    @Test
    void testFindByIdReturnsInfoWhenFound() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(createRow("123", "Title", "Desc"));

        // WHEN
        Info info = repository.findById("123");

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo("123");
        assertThat(info.getTitle()).isEqualTo("Title");
        assertThat(info.getDescription()).isEqualTo("Desc");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById("nonexistent");

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplaceReturnsInfoWhenApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        Info info = new Info();
        info.setId("id1");
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");

        // WHEN
        Info result = repository.replace("id1", info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void testReplaceReturnsNullWhenNotApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        Info info = new Info();
        info.setId("id1");
        info.setTitle("NewTitle");
        info.setDescription("NewDesc");

        // WHEN
        Info result = repository.replace("id1", info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean removed = repository.removeById("id1");

        // THEN
        assertThat(removed).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        // GIVEN
        when(session.prepare(anyString())).thenReturn(preparedStatement);
        when(session.execute(any())).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean removed = repository.removeById("id1");

        // THEN
        assertThat(removed).isFalse();
    }

    private Row createRow(String id, String title, String desc) {
        Row mockRow = mock(Row.class);
        when(mockRow.getString("id")).thenReturn(id);
        when(mockRow.getString("title")).thenReturn(title);
        when(mockRow.getString("description")).thenReturn(desc);
        return mockRow;
    }
}
