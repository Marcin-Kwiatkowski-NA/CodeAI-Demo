package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
        User user = new User("1", "john", "john@example.com", "password");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsUserIfExists() {
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
    void testFindByEmailReturnsNullIfNotExists() {
        // GIVEN
        // No user inserted

        // WHEN
        User found = repository.findByEmail("missing@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserIfExists() {
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
    void testFindByIdReturnsNullIfNotExists() {
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

        // THEN
        User found = repository.findById("4");
        assertNotNull(found);
        assertEquals("new", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionIfUserNotExists() {
        // GIVEN
        User user = new User("5", "ghost", "ghost@example.com", "password");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("5", user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
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
    void testRemoveByIdReturnsTrueIfUserNotExists() {
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
        User user = new User("7", "nullEmailUser", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(null, "nullIdUser", "nullid@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "noIdUser", "noid@example.com", "password");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(null, user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("8", "user8", "user8@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
        assertNotNull(repository.findById("8"));
    }
}
