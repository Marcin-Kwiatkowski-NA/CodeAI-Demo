package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void givenReplaceFails_whenReplace_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mock(com.mongodb.client.MongoClient.class), mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnsValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(ObjectId.isValid(id));
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void givenEmailNotFound_whenFindByEmail_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsUser() {
        // GIVEN
        MongoUserEntity entity = mock(MongoUserEntity.class);
        when(entity.convertTo()).thenReturn(new User("1", "user", "email@test.com", "pass"));
        when(collection.find(any())).thenReturn(new com.mongodb.client.FindIterable<>() {
            @Override public MongoUserEntity first() { return entity; }
        });

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void givenIdNotFound_whenFindById_thenThrowsInternalServerError() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    void givenValidUser_whenInsert_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void givenInsertFails_whenInsert_thenThrowsInternalServerError() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    void givenValidUser_whenReplace_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace("1", user);

        // THEN
        assertNotNull(result);
        assertEquals(user, result);
    }
