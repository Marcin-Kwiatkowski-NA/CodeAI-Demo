package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        repository.findAll().clear();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN

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
        String nonExistentId = "non-existent-id";
        Info newInfo = new Info();
        newInfo.setId(nonExistentId);
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(nonExistentId, newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void replace_shouldReplaceInfoWhenIdExists() {
        // GIVEN
        Info oldInfo = new Info();
        oldInfo.setId("123");
        oldInfo.setTitle("Old Title");
        oldInfo.setDescription("Old Description");
        repository.insert(oldInfo);

        Info newInfo = new Info();
        newInfo.setId("123");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        repository.replace("123", newInfo);

        // THEN
        Info result = repository.findById("123");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Description");
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
