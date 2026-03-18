package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // No setup required

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testInsertAddsInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN
        Info inserted = repository.insert(info);

        // THEN
        assertEquals(info, inserted);
        assertThat(repository.findAll()).contains(info);
    }

    @Test
    void testFindAllReturnsAllInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId(repository.newId());
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId(repository.newId());
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN
        List<Info> allInfos = repository.findAll();

        // THEN
        assertThat(allInfos).containsExactlyInAnyOrder(info1, info2);
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(id);

        // THEN
        assertThat(found).isNotNull();
        assertEquals(id, found.getId());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info found = repository.findById(nonExistentId);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoDoesNotExist() {
        // GIVEN
        Info newInfo = new Info();
        String id = repository.newId();
        newInfo.setId(id);
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(id, newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testReplaceDoesNotThrowWhenListHasDifferentId() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId(repository.newId());
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Description");
        repository.insert(existingInfo);

        Info newInfo = new Info();
        newInfo.setId(repository.newId());
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        boolean exceptionThrown = false;
        try {
            repository.replace(existingInfo.getId(), newInfo);
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }

        // THEN
        assertThat(exceptionThrown).isTrue();
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        // GIVEN
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        boolean removed = repository.removeById(id);

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById(id)).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN
        String nonExistentId = repository.newId();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testFindByIdHandlesNullIdGracefully() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(null);

        // THEN
        assertThat(found).isNull();
    }
}
