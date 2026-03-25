package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertNotNull(id1);
        assertNotNull(id2);
        assertTrue(!id1.equals(id2));
        assertNotNull(UUID.fromString(id1));
    }

    @Test
    void testInsertAddsInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN
        Info inserted = repository.insert(info);

        // THEN
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testFindAllReturnsAllInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN
        List<Info> allInfos = repository.findAll();

        // THEN
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById("123");

        // THEN
        assertNotNull(found);
        assertEquals("123", found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // Empty repository

        // WHEN
        Info found = repository.findById("nonexistent");

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullPointerExceptionGracefully() {
        // GIVEN
        repository.insert(null);

        // WHEN
        Info found = repository.findById("any");

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoDoesNotExist() {
        // GIVEN
        Info info = new Info();
        info.setId("nonexistent");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> repository.replace("nonexistent", info));
    }

    @Test
    void testReplaceUpdatesExistingInfoCorrectly() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Old Title");
        info1.setDescription("Old Description");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("New Title");
        info2.setDescription("New Description");
        repository.insert(info2);

        // WHEN
        Info result = repository.replace("2", info1);

        // THEN
        assertNull(result);
        Info found = repository.findById("2");
        assertNotNull(found);
        assertEquals("Old Title", found.getTitle());
        assertEquals("Old Description", found.getDescription());
    }

    @Test
    void testReplaceThrowsRuntimeExceptionWhenNoMatchingId() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> repository.replace("unknown", info));
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        boolean removed = repository.removeById("1");

        // THEN
        assertTrue(removed);
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN
        // Empty repository

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertTrue(result);
    }

    @Test
    void testRemoveByIdHandlesMultipleEntries() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN
        boolean removed = repository.removeById("2");

        // THEN
        assertTrue(removed);
        assertNull(repository.findById("2"));
        assertNotNull(repository.findById("1"));
    }
}
