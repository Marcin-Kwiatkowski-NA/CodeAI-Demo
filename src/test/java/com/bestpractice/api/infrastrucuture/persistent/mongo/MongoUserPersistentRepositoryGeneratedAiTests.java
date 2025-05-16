package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserPersistentRepositoryGeneratedAiTests.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Test
    void newId() {
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = repository.newId();
        assertNotNull(id);
        assertEquals(12, id.length());
    }

    @Test
    void findByEmail() {
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User user = repository.findByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void findById() {
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User user = repository.findById("64f8d3a7e9e8e9e9e9e9e9e9");
        assertNotNull(user);
        assertEquals("64f8d3a7e9e8e9e9e9e9e9e9", user.getId());
    }

    @Test
    void insert() {
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User user = new User("123", "test", "test@example.com", "password");
        User returnedUser = repository.insert(user);
        assertNotNull(returnedUser);
        assertEquals("123", returnedUser.getId());
        assertEquals("test", returnedUser.getUsername());
        assertEquals("test@example.com", returnedUser.getEmail());
        assertEquals("password", returnedUser.getPassword());
    }
}
