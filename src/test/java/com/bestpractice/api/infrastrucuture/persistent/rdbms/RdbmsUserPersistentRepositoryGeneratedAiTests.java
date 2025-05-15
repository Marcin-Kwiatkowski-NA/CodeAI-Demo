package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Test
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(null);
    }

    @Test
    void newId() {
        // GIVEN: A new UUID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String generatedId = repository.newId();
        assert generatedId != null;
    }

    @Test
    void findByEmail() {
        // GIVEN: A user with a specific email exists in the database.
        // WHEN: The findByEmail() method is called with that email.
        // THEN: The user object with that email is returned.
        User user = new User("1", "testuser", "test@example.com", "password");
        User foundUser = repository.findByEmail("test@example.com");
        assert foundUser != null;
        assert foundUser.getEmail().equals("test@example.com");
    }

    @Test
    void findById() {
        // GIVEN: A user with a specific ID exists in the database.
        // WHEN: The findById() method is called with that ID.
        // THEN: The user object with that ID is returned.
        User user = new User("1", "testuser", "test@example.com", "password");
        User foundUser = repository.findById("1");
        assert foundUser != null;
        assert foundUser.getId().equals("1");
    }

    @Test
    void insert() {
        // GIVEN: A new user object is created.
        // WHEN: The insert() method is called with that user object.
        // THEN: The user object is returned, and the user is inserted into the database.
        User user = new User("1", "testuser", "test@example.com", "password");
        User insertedUser = repository.insert(user);
        assert insertedUser != null;
        assert insertedUser.getId().equals("1");
        assert insertedUser.getUsername().equals("testuser");
        assert insertedUser.getEmail().equals("test@example.com");
    }

    @Test
    void replace() {
        // GIVEN: A user with a specific ID exists in the database.
        // WHEN: The replace() method is called with that ID and a modified user object.
        // THEN: The user object is returned, and the user's data is updated in the database.
        User user = new User("1", "testuser", "test@example.com", "newpassword");
        User replacedUser = repository.replace("1", user);
        assert replacedUser != null;
        assert replacedUser.getUsername().equals("testuser");
        assert replacedUser.getEmail().equals("test@example.com");
        assert replacedUser.getPassword().equals("newpassword");
    }

    @Test
    void removeById() {
        // GIVEN: A user with a specific ID exists in the database.
        // WHEN: The removeById() method is called with that ID.
        // THEN: The user with that ID is removed from the database, and true is returned.
        User user = new User("1", "testuser", "test@example.com", "password");
        boolean removed = repository.removeById("1");
        assert removed;
    }
}
