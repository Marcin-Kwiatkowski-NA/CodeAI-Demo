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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        User user = new User("1", "john", "john@example.com", "password123");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsCorrectUser() {
        // GIVEN
        User user = new User("2", "jane", "jane@example.com", "password456");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("jane@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("jane", found.getUsername());
    }

    @Test
    void testFindByEmailReturnsNullIfNotFound() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("3", "nullEmailUser", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsCorrectUser() {
        // GIVEN
        User user = new User("4", "mark", "mark@example.com", "password789");
        repository.insert(user);

        // WHEN
        User found = repository.findById("4");

        // THEN
        assertNotNull(found);
        assertEquals("mark@example.com", found.getEmail());
    }

    @Test
    void testFindByIdReturnsNullIfNotFound() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findById("unknown");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("5", "nullIdUser", "nullid@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User oldUser = new User("6", "oldName", "old@example.com", "oldPass");
        repository.insert(oldUser);
        User newUser = new User("6", "newName", "new@example.com", "newPass");

        // WHEN
        repository.replace("6", newUser);
        User found = repository.findById("6");

        // THEN
        assertNotNull(found);
        assertEquals("newName", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionIfUserNotFound() {
        // GIVEN
        User user = new User("7", "ghost", "ghost@example.com", "ghostPass");

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("nonexistent", user));
        assertTrue(exception.getMessage().contains("Data does not exist"));
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        // GIVEN
        User user = new User("8", "removeMe", "remove@example.com", "removePass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("8");

        // THEN
        assertTrue(result);
        assertNull(repository.findById("8"));
    }

    @Test
    void testRemoveByIdReturnsTrueIfUserNotFound() {
        // GIVEN
        // No user inserted

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertTrue(result);
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("9", "nullIdRemove", "nullremove@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
