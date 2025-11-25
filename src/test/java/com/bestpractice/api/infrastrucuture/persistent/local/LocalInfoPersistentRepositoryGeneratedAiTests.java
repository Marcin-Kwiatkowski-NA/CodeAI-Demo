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
import java.util.Objects;
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
    void findById_shouldReturnNullIfNotFound() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        Info result = repository.findById(nonExistentId);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void insert_shouldAddInfoToRepository() {
        // GIVEN
        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN
        repository.insert(info);
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).containsExactly(info);
    }

    @Test
    void replace_shouldReplaceExistingInfo() {
        // GIVEN
        Info oldInfo = new Info();
        oldInfo.setId(UUID.randomUUID().toString());
        oldInfo.setTitle("Old Title");
        oldInfo.setDescription("Old Description");

        Info newInfo = new Info();
        newInfo.setId(oldInfo.getId());
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        repository.insert(oldInfo);

        // WHEN
        repository.replace(oldInfo.getId(), newInfo);
        Info result = repository.findById(oldInfo.getId());

        // THEN
        assertThat(result).isEqualTo(newInfo);
    }

    @Test
    void replace_shouldThrowExceptionIfInfoDoesNotExist() {
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
    void removeById_shouldRemoveInfoFromRepository() {
        // GIVEN
        Info info = new Info();
        String id = UUID.randomUUID().toString();
        info.setId(id);
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        repository.insert(info);

        // WHEN
        boolean result = repository.removeById(id);
        Info removedInfo = repository.findById(id);

        // THEN
        assertThat(result).isTrue();
        assertThat(removedInfo).isNull();
    }

    @Test
    void removeById_shouldReturnTrueIfInfoDoesNotExist() {
        // GIVEN
        String nonExistentId = UUID.randomUUID().toString();

        // WHEN
        boolean result = repository.removeById(nonExistentId);

        // THEN
        assertThat(result).isTrue();
    }
}

package com.bestpractice.api.infrastrucuture.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Info extends SharedData {

    @Column(name = "id")
    private String id;

    @NotNull
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @Column(nullable = false, name = "description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(id, info.id) &&
                Objects.equals(title, info.title) &&
                Objects.equals(description, info.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
