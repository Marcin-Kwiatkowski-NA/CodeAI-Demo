package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testInsertAndFindAll() {
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title1");
        info.setDescription("Description1");
        repository.insert(info);
        List<Info> allInfos = repository.findAll();
        assertThat(allInfos).isNotEmpty();
        assertTrue(allInfos.contains(info));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        Info found = repository.findById(id);
        assertThat(found).isNotNull();
        assertEquals(id, found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        String nonExistentId = repository.newId();
        Info found = repository.findById(nonExistentId);
        assertThat(found).isNull();
    }

    @Test
    void testReplaceThrowsWhenDataDoesNotExist() {
        Info updated = new Info();
        updated.setId("nonexistent");
        updated.setTitle("Updated");
        updated.setDescription("Updated Desc");
        assertThatThrownBy(() -> repository.replace("nonexistent", updated))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceReplacesExistingInfo() {
        Info info1 = new Info();
        String id1 = repository.newId();
        info1.setId(id1);
        info1.setTitle("Title1");
        info1.setDescription("Desc1");

        Info info2 = new Info();
        String id2 = repository.newId();
        info2.setId(id2);
        info2.setTitle("Title2");
        info2.setDescription("Desc2");

        repository.insert(info1);
        repository.insert(info2);

        Info updated = new Info();
        updated.setId(id2);
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Desc");

        repository.replace(id1, updated);

        List<Info> allInfos = repository.findAll();
        assertThat(allInfos).hasSize(2);
        assertEquals("Updated Title", allInfos.get(0).getTitle());
        assertEquals("Updated Desc", allInfos.get(0).getDescription());
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        boolean result = repository.removeById(id);
        Info found = repository.findById(id);
        assertTrue(result);
        assertThat(found).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        String nonExistentId = repository.newId();
        boolean result = repository.removeById(nonExistentId);
        assertTrue(result);
    }
}
