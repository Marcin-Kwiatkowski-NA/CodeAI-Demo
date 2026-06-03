package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
        when(mongoDatabase.getCollection(any(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThatCode(() -> new ObjectId(id)).doesNotThrowAnyException();
    }

    @Test
    void testFindAll_ShouldReturnListOfInfo() {
        // GIVEN
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        info.setId("123");
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        when(entity.convertTo()).thenReturn(info);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo("123");
    }

    @Test
    void testFindAll_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById_ShouldReturnInfo() {
        // GIVEN
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        info.setId("abc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);
        when(entity.convertTo()).thenReturn(info);

        // WHEN
        Info result = repository.findById(new ObjectId().toString());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("abc");
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert_ShouldInsertAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoInfoEntity.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace_ShouldReplaceAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        when(entity.getId()).thenReturn(new ObjectId(info.getId()));
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), eq(entity), any())).thenReturn(updateResult);

        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(entity);

            // WHEN
            Info result = repository.replace(info.getId(), info);

            // THEN
            assertThat(result).isEqualTo(info);
        }
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());

        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenThrow(new RuntimeException("Conversion failed"));

            // WHEN THEN
            assertThatThrownBy(() -> repository.replace(info.getId(), info))
                    .isInstanceOf(InternalServerError.class)
                    .hasMessageContaining("Failed to insert");
        }
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
