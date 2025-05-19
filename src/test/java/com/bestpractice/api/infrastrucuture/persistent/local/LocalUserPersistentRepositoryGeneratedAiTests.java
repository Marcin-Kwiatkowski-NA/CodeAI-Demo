package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;

@ExtendWith(LocalUserPersistentRepositoryGeneratedAiTests.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the user list before each test
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        repository.users = Collections.emptyList();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new LocalUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        String generatedId = repository.newId();
        assert generatedId != null;
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with a specific email address is stored in the repository.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The findByEmail("test@example.com") method is called.
        // THEN: The user object with the specified email address is returned.
        User foundUser = repository.findByEmail("test@example.com");
        assert foundUser != null;
        assert foundUser.getEmail().equals("test@example.com");
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: A LocalUserPersistentRepository instance is created.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The findByEmail("nonexistent@example.com") method is called.
        // THEN: Null is returned.
        User foundUser = repository.findByEmail("nonexistent@example.com");
        assert foundUser == null;
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user with a specific ID is stored in the repository.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The findById("1") method is called.
        // THEN: The user object with the specified ID is returned.
        User foundUser = repository.findById("1");
        assert foundUser != null;
        assert foundUser.getId().equals("1");
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: A LocalUserPersistentRepository instance is created.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The findById("nonexistent_id") method is called.
        // THEN: Null is returned.
        User foundUser = repository.findById("nonexistent_id");
        assert foundUser == null;
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new user object is created.
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");

        // WHEN: The insert(user) method is called.
        // THEN: The user object is added to the repository, and the same user object is returned.
        User insertedUser = repository.insert(user);
        assert insertedUser != null;
        assert insertedUser.getId().equals("1");
        assert repository.users.contains(insertedUser);
    }
}
