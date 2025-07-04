package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
        // GIVEN: A MongoDB client and database are established.
        // WHEN: The newId() method is called.
        // THEN: A new ObjectId string is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = repository.newId();
        assertNotNull(id);
        assert(!id.isEmpty());
    }

    @Test
    void findByEmail() {
        // GIVEN: A MongoDB client and database are established.
        // AND: A MongoUserEntity is present in the database with a specific email.
        // WHEN: The findByEmail() method is called with that email.
        // THEN: The corresponding MongoUserEntity is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String email = "test@example.com";
        MongoUserEntity user = MongoUserEntity.convertFrom(new User(null, "test", email, "password"));
        MongoUserPersistentRepository.collection.insertOne(user);
        User foundUser = repository.findByEmail(email);
        assertNotNull(foundUser);
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById() {
        // GIVEN: A MongoDB client and database are established.
        // AND: A MongoUserEntity is present in the database with a specific ID.
        // WHEN: The findById() method is called with that ID.
        // THEN: The corresponding MongoUserEntity is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = "654321abcdeff000000000001";
        MongoUserEntity user = MongoUserEntity.convertFrom(new User(id, "test", "test@example.com", "password"));
        MongoUserPersistentRepository.collection.insertOne(user);
        User foundUser = repository.findById(id);
        assertNotNull(foundUser);
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert() {
        // GIVEN: A MongoDB client and database are established.
        // WHEN: The insert() method is called with a User object.
        // THEN: The User object is inserted into the database, and the same User object is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User user = new User(null, "test", "test@example.com", "password");
        User insertedUser = repository.insert(user);
        assertNotNull(insertedUser);
        assertEquals("test", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
```java
        // AND: The User object has the same data as the MongoUserEntity.
        // WHEN: The replace() method is called with the ID and the User object.
        // THEN: The MongoUserEntity is updated in the database, and the same User object is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = "654321abcdeff000000000001";
        User user = new User(id, "test", "test@example.com", "newPassword");
        MongoUserEntity existingUser = MongoUserEntity.convertFrom(new User(id, "test", "test@example.com", "password"));
        MongoUserPersistentRepository.collection.insertOne(existingUser);
        User replacedUser = repository.replace(id, user);
        assertNotNull(replacedUser);
        assertEquals("test", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("newPassword", replacedUser.getPassword());

        // Verify that the updated data is reflected in the database.
        User retrievedUser = repository.findById(id);
        assertNotNull(retrievedUser);
        assertEquals("test", retrievedUser.getUsername());
        assertEquals("test@example.com", retrievedUser.getEmail());
        assertEquals("newPassword", retrievedUser.getPassword());

        // Clean up the inserted data.
        MongoUserPersistentRepository.collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    @Test
    void removeById() {
        // GIVEN: A MongoDB client and database are established.
        // AND: A MongoUserEntity is present in the database with a specific ID.
        // WHEN: The removeById() method is called with that ID.
        // THEN: The MongoUserEntity with that ID is deleted from the database, and the method returns true.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = "654321abcdeff000000000001";
        User user = new User(id, "test", "test@example.com", "password");
        MongoUserPersistentRepository.collection.insertOne(user);
        boolean removed = repository.removeById(id);
        assertTrue(removed);
        assertFalse(repository.findById(id).isPresent());

        // Clean up the inserted data.
        MongoUserPersistentRepository.collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
}