package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoClient.getDatabase(any(String.class))).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(any(String.class), any(Class.class))).thenReturn(collection);
    }

    @Test
    void testNewId_ShouldReturnNonNullString() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isNotEmpty();
    }

    @Test
    void testFindAll_ShouldReturnConvertedList() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("title");
        verify(collection).find();
    }

    @Test
    void testFindAll_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById_ShouldReturnConvertedInfo() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(entity.getId().toString());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title");
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert_ShouldInsertSuccessfully() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoInfoEntity.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace_ShouldReplaceSuccessfully() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(info.getId(), info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection).replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace failed"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(any(Bson.class));
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}