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
    void newId_ShouldReturnUniqueNonNullString() {
        // GIVEN
        // repository already initialized

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void insert_ShouldAddUserAndReturnSameUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertThat(inserted).isSameAs(user);
        User found = repository.findById("1");
        assertThat(found).isSameAs(user);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        User user = new User("2", "alice", "alice@example.com", "secret");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("alice@example.com");

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void findByEmail_ShouldReturnNull_WhenUserDoesNotExist() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        User user = new User("3", "bob", "bob@example.com", "pwd");
        repository.insert(user);

        // WHEN
        User found = repository.findById("3");

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void findById_ShouldReturnNull_WhenUserDoesNotExist() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findById("999");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void replace_ShouldUpdateExistingUser() {
        // GIVEN
        User original = new User("4", "charlie", "charlie@example.com", "oldpass");
        repository.insert(original);
        User updated = new User("4", "charlie", "charlie@example.com", "newpass");

        // WHEN
        repository.replace("4", updated);

        // THEN
        User found = repository.findById("4");
        assertThat(found).isSameAs(updated);
        assertThat(found.getPassword()).isEqualTo("newpass");
    }

    @Test
    void replace_ShouldThrowRuntimeException_WhenUserDoesNotExist() {
        // GIVEN
        User newUser = new User("5", "dave", "dave@example.com", "pwd");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace("5", newUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void removeById_ShouldReturnTrueAndRemoveUser_WhenUserExists() {
        // GIVEN
        User user = new User("6", "eve", "eve@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById("6");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("6")).isNull();
    }

    @Test
    void removeById_ShouldReturnTrue_WhenUserDoesNotExist() {
        // GIVEN
        // repository is empty

        // WHEN
        boolean removed = repository.removeById("nonexistent");

        // THEN
        assertThat(removed).isTrue();
    }

    @Test
    void findByEmail_ShouldReturnNull_WhenEmailIsNull() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void findById_ShouldReturnNull_WhenIdIsNull() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void removeById_ShouldReturnTrue_WhenIdIsNull() {
        // GIVEN
        // repository is empty

        // WHEN
        boolean removed = repository.removeById(null);

        // THEN
        assertThat(removed).isTrue();
    }
}
