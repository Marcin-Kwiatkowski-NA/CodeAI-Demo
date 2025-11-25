package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void givenEmailExists_whenFindByEmailCalled_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void givenEmailDoesNotExist_whenFindByEmailCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findByEmail("nonexistent@example.com");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void givenIdExists_whenFindByIdCalled_thenReturnsUser() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findById("1");

        // THEN
        assertEquals("1", foundUser.getId());
    }

    @Test
    void givenIdDoesNotExist_whenFindByIdCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findById("nonexistentId");

        // THEN
        assertNull(foundUser);
    }

    @Test
    void givenUser_whenInsertCalled_thenUserIsAdded() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void givenExistingId_whenReplaceCalled_thenUserIsUpdated() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN
        repository.replace("1", updatedUser);

        // THEN
        User foundUser = repository.findById("1");
        assertEquals("updatedUser", foundUser.getUsername());
        assertEquals("updated@example.com", foundUser.getEmail());
    }

    @Test
    void givenNonExistingId_whenReplaceCalled_thenThrowsRuntimeException() {
        // GIVEN
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN THEN
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistentId", updatedUser));
    }

    @Test
    void givenExistingId_whenRemoveByIdCalled_thenUserIsRemoved() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertEquals(true, result);
        assertNull(repository.findById("1"));
    }

    @Test
    void givenNonExistingId_whenRemoveByIdCalled_thenReturnsTrue() {
        // GIVEN
        // No specific setup required

        // WHEN
        boolean result = repository.removeById("nonexistentId");

        // THEN
        assertEquals(true, result);
    }

    @Test
    void givenNullEmail_whenFindByEmailCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findByEmail(null);

        // THEN
        assertNull(foundUser);
    }

    @Test
    void givenNullId_whenFindByIdCalled_thenReturnsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        User foundUser = repository.findById(null);

        // THEN
        assertNull(foundUser);
    }

    @Test
    void givenNullId_whenReplaceCalled_thenThrowsRuntimeException() {
               // GIVEN
        User updatedUser = new User("1", "updatedUser", "updated@example.com", "newPassword");

        // WHEN THEN
        assertThrows(RuntimeException.class, () -> repository.replace(null, updatedUser));
    }

    @Test
    void givenNullId_whenRemoveByIdCalled_thenReturnsTrue() {
        // GIVEN
        // No specific setup required

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertEquals(true, result);
    }
}
