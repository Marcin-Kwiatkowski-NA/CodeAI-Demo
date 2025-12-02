package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void givenNewIdRequest_whenCalled_thenReturnsUniqueId() {
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
    void givenEmailExists_whenFindByEmailCalled_thenReturnsUser() {
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
    void givenEmailDoesNotExist_whenFindByEmailCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void givenIdExists_whenFindByIdCalled_thenReturnsUser() {
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
    void givenIdDoesNotExist_whenFindByIdCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findById("nonexistentId");

        // THEN
        assertThat(foundUser).isNull();
    }

    @Test
    void givenValidUser_whenInsertCalled_thenUserIsAdded() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo("1");
        assertThat(repository.findById("1")).isEqualTo(user);
    }

    @Test
    void givenExistingId_whenReplaceCalled_thenUserIsReplaced() {
        // GIVEN
        User originalUser = new User("1", "originalUser", "original@example.com", "password");
        repository.insert(originalUser);
        User newUser = new User("1", "newUser", "new@example.com", "newPassword");

        // WHEN
        repository.replace("1", newUser);

        // THEN
        User replacedUser = repository.findById("1");
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getUsername()).isEqualTo("newUser");
        assertThat(replacedUser.getEmail()).isEqualTo("new@example.com");
    }

    @Test
    void givenNonexistentId_whenReplaceCalled_thenThrowsRuntimeException() {
        // GIVEN
        User newUser = new User("1", "newUser", "new@example.com", "newPassword");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("nonexistentId", newUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void givenExistingId_whenRemoveByIdCalled_thenUserIsRemoved() {
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
    void givenNonexistentId_whenRemoveByIdCalled_thenReturnsTrue() {
        // GIVEN
        // No specific setup required

        // WHEN
        boolean result = repository.removeById("nonexistentId");

        // THEN
        assertThat(result).isTrue();
    }
}
