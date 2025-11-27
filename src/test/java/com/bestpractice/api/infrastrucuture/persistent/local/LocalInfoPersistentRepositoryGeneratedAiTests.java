package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void findById_ShouldReturnNullWhenNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info result = repository.findById(nonExistentId);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void insert_ShouldAddInfoToRepository() {
        // GIVEN
        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertThat(insertedInfo).isNotNull();
        assertThat(repository.findAll()).containsExactly(info);
    }

    @Test
    void replace_ShouldReplaceExistingInfo() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId(UUID.randomUUID().toString());
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");

        Info newInfo = new Info();
        newInfo.setId(originalInfo.getId());
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        repository.insert(originalInfo);

        // WHEN
        Info replacedInfo = repository.replace(originalInfo.getId(), newInfo);

        // THEN
        assertThat(replacedInfo).isNotNull();
        assertThat(repository.findAll()).containsExactly(newInfo);
    }

    @Test
    void replace_ShouldThrowExceptionWhenInfoDoesNotExist() {
        // GIVEN
        Info newInfo = new Info();
        String nonExistentId = UUID.randomUUID().toString();
        newInfo.setId(nonExistentId);
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(nonExistentId, newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void removeById_ShouldRemoveInfoFromRepository() {
        // GIVEN
        Info info = new Info();
        String id = UUID.randomUUID().toString();
        info.setId(id);
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void removeById_ShouldReturnTrueWhenInfoDoesNotExist() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }
}