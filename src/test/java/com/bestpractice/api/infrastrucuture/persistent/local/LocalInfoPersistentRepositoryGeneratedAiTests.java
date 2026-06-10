package com.bestpractice.api.infrastrucuture.persistent.local;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

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
        Assertions.assertThat(id1).isNotNull();
        Assertions.assertThat(id2).isNotNull();
        Assertions.assertThat(id1).isNotEqualTo(id2);
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
        Assertions.assertThat(allInfos).isNotEmpty();
        Assertions.assertThat(allInfos).contains(info);
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
        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found.getId()).isEqualTo(id);
        Assertions.assertThat(found.getTitle()).isEqualTo("Title2");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        String nonExistentId = repository.newId();

        // WHEN
        Info found = repository.findById(nonExistentId);

        // THEN
        Assertions.assertThat(found).isNull();
    }

    @Test
    void testReplaceReplacesExistingInfo() {
        // GIVEN
        Info original = new Info();
        String id = repository.newId();
        original.setId(id);
        original.setTitle("Original");
        original.setDescription("Original Desc");
        repository.insert(original);

        Info updated = new Info();
        updated.setId(id);
        updated.setTitle("Updated");
        updated.setDescription("Updated Desc");

        // WHEN
        repository.replace(id, updated);
        Info found = repository.findById(id);

        // THEN
        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found.getTitle()).isEqualTo("Updated");
        Assertions.assertThat(found.getDescription()).isEqualTo("Updated Desc");
    }

    @Test
    void testReplaceThrowsExceptionWhenNotFound() {
        // GIVEN
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Nonexistent");
        info.setDescription("Nonexistent Desc");

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> repository.replace(info.getId(), info))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        // GIVEN
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("ToRemove");
        info.setDescription("ToRemove Desc");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById(id);
        Info found = repository.findById(id);

        // THEN
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(found).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN
        String nonExistentId = repository.newId();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        Assertions.assertThat(result).isTrue();
    }
}