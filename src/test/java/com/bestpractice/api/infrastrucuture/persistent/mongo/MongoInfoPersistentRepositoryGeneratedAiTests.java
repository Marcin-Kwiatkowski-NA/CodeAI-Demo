package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mockMongoClient;
    private MongoDatabase mockMongoDatabase;
    private MongoCollection<MongoInfoEntity> mockCollection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mockMongoClient = mock(MongoClient.class);
        mockMongoDatabase = mock(MongoDatabase.class);
        mockCollection = mock(MongoCollection.class);

        when(mockMongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(mockCollection);

        repository = new MongoInfoPersistentRepository(mockMongoClient, mockMongoDatabase);
    }

    @Test
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findAll_shouldReturnListOfInfo() {
        // GIVEN
        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> mockCursor = mock(MongoCursor.class);
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        Info mockInfo = mock(Info.class);

        when(mockCollection.find()).thenReturn(mockFindIterable);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);
        when(mockCursor.hasNext()).thenReturn(true, false);
        when(mockCursor.next()).thenReturn(mockEntity);
        when(mockEntity.convertTo()).thenReturn(mockInfo);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(mockInfo);
    }

    @Test
    void findById_shouldReturnInfoWhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        Info mockInfo = mock(Info.class);

        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        when(mockCollection.find(any())).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(mockEntity);
        when(mockEntity.convertTo()).thenReturn(mockInfo);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockInfo);
    }

    @Test
    void insert_shouldInsertInfoAndReturnIt() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);

        when(MongoInfoEntity.convertFrom(info)).thenReturn(mockEntity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        verify(mockCollection).insertOne(mockEntity);
        assertThat(result).isEqualTo(info);
    }

    @Test
    void replace_shouldReplaceInfoAndReturnIt() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        UpdateResult mockUpdateResult = mock(UpdateResult.class);

        when(MongoInfoEntity.convertFrom(info)).thenReturn(mockEntity);
        when(mockUpdateResult.wasAcknowledged()).thenReturn(true);
        when(mockCollection.replaceOne(any(), eq(mockEntity), any())).thenReturn(mockUpdateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        verify(mockCollection).replaceOne(any(), eq(mockEntity), any());
        assertThat(result).isEqualTo(info);
    }

    @Test
    void removeById_shouldRemoveInfoAndReturnTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockDeleteResult = mock(DeleteResult.class);

        when(mockDeleteResult.wasAcknowledged()).thenReturn(true);
        when(mockCollection.deleteOne(any())).thenReturn(mockDeleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        verify(mockCollection).deleteOne(any());
        assertThat(result).isTrue();
    }
}
