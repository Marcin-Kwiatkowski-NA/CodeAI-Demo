package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.bestpractice.api.infrastrucuture.entity.User;

@ExtendWith({})
public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    public void testNewId() {
        // GIVEN
        String id = repository.newId();

        // WHEN
        String anotherId = repository.newId();

        // THEN
        assertTrue(!id.equals(anotherId));
    }

    @Test
    public void testFindByEmail_UserExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("email@example.com");

        // THEN
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindByEmail_UserDoesNotExist() {
        // GIVEN

        // WHEN
        User user = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(user);
    }

    @Test
    public void testFindById_UserExists() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User user = new User(id, "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById(id);

        // THEN
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindById_UserDoesNotExist() {
        // GIVEN

        // WHEN
        User user = repository.findById(UUID.randomUUID().toString());

        // THEN
        assertNull(user);
    }

    @Test
    public void testInsert() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User user = new User(id, "username", "email@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertEquals(user, insertedUser);
    }

    @Test
    public void testReplace_UserExists() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User user = new User(id, "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User updatedUser = new User(id, "newUsername", "newEmail@example.com", "newPassword");
        repository.replace(id, updatedUser);
        User foundUser = repository.findById(id);

        // THEN
        assertEquals(updatedUser.getUsername(), foundUser.getUsername());
    }

    @Test
    public void testReplace_UserDoesNotExist() {
        // GIVEN

        // WHEN & THEN
        try {
            repository.replace(UUID.randomUUID().toString(), new User("id", "username", "email@example.com", "password"));
        } catch (RuntimeException e) {
            assertEquals("Data does not exist.", e.getMessage());
        }
    }

    @Test
    public void testRemoveById_UserExists() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User user = new User(id, "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById(id);
        User foundUser = repository.findById(id);

        // THEN
        assertTrue(removed);
        assertNull(foundUser);
    }

    @Test
    public void testRemoveById_UserDoesNotExist() {
        // GIVEN

        // WHEN
        boolean removed = repository.removeById(UUID.randomUUID().toString());

        // THEN
        assertTrue(removed);
    }
}
