package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.Assert.*;

class LocalUserPersistentRepositoryTest {

    @Test
    void testGet() {
        LocalUserPersistentRepository generated = new LocalUserPersistentRepository();
        assertEquals("User", generated.get());
    }

    @Test
    void testGet() {
        LocalUserPersistentRepository generated = new LocalUserPersistentRepository();
        assertEquals("User", generated.get());
    }

    @Test
    void testGet() {
        LocalUserPersistentRepository generated = new LocalUserPersistentRepository();
        assertEquals("User", generated.get());
    }
}
