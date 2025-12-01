package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import org.bson.conversions.Bson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @InjectMocks
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoDatabase.getCollection(eq("users"), eq(MongoUserEntity.class))).thenReturn(collection);
    }

    @Test
    void newId_ShouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        User mockUser = new User("1", "testUser", email, "password123");
        when(mockEntity.convertTo()).thenReturn(mockUser);
        when(collection.find(any(Bson.class))).thenReturn(mock(MongoCollection.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String email = "test@example.com";
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        User mockUser = new User(id, "testUser", "test@example.com", "password123");
        when(mockEntity.convertTo()).thenReturn(mockUser);
        when(collection.find(any(Bson.class))).thenReturn(mock(MongoCollection.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        repository.insert(user);

        // THEN
        Mockito.verify(collection, Mockito.times(1)).insertOne(eq(mockEntity));
    }

    @Test
    void insert_ShouldThrowInternalServerError_WhenExceptionOccurs() {
               // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        when(collection.insertOne(any(MongoUserEntity.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password123");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), eq(mockEntity), eq(replaceOptions))).thenReturn(mockResult);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        Mockito.verify(collection, Mockito.times(1)).replaceOne(any(Bson.class), eq(mockEntity), eq(replaceOptions));
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password123");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockResult = mock(DeleteResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(mockResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        Mockito.verify(collection, Mockito.times(1)).deleteOne(any(Bson.class));
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}