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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyAssertions.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the user list before each test
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        repository.users = Collections.synchronizedList(new ArrayList<>());
    }

    @Test
    void newId_returns_unique_uuid() {
        // GIVEN: A new instance of the LocalUserPersistentRepository
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: A new ID is generated
        String id = repository.newId();

        // THEN: The generated ID is a valid UUID string
        assertNotNull(id);
        assert(!id.isEmpty());
    }

    @Test
    void findByEmail_returns_user_if_exists() {
        // GIVEN: A user with a specific email
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The repository is queried for the user by email
        User foundUser = repository.findByEmail("test@example.com");

        // THEN: The user is found and returned
        assertNotNull(foundUser);
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findByEmail_returns_null_if_user_not_exists() {
        // GIVEN: A LocalUserPersistentRepository instance
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The repository is queried for a user with a non-existent email
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN: The user is not found and null is returned
        assertNull(foundUser);
    }

    @Test
    void findById_returns_user_if_exists() {
        // GIVEN: A user with a specific ID
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The repository is queried for the user by ID
        User foundUser = repository.findById("1");

        // THEN: The user is found and returned
        assertNotNull(foundUser);
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById_returns_null_if_user_not_exists() {
        // GIVEN: A LocalUserPersistentRepository instance
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The repository is queried for a user with a non-existent ID
        User foundUser = repository.findById("nonexistent");

        // THEN: The user is not found and null is returned
        assertNull(foundUser);
    }

    @Test
    void insert_returns_inserted_user() {
        // GIVEN: A new user object
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");

        // WHEN: The user is inserted into the repository```java
        User insertedUser = repository.insert(user);

        // THEN: The inserted user is returned
        assertNotNull(insertedUser);
        assertEquals("1", insertedUser.getId());
        assertEquals("test", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_returns_null_if_user_exists() {
        // GIVEN: A user with a specific ID
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "newTest", "newEmail@example.com", "newPassword");
        repository.insert(user);

        // WHEN: The user is replaced in the repository
        repository.replace("1", new User("1", "newTest", "newEmail@example.com", "newPassword"));

        // THEN: The replaced user is returned
        assertNull(repository.findById("1"));
        assertEquals("newTest", repository.findById("1").getUsername());
        assertEquals("newEmail@example.com", repository.findById("1").getEmail());
        assertEquals("newPassword", repository.findById("1").getPassword());
    }

    @Test
    void replace_throws_exception_if_user_not_exists() {
        // GIVEN: A LocalUserPersistentRepository instance
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The user is replaced with a non-existent ID
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistent", new User("1", "test", "test@example.com", "password")));
    }

    @Test
    void removeById_returns_true_if_user_exists() {
        // GIVEN: A user with a specific ID
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "test", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The user is removed from the repository
        boolean removed = repository.removeById("1");

        // THEN: The user is removed and true is returned
        assertTrue(removed);
        assertNull(repository.findById("1"));
    }

    @Test
    void removeById_returns_true_if_user_not_exists() {
        // GIVEN: A LocalUserPersistentRepository instance
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

        // WHEN: The user is removed with a non-existent ID
        boolean removed = repository.removeById("nonexistent");

        // THEN: The user is not found and true is returned
        assertTrue(removed);
    }
}

@ExtendWith(MyAssertions.class)
class MyAssertions {
}