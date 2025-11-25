package com.bestpractice.api.infrastrucuture.persistent.mongo;
import com.mongodb.client.MongoCursor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
        MockitoAnnotations.openMocks(this);
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
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        MongoInfoEntity entity1 = mock(MongoInfoEntity.class);
        MongoInfoEntity entity2 = mock(MongoInfoEntity.class);

        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(entity1, entity2);
        when(entity1.convertTo()).thenReturn(new Info());
        when(entity2.convertTo()).thenReturn(new Info());

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).hasSize(2);
        verify(collection, times(1)).find();
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);

        when(collection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);
        when(entity.convertTo()).thenReturn(new Info());

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        verify(collection, times(1)).find(any());
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).insertOne(entity);
    }

    @Test
    void insert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        when(collection.insertOne(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        UpdateResult updateResult = mock(UpdateResult.class);

        when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);
        when(entity.getId()).thenReturn(new ObjectId(id));
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(entity), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).replaceOne(any(), eq(entity), any());
    }

    @Test
    void replace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);

        when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);
        when(collection.replaceOne(any(), eq(entity), any())).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(id, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);

        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any());
    }

    @Test
    void removeById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}