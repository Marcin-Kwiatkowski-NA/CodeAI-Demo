package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

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
    void testInsertAndFindAll() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        repository.insert(info);
        List<Info> allInfos = repository.findAll();
        assertTrue(allInfos.contains(info));
        assertEquals(1, allInfos.size());
    }

    @Test
    void testFindAllInitiallyEmpty() {
        List<Info> allInfos = repository.findAll();
        assertTrue(allInfos.isEmpty());
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById("123");
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
    }

    @Test
    void testFindByIdWithWhitespaceId() {
        Info info = new Info();
        info.setId(" ");
        info.setTitle("Whitespace");
        info.setDescription("Desc");
        repository.insert(info);
        Info found = repository.findById(" ");
        assertEquals(" ", found.getId());
        assertEquals("Whitespace", found.getTitle());
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
    void testReplaceThrowsExceptionWhenDataDoesNotExist() {
        Info newInfo = new Info();
        newInfo.setId("2");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");
        assertThatThrownBy(() -> repository.replace("2", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceWithSingleElementList() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Updated");
        newInfo.setDescription("Updated Desc");
        assertThatThrownBy(() -> repository.replace("1", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceHandlesEmptyStringId() {
        Info info = new Info();
        info.setId("");
        info.setTitle("Empty");
        info.setDescription("Desc");
        repository.insert(info);
        Info newInfo = new Info();
        newInfo.setId("");
        newInfo.setTitle("Updated");
        newInfo.setDescription("Updated Desc");
        assertThatThrownBy(() -> repository.replace("", newInfo))
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
        Info found = repository.findById("1");
        assertTrue(result);
        assertNull(found);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        boolean result = repository.removeById("nonexistent");
        assertTrue(result);
    }

    @Test
    void testRemoveByIdWithEmptyStringId() {
        Info info = new Info();
        info.setId("");
        info.setTitle("Empty");
        info.setDescription("Desc");
        repository.insert(info);
        boolean result = repository.removeById("");
        assertTrue(result);
    }

    @Test
    void testRemoveByIdWithWhitespaceId() {
        Info info = new Info();
        info.setId(" ");
        info.setTitle("Whitespace");
        info.setDescription("Desc");
        repository.insert(info);
        boolean result = repository.removeById(" ");
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
    void testInsertDuplicateIds() {
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
        assertEquals("dup", allInfos.get(0).getId());
    }

    @Test
    void testFindAllAfterMultipleInserts() {
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
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
    void testFindAllAfterRemoveAll() {
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        repository.insert(info1);
        repository.removeById("1");
        List<Info> allInfos = repository.findAll();
        assertTrue(allInfos.isEmpty());
    }
}
