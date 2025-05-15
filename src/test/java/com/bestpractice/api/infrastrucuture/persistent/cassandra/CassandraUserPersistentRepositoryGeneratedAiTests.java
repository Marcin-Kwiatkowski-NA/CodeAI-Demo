package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

import static org.junit.jupiter.api.Assertions.*;

class CassandraUserPersistentRepositoryGeneratedAiTests {

    @Test
    void newId() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling newId()
        // THEN newId() should return a string
        String result = new CassandraUserPersistentRepository().newId();
        assertNotNull(result);
    }

    @Test
    void findByEmail() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling findByEmail("test@example.com")
        // THEN findByEmail() should return a User object
        User user = new User("1", "test", "test@example.com", "password");
        User foundUser = new CassandraUserPersistentRepository().findByEmail("test@example.com");
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling findById("1")
        // THEN findById() should return a User object
        User user = new User("1", "test", "test@example.com", "password");
        User foundUser = new CassandraUserPersistentRepository().findById("1");
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling insert(new User("1", "test", "test@example.com", "password"))
        // THEN insert() should return the inserted User object
        User user = new User("1", "test", "test@example.com", "password");
        User insertedUser = new CassandraUserPersistentRepository().insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("test", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling replace("1", new User("1", "test", "test@example.com", "password"))
        // THEN replace() should return the replaced User object
        User user = new User("1", "test", "test@example.com", "password");
        User replacedUser = new CassandraUserPersistentRepository().replace("1", user);
        assertEquals("1", replacedUser.getId());
        assertEquals("test", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("password", replacedUser.getPassword());
    }

    @Test
    void removeById() {
        // GIVEN a CassandraUserPersistentRepository
        // WHEN calling removeById("1")
        // THEN removeById() should return true
        User user = new User("1", "test", "test@example.com", "password");
        boolean result = new CassandraUserPersistentRepository().removeById("1");
        assertTrue(result);
    }
}
