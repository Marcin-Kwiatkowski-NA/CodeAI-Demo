package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.mockito.Mockito;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
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
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

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
    private DeleteResult deleteResult;

    @Mock
    private UpdateResult updateResult;

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(any(String.class), any(Class.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnNonNullString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isNotEmpty();
    }

    @Test
    void testFindAll_ShouldReturnConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("title");
    }

    @Test
    void testFindAll_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        when(collection.find()).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById_ShouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title");
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert_ShouldReturnSameInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any(MongoInfoEntity.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace_ShouldReturnInfoWhenAcknowledged() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(info.getId(), info);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
