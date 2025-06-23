package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.*;

public class MongoUserPersistentRepositoryTest {

    private final MongoUserPersistentRepository repository;

    @Test
    public void testNewId() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertEquals("user123", repository.newId());
    }

    @Test
    public void testFindByEmail() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.findByEmail("test@example.com"));
    }

    @Test
    public void testFindById() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.findById("12345"));
    }

    @Test
    public void testInsert() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        repository.insert(new MongoUserEntity("user123", "john.doe", "john@example.com", "password123"));
    }

    @Test
    public void testReplaceById() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.removeById("12345"));
    }

    @Test
    public void testReplaceByEmail() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.replace("12345", new MongoUserEntity("user123", "john.doe", "john@example.com", "password123")));
    }

    @Test
    public void testFindByUsername() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.findByUsername("john"));
    }

    @Test
    public void testFindByPassword() {
        repository = new MongoUserPersistentRepository(
                "test-repo",
                "test-db"
        );
        assertTrue(repository.findByPassword("password123"));
    }
}
