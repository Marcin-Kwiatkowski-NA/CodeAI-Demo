package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        // No setup required

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
    void testInsertAndFindById() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");

        // WHEN
        repository.insert(user);
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("John", found.getUsername());
        assertEquals("john@example.com", found.getEmail());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findById("nonexistent");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByEmailReturnsUserWhenExists() {
        // GIVEN
        User user = new User("1", "Alice", "alice@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("alice@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("1", found.getId());
        assertEquals("Alice", found.getUsername());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findByEmail("missing@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User user = new User("1", "Bob", "bob@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "Bobby", "bob@example.com", "newpass");

        // WHEN
        repository.replace("1", updatedUser);
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("Bobby", found.getUsername());
        assertEquals("newpass", found.getPassword());
    }

    @Test
    void testReplaceThrowsExceptionWhenUserNotFound() {
        // GIVEN
        User user = new User("1", "Charlie", "charlie@example.com", "password");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("nonexistent", user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        // GIVEN
        User user = new User("1", "David", "david@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");
        User found = repository.findById("1");

        // THEN
        assertTrue(result);
        assertNull(found);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenUserNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertTrue(result);
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("1", "Eve", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(null, "Frank", "frank@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceWithNullIdThrowsException() {
        // GIVEN
        User user = new User("1", "Greg", "greg@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("2", "Gregory", "greg@example.com", "newpass");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(null, updatedUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdWithNullIdReturnsTrue() {
        // GIVEN
        User user = new User("1", "Helen", "helen@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
