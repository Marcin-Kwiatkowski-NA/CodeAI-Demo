package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertNotNull(id1);
        assertNotNull(id2);
        assertTrue(!id1.equals(id2));
        assertNotNull(UUID.fromString(id1));
    }

    @Test
    void testInsertAddsUserSuccessfully() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsUserWhenExists() {
        // GIVEN
        User user = new User("2", "jane", "jane@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("jane@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("jane@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotExists() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findByEmail("missing@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserWhenExists() {
        // GIVEN
        User user = new User("3", "mark", "mark@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById("3");

        // THEN
        assertNotNull(found);
        assertEquals("3", found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findById("nonexistent");

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User oldUser = new User("4", "old", "old@example.com", "password");
        repository.insert(oldUser);
        User newUser = new User("4", "new", "new@example.com", "newpass");

        // WHEN
        repository.replace("4", newUser);
        User found = repository.findById("4");

        // THEN
        assertNotNull(found);
        assertEquals("new", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionWhenUserNotExists() {
        // GIVEN
        User user = new User("5", "ghost", "ghost@example.com", "password");

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> repository.replace("5", user));
    }

    @Test
    void testRemoveByIdRemovesUserSuccessfully() {
        // GIVEN
        User user = new User("6", "remove", "remove@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("6");

        // THEN
        assertTrue(result);
        assertNull(repository.findById("6"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenUserNotExists() {
        // GIVEN
        // No user inserted

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertTrue(result);
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("7", "nullmail", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(null, "noid", "noid@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceWithNullIdThrowsException() {
        // GIVEN
        User user = new User("8", "alpha", "alpha@example.com", "password");
        repository.insert(user);
        User newUser = new User("8", "beta", "beta@example.com", "newpass");

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> repository.replace(null, newUser));
    }

    @Test
    void testRemoveByIdWithNullIdReturnsTrue() {
        // GIVEN
        User user = new User("9", "gamma", "gamma@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
