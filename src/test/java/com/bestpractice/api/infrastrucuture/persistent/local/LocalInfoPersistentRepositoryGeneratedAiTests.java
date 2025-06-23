package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.*;

package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.ArrayList;
import java.util.List;

class LocalInfoPersistentRepositoryGeneratedAiTests {

    @Test
    void testNewId() {
        LocalInfoPersistentRepository impl = new LocalInfoPersistentRepository();
        String id = impl.newId();
        assertEquals("1234567890", id);
    }

    @Test
    void test findAll() {
        LocalInfoPersistentRepository impl = new LocalInfoPersistentRepository();
        List<Info> results = impl.findAll();
        assertEquals(3, results.size());
    }

    @Test
    void test findById(LocalInfoPersistentRepository impl) {
        impl.findById("1234567890");
        assertEquals(1234567890, impl.findById("1234567890"));
    }

    @Test
    void test insert(LocalInfoPersistentRepository impl) {
        impl.insert(new Info("1234567890"));
        assertEquals("1234567890", impl.findById("1234567890"));
    }

    @Test
    void test replace(LocalInfoPersistentRepository impl) {
        impl.replace("1234567890", new Info("1234567890"));
        assertEquals("1234567890", impl.findById("1234567890"));
    }

    @Test
    void test removeById(LocalInfoPersistentRepository impl) {
        impl.removeById("1234567890");
        assertEquals(2, impl.findById("1234567890"));
    }

    @Test
    void test newId() {
        LocalInfoPersistentRepository impl = new LocalInfoPersistentRepository();
        String id = impl.newId();
        assertEquals("1234567890", id);
    }
}
