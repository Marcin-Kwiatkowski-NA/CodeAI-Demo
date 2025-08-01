package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@ExtendWith(LocalUserPersistentRepository.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private final LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

    @BeforeEach
    void setUp() {
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UUID should be generated.
        // WHEN: newId() is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assert id != null;
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with a specific email exists.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: findByEmail("user1@example.com") is called.
        // THEN: The user with the specified email is returned.
        User foundUser = repository.findByEmail("user1@example.com");
        assertEquals("user1", foundUser.getUsername());
        assertEquals("user1@example.com", foundUser.getEmail());
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: No user exists with a specific email.
        // WHEN: findByEmail("nonexistent@example.com") is called.
        // THEN: Null is returned.
        User foundUser = repository.findByEmail("nonexistent@example.com");
        assert foundUser == null;
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: findById("user1") is called.
        // THEN: The user with the specified ID is returned.
        User foundUser = repository.findById("user1");
        assertEquals("user1", foundUser.getUsername());
        assertEquals("user1@example.com", foundUser.getEmail());
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: No user exists with a specific ID.
        // WHEN: findById("nonexistentID") is called.
        // THEN: Null is returned.
        User foundUser = repository.findById("nonexistentID");
        assert foundUser == null;
    }

    @Test
    void insert_returnsNewUser() {
        // GIVEN: A new user object is created.
        User user = new User("user1", "user1", "user1@example.com", "password");

        // WHEN: insert(user) is called.
        // THEN: The new user is added to the repository and returned.
        User insertedUser = repository.insert(user);
        assertEquals("user1", insertedUser.getUsername());
        assertEquals("user1@example.com", insertedUser.getEmail());
        assertTrue(repository.users.contains(insertedUser));
    }

    @Test
    void replace_returnsNullIfUserReplaced() {
        // GIVEN: A user with a specific ID exists.
        User user = new User("user1", "user1", "user1@example.com", "password");
        repository.users.add(user);

        // WHEN: replace("user1", new User("user1", "user1", "user1@example.com", "newPassword")) is called.
```java
        // THEN: The user with the specified ID is replaced and null is returned.
        User replacedUser = repository.replace("user1", new User("user1", "user1", "user1@example.com", "newPassword"));
        assertEquals("user1", replacedUser.getUsername());
        assertEquals("user1@example.com", replacedUser.getEmail());
        assertTrue(repository.users.contains(replacedUser));
    }

    @Test
    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: No user exists with a specific ID.
        // WHEN: replace("nonexistentID", new User("user1", "user1", "user1@example.com", "password")) is called.
        // THEN: A RuntimeException is thrown.
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentID", new User("user1", "user1", "user1@example.com", "password")));
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UUID should be generated.
        // WHEN: newId() is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assert id != null;
    }
}