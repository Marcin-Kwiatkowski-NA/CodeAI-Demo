package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
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
    void newId_ShouldGenerateUniqueId() {
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
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void findByEmail_ShouldReturnNull_WhenEmailDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById("1");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo("1");
    }

    @Test
    void findById_ShouldReturnNull_WhenIdDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findById("nonexistent-id");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void insert_ShouldAddUserToRepository() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(repository.findById("1")).isEqualTo(user);
    }

    @Test
    void replace_ShouldUpdateUser_WhenIdExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "updateduser", "updated@example.com", "newpassword");

        // WHEN
        repository.replace("1", updatedUser);

        // THEN
        User foundUser = repository.findById("1");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("updateduser");
        assertThat(foundUser.getEmail()).isEqualTo("updated@example.com");
    }

    @Test
    void replace_ShouldThrowException_WhenIdDoesNotExist() {
        // GIVEN
        User updatedUser = new User("nonexistent-id", "updateduser", "updated@example.com", "newpassword");

        // WHEN
        Throwable thrown = org.assertj.core.api.Assertions.catchThrowable(() -> repository.replace("nonexistent-id", updatedUser));

        // THEN
        assertThat(thrown).isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void removeById_ShouldRemoveUser_WhenIdExists() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("1")).isNull();
    }

    @Test
    void removeById_ShouldReturnTrue_WhenIdDoesNotExist() {
        // GIVEN
        // No specific setup required

        // WHEN
        boolean result = repository.removeById("nonexistent-id");

        // THEN
        assertThat(result).isTrue();
    }
}
