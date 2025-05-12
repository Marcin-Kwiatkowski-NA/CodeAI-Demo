package com.bestpractice.api.infrastrucuture.persistent.rdbms.Data;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.ExtensionTest;

import java.util.List;

import java.util.UUID;

class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @ExtensionTest
    void testNewId() {
        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository();
        String id = repository.newId();
        assertEquals(UUID.randomUUID().toString(), id);
    }

    @ExtensionTest
    void testFindById(RdbmsInfoPersistentRepository repository) {
        String id = "12345";
        assertEquals(repository.findById(id), id);
    }

    @ExtensionTest
    void test findAll() {
        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository();
        List<Info> results = repository.findAll();
        assertEquals(3, results.size());
        assertEquals(1, results.get(0));
        assertEquals(Info.title("Example Data"), results.get(0).getTitle());
        assertEquals(Info.description("Some description"), results.get(0).getDescription());
    }

    @ExtensionTest
    void testReplace(RdbmsInfoPersistentRepository repository) {
        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository();
        String id = "12345";
        String newId = "67890";
        repository.replace(id, newId);
        assertEquals(12345, repository.findById(id));
    }

    @ExtensionTest
    void testRemoveById(RdbmsInfoPersistentRepository repository) {
        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository();
        String idToDelete = "12345";
        repository.removeById(idToDelete);
        assertEquals(12345, repository.findById(idToDelete));
    }

    @ExtensionTest
    void testNewId() {
        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository();
        String id = repository.newId();
        assertEquals(UUID.randomUUID().toString(), id);
    }
}
