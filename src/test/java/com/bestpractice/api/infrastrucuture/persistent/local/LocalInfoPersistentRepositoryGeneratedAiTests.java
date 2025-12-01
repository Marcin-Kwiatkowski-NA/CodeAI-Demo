package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No specific setup required

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void findAll_shouldReturnEmptyListInitially() {
        // GIVEN
        // No specific setup required

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void findById_shouldReturnNullWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info result = repository.findById(nonExistentId);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void findById_shouldReturnInfoWhenIdExists() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        Info result = repository.findById("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void insert_shouldAddInfoToRepository() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(repository.findAll()).containsExactly(info);
    }

    @Test
    void replace_shouldThrowExceptionWhenIdNotFound() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("non-existent-id", info))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void replace_shouldUpdateInfoWhenIdExists() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId("123");
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");
        repository.insert(originalInfo);

        Info updatedInfo = new Info();
        updatedInfo.setId("123");
        updatedInfo.setTitle("Updated Title");
        updatedInfo.setDescription("Updated Description");

        // WHEN
        try {
            repository.replace("123", updatedInfo);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Data does not exist.");
        }

        // THEN
        Info result = repository.findById("123");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void removeById_shouldReturnTrueWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldRemoveInfoWhenIdExists() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById("123");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("123")).isNull();
    }
}
