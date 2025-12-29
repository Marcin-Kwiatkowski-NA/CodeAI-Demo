package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private UserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void shouldGenerateNewId() {
        // GIVEN
        // WHEN
        String generatedId = repository.newId();

        // THEN
        assertNotNull(generatedId);
        assertFalse(generatedId.isEmpty());
        assertEquals(36, generatedId.length()); // UUID format
    }

    @Test
    void shouldFindUserByEmailWhenUserExists() {
        // GIVEN
        User existingUser = new User("user1", "john", "john@example.com", "password123");
        repository.insert(existingUser);

        // WHEN
        User foundUser = repository.findByEmail("john@example.com");

        // THEN
        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getId());
        assertEquals("john", foundUser.getUsername());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void shouldFindUserByEmailWhenUserDoesNotExist() {
        // GIVEN
        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void shouldFindUserByIdWhenUserExists() {
        // GIVEN
        User existingUser = new User("user1", "john", "john@example.com", "password123");
        repository.insert(existingUser);

        // WHEN
        User foundUser = repository.findById("user1");

        // THEN
        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getId());
        assertEquals("john", foundUser.getUsername());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void shouldFindUserByIdWhenUserDoesNotExist() {
        // GIVEN
        // WHEN
        User foundUser = repository.findById("nonexistent-id");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void shouldInsertUserSuccessfully() {
        // GIVEN
        User newUser = new User("user1", "alice", "alice@example.com", "securepass");

        // WHEN
        User insertedUser = repository.insert(newUser);

        // THEN
        assertNotNull(insertedUser);
        assertEquals("user1", insertedUser.getId());
        assertEquals("alice", insertedUser.getUsername());
        assertEquals("alice@example.com", insertedUser.getEmail());
    }

    @Test
    void shouldReplaceUserSuccessfully() {
        // GIVEN
        User originalUser = new User("user1", "john", "john@example.com", "password123");
        repository.insert(originalUser);

        User updatedUser = new User("user1", "jane", "jane@example.com", "newpassword");

        // WHEN
        User replacedUser = repository.replace("user1", updatedUser);

        // THEN
        assertNull(replacedUser);
        assertNotNull(repository.findByEmail("jane@example.com"));
        assertNull(repository.findByEmail("john@example.com"));
    }

    @Test
    void shouldThrowExceptionWhenReplacingNonExistentUser() {
        // GIVEN
        User userToReplace = new User("user1", "john", "john@example.com", "password123");
        repository.insert(userToReplace);

        // WHEN
        assertThrows(RuntimeException.class, () -> {
            repository.replace("nonexistent-id", new User("new-id", "new-user", "new@example.com", "newpass"));
        });
    }

    @Test
    void shouldRemoveUserByIdSuccessfully() {
        // GIVEN
        User userToBeRemoved = new User("user1", "john", "john@example.com", "password123");
        repository.insert(userToBeRemoved);

        // WHEN
        boolean result = repository.removeById("user1");

        // THEN
        assertTrue(result);
        assertNull(repository.findByEmail("john@example.com"));
    }

    @Test
    void shouldReturnTrueWhenRemovingNonExistentUser() {
        // GIVEN
        // WHEN
        boolean result = repository.removeById("nonexistent-id");

        // THEN
        assertTrue(result);
    }
}
