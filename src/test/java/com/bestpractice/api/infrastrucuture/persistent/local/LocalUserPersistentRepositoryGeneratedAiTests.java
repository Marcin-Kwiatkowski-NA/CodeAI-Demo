package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void givenNothing_whenNewId_thenReturnsNonNullUniqueId() {
        // GIVEN - nothing to set up

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
    }

    @Test
    void givenExistingUser_whenFindByEmail_thenReturnsUser() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("email@example.com");

        // THEN
        assertNotNull(found);
        assertEquals(user.getEmail(), found.getEmail());
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenReturnsNull() {
        // GIVEN - repository is empty

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void givenNullEmail_whenFindByEmail_thenReturnsNull() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void givenExistingUser_whenFindById_thenReturnsUser() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(user.getId());

        // THEN
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }

    @Test
    void givenNonExistingId_whenFindById_thenReturnsNull() {
        // GIVEN - repository is empty

        // WHEN
        User found = repository.findById(UUID.randomUUID().toString());

        // THEN
        assertNull(found);
    }

    @Test
    void givenNullId_whenFindById_thenReturnsNull() {
        // GIVEN
        User user = new User(null, "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void givenUser_whenInsert_thenUserIsStored() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById(user.getId()));
    }

    @Test
    void givenExistingUser_whenReplace_thenUserIsUpdated() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User original = new User(id, "username", "email@example.com", "password");
        repository.insert(original);
        User updated = new User(id, "newUsername", "newemail@example.com", "newpassword");

        // WHEN
        User result = repository.replace(id, updated);

        // THEN
        assertNull(result);
        User found = repository.findById(id);
        assertEquals("newUsername", found.getUsername());
        assertEquals("newemail@example.com", found.getEmail());
    }

    @Test
    void givenNonExistingUser_whenReplace_thenThrowsException() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> repository.replace(user.getId(), user));
    }

    @Test
    void givenExistingUser_whenRemoveById_thenUserIsRemoved() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        User user = new User(id, "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById(id);

        // THEN
        assertTrue(removed);
        assertNull(repository.findById(id));
    }

    @Test
    void givenNonExistingUser_whenRemoveById_thenReturnsTrue() {
        // GIVEN - repository is empty

        // WHEN
        boolean removed = repository.removeById(UUID.randomUUID().toString());

        // THEN
        assertTrue(removed);
    }

    @Test
    void givenNullId_whenRemoveById_thenReturnsTrueAndDoesNotRemoveExistingUser() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "username", "email@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById(null);

        // THEN
        assertTrue(removed);
        assertNotNull(repository.findById(user.getId()));
    }
}
