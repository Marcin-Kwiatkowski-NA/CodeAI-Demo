package com.bestpractice.api.infrastrucuture.persistent.local;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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
    void newId_ShouldGenerateUniqueId() {
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
    void findAll_ShouldReturnEmptyListInitially() {
        // GIVEN
        // No specific setup required

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isNotNull();
        assertThat(infos).isEmpty();
    }

    @Test
    void findById_ShouldReturnNullWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info result = repository.findById(nonExistentId);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void findById_ShouldReturnInfoWhenIdExists() {
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
    void insert_ShouldAddInfoToRepository() {
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
    void replace_ShouldThrowExceptionWhenIdNotFound() {
        // GIVEN
        Info info = new Info();
        info.setId("new-id");
        info.setTitle("New Title");
        info.setDescription("New Description");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("non-existent-id", info))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void replace_ShouldReplaceInfoWhenIdExists() {
        // GIVEN
        Info oldInfo = new Info();
        oldInfo.setId("test-id");
        oldInfo.setTitle("Old Title");
        oldInfo.setDescription("Old Description");
        repository.insert(oldInfo);

        Info newInfo = new Info();
        newInfo.setId("test-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        repository.replace("test-id", newInfo);

        // THEN
        Info result = repository.findById("test-id");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("test-id");
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Description");
    }

    @Test
    void removeById_ShouldReturnTrueWhenIdNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }