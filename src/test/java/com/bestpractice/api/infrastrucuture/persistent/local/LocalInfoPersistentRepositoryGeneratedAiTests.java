package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }

    @Test
    void testInsertAndFindAll() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title1");
        info.setDescription("Description1");

        // WHEN
        repository.insert(info);
        List<Info> allInfos = repository.findAll();

        // THEN
        assertNotNull(allInfos);
        assertTrue(allInfos.contains(info));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title2");
        info.setDescription("Description2");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(id);

        // THEN
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        String nonExistentId = repository.newId();

        // WHEN
        Info found = repository.findById(nonExistentId);

        // THEN
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullPointerGracefully() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Null ID");
        info.setDescription("Testing null ID");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(null);

        // THEN
        assertNull(found);
    }

    @Test
    void testReplaceThrowsExceptionWhenDataDoesNotExist() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceReplacesExistingInfoWhenValid() {
        // GIVEN
        Info info1 = new Info();
        String id1 = repository.newId();
        info1.setId(id1);
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        String id2 = repository.newId();
        info2.setId(id2);
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        Info updated = new Info();
        updated.setId(id1);
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Description");

        // WHEN
        repository.replace(id2, updated);
        Info found = repository.findById(id1);

        // THEN
        assertNotNull(found);
        assertEquals("Updated Title", found.getTitle());
        assertEquals("Updated Description", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenListEmpty() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Empty List");
        info.setDescription("Should throw exception");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        // GIVEN
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title3");
        info.setDescription("Description3");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById(id);
        Info found = repository.findById(id);

        // THEN
        assertTrue(result);
        assertNull(found);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN
        String nonExistentId = repository.newId();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertTrue(result);
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        // GIVEN
        Info info = new Info();
        info.setId(null);
        info.setTitle("Null ID");
        info.setDescription("Testing null ID removal");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById(null);

        // THEN
        assertTrue(result);
    }
}
