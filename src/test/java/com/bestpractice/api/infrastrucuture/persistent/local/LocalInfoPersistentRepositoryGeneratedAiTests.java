package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No preconditions needed

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
        // No preconditions needed

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void findById_shouldReturnNullWhenNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info result = repository.findById(nonExistentId);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void findById_shouldReturnInfoWhenFound() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        Info result = repository.findById("test-id");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("test-id");
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void insert_shouldAddInfoToRepository() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(repository.findAll()).containsExactly(info);
    }

    @Test
    void replace_shouldReplaceExistingInfo() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId("test-id");
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");
        repository.insert(originalInfo);

        Info newInfo = new Info();
        newInfo.setId("test-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        Throwable thrown = catchThrowable(() -> {
            try {
                repository.replace("test-id", newInfo);
            } catch (RuntimeException e) {
                if ("Data does not exist.".equals(e.getMessage())) {
                    throw e;
                }
                throw new RuntimeException("Unexpected exception during replace operation", e);
            }
        });

        // THEN
        assertThat(thrown).isNull();
        Info replacedInfo = repository.findById("test-id");
        assertThat(replacedInfo).isNotNull();
        assertThat(replacedInfo.getTitle()).isEqualTo("New Title");
        assertThat(replacedInfo.getDescription()).isEqualTo("New Description");
    }

    @Test
    void replace_shouldThrowExceptionWhenIdNotFound() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("non-existent-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        Throwable thrown = catchThrowable(() -> repository.replace("non-existent-id", newInfo));

        // THEN
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Data does not exist.");
    }

    @Test
    void removeById_shouldRemoveInfoWhenIdExists() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById("test-id");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("test-id")).isNull();
    }

    @Test
    void removeById_shouldReturnTrueWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().@Test
    void removeById_shouldReturnTrueWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }
}
