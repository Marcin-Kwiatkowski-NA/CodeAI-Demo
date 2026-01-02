package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.DeleteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        // Mock the getCollection call to return our mocked collection
        when(mongoDatabase.getCollection(eq("infos"), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(null, mongoDatabase);
    }

    @Test
    void testNewId() {
        // GIVEN nothing special

        // WHEN generating a new id
        String id = repository.newId();

        // THEN the id should be a valid ObjectId string
        assertThat(id).isNotNull();
        assertThat(id).hasSize(24);
        assertThat(ObjectId.isValid(id)).isTrue();
    }

    @Test
    void testFindAll() {
        // GIVEN a collection that returns one entity
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId("507f1f77bcf86cd799439011"), "Title", "Description");
        FindIterable<MongoInfoEntity> findIterable = Mockito.mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = Mockito.mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN retrieving all infos
        List<Info> result = repository.findAll();

        // THEN the result should contain one Info with matching fields
        assertThat(result).hasSize(1);
        Info info = result.get(0);
        assertThat(info.getId()).isEqualTo(entity.getId().toString());
        assertThat(info.getTitle()).isEqualTo(entity.getTitle());
        assertThat(info.getDescription()).isEqualTo(entity.getDescription());
    }

    @Test
    void testFindAllThrowsInternalServerError() {
        // GIVEN a collection that throws an exception on find
        when(collection.find()).thenThrow(new RuntimeException("db error"));

        // WHEN retrieving all infos
        // THEN an InternalServerError should be thrown
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById() {
        // GIVEN a collection that returns a specific entity
        String id = "507f1f77bcf86cd799439011";
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "Title", "Description");
        when(collection.find(any())).thenReturn(Mockito.mock(FindIterable.class));
        when(collection.find(any()).first()).thenReturn(entity);

        // WHEN retrieving by id
        Info result = repository.findById(id);

        // THEN the result should match the entity
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdThrowsInternalServerError() {
        // GIVEN a collection that throws on find
        when(collection.find(any())).thenThrow(new RuntimeException("db error"));

        // WHEN retrieving by id
        // THEN an InternalServerError should be thrown
        assertThatThrownBy(() -> repository.findById("507f1f77bcf86cd799439011"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert() {
        // GIVEN an Info object to insert
        Info info = new Info();
        info.setId("507f1f77bcf86cd799439011");
        info.setTitle("New Title");
        info.setDescription("New Description");

        // WHEN inserting the info
        Info result = repository.insert(info);

        // THEN the repository should return the same object
        assertThat(result).isSameAs(info);
        // And the collection's insertOne should have been called
        Mockito.verify(collection).insertOne(ArgumentMatchers.any(MongoInfoEntity.class));
    }

    @Test
    void testInsertThrowsInternalServerError() {
        // GIVEN a collection that throws on insert
        Info info = new Info();
        info.setId("507f1f77bcf86cd799439011");
        when(collection.insertOne(any())).thenThrow(new RuntimeException("db error"));

        // WHEN inserting
        // THEN an InternalServerError should be thrown
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace() {
        // GIVEN an existing id and new Info
        String id = "507f1f77bcf86cd799439011";
        Info info = new Info();
        info.setId(id);
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        UpdateResult updateResult = Mockito.mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN replacing the info
        Info result = repository.replace(id, info);

        // THEN the repository should return the same object
        assertThat(result).isSameAs(info);
        // And replaceOne should have been called
        Mockito.verify(collection).replaceOne(any(), any(), any());
    }

    @Test
    void testReplaceThrowsInternalServerError() {
        // GIVEN a collection that throws on replace
        Info info = new Info();
        info.setId("507f1f77bcf86cd799439011");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeException("db error"));

        // WHEN replacing
        // THEN an InternalServerError should be thrown
        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById() {
        // GIVEN a collection that acknowledges deletion
        DeleteResult deleteResult = Mockito.mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN removing by id
        boolean result = repository.removeById("507f1f77bcf86cd799439011");

        // THEN the result should be true
        assertThat(result).isTrue();
        // And deleteOne should have been called
        Mockito.verify(collection).deleteOne(any());
    }

    @Test
    void testRemoveByIdThrowsInternalServerError() {
        // GIVEN a collection that throws on delete
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("db error"));

        // WHEN removing
        // THEN an InternalServerError should be thrown
        assertThatThrownBy(() -> repository.removeById("507f1f77bcf86cd799439011"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
