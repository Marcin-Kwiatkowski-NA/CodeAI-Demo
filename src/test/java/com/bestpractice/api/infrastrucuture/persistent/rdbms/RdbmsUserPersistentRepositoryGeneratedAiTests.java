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
        repository = new RdbmsUserPersistentRepository(new JdbcTemplate());
    }

    @Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assertNotNull(id, "UUID should not be null");
        assertTrue(id.length() > 30, "UUID should be long enough");
    }

    @Test
    void findByEmail_returnsUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        User foundUser = repository.findByEmail("test@example.com");
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findById_returnsUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        User foundUser = repository.findById("1");
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void insert_insertsUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        User insertedUser = repository.insert(user);
        assertNotNull(insertedUser, "Inserted user should not be null");
        assertEquals("1", insertedUser.getId());
        assertEquals("testuser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
    }

    @Test
    void replace_replacesUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        User replacedUser = repository.replace("1", user);
        assertNotNull(replacedUser, "Replaced user should not be null");
        assertEquals("1", replacedUser.getId());
        assertEquals("testuser", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
    }

    @Test
    void removeById_removesUser() {
        User user = new User("1", "testuser", "test@example.com", "password");
        boolean removed = repository.removeById("1");
        assertTrue(removed, "User should be removed");
    }
}
