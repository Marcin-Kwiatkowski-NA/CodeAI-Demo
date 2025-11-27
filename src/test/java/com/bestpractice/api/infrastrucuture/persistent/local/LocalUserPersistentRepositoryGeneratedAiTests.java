package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No specific setup required

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password123");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void findByEmail_shouldReturnNull_whenEmailDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password123");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById(user.getId());

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user.getId());
    }

    @Test
    void findById_shouldReturnNull_whenIdDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findById("nonexistent-id");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void insert_shouldAddUserToRepository() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password123");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(repository.findById(user.getId())).isEqualTo(user);
    }

    @Test
    void replace_shouldUpdateUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password123");
        repository.insert(user);
        User updatedUser = new User(user.getId(), "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        repository.replace(user.getId(), updatedUser);

        // THEN
        User foundUser = repository.findById(user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("updatedUser");
        assertThat(foundUser.getEmail()).isEqualTo("updated@example.com");
    }

    @Test
    void replace_shouldThrowException_whenIdDoesNotExist() {
        // GIVEN
        User updatedUser = new User("nonexistent-id", "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        Throwable thrown = org.assertj.core.api.Assertions.catchThrowable(() -> repository.replace("nonexistent-id", updatedUser));

        // THEN
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Data does not exist.");
    }

    @Test
    void removeById_shouldRemoveUser_whenIdExists() {
        // GIVEN
        User user = new User(UUID.randomUUID().toString(), "testUser", "test@example.com", "password123");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(user.getId());

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById(user.getId())).isNull();
    }

    @Test
    void removeById_shouldReturnTrue_whenIdDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        boolean result = repository.removeById("nonexistent-id");

        // THEN
        assertThat(result).isTrue();
    }
}
