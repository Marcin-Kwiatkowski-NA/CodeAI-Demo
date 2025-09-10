package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;

void replace() {
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = MongoDatabase.builder().build();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(new User(id, "testUser", "test@example.com", "password"));
        mongoDatabase.getCollection(COLLECTION_NAME, MongoUserEntity.class).insertOne(mongoUserEntity);
        User user = new User(id, "testUser", "test@example.com", "password");
        User replacedUser = repository.replace(id, user);
        assertNotNull(replacedUser, "User should not be null");
        assertEquals("testUser", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
        assertEquals("password", replacedUser.getPassword(), "Password should match");
    }

    @Test
    void removeById() {
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = MongoDatabase.builder().build();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(new User(id, "testUser", "test@example.com", "password"));
        mongoDatabase.getCollection(COLLECTION_NAME, MongoUserEntity.class).insertOne(mongoUserEntity);
        boolean removed = repository.removeById(id);
        assertTrue(removed, "Removal should be successful");
        assertFalse(repository.findById(id).isPresent(), "User should not exist after removal");
    }
}
