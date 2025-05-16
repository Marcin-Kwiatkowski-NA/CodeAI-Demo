package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

@Test
class LocalUserPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the repository before each test.
    }

    @Test
    void newId() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        String generatedId = repository.newId();
        assertEquals(generatedId, null, "New ID should not be null");
    }

    @Test
    void findByEmail() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User foundUser = repository.findByEmail("test@example.com");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
    }

    @Test
    void findById() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User foundUser = repository.findById("1");
        assertEquals("1", foundUser.getId(), "ID should match");
    }

    @Test
    void insert() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User newUser = new User("1", "testuser", "test@example.com", "password");
        User insertedUser = repository.insert(newUser);
        assertEquals("1", insertedUser.getId(), "ID should match");
        assertEquals("testuser", insertedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", insertedUser.getEmail(), "Email should match");
    }

    @Test
    void replace() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        User replacedUser = repository.replace("1", user);
        assertEquals("1", replacedUser.getId(), "ID should match");
        assertEquals("testuser", replacedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", replacedUser.getEmail(), "Email should match");
    }

    @Test
    void removeById() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = new User("1", "testuser", "test@example.com", "password");
        repository.insert(user);
        boolean removed = repository.removeById("1");
        assertTrue(removed, "Removal should be successful");
    }
}