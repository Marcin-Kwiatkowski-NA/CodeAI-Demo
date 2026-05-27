package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
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
import static org.mockito.ArgumentMatchers.any;


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
    private MongoCursor<MongoInfoEntity> mongoCursor;

    @InjectMocks
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(any(String.class), any(Class.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnNonNullString() {
        String id = repository.newId();
        assertThat(id).isNotNull();
        assertThat(ObjectId.isValid(id)).isTrue();
    }

    @Test
    void testFindAll_ShouldReturnConvertedList() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(mongoCursor);
        when(mongoCursor.hasNext()).thenReturn(true, false);
        when(mongoCursor.next()).thenReturn(entity);

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
    void testFindById_ShouldReturnConvertedInfo() {
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        Info result = repository.findById(id);

        assertThat(result.getTitle()).isEqualTo("title");
        assertThat(result.getDescription()).isEqualTo("desc");
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findById(id))
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

        verify(collection, atLeastOnce()).insertOne(any(MongoInfoEntity.class));
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any(MongoInfoEntity.class));

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
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);

        Info result = repository.replace(info.getId(), info);

        assertThat(result).isEqualTo(info);
        verify(collection, atLeastOnce()).replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        boolean result = repository.removeById(id);

        assertThat(result).isTrue();
        verify(collection, atLeastOnce()).deleteOne(any(Bson.class));
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
