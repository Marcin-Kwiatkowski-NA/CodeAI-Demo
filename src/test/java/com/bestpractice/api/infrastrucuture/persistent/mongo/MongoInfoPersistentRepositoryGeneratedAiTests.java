package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    @InjectMocks
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(collection);
    }

    @Test
    void newId_ShouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findAll_ShouldReturnListOfInfo() {
        // GIVEN
        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> mockCursor = mock(MongoCursor.class);
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        when(collection.find()).thenReturn(mockFindIterable);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);
        when(mockCursor.hasNext()).thenReturn(true, false);
        when(mockCursor.next()).thenReturn(mockEntity);
        when(mockEntity.convertTo()).thenReturn(new Info());

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    void findAll_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        when(collection.find()).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void findById_ShouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        when(collection.find(any())).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(mockEntity);
        when(mockEntity.convertTo()).thenReturn(new Info());

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    void findById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        when(mockEntity.convertFrom(info)).thenReturn(mockEntity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection).insertOne(mockEntity);
    }

    @Test
    void insert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        doThrow(new RuntimeException("Database error")).when(collection).insertOne(any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        UpdateResult mockUpdateResult = mock(UpdateResult.class);
        when(mockEntity.convertFrom(info)).thenReturn(mockEntity);
        when(mockUpdateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(mockEntity), any())).thenReturn(mockUpdateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection).replaceOne(any(), eq(mockEntity), any());
    }

    @Test
    void replace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        when(mockEntity.convertFrom(info)).thenReturn(mockEntity);
        doThrow(new RuntimeException("Database error")).when(collection).replaceOne(any(), eq(mockEntity), any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(id, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockResult = mock(DeleteResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(mockResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(any());
    }

    @Test
    void removeById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Database error")).when(collection).deleteOne(any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
