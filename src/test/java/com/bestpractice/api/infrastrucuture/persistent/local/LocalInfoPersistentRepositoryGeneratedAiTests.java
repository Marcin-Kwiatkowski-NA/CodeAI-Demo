package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String id1 = repository.newId();
        String id2 = repository.newId();
        assertTrue(id1 != null && id2 != null);
        assertTrue(!id1.equals(id2));
    }

    @Test
    void testInsertAddsInfoSuccessfully() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Info inserted = repository.insert(info);
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testFindAllReturnsEmptyListInitially() {
        List<Info> allInfos = repository.findAll();
        assertTrue(allInfos.isEmpty());
    }

    @Test
    void testFindAllReturnsSingleElementList() {
        Info info = new Info();
        info.setId("single");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        List<Info> allInfos = repository.findAll();
        assertEquals(1, allInfos.size());
        assertTrue(allInfos.contains(info));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById("123");
        assertEquals(info, found);
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        Info found = repository.findById("nonexistent");
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullPointerExceptionGracefully() {
        Info info = new Info();
        info.setId(null);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById(null);
        assertNull(found);
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");
        assertThatThrownBy(() -> repository.replace("1", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceThrowsExceptionWhenSingleElementList() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Updated Title");
        newInfo.setDescription("Updated Description");
        assertThatThrownBy(() -> repository.replace("1", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        boolean result = repository.removeById("1");
        assertTrue(result);
        assertTrue(!repository.findAll().contains(info));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        boolean result = repository.removeById("nonexistent");
        assertTrue(result);
    }

    @Test
    void testFindByIdHandlesNoSuchElementExceptionGracefully() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById("nonexistent");
        assertNull(found);
    }

    @Test
    void testReplaceHandlesDuplicateIds() {
        Info info1 = new Info();
        info1.setId("dup");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("dup");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        repository.insert(info1);
        repository.insert(info2);
        Info newInfo = new Info();
        newInfo.setId("dup");
        newInfo.setTitle("Updated");
        newInfo.setDescription("Updated Desc");
        assertThatThrownBy(() -> repository.replace("dup", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testFindAllHandlesDuplicateEntries() {
        Info info1 = new Info();
        info1.setId("dup");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("dup");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        repository.insert(info1);
        repository.insert(info2);
        List<Info> allInfos = repository.findAll();
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindAllReflectsInsertionOrder() {
        Info info1 = new Info();
        info1.setId("A");
        info1.setTitle("First");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("B");
        info2.setTitle("Second");
        info2.setDescription("Desc2");
        repository.insert(info1);
        repository.insert(info2);
        List<Info> allInfos = repository.findAll();
        assertEquals("A", allInfos.get(0).getId());
        assertEquals("B", allInfos.get(1).getId());
    }
}
