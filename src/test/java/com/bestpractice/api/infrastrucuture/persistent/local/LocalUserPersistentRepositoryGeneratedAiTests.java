package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.junit.jupiter.api.ExtensionRegistry.create;

@ExtendWith(MyExtension.class)
class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "Generated ID should not be null");
        assert(!id.isEmpty(), "Generated ID should not be empty");
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with a specific email exists in the repository.
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The findByEmail("test@example.com") method is called.
        // THEN: The user with the email "test@example.com" is returned.
        User foundUser = repository.findByEmail("test@example.com");
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail("nonexistent@example.com") method is called.
        // THEN: Null is returned.
        User notFoundUser = repository.findByEmail("nonexistent@example.com");
        assertNull(notFoundUser, "User should be null when not found");
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The findById("1") method is called.
        // THEN: The user with the ID "1" is returned.
        User foundUser = repository.findById("1");
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The findById("nonexistentId") method is called.
        // THEN: Null is returned.
        User notFoundUser = repository.findById("nonexistentId");
        assertNull(notFoundUser, "User should be null when not found");
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: A new user object.
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN: The insert(user) method is called.
        // THEN: The user is added to the repository, and the user object is returned.
        User insertedUser = repository.insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("testUser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
    }

    @Test
    void replace_replacesUserByIdAndReturnsNull() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The replace("1", new User("1", "newTestUser", "newTest@example.com", "newPassword")) method is called.
        // THEN: The user with the ID "1" is replaced, and null is```java
        "1", "newTestUser", "newTest@example.com", "newPassword")) method is called.
        // THEN: The user with the ID "1" is replaced, and null is returned.
        assertNull(repository.findById("1"), "User should be replaced");
    }

    @Test
    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace("nonexistentId", new User("1", "testUser", "test@example.com", "password")) method is called.
        // THEN: A RuntimeException with the message "Data does not exist." is thrown.
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentId", new User("1", "testUser", "test@example.com", "password")));
    }

    @Test
    void removeById_removesUserByIdAndReturnsTrue() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN: The removeById("1") method is called.
        // THEN: The user with the ID "1" is removed from the repository, and true is returned.
        assertTrue(repository.removeById("1"), "Removal should succeed");
        assertNull(repository.findById("1"), "User should be removed");
    }

    @Test
    void removeById_returnsTrueIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The removeById("nonexistentId") method is called.
        // THEN: True is returned, and the repository remains unchanged.
        assertTrue(repository.removeById("nonexistentId"), "Removal should succeed even if not found");
    }
}

@ExtendWith(MyExtension.class)
class MyExtension {}
