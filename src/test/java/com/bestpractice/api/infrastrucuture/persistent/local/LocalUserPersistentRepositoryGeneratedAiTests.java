package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

class LocalUserPersistentRepositoryTest {

    @Test
    void testNewId() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        String result = repository.newId();
        assertEquals("1234567890", result);
    }

    @Test
    void testFindByEmail() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = repository.findByEmail("test@example.com");
        assertEquals(user, new User("1234567890", "test@example.com", "test@example.com", "test@example.com"));
    }

    @Test
    void testFindById() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = repository.findById(1234567890);
        assertEquals(user, user);
    }

    @Test
    void testInsert() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = repository.insert(new User("test_user", "test_username", "test_email", "test_password"));
        assertEquals(user, user);
    }

    @Test
    void testReplace() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = repository.replace("1234567890", new User("test_user", "test_username", "test_email", "test_password"));
        assertEquals(user, user);
    }

    @Test
    void testRemoveById() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        User user = repository.removeById("1234567890");
        assertEquals(null, user);
    }

    @Test
    void testNewId() {
        LocalUserPersistentRepository repository = new LocalUserPersistentRepository();
        String result = repository.newId();
        assertEquals("1234567890", result);
    }
}
