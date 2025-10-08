package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    public void testNewIdGeneratesUniqueId() {
        // GIVEN no preconditions

        // WHEN generating a new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN the IDs should be non-null, non-empty, and unique
        assertNotNull(id1);
        assertNotNull(id2);
        assertFalse(id1.isEmpty());
        assertFalse(id2.isEmpty());
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    public void testInsertAndFindById() {
        // GIVEN a user to insert
        User user = new User("1", "username", "email@example.com", "password");

        // WHEN inserting and retrieving by ID
        repository.insert(user);
        User found = repository.findById("1");

        // THEN the retrieved user should match the inserted one
        assertNotNull(found);
        assertEquals("1", found.getId());
        assertEquals("username", found.getUsername());
    }

    @Test
    public void testFindByIdNotFoundReturnsNull() {
        // GIVEN no users in repository

        // WHEN searching for a non-existing ID
        User found = repository.findById("nonexistent");

        // THEN result should be null
        assertNull(found);
    }

    @Test
    public void testFindByEmail() {
        // GIVEN a user inserted into repository
        User user = new User("2", "username2", "email2@example.com", "password2");
        repository.insert(user);

        // WHEN searching by email
        User found = repository.findByEmail("email2@example.com");

        // THEN the retrieved user should match the inserted one
        assertNotNull(found);
        assertEquals("2", found.getId());
        assertEquals("email2@example.com", found.getEmail());
    }

    @Test
    public void testFindByEmailNotFoundReturnsNull() {
        // GIVEN no users in repository

        // WHEN searching for a non-existing email
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN result should be null
        assertNull(found);
    }

    @Test
    public void testReplaceExistingUser() {
        // GIVEN a user inserted into repository
        User user = new User("3", "username3", "email3@example.com", "password3");
        repository.insert(user);
        User newUser = new User("3", "newUsername", "newEmail@example.com", "newPassword");

        // WHEN replacing the user
        repository.replace("3", newUser);
        User found = repository.findById("3");

        // THEN the user should be updated
        assertNotNull(found);
        assertEquals("newUsername", found.getUsername());
        assertEquals("newEmail@example.com", found.getEmail());
    }

    @Test
    public void testReplaceNonExistingUserThrowsException() {
        // GIVEN no users in repository
        User newUser = new User("4", "username4", "email4@example.com", "password4");

        // WHEN replacing a non-existing user THEN expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("4", newUser));
    }

    @Test
    public void testRemoveByIdExistingUser() {
        // GIVEN a user inserted into repository
        User user = new User("5", "username5", "email5@example.com", "password5");
        repository.insert(user);

        // WHEN removing the user by ID
        boolean result = repository.removeById("5");

        // THEN the removal should be successful and user should not be found
        assertTrue(result);
        assertNull(repository.findById("5"));
    }

    @Test
    public void testRemoveByIdNonExistingUser() {
        // GIVEN no users in repository

        // WHEN removing a non-existing user
        boolean result = repository.removeById("nonexistent");

        // THEN the method should return true
        assertTrue(result);
    }
}
