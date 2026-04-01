package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNotNull(id1);
        assertNotNull(id2);
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
    void testFindAllReturnsAllInfos() {
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
    void testReplaceThrowsExceptionWhenInfoDoesNotExist() {
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        assertThatThrownBy(() -> repository.replace("1", info))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceUpdatesInfoWhenValid() {
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        repository.insert(info2);

        Info updated = new Info();
        updated.setId("2");
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Desc");

        repository.replace("2", updated);
        Info found = repository.findById("2");
        assertNotNull(found);
        assertEquals("Updated Title", found.getTitle());
        assertEquals("Updated Desc", found.getDescription());
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
        assertNull(repository.findById("1"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        boolean result = repository.removeById("nonexistent");
        assertTrue(result);
    }
}
