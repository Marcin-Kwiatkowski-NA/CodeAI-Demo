package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> mockCollection;

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        // Configure the mock database to return the mock collection
        when(mockDatabase.getCollection(anyString(), any())).thenReturn(mockCollection);
        repository = new MongoInfoPersistentRepository(mockDatabase, mockDatabase);
        // Reset any interactions from previous tests
        Mockito.reset(mockDatabase, mockCollection);
    }

    @Test
    void testNewId() {
        // GIVEN
        // (No specific setup required)

        // WHEN
        String result = repository.newId();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).matches("^[a-fA-F0-9]{24}$");
    }

    @Test
    void testFindAllReturnsAllInfos() {
        // GIVEN
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        MongoInfoEntity entity1 = new MongoInfoEntity(new ObjectId("507f1f77bcf86cd799439011"), "Test1", "Desc1");
        MongoInfoEntity entity2 = new MongoInfoEntity(new ObjectId("507f1f77bcf86cd799439012"), "Test2", "Desc2");
        when(mockCollection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(entity1, entity2);

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        assertThat(infos.get(0).getName()).isEqualTo("Test1");
        assertThat(infos.get(1).getName()).isEqualTo("Test2");
    }

    @Test
    void testFindAllThrowsInternalServerErrorOnException() {
        // GIVEN
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(mockCollection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenThrow(new RuntimeException("Simulated exception"));

        // WHEN
        Throwable thrown = null;
        try {
            repository.findAll();
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated exception");
    }

    @Test
    void testFindByIdReturnsInfoWhenFound() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "TestName", "TestDesc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(mockCollection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getName()).isEqualTo("TestName");
    }

    @Test
    void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(mockCollection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenThrow(new RuntimeException("Simulated find exception"));

        // WHEN
        Throwable thrown = null;
        try {
            repository.findById(id);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated find exception");
    }

    @Test
    void testInsertAddsEntityAndReturnsSameInfo() {
        // GIVEN
        Info info = new Info();
        String id = new ObjectId().toString();
        info.setId(id);
        info.setName("InsertName");
        info.setDescription("InsertDesc");
        doNothing().when(mockCollection).insertOne(any(MongoInfoEntity.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        ArgumentCaptor<MongoInfoEntity> captor = ArgumentCaptor.forClass(MongoInfoEntity.class);
        verify(mockCollection).insertOne(captor.capture());
        MongoInfoEntity inserted = captor.getValue();
        assertThat(inserted.getId()).isEqualTo(new ObjectId(id));
        assertThat(inserted.getName()).isEqualTo("InsertName");
        assertThat(inserted.getDescription()).isEqualTo("InsertDesc");
        assertThat(result).isSameAs(info);
    }

    @Test
    void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        String id = new ObjectId().toString();
        info.setId(id);
        doThrow(new RuntimeException("Simulated insert exception")).when(mockCollection).insertOne(any(MongoInfoEntity.class));

        // WHEN
        Throwable thrown = null;
        try {
            repository.insert(info);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated insert exception");
    }

    @Test
    void testReplaceUpdatesEntityAndReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setName("ReplaceName");
        info.setDescription("ReplaceDesc");
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "ReplaceName", "ReplaceDesc");
        UpdateResult updateResult = UpdateResult.acknowledged(1);
        when(mockCollection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        ArgumentCaptor<MongoInfoEntity> captor = ArgumentCaptor.forClass(MongoInfoEntity.class);
        verify(mockCollection).replaceOne(any(), captor.capture(), any());
        MongoInfoEntity captured = captor.getValue();
        assertThat(captured.getId()).isEqualTo(new ObjectId(id));
        assertThat(captured.getName()).isEqualTo("ReplaceName");
        assertThat(captured.getDescription()).isEqualTo("ReplaceDesc");
        assertThat(result).isSameAs(info);
    }

    @Test
    void testReplaceThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        when(mockCollection.replaceOne(any(), any(), any())).thenThrow(new RuntimeException("Simulated replace exception"));

        // WHEN
        Throwable thrown = null;
        try {
            repository.replace(id, info);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated replace exception");
    }

    @Test
    void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = DeleteResult.acknowledged(1);
        when(mockCollection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdNotAcknowledgedReturnsFalse() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = DeleteResult.unacknowledged();
        when(mockCollection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(mockCollection.deleteOne(any())).thenThrow(new RuntimeException("Simulated delete exception"));

        // WHEN
        Throwable thrown = null;
        try {
            repository.removeById(id);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated delete exception");
    }

    @Test
    void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(mockCollection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenThrow(new RuntimeException("Simulated find exception"));

        // WHEN
        Throwable thrown = null;
        try {
            repository.findById(id);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
        assertThat(thrown.getMessage()).isEqualTo("Simulated find exception");
    }
}
