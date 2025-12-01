package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewId() {
        // GIVEN
        // No preconditions needed

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertEquals(36, id.length()); // UUID length is 36
    }

    @Test
    void testFindByEmailWhenUserExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void testFindByEmailWhenUserDoesNotExist() {
        // GIVEN
        // No users added to the repository

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void testFindByIdWhenUserExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById("1");

        // THEN
        assertNotNull(foundUser);
        assertEquals("1", foundUser.getId());
    }

    @Test
    void testFindByIdWhenUserDoesNotExist() {
        // GIVEN
        // No users added to the repository

        // WHEN
        User foundUser = repository.findById("nonexistentId");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void testInsert() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertNotNull(insertedUser);
        assertEquals("1", insertedUser.getId());
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testReplaceWhenUserExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        repository.replace("1", updatedUser);

        // THEN
        User replacedUser = repository.findById("1");
        assertNotNull(replacedUser);
        assertEquals("updatedUser", replacedUser.getUsername());
        assertEquals("updated@example.com", replacedUser.getEmail());
    }

    @Test
    void testReplaceWhenUserDoesNotExist() {
        // GIVEN
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("nonexistentId", updatedUser));

        // THEN
        assertEquals("Data does not exist.", exception.getMessage());
    }

    @Test
    void testRemoveByIdWhenUserExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertEquals(true, result);
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdWhenUserDoesNotExist() {
        // GIVEN
        // No users added to the repository

        // WHEN
        boolean result = repository.removeById("nonexistentId");

        // THEN
        assertEquals(true, result);
    }

    @Test
    void testFindByEmailHandlesNullInput() {
        // GIVEN
        // No users added to the repository

        // WHEN
        User foundUser = repository.findByEmail(null);

        // THEN
        assertNull(foundUser);
    }

    @Test
    void testFindByIdHandlesNullInput() {
        // GIVEN
        // No users added to the repository

        // WHEN
        User foundUser = repository.findById(null);

        //        // THEN
        assertNull(foundUser);
    }

    @Test
    void testReplaceHandlesNullInput() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(null, user));

        // THEN
        assertEquals("Data does not exist.", exception.getMessage());
    }

    @Test
    void testRemoveByIdHandlesNullInput() {
        // GIVEN
        // No users added to the repository

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertEquals(true, result);
    }
}
