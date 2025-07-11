package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(LocalUserPersistentRepository.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private final LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

    @BeforeEach
    void setUp() {
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: newId() is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id);
        assert(!id.isEmpty());
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with a specific email exists.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: findByEmail("test@example.com") is called.
        // THEN: The user with the specified email is returned.
        User foundUser = repository.findByEmail("test@example.com");
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: No user exists with the specified email.
        // WHEN: findByEmail("nonexistent@example.com") is called.
        // THEN: Null is returned.
        User foundUser = repository.findByEmail("nonexistent@example.com");
        assertNull(foundUser);
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: findById("1") is called.
        // THEN: The user with the specified ID is returned.
        User foundUser = repository.findById("1");
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: No user exists with the specified ID.
        // WHEN: findById("nonexistentID") is called.
        // THEN: Null is returned.
        User foundUser = repository.findById("nonexistentID");
        assertNull(foundUser);
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new user object is created.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: insert(user) is called.
        // THEN: The user is added to the repository, and the user object is returned.
        User insertedUser = repository.insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("test", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_returnsNullIfReplacementSuccessful() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: replace("1", new User("1", "test", "test@example.com", "new```java
        assertEquals("1", replacedUser.getId());
        assertEquals("test", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("newPassword", replacedUser.getPassword());
    }

    @Test
    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: No user exists with the specified ID.
        // WHEN: replace("nonexistentID", new User("1", "test", "test@example.com", "password")) is called.
        // THEN: A RuntimeException is thrown with the message "Data does not exist.".
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentID", new User("1", "test", "test@example.com", "password")));
    }

    @Test
    void removeById_returnsTrueIfRemovalSuccessful() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: removeById("1") is called.
        // THEN: The user with the specified ID is removed, and true is returned.
        assertTrue(repository.removeById("1"));
        assertFalse(repository.findById("1").isPresent());
    }

    @Test
    void removeById_returnsTrueIfRemovalSuccessfulEvenIfNoUserExists() {
        // GIVEN: No user exists with the specified ID.
        // WHEN: removeById("nonexistentID") is called.
        // THEN: true is returned.
        assertTrue(repository.removeById("nonexistentID"));
    }
}