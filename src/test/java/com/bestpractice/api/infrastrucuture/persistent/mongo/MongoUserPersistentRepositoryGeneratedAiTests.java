package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        when(mongoDatabase.getCollection("users", MongoUserEntity.class)).thenReturn(collection);
    }

    @Test
    void newId_ShouldGenerateNewId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).isInstanceOf(String.class);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        when(collection.find(Filters.eq("email", email))).thenReturn(mock(Iterable.class));
        when(collection.find(Filters.eq("email", email)).first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(new User("1", "username", email, "password"));

        // WHEN
        User user = repository.findByEmail(email);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_ShouldThrowException_WhenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(Filters.eq("email", email)).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenReturn(mock(Iterable.class));
        when(collection.find(Filters.eq("_id", new ObjectId(id))).first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(new User(id, "username", "email", "password"));

        // WHEN
        User user = repository.findById(id);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void findById_ShouldThrowException_WhenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(Filters.eq("_id", new ObjectId(id))).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        repository.insert(user);

        // THEN
        verify(collection, times(1)).insertOne(mongoUserEntity);
    }

    @Test
    void insert_ShouldThrowException_WhenInsertFails() {
        // GIVEN
        User user = new User("1", "username", "email", "password");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(collection.replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions)).thenReturn(updateResult);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getId()).isEqualTo(id);
        verify(collection, times(1)).replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions);
    }

    @Test
    void replace_ShouldThrowException_WhenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        ObjectId objectId = new ObjectId(id);
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(collection.deleteOne(Filters.eq("_id", objectId))).thenReturn(deleteResult);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean isRemoved = repository.removeById(id);

        // THEN
        assertThat(isRemoved).isTrue();
        verify(collection, times(1)).deleteOne(Filters.eq("_id", objectId));
    }

    @Test
    void removeById_ShouldThrowException_WhenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        ObjectId objectId = new ObjectId(id);
        doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(Filters.eq("_id", objectId));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}