package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(null);
    }

    @Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assertNotNull(id);
        assertTrue(id.length() > 32);
    }

    @Test
    void findByEmail_returnsUserIfEmailExists() {
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User foundUser = repository.findByEmail("test@example.com");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById_returnsUserIfIdExists() {
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User foundUser = repository.findById("1");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        User insertedUser = repository.insert(user);
        assertNotNull(insertedUser);
        assertEquals("1", insertedUser.getId());
        assertEquals("testuser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_updatesUserAndReturnsUpdatedUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User updatedUser = repository.replace("1", user);
        assertNotNull(updatedUser);
        assertEquals("testuser", updatedUser.getUsername());
        assertEquals("test@example.com", updatedUser.getEmail());
        assertEquals("newpassword", updatedUser.getPassword());
    }

    @Test
    void removeById_removesUserByIdAndReturnsTrue() {
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        boolean removed = repository.removeById("1");
        assertTrue(removed);
        assertNull(repository.findById("1"));
    }
}
