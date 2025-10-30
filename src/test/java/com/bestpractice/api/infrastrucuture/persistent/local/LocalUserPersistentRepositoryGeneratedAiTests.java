package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN no specific setup

        // WHEN generating a new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN the IDs should be unique and non-null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testInsertAndFindById() {
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
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN no users in repository

        // WHEN searching for a non-existing ID
        User found = repository.findById("nonexistent");

        // THEN result should be null
        assertNull(found);
    }

    @Test
    void testFindByEmail() {
        // GIVEN a user inserted into repository
        User user = new User("2", "user2", "test@example.com", "password");
        repository.insert(user);

        // WHEN searching by email
        User found = repository.findByEmail("test@example.com");

        // THEN the correct user should be returned
        assertNotNull(found);
        assertEquals("2", found.getId());
        assertEquals("test@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        // GIVEN no users in repository

        // WHEN searching for a non-existing email
        User found = repository.findByEmail("missing@example.com");

        // THEN result should be null
        assertNull(found);
    }

    @Test
    void testReplaceExistingUser() {
        // GIVEN a user inserted into repository
        User original = new User("3", "original", "orig@example.com", "pass");
        repository.insert(original);
        User replacement = new User("3", "updated", "updated@example.com", "newpass");

        // WHEN replacing the user
        User result = repository.replace("3", replacement);

        // THEN the replace method should return null and the user should be updated
        assertNull(result);
        User found = repository.findById("3");
        assertNotNull(found);
        assertEquals("updated", found.getUsername());
    }

    @Test
    void testReplaceNonExistingUserThrowsException() {
        // GIVEN no users in repository
        User replacement = new User("4", "updated", "updated@example.com", "newpass");

        // WHEN replacing a non-existing user THEN expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("4", replacement));
    }

    @Test
    void testRemoveByIdExistingUser() {
        // GIVEN a user inserted into repository
        User user = new User("5", "user5", "user5@example.com", "pass");
        repository.insert(user);

        // WHEN removing the user by ID
        boolean result = repository.removeById("5");

        // THEN the removal should be successful and user should not be found
        assertTrue(result);
        assertNull(repository.findById("5"));
    }

    @Test
    void testRemoveByIdNonExistingUser() {
        // GIVEN no users in repository

        // WHEN removing a non-existing user
        boolean result = repository.removeById("nonexistent");

        // THEN the method should return true
        assertTrue(result);
    }
}
