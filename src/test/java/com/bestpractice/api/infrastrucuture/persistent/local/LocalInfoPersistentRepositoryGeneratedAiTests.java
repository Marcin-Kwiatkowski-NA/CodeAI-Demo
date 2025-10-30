package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN - Prepare repository
        // WHEN - Generate new ID
        String id1 = repository.newId();
        String id2 = repository.newId();
        // THEN - IDs should be unique and non-null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsEmptyListInitially() {
        // GIVEN - Fresh repository
        // WHEN - Retrieve all infos
        List<Info> infos = repository.findAll();
        // THEN - List should be empty
        assertNotNull(infos);
        assertTrue(infos.isEmpty());
    }

    @Test
    void testInsertAddsInfo() {
        // GIVEN - Create info object
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Description");
        // WHEN - Insert info
        Info inserted = repository.insert(info);
        // THEN - Returned info should match and repository should contain it
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - Insert info
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        // WHEN - Find by ID
        Info found = repository.findById(id);
        // THEN - Found info should match inserted
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Title", found.getTitle());
    }

    @Test
    void testFindByIdReturnsNullForNonExistingId() {
        // GIVEN - No infos inserted
        // WHEN - Find by non-existing ID
        Info found = repository.findById("non-existing-id");
        // THEN - Should return null
        assertNull(found);
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - Insert info
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Old Title");
        info.setDescription("Old Description");
        repository.insert(info);
        // WHEN - Replace info
        Info newInfo = new Info();
        newInfo.setId(id);
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");
        Info replaced = repository.replace(id, newInfo);
        // THEN - Replacement should occur
        assertEquals(newInfo, replaced);
        Info found = repository.findById(id);
        assertEquals("New Title", found.getTitle());
        assertEquals("New Description", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionForNonExistingId() {
        // GIVEN - No infos inserted
        Info info = new Info();
        info.setId("some-id");
        info.setTitle("Title");
        info.setDescription("Description");
        // WHEN & THEN - Expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("non-existing-id", info));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - Insert info
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        // WHEN - Remove by ID
        boolean result = repository.removeById(id);
        // THEN - Should return true and info should be removed
        assertTrue(result);
        assertNull(repository.findById(id));
    }

    @Test
    void testRemoveByIdReturnsTrueForNonExistingId() {
        // GIVEN - No infos inserted
        // WHEN - Remove by non-existing ID
        boolean result = repository.removeById("non-existing-id");
        // THEN - Should return true
        assertTrue(result);
    }
}