package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        User user = new User(repository.newId(), "john", "john@example.com", "password123");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById(user.getId()));
    }

    @Test
    void testFindByEmailReturnsUserIfExists() {
        // GIVEN
        User user = new User(repository.newId(), "alice", "alice@example.com", "securePass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("alice@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("alice@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullIfNotExists() {
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
        User user = new User(repository.newId(), "bob", null, "pass123");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserIfExists() {
        // GIVEN
        User user = new User(repository.newId(), "bob", "bob@example.com", "pass123");
        repository.insert(user);

        // WHEN
        User found = repository.findById(user.getId());

        // THEN
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }

    @Test
    void testFindByIdReturnsNullIfNotExists() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findById("unknown-id");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(repository.newId(), "charlie", "charlie@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User original = new User(repository.newId(), "charlie", "charlie@example.com", "oldPass");
        repository.insert(original);
        User updated = new User(original.getId(), "charlieUpdated", "charlie@example.com", "newPass");

        // WHEN
        repository.replace(original.getId(), updated);
        User found = repository.findById(original.getId());

        // THEN
        assertNotNull(found);
        assertEquals("charlieUpdated", found.getUsername());
        assertEquals("newPass", found.getPassword());
    }

    @Test
    void testReplaceThrowsExceptionIfUserNotExists() {
        // GIVEN
        User user = new User("nonexistent-id", "ghost", "ghost@example.com", "nopass");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("nonexistent-id", user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        // GIVEN
        User user = new User(repository.newId(), "david", "david@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(user.getId());
        User found = repository.findById(user.getId());

        // THEN
        assertTrue(result);
        assertNull(found);
    }

    @Test
    void testRemoveByIdReturnsTrueIfUserNotExists() {
        // GIVEN
        String nonExistingId = "non-existing-id";

        // WHEN
        boolean result = repository.removeById(nonExistingId);

        // THEN
        assertTrue(result);
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(repository.newId(), "eve", "eve@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }

    @Test
    void testReplaceHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(repository.newId(), "frank", "frank@example.com", "pass");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(null, user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceHandlesEmptyUserListGracefully() {
        // GIVEN
        User user = new User(repository.newId(), "george", "george@example.com", "pass");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }
}
