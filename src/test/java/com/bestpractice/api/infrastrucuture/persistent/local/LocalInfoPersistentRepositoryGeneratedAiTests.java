package com.bestpractice.api.infrastrucuture.persistent.local;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_ShouldReturnUniqueNonNullString() {
        // GIVEN nothing

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull().isNotEmpty();
    }

    @Test
    void findAll_WhenRepositoryEmpty_ShouldReturnEmptyList() {
        // GIVEN repository with no data

        // WHEN
        List<Info> all = repository.findAll();

        // THEN
        assertThat(all).isEmpty();
    }

    @Test
    void insert_ShouldAddInfoAndReturnIt() {
        // GIVEN
        Info info = createInfo(repository.newId(), "Title", "Description");

        // WHEN
        Info inserted = repository.insert(info);

        // THEN
        assertThat(inserted).isSameAs(info);
        assertThat(repository.findAll()).containsExactly(info);
        assertThat(repository.findById(info.getId())).isSameAs(info);
    }

    @Test
    void findById_WhenInfoExists_ShouldReturnIt() {
        // GIVEN
        Info info = createInfo(repository.newId(), "Title", "Description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById(info.getId());

        // THEN
        assertThat(found).isSameAs(info);
    }

    @Test
    void findById_WhenInfoDoesNotExist_ShouldReturnNull() {
        // GIVEN

        // WHEN
        Info found = repository.findById("non-existent-id");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void replace_WhenIdExists_ShouldReplaceFirstNonMatchingElement() {
        // GIVEN
        Info info1 = createInfo("1", "Title1", "Desc1");
        Info info2 = createInfo("2", "Title2", "Desc2");
        repository.insert(info1);
        repository.insert(info2);

        Info newInfo = createInfo("3", "NewTitle", "NewDesc");

        // WHEN
        Info result = repository.replace("1", newInfo);

        // THEN
        assertThat(result).isNull();
        // The element with id "2" should be replaced
        assertThat(repository.findById("1")).isSameAs(info1);
        assertThat(repository.findById("2")).isNull();
        assertThat(repository.findById("3")).isSameAs(newInfo);
    }

    @Test
    void replace_WhenIdDoesNotExist_ShouldThrowRuntimeException() {
        // GIVEN
        Info newInfo = createInfo("3", "NewTitle", "NewDesc");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace("99", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist.");
    }

    @Test
    void removeById_WhenIdExists_ShouldRemoveAndReturnTrue() {
        // GIVEN
        Info info = createInfo(repository.newId(), "Title", "Description");
        repository.insert(info);

        // WHEN
        boolean removed = repository.removeById(info.getId());

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findById(info.getId())).isNull();
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void removeById_WhenIdDoesNotExist_ShouldReturnTrueWithoutChange() {
        // GIVEN
        Info info = createInfo(repository.newId(), "Title", "Description");
        repository.insert(info);
        int initialSize = repository.findAll().size();

        // WHEN
        boolean removed = repository.removeById("non-existent-id");

        // THEN
        assertThat(removed).isTrue();
        assertThat(repository.findAll()).hasSize(initialSize);
    }

    // Helper method to create Info instances
    private Info createInfo(String id, String title, String description) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(title);
        info.setDescription(description);
        return info;
    }
}
