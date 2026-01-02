package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void newId_ShouldReturnUniqueNonNullValues() {
        // GIVEN
        // (none)

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void findByEmail_WhenUserExists_ShouldReturnUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("john@example.com");

        // THEN
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo("1");
        assertThat(found.getUsername()).isEqualTo("john");
    }

    @Test
    void findByEmail_WhenUserDoesNotExist_ShouldReturnNull() {
        // GIVEN
        // (none)

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void findByEmail_WhenEmailIsNull_ShouldReturnNull() {
        // GIVEN
        // (none)

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUser() {
        // GIVEN
        User user = new User("2", "alice", "alice@example.com", "secret");
        repository.insert(user);

        // WHEN
        User found = repository.findById("2");

        // THEN
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("alice");
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldReturnNull() {
        // GIVEN
        // (none)

        // WHEN
        User found = repository.findById("unknown");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void findById_WhenIdIsNull_ShouldReturnNull() {
        // GIVEN
        // (none)

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void insert_ShouldAddUserAndReturnIt() {
        // GIVEN
        User user = new User("3", "bob", "bob@example.com", "pwd");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertThat(inserted).isSameAs(user);
        assertThat(repository.findById("3")).isSameAs(user);
    }

    @Test
    void replace_WhenUserExists_ShouldReplaceAndReturnNull() {
        // GIVEN
        User original = new User("4", "charlie", "charlie@example.com", "oldpwd");
        repository.insert(original);
        User replacement = new User("4", "charlie", "charlie@example.com", "newpwd");

        // WHEN
        User result = repository.replace("4", replacement);

        // THEN
        assertThat(result).isNull();
        User found = repository.findById("4");
        assertThat(found).isNotNull();
        assertThat(found.getPassword()).isEqualTo("newpwd");
    }

    @Test
    void replace_WhenUserDoesNotExist_ShouldThrowRuntimeException() {
        // GIVEN
        User replacement = new User("5", "dave", "dave@example.com", "pwd");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace("5", replacement))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void replace_WhenIdIsNull_ShouldThrowRuntimeException() {
        // GIVEN
        User replacement = new User("6", "eve", "eve@example.com", "pwd");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(null, replacement))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void removeById_WhenUserExists_ShouldReturnTrueAndRemoveUser() {
        // GIVEN
        User user = new User("7", "frank", "frank@example.com", "pwd");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById("7");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("7")).isNull();
    }

    @Test
    void removeById_WhenUserDoesNotExist_ShouldReturnTrue() {
        // GIVEN
        // (none)

        // WHEN
        boolean removed = repository.removeById("nonexistent");

        // THEN
        assertThat(removed).isTrue();
    }

    @Test
    void removeById_WhenIdIsNull_ShouldReturnTrue() {
        // GIVEN
        // (none)

        // WHEN
        boolean removed = repository.removeById(null);

        // THEN
        assertThat(removed).isTrue();
    }
}
