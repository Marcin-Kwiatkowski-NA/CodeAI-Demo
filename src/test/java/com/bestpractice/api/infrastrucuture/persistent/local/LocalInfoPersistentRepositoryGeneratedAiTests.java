package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

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
    void testInsertAddsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        // WHEN
        Info returned = repository.insert(info);
        // THEN
        assertThat(returned).isSameAs(info);
        assertThat(repository.findAll()).containsExactly(info);
        assertThat(repository.findById("1")).isEqualTo(info);
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // WHEN
        Info result = repository.findById("nonexistent");
        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN
        Info original = new Info();
        original.setId("1");
        original.setTitle("Original");
        original.setDescription("Original Description");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("1");
        updated.setTitle("Updated");
        updated.setDescription("Updated Description");
        // WHEN
        Info replaceResult = repository.replace("1", updated);
        // THEN
        assertThat(replaceResult).isNull();
        assertThat(repository.findById("1")).isEqualTo(updated);
        assertThat(repository.findAll()).containsExactly(updated);
    }

    @Test
    void testReplaceReturnsNullWhenUpdated() {
        // GIVEN
        Info original = new Info();
        original.setId("1");
        original.setTitle("Original");
        original.setDescription("Original Description");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("1");
        updated.setTitle("Updated");
        updated.setDescription("Updated Description");
        // WHEN
        Info result = repository.replace("1", updated);
        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testReplaceThrowsWhenNotFound() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("missing");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");
        // WHEN
        RuntimeException exception = null;
        try {
            repository.replace("missing", newInfo);
        } catch (RuntimeException e) {
            exception = e;
        }
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception).hasMessageContaining("Data does not exist.");
    }

    @Test
    void testRemoveByIdRemovesExisting() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);
        // WHEN
        boolean removed = repository.removeById("1");
        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("1")).isNull();
        assertThat(repository.findAll()).doesNotContain(info);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN
        // WHEN
        boolean result = repository.removeById("nonexistent");
        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testFindAllReturnsAllInsertedInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title 1");
        info1.setDescription("Description 1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title 2");
        info2.setDescription("Description 2");

        repository.insert(info1);
        repository.insert(info2);
        // WHEN
        List<Info> allInfos = repository.findAll();
        // THEN
        assertThat(allInfos).containsExactly(info1, info2);
    }
}
