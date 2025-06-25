package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository = new LocalUserPersistentRepository();

    @BeforeEach
    void setUp() {
        // Reset the list of users before each test
        repository.users.clear();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new UUID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "UUID should not be null");
        assertTrue(id.length() > 32, "UUID should be at least 32 characters long");
    }

    @Test
    void findByEmail_returnsUserIfFound() {
        // GIVEN: A user with a specific email exists in the repository.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The findByEmail("test@example.com") method is called.
        // THEN: The user with the specified email should be returned.
        User foundUser = repository.findByEmail("test@example.com");
        assertEquals(user, foundUser, "User should match the expected user");
    }

    @Test
    void findByEmail_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified email.
        // WHEN: The findByEmail("nonexistent@example.com") method is called.
        // THEN: Null should be returned.
        User foundUser = repository.findByEmail("nonexistent@example.com");
        assertNull(foundUser, "User should be null when not found");
    }

    @Test
    void findById_returnsUserIfFound() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The findById("1") method is called.
        // THEN: The user with the specified ID should be returned.
        User foundUser = repository.findById("1");
        assertEquals(user, foundUser, "User should match the expected user");
    }

    @Test
    void findById_returnsNullIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The findById("nonexistentID") method is called.
        // THEN: Null should be returned.
        User foundUser = repository.findById("nonexistentID");
        assertNull(foundUser, "User should be null when not found");
    }

    @Test
    void insert_returnsInsertedUser() {
        // GIVEN: A new user object is created.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The insert(user) method is called.
        // THEN: The inserted user object should be returned.
        User insertedUser = repository.insert(user);
        assertEquals(user, insertedUser, "Inserted user should match the original user");
        assertNotNull(insertedUser.getId(), "Inserted user should have an ID");
    }

    @Test
    void replace_returnsNullIfReplacementSuccessful() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The replace("1", new User("1```java
        "test", "test@example.com", "password"));
        assertNotNull(updatedUser.getId(), "Updated user should have an ID");
        assertEquals(user, updatedUser, "Updated user should match the original user");
    }

    @Test
    void replace_throwsExceptionIfUserNotFound() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The replace("nonexistentID", new User("1", "test", "test@example.com", "password")) method is called.
        // THEN: A RuntimeException should be thrown with the message "Data does not exist."
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentID", new User("1", "test", "test@example.com", "password")));
    }

    @Test
    void removeById_returnsTrueIfRemovalSuccessful() {
        // GIVEN: A user with a specific ID exists in the repository.
        User user = new User("1", "test", "test@example.com", "password");
        repository.users.add(user);

        // WHEN: The removeById("1") method is called.
        // THEN: True should be returned, and the user with the specified ID should be removed from the repository.
        boolean removed = repository.removeById("1");
        assertTrue(removed, "Removal should be successful");
        assertEquals(0, repository.users.size(), "Repository size should remain unchanged");
    }

    @Test
    void removeById_returnsTrueIfNoChangeOccurred() {
        // GIVEN: No user exists in the repository with the specified ID.
        // WHEN: The removeById("nonexistentID") method is called.
        // THEN: True should be returned, and the repository should remain unchanged.
        boolean removed = repository.removeById("nonexistentID");
        assertTrue(removed, "Removal should be successful");
        assertEquals(1, repository.users.size(), "Repository size should remain unchanged");
    }
}