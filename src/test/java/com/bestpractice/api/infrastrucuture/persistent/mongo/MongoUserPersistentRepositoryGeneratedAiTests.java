package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoDatabase.getCollection("users", MongoUserEntity.class)).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
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
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "testUser", email, "password");
        when(collection.find(any(Bson.class))).thenReturn(mock(Iterable.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenExceptionOccurs() {
        // GIVEN
        String email = "test@example.com";
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(collection.find(any(Bson.class))).thenReturn(mock(Iterable.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void findById_shouldThrowInternalServerError_whenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection).insertOne(mongoUserEntity);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void insert_shouldThrowInternalServerError_whenExceptionOccurs() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        when(collection.insertOne(any(MongoUserEntity.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        verify(collection).replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void replace_shouldThrowInternalServerError_whenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_shouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        verify(collection).deleteOne(any(Bson.class));
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldThrowInternalServerError_whenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}