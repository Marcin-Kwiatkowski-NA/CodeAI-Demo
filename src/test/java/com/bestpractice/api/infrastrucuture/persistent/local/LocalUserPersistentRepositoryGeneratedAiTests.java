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
        // repository initialized

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
        User user = new User("1", "testuser", "test@example.com", "password");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsUserIfExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("test@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("test@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullIfNotExists() {
        // GIVEN
        // repository empty

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("1", "testuser", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserIfExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("1", found.getId());
    }

    @Test
    void testFindByIdReturnsNullIfNotExists() {
        // GIVEN
        // repository empty

        // WHEN
        User found = repository.findById("nonexistent");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User user = new User("1", "olduser", "old@example.com", "oldpass");
        repository.insert(user);
        User newUser = new User("1", "newuser", "new@example.com", "newpass");

        // WHEN
        repository.replace("1", newUser);
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("newuser", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionIfUserNotExists() {
        // GIVEN
        User newUser = new User("1", "newuser", "new@example.com", "newpass");

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("1", newUser));
        assertTrue(exception.getMessage().contains("Data does not exist"));
    }

    @Test
    void testRemoveByIdRemovesUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");
        User found = repository.findById("1");

        // THEN
        assertTrue(result);
        assertNull(found);
    }

    @Test
    void testRemoveByIdReturnsTrueIfUserNotExists() {
        // GIVEN
        // repository empty

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertTrue(result);
    }

    @Test
    void testReplaceHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User newUser = new User("2", "newuser", "new@example.com", "newpass");

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(null, newUser));
        assertTrue(exception.getMessage().contains("Data does not exist"));
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
