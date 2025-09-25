package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
    public void testInsertAndFindById() {
        // GIVEN a new user
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
        User user = new User("2", "user2", "test@example.com", "password");
        repository.insert(user);

        // WHEN searching by email
        User found = repository.findByEmail("test@example.com");

        // THEN the retrieved user should match
        assertNotNull(found);
        assertEquals("2", found.getId());
        assertEquals("test@example.com", found.getEmail());
    }

    @Test
    public void testFindByEmailNotFoundReturnsNull() {
        // GIVEN no users in repository

        // WHEN searching for a non-existing email
        User found = repository.findByEmail("missing@example.com");

        // THEN result should be null
        assertNull(found);
    }

    @Test
    public void testReplaceExistingUser() {
        // GIVEN a user in repository
        User user = new User("3", "user3", "email3@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("3", "updatedUser", "updated@example.com", "newpassword");

        // WHEN replacing the user
        User result = repository.replace("3", updatedUser);

        // THEN the replace method should return null and the user should be updated
        assertNull(result);
        User found = repository.findById("3");
        assertNotNull(found);
        assertEquals("updatedUser", found.getUsername());
        assertEquals("updated@example.com", found.getEmail());
    }

    @Test
    public void testReplaceNonExistingUserThrowsException() {
        // GIVEN no users in repository
        User user = new User("4", "user4", "email4@example.com", "password");

        // WHEN & THEN replacing should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("4", user));
    }

    @Test
    public void testRemoveByIdExistingUser() {
        // GIVEN a user in repository
        User user = new User("5", "user5", "email5@example.com", "password");
        repository.insert(user);

        // WHEN removing by ID
        boolean result = repository.removeById("5");

        // THEN removal should be successful and user should not be found
        assertTrue(result);
        assertNull(repository.findById("5"));
    }

    @Test
    public void testRemoveByIdNonExistingUser() {
        // GIVEN no users in repository

        // WHEN removing a non-existing user
        boolean result = repository.removeById("nonexistent");

        // THEN removal should return true
        assertTrue(result);
    }
}
