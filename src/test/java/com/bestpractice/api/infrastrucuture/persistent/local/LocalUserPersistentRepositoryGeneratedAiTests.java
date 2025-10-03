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

        // THEN - assert they are not null and unique
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

        // WHEN - insert user
        User inserted = repository.insert(user);

        // THEN - assert returned user is same and can be found
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

        // THEN - assert found user matches
        assertNotNull(found);
        assertEquals("1", found.getId());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotExists() {
        // GIVEN - repository empty

        // WHEN - find by email
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN - assert null
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN - find by id
        User found = repository.findById("1");

        // THEN - assert found user matches
        assertNotNull(found);
        assertEquals("username", found.getUsername());
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        // GIVEN - repository empty

        // WHEN - find by id
        User found = repository.findById("nonexistent");

        // THEN - assert null
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "newUsername", "email@example.com", "password");

        // WHEN - replace user
        repository.replace("1", updatedUser);

        // THEN - assert user updated
        User found = repository.findById("1");
        assertEquals("newUsername", found.getUsername());
    }

    @Test
    void testReplaceThrowsWhenUserNotExists() {
        // GIVEN - repository empty
        User user = new User("1", "username", "email@example.com", "password");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("1", user));
    }

    @Test
    void testRemoveByIdRemovesUserWhenExists() {
        // GIVEN - insert a user
        User user = new User("1", "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN - remove by id
        boolean result = repository.removeById("1");

        // THEN - assert removal successful and user no longer exists
        assertTrue(result);
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenUserNotExists() {
        // GIVEN - repository empty

        // WHEN - remove by id
        boolean result = repository.removeById("nonexistent");

        // THEN - assert true
        assertTrue(result);
    }
}
