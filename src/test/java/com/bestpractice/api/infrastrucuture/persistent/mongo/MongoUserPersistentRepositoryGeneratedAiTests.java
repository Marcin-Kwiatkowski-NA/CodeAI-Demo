package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.quality.Strictness;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

    private static final String EMAIL = "test@example.com";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password123";
    private static final String ID = "507f1f77bcf86cd799439011";

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private UserPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = Mockito.mock(MongoClient.class);
        mongoDatabase = Mockito.mock(MongoDatabase.class);
        collection = Mockito.mock(MongoCollection.class);

        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString(), any())).thenReturn(collection);

        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void givenValidEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        User user = new User(ID, USERNAME, EMAIL, PASSWORD);
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        when(collection.find(Filters.eq("email", EMAIL))).thenReturn(Arrays.asList(mongoUserEntity));

        // WHEN
        User result = repository.findByEmail(EMAIL);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getUsername()).isEqualTo(USERNAME);
        assertThat(result.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void givenInvalidEmail_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(Filters.eq("email", EMAIL))).thenReturn(Arrays.asList());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(EMAIL))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to find user with email");
    }

    @Test
    public void givenValidId_whenFindByID_thenReturnsUser() {
        // GIVEN
        User user = new User(ID, USERNAME, EMAIL, PASSWORD);
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        when(collection.find(Filters.eq("_id", ID))).thenReturn(Arrays.asList(mongoUserEntity));

        // WHEN
        User result = repository.findByID(ID);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getUsername()).isEqualTo(USERNAME);
        assertThat(result.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void givenInvalidId_whenFindByID_thenReturnsNull() {
        // GIVEN
        when(collection.find(Filters.eq("_id", "invalid-id"))).thenReturn(Arrays.asList());

        // WHEN
        User result = repository.findByID("invalid-id");

        // THEN
        assertThat(result).isNull();
    }

    @Test
    public void givenValidUser_whenSave_thenSavesUser() {
        // GIVEN
        User user = new User(ID, USERNAME, EMAIL, PASSWORD);
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        repository.save(user);

        // THEN
        verify(collection).insertOne(mongoUserEntity);
    }

    @Test
    public void givenExistingUser_whenUpdate_thenUpdatesUser() {
        // GIVEN
        User user = new User(ID, USERNAME, EMAIL, PASSWORD);
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        repository.update(user);

        // THEN
        verify(collection).replaceOne(Filters.eq("_id", ID), mongoUserEntity);
    }

    @Test
    public void givenUser_whenDelete_thenDeletesUser() {
        // GIVEN
        String id = "test-id";
        when(collection.find(Filters.eq("_id", id))).thenReturn(Arrays.asList());

        // WHEN
        repository.delete(id);

        // THEN
        verify(collection).deleteOne(Filters.eq("_id", id));
    }

    @Test
    @Test
    public void givenInvalidUser_whenSave_thenThrowsInternalServerError() {
        // GIVEN
        User invalidUser = new User(null, null, null, null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.save(invalidUser))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("User cannot be null or empty");
    }

    @Test
    public void givenInvalidUser_whenUpdate_thenThrowsInternalServerError() {
        // GIVEN
        User invalidUser = new User(null, null, null, null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.update(invalidUser))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("User cannot be null or empty");
    }

    @Test
    public void givenInvalidId_whenDelete_thenThrowsInternalServerError() {
        // GIVEN
        String invalidId = "";

        // WHEN & THEN
        assertThatThrownBy(() -> repository.delete(invalidId))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Invalid user ID");
    }
}
