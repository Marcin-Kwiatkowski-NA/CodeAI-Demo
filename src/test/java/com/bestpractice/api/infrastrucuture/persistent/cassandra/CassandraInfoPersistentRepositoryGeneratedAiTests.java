package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

package com.bestpractice.api.infrastrucuture.persistent.api.infrastrucuture.persistent.cassandra;

import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.List;

class CassandraInfoPersistentRepositoryTest {

    @Test
    void testNewId() {
        CassandraInfoPersistentRepository repository = new CassandraInfoPersistentRepository();
        String id = repository.newId();
        assertEquals("1234567890", id);
    }

    @Test
    void testFindById(String id) {
        CassandraInfoPersistentRepository repository = new CassandraInfoPersistentRepository();
        String id = repository.findById(id);
        assertEquals("1234567890", id);
    }

    @Test
    void testReplace() {
        CassandraInfoPersistentRepository repository = new CassandraInfoPersistentRepository();
        String id = repository.findById(id);
        assertEquals("1234567890", id);
    }

    @Test
    void testNewId() {
        CassandraInfoPersistentRepository repository = new CassandraInfoPersistentRepository();
        String id = repository.newId();
        assertEquals("1234567890", id);
    }
}
