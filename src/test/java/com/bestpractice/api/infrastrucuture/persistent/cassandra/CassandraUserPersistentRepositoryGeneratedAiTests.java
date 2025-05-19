package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.Test;
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
        // GIVEN a CassandraUserPersistentRepository and a User object
        // WHEN calling findByEmail() with a valid email
        // THEN findByEmail() should return the User object
        User user = new User("1", "test", "test@example.com", "password");
        User foundUser = new CassandraUserPersistentRepository().findByEmail("test@example.com");
        assertEquals("1", foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById() {
        // GIVEN a CassandraUserPersistentRepository and a User object
        // WHEN calling findById() with a valid id
        // THEN findById() should return the User object
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
        // WHEN calling insert() with a User object
        // THEN insert() should return the User object
        User user = new User("1", "test", "test@example.com", "password");
        User insertedUser = new CassandraUserPersistentRepository().insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("test", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace() {
        // GIVEN a CassandraUserPersistentRepository and a User object
        // WHEN calling replace() with an id and a User object
        // THEN replace() should return the User object
        User user = new User("1", "test", "test@example.com", "password");
        User replacedUser = new CassandraUserPersistentRepository().replace("1", user);
        assertEquals("1", replacedUser.getId());
        assertEquals("test", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("password", replacedUser.getPassword());
    }

    @Test
    void removeById() {
        // GIVEN a CassandraUserPersistentRepository and a User object
        // WHEN calling removeById() with an id
        // THEN removeById() should return true
        User user = new User("1", "test", "test@example.com", "password");
        boolean result = new CassandraUserPersistentRepository().removeById("1");
        assertTrue(result);
    }
}
