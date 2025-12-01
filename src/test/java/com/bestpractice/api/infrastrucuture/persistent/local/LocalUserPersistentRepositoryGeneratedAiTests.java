package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertEquals(false, id1.isEmpty());
        assertEquals(false, id2.isEmpty());
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findByEmail_shouldReturnNull_whenEmailDoesNotExist() {
        // GIVEN

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById(user.getId());

        // THEN
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    void findById_shouldReturnNull_whenIdDoesNotExist() {
        // GIVEN

        // WHEN
        User foundUser = repository.findById("nonexistent-id");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void insert_shouldAddUserToRepository() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertEquals(user, repository.findById(user.getId()));
    }

    @Test
    void replace_shouldUpdateUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = new User(user.getId(), "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        repository.replace(user.getId(), updatedUser);

        // THEN
        User replacedUser = repository.findById(user.getId());
        assertEquals("updatedUser", replacedUser.getUsername());
        assertEquals("updated@example.com", replacedUser.getEmail());
    }

    @Test
    void replace_shouldThrowException_whenIdDoesNotExist() {
        // GIVEN
        User updatedUser = new User("nonexistent-id", "updatedUser", "updated@example.com", "newPassword");

        // WHEN THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("nonexistent-id", updatedUser));

        // THEN
        assertEquals("Data does not exist.", exception.getMessage());
    }

    @Test
    void removeById_shouldRemoveUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(user.getId());

        // THEN
        assertEquals(true, result);
        assertNull(repository.findById(user.getId()));
    }

    @Test
    void removeById_shouldReturnTrue_whenIdDoesNotExist() {
        // GIVEN

        // WHEN
        boolean result = repository.removeById("nonexistent-id");

        // THEN
        assertEquals(true, result);
    }
}
