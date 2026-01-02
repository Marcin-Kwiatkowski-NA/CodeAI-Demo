package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdReturnsUniqueNonNull() {
        // GIVEN
        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull().isNotEmpty();
        assertThat(id2).isNotNull().isNotEmpty();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testFindAllInitiallyEmpty() {
        // GIVEN
        // WHEN
        List<Info> all = repository.findAll();

        // THEN
        assertThat(all).isNotNull().isEmpty();
    }

    @Test
    void testFindAllAfterInsert() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Desc");
        repository.insert(info);

        // WHEN
        List<Info> all = repository.findAll();

        // THEN
        assertThat(all).containsExactly(info);
    }

    @Test
    void testFindByIdReturnsInfoWhenExists() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Desc");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(info.getId());

        // THEN
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(info.getId());
        assertThat(found.getTitle()).isEqualTo(info.getTitle());
        assertThat(found.getDescription()).isEqualTo(info.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        String nonExistingId = repository.newId();

        // WHEN
        Info found = repository.findById(nonExistingId);

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testInsertAddsInfoAndReturnsIt() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Desc");

        // WHEN
        Info inserted = repository.insert(info);

        // THEN
        assertThat(inserted).isSameAs(info);
        assertThat(repository.findAll()).containsExactly(info);
    }

    @Test
    void testReplaceUpdatesInfoWhenExists() {
        // GIVEN
        Info oldInfo = new Info();
        oldInfo.setId(repository.newId());
        oldInfo.setTitle("Old Title");
        oldInfo.setDescription("Old Desc");
        repository.insert(oldInfo);

        Info newInfo = new Info();
        newInfo.setId(oldInfo.getId());
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Desc");

        // WHEN
        Info result = repository.replace(oldInfo.getId(), newInfo);

        // THEN
        assertThat(result).isNull();
        Info found = repository.findById(oldInfo.getId());
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("New Title");
        assertThat(found.getDescription()).isEqualTo("New Desc");
    }

    @Test
    void testReplaceThrowsRuntimeExceptionWhenNotFound() {
        // GIVEN
        String nonExistingId = repository.newId();
        Info newInfo = new Info();
        newInfo.setId(nonExistingId);
        newInfo.setTitle("Title");
        newInfo.setDescription("Desc");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(nonExistingId, newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdReturnsTrueAndRemovesInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Desc");
        repository.insert(info);

        // WHEN
        boolean removed = repository.removeById(info.getId());

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findAll()).doesNotContain(info);
        assertThat(repository.findById(info.getId())).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN
        String nonExistingId = repository.newId();

        // WHEN
        boolean removed = repository.removeById(nonExistingId);

        // THEN
        assertThat(removed).isTrue();
    }
}
