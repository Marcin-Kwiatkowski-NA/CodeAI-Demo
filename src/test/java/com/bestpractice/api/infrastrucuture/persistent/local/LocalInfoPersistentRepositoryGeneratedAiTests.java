package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_ShouldReturnUniqueNonNullString() {
        // GIVEN
        // (no preconditions)

        // WHEN
        String firstId = repository.newId();
        String secondId = repository.newId();

        // THEN
        assertThat(firstId).isNotNull().isNotEmpty();
        assertThat(secondId).isNotNull().isNotEmpty();
        assertThat(firstId).isNotEqualTo(secondId);
    }

    @Test
    void findAll_ShouldReturnAllInsertedInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title 1");
        info1.setDescription("Desc 1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title 2");
        info2.setDescription("Desc 2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN
        List<Info> allInfos = repository.findAll();

        // THEN
        assertThat(allInfos).containsExactly(info1, info2);
    }

    @Test
    void findById_WhenIdExists_ShouldReturnMatchingInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("unique-id");
        info.setTitle("Sample");
        info.setDescription("Sample description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById("unique-id");

        // THEN
        assertThat(found).isEqualTo(info);
    }

    @Test
    void findById_WhenIdDoesNotExist_ShouldReturnNull() {
        // GIVEN
        // (no infos inserted)

        // WHEN
        Info found = repository.findById("non-existent-id");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void insert_ShouldAddInfoAndReturnIt() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("new-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        Info inserted = repository.insert(newInfo);

        // THEN
        assertThat(inserted).isEqualTo(newInfo);
        assertThat(repository.findById("new-id")).isEqualTo(newInfo);
    }

    @Test
    void replace_WhenIdExists_ShouldUpdateInfo() {
        // GIVEN
        Info original = new Info();
        original.setId("replace-id");
        original.setTitle("Old Title");
        original.setDescription("Old Description");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replace-id");
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Description");

        // WHEN
        repository.replace("replace-id", updated);

        // THEN
        Info found = repository.findById("replace-id");
        assertThat(found).isEqualTo(updated);
        assertThat(found.getTitle()).isEqualTo("Updated Title");
        assertThat(found.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void replace_WhenIdDoesNotExist_ShouldThrowRuntimeException() {
        // GIVEN
        Info updated = new Info();
        updated.setId("missing-id");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("missing-id", updated))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void removeById_WhenIdExists_ShouldReturnTrueAndRemoveInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("remove-id");
        info.setTitle("To be removed");
        info.setDescription("Will be gone");
        repository.insert(info);

        // WHEN
        boolean removed = repository.removeById("remove-id");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById("remove-id")).isNull();
    }

    @Test
    void removeById_WhenIdDoesNotExist_ShouldReturnTrueWithoutError() {
        // GIVEN
        // (no infos inserted)

        // WHEN
        boolean removed = repository.removeById("non-existent-id");

        // THEN
        assertThat(removed).isTrue();
    }
}
