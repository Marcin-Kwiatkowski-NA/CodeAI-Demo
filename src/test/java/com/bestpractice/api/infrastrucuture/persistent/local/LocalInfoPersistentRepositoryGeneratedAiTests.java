package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN
        // No preconditions required

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
        // No preconditions required

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void findById_shouldReturnNullWhenIdDoesNotExist() {
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
    void replace_shouldThrowExceptionWhenIdDoesNotExist() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("456");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("123", newInfo))
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
    void removeById_shouldReturnTrueWhenIdDoesNotExist() {
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
        booleanresult = repository.removeById("123");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("123")).isNull();
    }
}