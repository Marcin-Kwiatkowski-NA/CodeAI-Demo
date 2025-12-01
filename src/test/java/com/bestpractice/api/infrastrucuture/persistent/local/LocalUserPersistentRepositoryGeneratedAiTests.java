package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No preconditions required

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
        User user = new User("1", "testUser", "test@example.com", "password");
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
        // No users added

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById("1");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo("1");
    }

    @Test
    void findById_shouldReturnNull_whenIdDoesNotExist() {
        // GIVEN
        // No users added

        // WHEN
        User foundUser = repository.findById("nonexistentId");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void insert_shouldAddUserToRepository() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(repository.findById("1")).isEqualTo(user);
    }

    @Test
    void replace_shouldUpdateUser_whenIdExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        repository.replace("1", updatedUser);

        // THEN
        User foundUser = repository.findById("1");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("updatedUser");
        assertThat(foundUser.getEmail()).isEqualTo("updated@example.com");
    }

    @Test
    void replace_shouldThrowException_whenIdDoesNotExist() {
        // GIVEN
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("nonexistentId", updatedUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void removeById_shouldRemoveUser_whenIdExists() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("1")).isNull();
    }

    @Test
    void removeById_shouldReturnTrue_whenIdDoesNotExist() {
        // GIVEN
        // No users added

        // WHEN
        boolean result = repository.removeById("nonexistentId");

        // THEN
        assertThat(result).isTrue();
    }
}
