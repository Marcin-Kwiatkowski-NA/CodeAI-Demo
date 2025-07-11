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
        // GIVEN: A new MongoUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new ObjectId string is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        String id = repository.newId();
        assertNotNull(id);
        assert(!id.isEmpty());
    }

    @Test
    void findByEmail() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        // AND: A MongoUserEntity is present in the database with a specific email.
        // WHEN: The findByEmail() method is called with that email.
        // THEN: The corresponding MongoUserEntity is retrieved and converted to a User object.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity userEntity = new MongoUserEntity("1", "test", "test@example.com", "password");
        repository.collection.insertOne(userEntity);
        User user = repository.findByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("test", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        repository.collection.deleteOne(Filters.eq("email", "test@example.com"));
    }

    @Test
    void findById() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        // AND: A MongoUserEntity is present in the database with a specific ID.
        // WHEN: The findById() method is called with that ID.
        // THEN: The corresponding MongoUserEntity is retrieved and converted to a User object.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity userEntity = new MongoUserEntity("1", "test", "test@example.com", "password");
        repository.collection.insertOne(userEntity);
        String id = "1";
        User user = repository.findById(id);
        assertNotNull(user);
        assertEquals("test", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        repository.collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    @Test
    void insertUser() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        // WHEN: The insertUser() method is called with a new User object.
        // THEN: A new MongoUserEntity is inserted into the database with the provided User details.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User newUser = new User("new_test", "new@example.com", "new_password");
        String id = repository.insertUser(newUser);
        assertNotNull(id);
        assert !id.isEmpty();
        User retrievedUser = repository.findById(id);
        assertNotNull```java
        assertNotNull(retrievedUser);
        assertEquals("new_test", retrievedUser.getUsername());
        assertEquals("new@example.com", retrievedUser.getEmail());
        assertEquals("new_password", retrievedUser.getPassword());
        repository.collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    @Test
    void replaceUser() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        // AND: A MongoUserEntity is present in the database with a specific ID.
        // WHEN: The replaceUser() method is called with that ID and a modified User object.
        // THEN: The MongoUserEntity is updated in the database with the provided User object, and the same User object is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity userEntity = new MongoUserEntity("1", "test", "test@example.com", "password");
        repository.collection.insertOne(userEntity);
        User updatedUser = new User("1", "updated_test", "updated@example.com", "new_password");
        User returnedUser = repository.replaceUser("1", updatedUser);
        assertNotNull(returnedUser);
        assertEquals("updated_test", returnedUser.getUsername());
        assertEquals("updated@example.com", returnedUser.getEmail());
        assertEquals("new_password", returnedUser.getPassword());
        repository.collection.deleteOne(Filters.eq("_id", new ObjectId("1")));
    }

    @Test
    void removeById() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        // AND: A MongoUserEntity is present in the database with a specific ID.
        // WHEN: The removeById() method is called with that ID.
        // THEN: The MongoUserEntity is deleted from the database, and the method returns true (acknowledgment).
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity userEntity = new MongoUserEntity("1", "test", "test@example.com", "password");
        repository.collection.insertOne(userEntity);
        boolean acknowledgment = repository.removeById("1");
        assertTrue(acknowledgment);
        repository.collection.deleteOne(Filters.eq("_id", new ObjectId("1")));
    }
}