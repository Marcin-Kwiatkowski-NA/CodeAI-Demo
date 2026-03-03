package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.User;

class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewIdReturnsNonNullUnique() {
        // GIVEN
        // repository is initialized in setUp

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull().isNotEmpty();
        assertThat(id2).isNotNull().isNotEmpty();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testInsertAddsUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertThat(inserted).isSameAs(user);
        assertThat(repository.findById("1")).isSameAs(user);
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("john@example.com");

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByEmailReturnsNullWhenEmailIsNull() {
        // GIVEN
        User user = new User("1", "john", null, "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById("1");

        // THEN
        assertThat(found).isSameAs(user);
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // repository is empty

        // WHEN
        User found = repository.findById("nonexistent");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByIdReturnsNullWhenIdIsNull() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testReplaceUpdatesUser() {
        // GIVEN
        User original = new User("1", "john", "john@example.com", "pass");
        User updated = new User("1", "johnny", "johnny@example.com", "newpass");
        repository.insert(original);

        // WHEN
        User result = repository.replace(updated);

        // THEN
        assertThat(result).isNull();
        User found = repository.findById("1");
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("johnny");
        assertThat(found.getEmail()).isEqualTo("johnny@example.com");
        assertThat(found.getPassword()).isEqualTo("newpass");
    }

    @Test
    void testReplaceThrowsRuntimeExceptionWhenIdNotFound() {
        // GIVEN
        User updated = new User("1", "johnny", "johnny@example.com", "newpass");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(updated))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User with id 1 not found");
    }

    @Test
    void testRemoveByIdReturnsTrueAndRemovesUser() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean removed = repository.removeById("1");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("1")).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenIdNotFound() {
        // GIVEN
        // repository is empty

        // WHEN
        boolean removed = repository.removeById("nonexistent");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("nonexistent")).isNull();
    }
}
