package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
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
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(new ObjectId(newId)).isInstanceOf(ObjectId.class);
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("123", "testUser", email, "password");
        when(collection.find(Filters.eq("email", email))).thenReturn(mockFindIterable(mongoUserEntity));
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findByEmail(email);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(Filters.eq("email", email))).thenReturn(mockFindIterable(null));

        // WHEN & THEN
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
        when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenReturn(mockFindIterable(mongoUserEntity));
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User actualUser = repository.findById(id);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findById_shouldThrowInternalServerError_whenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenReturn(mockFindIterable(null));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("123", "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        verify(collection).insertOne(mongoUserEntity);
        assertThat(insertedUser).isEqualTo(user);
    }

    @Test
    void insert_shouldThrowInternalServerError_whenInsertionFails() {
        // GIVEN
        User user = new User("123", "testUser", "test@example.com", "password");
        doThrow(new RuntimeException("Insertion error")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOfInternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions))
                .thenReturn(updateResult);

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        verify(collection).replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions);
        assertThat(replacedUser).isEqualTo(user);
    }

    @Test
    void replace_shouldThrowInternalServerError_whenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace error")).when(collection)
                .replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions);

        // WHEN & THEN
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
        when(collection.deleteOne(Filters.eq("_id", new ObjectId(id)))).thenReturn(deleteResult);

        // WHEN
        boolean isRemoved = repository.removeById(id);

        // THEN
        verify(collection).deleteOne(Filters.eq("_id", new ObjectId(id)));
        assertThat(isRemoved).isTrue();
    }

    @Test
    void removeById_shouldThrowInternalServerError_whenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete error")).when(collection).deleteOne(Filters.eq("_id", new ObjectId(id)));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }

    private <T> com.mongodb.client.FindIterable<T> mockFindIterable(T entity) {
        com.mongodb.client.FindIterable<T> findIterable = mock(com.mongodb.client.FindIterable.class);
        when(findIterable.first()).thenReturn(entity);
        return findIterable;
    }
}
