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

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - assert IDs are not null and unique
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - assert list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - assert found info matches
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository without matching id

        // WHEN - find by id
        Info found = repository.findById("nonexistent");

        // THEN - assert null returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfo() {
        // GIVEN - new info
        Info info = new Info();
        info.setId("abc");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN - insert info
        Info inserted = repository.insert(info);

        // THEN - assert inserted info is returned and stored
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("id1");
        original.setTitle("Original");
        original.setDescription("Original Desc");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("id1");
        updated.setTitle("Updated");
        updated.setDescription("Updated Desc");

        // WHEN - replace info
        repository.replace("id1", updated);

        // THEN - assert updated info is stored
        Info found = repository.findById("id1");
        assertEquals("Updated", found.getTitle());
        assertEquals("Updated Desc", found.getDescription());
    }

    @Test
    void testReplaceThrowsWhenInfoNotFound() {
        // GIVEN - no info with given id
        Info updated = new Info();
        updated.setId("idX");
        updated.setTitle("Updated");
        updated.setDescription("Updated Desc");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("idX", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - assert removal successful
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN - no info with given id

        // WHEN - remove by id
        boolean result = repository.removeById("nonexistent");

        // THEN - assert true returned even when not found
        assertTrue(result);
    }
}