package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testNewIdBoundaryFormat() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id.length()).isEqualTo(36);
        assertThat(id).contains("-");
    }

    @Test
    void testInsertAddsUserSuccessfully() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testInsertHandlesNullUserGracefully() {
        // GIVEN
        User user = null;

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testFindByEmailReturnsCorrectUser() {
        // GIVEN
        User user = new User("2", "jane", "jane@example.com", "password456");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("jane@example.com");

        // THEN
        assertThat(found).isNotNull();
        assertEquals("jane", found.getUsername());
    }

    @Test
    void testFindByEmailEmptyStringBoundary() {
        // GIVEN
        User user = new User("emptyEmail", "emptyUser", "", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("");

        // THEN
        assertEquals(user, found);
    }

    @Test
    void testFindByEmailWhitespaceBoundary() {
        // GIVEN
        User user = new User("spaceEmail", "spaceUser", " ", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(" ");

        // THEN
        assertEquals(user, found);
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findByEmail("missing@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        // GIVEN
        User user = new User("10", "nullEmailUser", null, "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByIdReturnsCorrectUser() {
        // GIVEN
        User user = new User("3", "mark", "mark@example.com", "password789");
        repository.insert(user);

        // WHEN
        User found = repository.findById("3");

        // THEN
        assertThat(found).isNotNull();
        assertEquals("mark@example.com", found.getEmail());
    }

    @Test
    void testFindByIdEmptyStringBoundary() {
        // GIVEN
        User user = new User("", "emptyIdUser", "emptyid@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById("");

        // THEN
        assertEquals(user, found);
    }

    @Test
    void testFindByIdWhitespaceBoundary() {
        // GIVEN
        User user = new User(" ", "spaceIdUser", "spaceid@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById(" ");

        // THEN
        assertEquals(user, found);
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findById("unknown");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("11", "nullIdUser", "nullid@example.com", "pass");
        repository.insert(user);

        // WHEN
        User found = repository.findById(null);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        // GIVEN
        User oldUser = new User("4", "oldUser", "old@example.com", "oldPass");
        repository.insert(oldUser);
        User newUser = new User("4", "newUser", "new@example.com", "newPass");

        // WHEN
        repository.replace("4", newUser);
        User found = repository.findById("4");

        // THEN
        assertThat(found).isNotNull();
        assertEquals("newUser", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionWhenUserNotFound() {
        // GIVEN
        User user = new User("5", "ghost", "ghost@example.com", "ghostPass");

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("nonexistent", user));
        assertThat(exception.getMessage()).isEqualTo("Data does not exist.");
    }

    @Test
    void testReplaceThrowsExceptionWhenIdIsNull() {
        // GIVEN
        User user = new User("6", "nullId", "nullid@example.com", "pass");

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(null, user));
        assertThat(exception.getMessage()).isEqualTo("Data does not exist.");
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        // GIVEN
        User user = new User("7", "removeMe", "remove@example.com", "removePass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("7");
        User found = repository.findById("7");

        // THEN
        assertThat(result).isTrue();
        assertThat(found).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenUserNotFound() {
        // GIVEN
        // No users inserted

        // WHEN
        boolean result = repository.removeById("unknown");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        User user = new User("8", "nullIdRemove", "nullremove@example.com", "pass");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testBoundaryEmptyRepositoryFindByEmail() {
        // GIVEN
        // Empty repository

        // WHEN
        User found = repository.findByEmail("any@example.com");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testBoundaryEmptyRepositoryFindById() {
        // GIVEN
        // Empty repository

        // WHEN
        User found = repository.findById("anyId");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testBoundaryEmptyRepositoryRemoveById() {
        // GIVEN
        // Empty repository

        // WHEN
        boolean result = repository.removeById("anyId");

        // THEN
        assertThat(result).isTrue();
    }
}
