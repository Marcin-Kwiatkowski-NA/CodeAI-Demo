package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoClient.getDatabase(any(String.class))).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(any(String.class), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(ObjectId.isValid(newId)).isTrue();
    }

    @Test
    void testFindByEmailReturnsUserSuccessfully() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        // WHEN
        User result = repository.findByEmail("test@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testUser");
    }

    @Test
    void testFindByEmailThrowsInternalServerErrorWhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findByEmail("error@example.com"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testFindByIdReturnsUserSuccessfully() {
        // GIVEN
        ObjectId id = new ObjectId();
        MongoUserEntity mongoUserEntity = new MongoUserEntity(id, "user1", "user1@example.com", "pwd");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        // WHEN
        User result = repository.findById(id.toString());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id.toString());
    }

    @Test
    void testFindByIdThrowsInternalServerErrorWhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB failure"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsertInsertsUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "insertUser", "insert@example.com", "pwd");

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testInsertThrowsInternalServerErrorOnFailure() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "failUser", "fail@example.com", "pwd");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplaceUpdatesUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "replaceUser", "replace@example.com", "pwd");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);

        // WHEN
        User result = repository.replace(user.getId(), user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(collection, times(1)).replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplaceThrowsInternalServerErrorOnFailure() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "replaceFail", "fail@example.com", "pwd");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace failed"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any(Bson.class));
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorOnFailure() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}