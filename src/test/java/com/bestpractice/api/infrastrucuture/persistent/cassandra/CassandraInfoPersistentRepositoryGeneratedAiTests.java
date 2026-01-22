package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CassandraInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private CqlSession session;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Row row1;

    @Mock
    private Row row2;

    private CassandraInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        reset(session, preparedStatement, resultSet, row1, row2);
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testNewIdReturnsValidUuid() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isNotNull();
    }

    @Test
    void testFindAllReturnsListOfInfo() {
        // GIVEN
        when(session.prepare(repository.selectInfo.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind()).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.iterator()).thenReturn(Arrays.asList(row1, row2).iterator());
        when(row1.getString("id")).thenReturn("id1");
        when(row1.getString("title")).thenReturn("title1");
        when(row1.getString("description")).thenReturn("desc1");
        when(row2.getString("id")).thenReturn("id2");
        when(row2.getString("title")).thenReturn("title2");
        when(row2.getString("description")).thenReturn("desc2");

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        Info first = infos.get(0);
        assertThat(first.getId()).isEqualTo("id1");
        assertThat(first.getTitle()).isEqualTo("title1");
        assertThat(first.getDescription()).isEqualTo("desc1");
        Info second = infos.get(1);
        assertThat(second.getId()).isEqualTo("id2");
        assertThat(second.getTitle()).isEqualTo("title2");
        assertThat(second.getDescription()).isEqualTo("desc2");
    }

    @Test
    void testFindByIdReturnsInfoWhenFound() {
        // GIVEN
        String id = "some-id";
        when(session.prepare(repository.selectInfoWithWhere.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(row1);
        when(row1.getString("id")).thenReturn(id);
        when(row1.getString("title")).thenReturn("title");
        when(row1.getString("description")).thenReturn("desc");

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo("title");
        assertThat(info.getDescription()).isEqualTo("desc");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        String id = "missing-id";
        when(session.prepare(repository.selectInfoWithWhere.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.one()).thenReturn(null);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertThat(info).isNull();
    }

    @Test
    void testInsertReturnsInfoWhenApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("id");
        info.setTitle("title");
        info.setDescription("desc");
        when(session.prepare(repository.insertInfo.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void testInsertReturnsNullWhenNotApplied() {
        // GIVEN
        Info info = new Info();
        info.setId("id");
        info.setTitle("title");
        info.setDescription("desc");
        when(session.prepare(repository.insertInfo.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(info.getId(), info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplaceReturnsInfoWhenApplied() {
        // GIVEN
        String id = "id";
        Info info = new Info();
        info.setId(id);
        info.setTitle("new-title");
        info.setDescription("new-desc");
        when(session.prepare(repository.replaceInfo.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id, info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isSameAs(info);
    }

    @Test
    void testReplaceReturnsNullWhenNotApplied() {
        // GIVEN
        String id = "id";
        Info info = new Info();
        info.setId(id);
        info.setTitle("new-title");
        info.setDescription("new-desc");
        when(session.prepare(repository.replaceInfo.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id, info.getTitle(), info.getDescription()))
                .thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenApplied() {
        // GIVEN
        String id = "id";
        when(session.prepare(repository.removeById.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(true);

        // WHEN
        boolean success = repository.removeById(id);

        // THEN
        assertThat(success).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotApplied() {
        // GIVEN
        String id = "id";
        when(session.prepare(repository.removeById.build())).thenReturn(preparedStatement);
        when(preparedStatement.bind(id)).thenReturn(preparedStatement);
        when(session.execute(preparedStatement)).thenReturn(resultSet);
        when(resultSet.wasApplied()).thenReturn(false);

        // WHEN
        boolean success = repository.removeById(id);

        // THEN
        assertThat(success).isFalse();
    }
}
