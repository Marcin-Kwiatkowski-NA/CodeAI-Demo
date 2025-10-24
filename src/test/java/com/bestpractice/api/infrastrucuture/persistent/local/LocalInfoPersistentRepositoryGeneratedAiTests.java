package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate two IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - assert they are not null and unique
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsEmptyListInitially() {
        // GIVEN - repository is empty

        // WHEN - find all infos
        List<Info> infos = repository.findAll();

        // THEN - assert list is empty
        assertNotNull(infos);
        assertTrue(infos.isEmpty());
    }

    @Test
    void testInsertAddsInfo() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN - insert info
        Info inserted = repository.insert(info);

        // THEN - assert inserted info is returned and stored
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testFindByIdReturnsInfoWhenExists() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("abc");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("abc");

        // THEN - assert found info matches
        assertNotNull(found);
        assertEquals("abc", found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotExists() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("nonexistent");

        // THEN - assert null is returned
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("id1");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");

        // WHEN - replace info
        repository.replace("id1", info2);

        // THEN - assert info is replaced
        Info found = repository.findById("id1");
        assertNotNull(found);
        assertEquals("Title2", found.getTitle());
        assertEquals("Desc2", found.getDescription());
    }

    @Test
    void testReplaceThrowsWhenDataNotExists() {
        // GIVEN - repository is empty
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("id1", info));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title");
        info.setDescription("Desc");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("id1");

        // THEN - assert removal
        assertTrue(result);
        assertNull(repository.findById("id1"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotExists() {
        // GIVEN - repository is empty

        // WHEN - remove non-existing id
        boolean result = repository.removeById("nonexistent");

        // THEN - assert true is returned
        assertTrue(result);
    }
}