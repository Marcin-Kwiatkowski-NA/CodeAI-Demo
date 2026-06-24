package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueAndNonEmptyIds() {
        String id1 = repository.newId();
        String id2 = repository.newId();
        assertNotNull(id1);
        assertNotNull(id2);
        assertFalse(id1.isEmpty());
        assertFalse(id2.isEmpty());
        assertNotEquals(id1, id2);
    }

    @Test
    void testFindAllInitiallyEmpty() {
        List<Info> infos = repository.findAll();
        assertNotNull(infos);
        assertTrue(infos.isEmpty());
    }

    @Test
    void testInsertAddsInfoSuccessfully() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        Info inserted = repository.insert(info);
        assertEquals(info, inserted);
        assertEquals(1, repository.findAll().size());
        assertEquals("1", repository.findAll().get(0).getId());
    }

    @Test
    void testInsertAllowsNullInfo() {
        Info info = null;
        Info result = repository.insert(info);
        assertNull(result);
        assertTrue(repository.findAll().contains(null));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById("123");
        assertNotNull(found);
        assertEquals("123", found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        Info found = repository.findById("nonexistent");
        assertNull(found);
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        Info info = new Info();
        info.setId("1");
        repository.insert(info);
        Info result = repository.findById(null);
        assertNull(result);
    }

    @Test
    void testFindByIdWithWhitespaceId() {
        Info info = new Info();
        info.setId(" ");
        repository.insert(info);
        Info found = repository.findById(" ");
        assertNotNull(found);
        assertEquals(" ", found.getId());
    }

    @Test
    void testReplaceThrowsWhenDataDoesNotExist() {
        Info info = new Info();
        info.setId("1");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> repository.replace("1", info));
        assertEquals("Data does not exist.", exception.getMessage());
    }

    @Test
    void testReplaceReplacesCorrectlyWhenConditionMet() {
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        repository.insert(info2);

        Info newInfo = new Info();
        newInfo.setId("3");
        newInfo.setTitle("Updated");

        repository.replace("1", newInfo);

        assertEquals("Updated", repository.findAll().get(1).getTitle());
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        Info info = new Info();
        info.setId("1");
        repository.insert(info);
        boolean result = repository.removeById("1");
        assertTrue(result);
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        boolean result = repository.removeById("nonexistent");
        assertTrue(result);
    }

    @Test
    void testRemoveByIdHandlesNullIdGracefully() {
        Info info = new Info();
        info.setId("1");
        repository.insert(info);
        boolean result = repository.removeById(null);
        assertTrue(result);
    }

    @Test
    void testFindAllAfterMultipleInsertions() {
        for (int i = 0; i < 5; i++) {
            Info info = new Info();
            info.setId(String.valueOf(i));
            repository.insert(info);
        }
        List<Info> infos = repository.findAll();
        assertEquals(5, infos.size());
        assertEquals("0", infos.get(0).getId());
        assertEquals("4", infos.get(4).getId());
    }

    @Test
    void testFindByIdWithEmptyStringId() {
        Info info = new Info();
        info.setId("");
        repository.insert(info);
        Info found = repository.findById("");
        assertNotNull(found);
        assertEquals("", found.getId());
    }

    @Test
    void testReplaceDoesNotThrowWhenListHasMultipleElements() {
        Info info1 = new Info();
        info1.setId("1");
        repository.insert(info1);
        Info info2 = new Info();
        info2.setId("2");
        repository.insert(info2);
        Info newInfo = new Info();
        newInfo.setId("3");
        assertDoesNotThrow(() -> repository.replace("2", newInfo));
    }

    @Test
    void testRemoveByIdWithWhitespaceId() {
        Info info = new Info();
        info.setId(" ");
        repository.insert(info);
        boolean result = repository.removeById(" ");
        assertTrue(result);
        assertNull(repository.findById(" "));
    }

    @Test
    void testFindByIdWithMultipleElements() {
        Info info1 = new Info();
        info1.setId("1");
        repository.insert(info1);
        Info info2 = new Info();
        info2.setId("2");
        repository.insert(info2);
        Info found = repository.findById("2");
        assertNotNull(found);
        assertEquals("2", found.getId());
    }
}
