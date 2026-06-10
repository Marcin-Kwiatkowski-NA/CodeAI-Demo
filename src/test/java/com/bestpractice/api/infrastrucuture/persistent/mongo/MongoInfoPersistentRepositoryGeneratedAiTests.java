package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.mockito.Mockito;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.clearInlineMocks;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class MongoInfoPersistentRepositoryGeneratedAiTests {

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
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewIdGeneratesValidObjectIdString() {
        String newId = repository.newId();
        assertThat(newId).isNotNull();
        assertThat(ObjectId.isValid(newId)).isTrue();
    }

    @Test
    void testFindAllReturnsConvertedList() {
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        List<Info> result = repository.findAll();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0)).isEqualTo(info);
    }

    @Test
    void testFindAllThrowsInternalServerErrorOnException() {
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindByIdReturnsConvertedInfo() {
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        Info result = repository.findById(id);

        assertThat(result).isEqualTo(info);
    }

    @Test
    void testFindByIdThrowsInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsertSuccess() {
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        mockStatic(MongoInfoEntity.class);
        try {
            when(MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            Info result = repository.insert(info);
            assertThat(result).isEqualTo(info);
            verify(collection, times(1)).insertOne(mongoEntity);
        } finally {
            clearInlineMocks();
        }
    }

    @Test
    void testInsertThrowsInternalServerErrorOnException() {
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        mockStatic(MongoInfoEntity.class);
        try {
            when(MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoInfoEntity.class));
            assertThatThrownBy(() -> repository.insert(info))
                    .isInstanceOf(InternalServerError.class)
                    .hasMessageContaining("Failed to insert");
        } finally {
            clearInlineMocks();
        }
    }

    @Test
    void testReplaceSuccess() {
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        when(mongoEntity.getId()).thenReturn(new ObjectId());
        mockStatic(MongoInfoEntity.class);
        try {
            when(MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(Bson.class), eq(mongoEntity), any(ReplaceOptions.class))).thenReturn(updateResult);
            Info result = repository.replace("someId", info);
            assertThat(result).isEqualTo(info);
        } finally {
            clearInlineMocks();
        }
    }

    @Test
    void testReplaceThrowsInternalServerErrorOnException() {
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        when(mongoEntity.getId()).thenReturn(new ObjectId());
        mockStatic(MongoInfoEntity.class);
        try {
            when(MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            when(collection.replaceOne(any(Bson.class), eq(mongoEntity), any(ReplaceOptions.class)))
                    .thenThrow(new RuntimeException("Replace failed"));
            assertThatThrownBy(() -> repository.replace("someId", info))
                    .isInstanceOf(InternalServerError.class)
                    .hasMessageContaining("Failed to insert");
        } finally {
            clearInlineMocks();
        }
    }

    @Test
    void testRemoveByIdSuccess() {
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        boolean result = repository.removeById(id);

        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
