package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;

@ExtendWith(LocalUserPersistentRepositoryGeneratedAiTests.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void newId() {
        // GIVEN a new ID is generated
        // WHEN a new UUID is created
        // THEN a string representing the UUID is returned
        String generatedId = repository.newId();
        assert generatedId != null;
    }

    @Test
    void findByEmail() {
        // GIVEN a user exists with a specific email
        // WHEN the repository is queried for a user by email
        // THEN the user is found and returned
        User newUser = new User("1", "test", "test@example.com", "password");
        repository.users.add(newUser);
        User foundUser = repository.findByEmail("test@example.com");
        assert foundUser != null;
        assert foundUser.getEmail().equals("test@example.com");
    }

    @Test
    void findById() {
        // GIVEN a user exists with a specific ID
        // WHEN the repository is queried for a user by ID
        // THEN the user is found and returned
        User newUser = new User("1", "test", "test@example.com", "password");
        repository.users.add(newUser);
        User foundUser = repository.findById("1");
        assert foundUser != null;
        assert foundUser.getId().equals("1");
    }

    @Test
    void insert() {
        // GIVEN a new user object
        // WHEN the user is inserted into the repository
        // THEN the user is added to the repository's list
        User newUser = new User("1", "test", "test@example.com", "password");
        User insertedUser = repository.insert(newUser);
        assert insertedUser != null;
        assert repository.users.contains(newUser);
    }

    @Test
    void replace() {
        // GIVEN a user exists with a specific ID
        // WHEN the user is replaced with a new user object
        // THEN the old user is removed and the new user is added
        User newUser = new User("1", "newTest", "newTest@example.com", "newPassword");
        User oldUser = repository.findById("1");
        repository.replace("1", newUser);
        assert !repository.users.contains(oldUser);
        assert repository.users.contains(newUser);
    }

    @Test
    void removeById() {
        // GIVEN a user exists with a specific ID
        // WHEN the user is removed from the repository by ID
        // THEN the user is removed from the repository's list
        User newUser = new User("1", "test", "test@example.com", "password");
        repository.users.add(newUser);
        boolean removed = repository.removeById("1");
        assert removed;
        assert !repository.users.contains(newUser);
    }
}
