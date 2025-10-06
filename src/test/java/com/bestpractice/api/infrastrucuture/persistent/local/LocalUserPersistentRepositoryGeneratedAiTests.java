package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN - prepare repository

        // WHEN - generate two IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - IDs should be unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testInsertAddsUser() {
        // GIVEN - create a user
        User user = new User("1", "username", "email@example.com", "password");

        // WHEN - insert the user
        User inserted = repository.insert(user);

        // THEN - inserted user should be same as provided
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN - find by email
        User found = repository.findByEmail("email@example.com");

        // THEN - found user should match
        assertNotNull(found);
        assertEquals("1", found.getId());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotExists() {
        // GIVEN - repository is empty

        // WHEN - find by email
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN - should return null
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN - find by id
        User found = repository.findById("1");

        // THEN - found user should match
        assertNotNull(found);
        assertEquals("username", found.getUsername());
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        // GIVEN - repository is empty

        // WHEN - find by id
        User found = repository.findById("nonexistent");

        // THEN - should return null
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);
        User newUser = new User("1", "newUsername", "newemail@example.com", "newpassword");

        // WHEN - replace the user
        repository.replace("1", newUser);

        // THEN - user should be updated
        User found = repository.findById("1");
        assertEquals("newUsername", found.getUsername());
        assertEquals("newemail@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionWhenNotExists() {
        // GIVEN - repository is empty
        User newUser = new User("1", "newUsername", "newemail@example.com", "newpassword");

        // WHEN & THEN - replace should throw exception
        assertThrows(RuntimeException.class, () -> repository.replace("1", newUser));
    }

    @Test
    void testRemoveByIdRemovesUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN - remove by id
        boolean result = repository.removeById("1");

        // THEN - should return true and user should be removed
        assertTrue(result);
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotExists() {
        // GIVEN - repository is empty

        // WHEN - remove by id
        boolean result = repository.removeById("nonexistent");

        // THEN - should return true
        assertTrue(result);
    }
}
