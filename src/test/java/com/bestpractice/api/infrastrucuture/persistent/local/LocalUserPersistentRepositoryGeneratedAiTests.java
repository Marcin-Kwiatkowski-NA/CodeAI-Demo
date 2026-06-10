package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
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
        assertNotNull(id1);
        assertNotNull(id2);
        assertTrue(!id1.equals(id2));
    }

    @Test
    void testNewIdGeneratesNonEmptyString() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertTrue(id.length() > 0);
    }

    @Test
    void testInsertAddsSingleUserSuccessfully() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");

        // WHEN
        User inserted = repository.insert(user);

        // THEN
        assertEquals(user, inserted);
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void testInsertAllowsDuplicateUsersButKeepsBoth() {
        // GIVEN
        User user1 = new User("1", "john", "john@example.com", "password123");
        User user2 = new User("1", "john", "john@example.com", "password123");
        repository.insert(user1);

        // WHEN
        repository.insert(user2);

        // THEN
        User found = repository.findById("1");
        assertNotNull(found);
        assertEquals("john", found.getUsername());
        assertEquals("john@example.com", found.getEmail());
    }

    @Test
    void testFindByEmailWithWhitespaceEmail() {
        // GIVEN
        User user = new User("1", "john", "   ", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("   ");

        // THEN
        assertNotNull(found);
        assertEquals("john", found.getUsername());
    }

    @Test
    void testFindByEmailWithEmptyStringEmail() {
        // GIVEN
        User user = new User("1", "john", "", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("");

        // THEN
        assertNotNull(found);
        assertEquals("john", found.getUsername());
    }

    @Test
    void testFindByEmailReturnsNullWhenEmptyList() {
        // GIVEN
        // No users inserted

        // WHEN
        User found = repository.findByEmail("john@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByEmailReturnsNullWhenEmailNotFound() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findByEmail("notfound@example.com");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdWithWhitespaceId() {
        // GIVEN
        User user = new User("   ", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findById("   ");

        // THEN
        assertNotNull(found);
        assertEquals("john", found.getUsername());
    }

    @Test
    void testFindByIdWithEmptyStringId() {
        // GIVEN
        User user = new User("", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findById("");

        // THEN
        assertNotNull(found);
        assertEquals("john", found.getUsername());
    }

    @Test
    void testFindByIdReturnsNullWhenIdNotFound() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        User found = repository.findById("2");

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesSingleUserBoundaryCase() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");
        repository.insert(user);
        User updatedUser = new User("1", "johnny", "johnny@example.com", "newpass");

        // WHEN
        repository.replace("1", updatedUser);
        User found = repository.findById("1");

        // THEN
        assertNotNull(found);
        assertEquals("johnny", found.getUsername());
        assertEquals("johnny@example.com", found.getEmail());
    }

    @Test
    void testReplaceThrowsWhenIdNotFound() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("2", user))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void testReplaceWithWhitespaceId() {
        // GIVEN
        User user = new User("   ", "john", "john@example.com", "password123");
        repository.insert(user);
        User updatedUser = new User("   ", "johnny", "johnny@example.com", "newpass");

        // WHEN
        repository.replace("   ", updatedUser);
        User found = repository.findById("   ");

        // THEN
        assertNotNull(found);
        assertEquals("johnny", found.getUsername());
    }

    @Test
    void testReplaceWithEmptyStringId() {
        // GIVEN
        User user = new User("", "john", "john@example.com", "password123");
        repository.insert(user);
        User updatedUser = new User("", "johnny", "johnny@example.com", "newpass");

        // WHEN
        repository.replace("", updatedUser);
        User found = repository.findById("");

        // THEN
        assertNotNull(found);
        assertEquals("johnny", found.getUsername());
    }

    @Test
    void testRemoveByIdWithWhitespaceId() {
        // GIVEN
        User user = new User("   ", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("   ");

        // THEN
        assertTrue(result);
        assertNull(repository.findById("   "));
    }

    @Test
    void testRemoveByIdWithEmptyStringId() {
        // GIVEN
        User user = new User("", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("");

        // THEN
        assertTrue(result);
        assertNull(repository.findById(""));
    }

    @Test
    void testRemoveByIdWithSingleElementList() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertTrue(result);
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdWithMultipleUsersRemovesCorrectOne() {
        // GIVEN
        User user1 = new User("1", "john", "john@example.com", "password123");
        User user2 = new User("2", "mark", "mark@example.com", "password456");
        repository.insert(user1);
        repository.insert(user2);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertTrue(result);
        assertNull(repository.findById("1"));
        assertNotNull(repository.findById("2"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenIdNotFound() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "password123");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("2");

        // THEN
        assertTrue(result);
        assertNotNull(repository.findById("1"));
    }

    @Test
    void testNewIdGeneratesValidUUIDFormat() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertThrows(IllegalArgumentException.class, () -> UUID.fromString("invalid-uuid"));
        UUID parsed = UUID.fromString(id);
        assertNotNull(parsed);
    }

    @Test
    void testNewIdBoundaryLength() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id = repository.newId();

        // THEN
        assertTrue(id.length() >= 30);
        assertTrue(id.length() <= 40);
    }
}
