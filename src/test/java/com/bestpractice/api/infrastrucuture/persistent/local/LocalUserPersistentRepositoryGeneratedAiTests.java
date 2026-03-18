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
        // No preconditions

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
        User user = new User("1", "John", "john@example.com", "password");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testFindByEmailReturnsUserWhenExists() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("john@example.com");

        // THEN
        assertNotNull(found);
        assertEquals("john@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotExists() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("1", "John", null, "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdReturnsUserWhenExists() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("1", found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findById("999");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User(null, "John", "john@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "Johnny", "johnny@example.com", "newpassword");

        // WHEN
        repository.replace("1", updatedUser);
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("Johnny", found.getUsername());
        assertEquals("johnny@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionWhenUserNotFound() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("999", user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
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
        boolean result = repository.removeById("999");

        // THEN
        assertTrue(result);
    }

    @Test
    void testReplaceHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("2", "Johnny", "johnny@example.com", "newpassword");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(null, updatedUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("1", "John", "john@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
