package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalUserPersistentRepositoryGeneratedAiTests {

    private LocalUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalUserPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueIds() {
        String id1 = repository.newId();
        String id2 = repository.newId();
        Assertions.assertThat(id1).isNotNull();
        Assertions.assertThat(id2).isNotNull();
        Assertions.assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testInsertAndFindById() {
        String id = UUID.randomUUID().toString();
        User user = new User(id, "user1", "user1@example.com", "password");
        repository.insert(user);
        User found = repository.findById(id);
        Assertions.assertThat(found).isNotNull();
        assertEquals(id, found.getId());
        assertEquals("user1", found.getUsername());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        String id = UUID.randomUUID().toString();
        User found = repository.findById(id);
        Assertions.assertThat(found).isNull();
    }

    @Test
    void testFindByEmailReturnsUserWhenExists() {
        User user = new User(UUID.randomUUID().toString(), "user2", "user2@example.com", "password");
        repository.insert(user);
        User found = repository.findByEmail("user2@example.com");
        Assertions.assertThat(found).isNotNull();
        assertEquals("user2@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailReturnsNullWhenNotFound() {
        User found = repository.findByEmail("nonexistent@example.com");
        Assertions.assertThat(found).isNull();
    }

    @Test
    void testReplaceUpdatesExistingUser() {
        String id = UUID.randomUUID().toString();
        User original = new User(id, "oldUser", "old@example.com", "oldPass");
        repository.insert(original);
        User updated = new User(id, "newUser", "new@example.com", "newPass");
        repository.replace(id, updated);
        User found = repository.findById(id);
        Assertions.assertThat(found).isNotNull();
        assertEquals("newUser", found.getUsername());
        assertEquals("new@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsExceptionWhenUserNotFound() {
        String id = UUID.randomUUID().toString();
        User user = new User(id, "user", "user@example.com", "password");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(id, user));
        Assertions.assertThat(exception.getMessage()).contains("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingUser() {
        String id = UUID.randomUUID().toString();
        User user = new User(id, "user3", "user3@example.com", "password");
        repository.insert(user);
        boolean result = repository.removeById(id);
        User found = repository.findById(id);
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(found).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenUserNotFound() {
        String id = UUID.randomUUID().toString();
        boolean result = repository.removeById(id);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testFindByEmailHandlesNullEmailGracefully() {
        User user = new User(UUID.randomUUID().toString(), "user", null, "password");
        repository.insert(user);
        User result = repository.findByEmail(null);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        User user = new User(UUID.randomUUID().toString(), "user", "user@example.com", "password");
        repository.insert(user);
        User result = repository.findById(null);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void testReplaceThrowsExceptionWhenIdIsNull() {
        User user = new User(UUID.randomUUID().toString(), "user", "user@example.com", "password");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(null, user));
        Assertions.assertThat(exception.getMessage()).contains("Data does not exist");
    }

    @Test
    void testReplaceThrowsExceptionWhenUserIsNull() {
        String id = UUID.randomUUID().toString();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace(id, null));
        Assertions.assertThat(exception.getMessage()).contains("Data does not exist");
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        boolean result = repository.removeById(null);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testInsertMultipleUsersMaintainsOrder() {
        User user1 = new User(UUID.randomUUID().toString(), "user1", "user1@example.com", "password1");
        User user2 = new User(UUID.randomUUID().toString(), "user2", "user2@example.com", "password2");
        repository.insert(user1);
        repository.insert(user2);
        Assertions.assertThat(repository.findById(user1.getId())).isNotNull();
        Assertions.assertThat(repository.findById(user2.getId())).isNotNull();
        assertEquals("user1", repository.findById(user1.getId()).getUsername());
        assertEquals("user2", repository.findById(user2.getId()).getUsername());
    }

    @Test
    void testReplaceDoesNotReturnUserButUpdatesState() {
        String id = UUID.randomUUID().toString();
        User user = new User(id, "original", "original@example.com", "pass");
        repository.insert(user);
        User updated = new User(id, "updated", "updated@example.com", "newPass");
        User result = repository.replace(id, updated);
        Assertions.assertThat(result).isNull();
        Assertions.assertThat(repository.findById(id)).isNotNull();
        assertEquals("updated", repository.findById(id).getUsername());
    }

    @Test
    void testRemoveByIdRemovesOnlyMatchingUser() {
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        User user1 = new User(id1, "user1", "user1@example.com", "password1");
        User user2 = new User(id2, "user2", "user2@example.com", "password2");
        repository.insert(user1);
        repository.insert(user2);
        boolean result = repository.removeById(id1);
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(repository.findById(id1)).isNull();
        Assertions.assertThat(repository.findById(id2)).isNotNull();
    }

    @Test
    void testFindByEmailWithDuplicateEmailsReturnsFirstMatch() {
        User user1 = new User(UUID.randomUUID().toString(), "userA", "dup@example.com", "passwordA");
        User user2 = new User(UUID.randomUUID().toString(), "userB", "dup@example.com", "passwordB");
        repository.insert(user1);
        repository.insert(user2);
        User found = repository.findByEmail("dup@example.com");
        Assertions.assertThat(found).isNotNull();
        assertEquals("userA", found.getUsername());
    }

    @Test
    void testFindByIdWithDuplicateIdsReturnsFirstMatch() {
        String id = UUID.randomUUID().toString();
        User user1 = new User(id, "userA", "a@example.com", "passwordA");
        User user2 = new User(id, "userB", "b@example.com", "passwordB");
        repository.insert(user1);
        repository.insert(user2);
        User found = repository.findById(id);
        Assertions.assertThat(found).isNotNull();
        assertEquals("userA", found.getUsername());
    }

    @Test
    void testInsertReturnsSameUserInstance() {
        User user = new User(UUID.randomUUID().toString(), "user", "user@example.com", "password");
        User inserted = repository.insert(user);
        Assertions.assertThat(inserted).isSameAs(user);
    }
}
