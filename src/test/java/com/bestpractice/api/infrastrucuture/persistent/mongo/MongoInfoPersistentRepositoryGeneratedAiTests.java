package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void newId_shouldGenerateNewId() {
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
        List<MongoInfoEntity> mockEntities = new ArrayList<>();
        MongoCursor<MongoInfoEntity> mockCursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(mockCursor);
        when(mockCursor.hasNext()).thenReturn(true, false);
        when(mockCursor.next()).thenReturn(new MongoInfoEntity());

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        when(collection.find(any())).thenReturn(mock(MongoCursor.class));
        when(mockEntity.convertTo()).thenReturn(new Info());

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    void insert_shouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        when(MongoInfoEntity.convertFrom(info)).thenReturn(mockEntity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).insertOne(mockEntity);
    }

    @Test
    void replace_shouldReplaceInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        MongoInfoEntity mockEntity = mock(MongoInfoEntity.class);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(MongoInfoEntity.convertFrom(info)).thenReturn(mockEntity);
        when(collection.replaceOne(any(), eq(mockEntity), any())).thenReturn(mockResult);
        when(mockResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).replaceOne(any(), eq(mockEntity), any());
    }

    @Test
    void removeById_shouldRemoveInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockResult = mock(DeleteResult.class);
        when(collection.deleteOne(any())).thenReturn(mockResult);
        when(mockResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any());
    }
}