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
    void testNewIdReturnsNonNullAndUnique() {
        // GIVEN nothing

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testInsertAddsUser() {
        // GIVEN
        String id = repository.newId();
        User user = new User(id, "john_doe", "john@example.com", "secret");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertThat(inserted).isSameAs(user);
        assertThat(repository.findById(id)).isSameAs(user);
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        String id = repository.newId();
        User user = new User(id, "alice", "alice@example.com", "password");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("alice@example.com");

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        // GIVEN nothing inserted

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        String id = repository.newId();
        User user = new User(id, "bob", "bob@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById(id);

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN nothing inserted

        // WHEN
        User found = repository.findById("unknown-id");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testReplaceUpdatesUser() {
        // GIVEN
        String id = repository.newId();
        User original = new User(id, "original", "original@example.com", "oldpass");
        repository.insert(original);

        User replacement = new User(id, "replacement", "replacement@example.com", "newpass");

        // WHEN
        User result = repository.replace(id, replacement);

        // THEN
        assertThat(result).isNull(); // replace returns null
        User found = repository.findById(id);
        assertThat(found).isSameAs(replacement);
        assertThat(found.getUsername()).isEqualTo("replacement");
        assertThat(found.getEmail()).isEqualTo("replacement@example.com");
    }

    @Test
    void testReplaceThrowsRuntimeExceptionWhenNotFound() {
        // GIVEN
        String nonexistentId = "nonexistent-id";
        User replacement = new User(nonexistentId, "user", "user@example.com", "pass");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(nonexistentId, replacement))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdRemovesUser() {
        // GIVEN
        String id = repository.newId();
        User user = new User(id, "remove", "remove@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById(id);

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById(id)).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN nothing inserted

        // WHEN
        boolean removed = repository.removeById("unknown-id");

        // THEN
        assertThat(removed).isTrue();
    }
}
