package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    @Mock
    private FindIterable<MongoInfoEntity> findIterable;

    @Mock
    private MongoCursor<MongoInfoEntity> cursor;

    @Mock
    private UpdateResult updateResult;

    @Mock
    private DeleteResult deleteResult;

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnValidObjectIdString() {
        String id = repository.newId();
        assertThat(id).isNotNull();
        assertThatCode(() -> new ObjectId(id)).doesNotThrowAnyException();
    }

    @Test
    void testFindAll_ShouldReturnListOfInfo() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        List<Info> result = repository.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("title");
    }

    @Test
    void testFindAll_ShouldThrowInternalServerErrorOnException() {
        when(collection.find()).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById_ShouldReturnInfo() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        Info result = repository.findById(entity.getId().toString());

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title");
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert_ShouldInsertSuccessfully() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        Info result = repository.insert(info);

        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any(MongoInfoEntity.class));

        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace_ShouldReplaceSuccessfully() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        Info result = repository.replace(info.getId(), info);

        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace error"));

        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        boolean result = repository.removeById(new ObjectId().toString());

        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete error"));

        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}