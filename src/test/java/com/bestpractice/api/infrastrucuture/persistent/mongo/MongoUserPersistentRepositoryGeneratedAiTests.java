package com.bestpractice.api.infrastrucuture.persistent.mongo;

        // THEN: The User object is inserted into the database and returned.

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        User insertedUser = repository.insert(new User("1", "test", "test@example.com", "password"));
        assertEquals(12, newId.length(), "newId should return a 12-character ObjectId");
        assertEquals("test", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
        assertEquals("password", foundUser.getPassword(), "Password should match");
    }

    @Test
    void replace() {
        // GIVEN: A MongoUserPersistentRepository instance is created with some data.
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = mongoDatabaseBuilder();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);

        // Setup some test data
        MongoUserEntity user = MongoUserEntity.convertFrom(new User("1", "test", "test@example.com", "password"));
        repository.collection.insertOne(user);

        // WHEN: The replaceById("1") method is called.
        boolean replaced = repository.replaceById("1");

        // THEN: The User object with the specified ID is updated in the database and the method returns true.
        assertTrue(replaced, "replaceById should return true");
        assertEquals("1", repository.findByEmail("test@example.com").getId(), "User ID should be updated");
    }

    @Test
    void removeById() {
        // GIVEN: A MongoUserPersistentRepository instance is created with some data.
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = mongoDatabaseBuilder();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);

        // Setup some test data
        MongoUserEntity user = MongoUserEntity.convertFrom(new User("1", "test", "test@example.com", "password"));
        repository.collection.insertOne(user);

        // WHEN: The removeById("1") method is called.
        boolean removed = repository.removeById("1");

        // THEN: The User object with the specified ID is removed from the database and the method returns true.
        assertTrue(removed, "removeById should return true");
        assertNull(repository.findByEmail("test@example.com"), "User should be removed");
    }

    private MongoDatabase mongoDatabaseBuilder() {
        return MongoDatabase.builder().build();
    }
}
