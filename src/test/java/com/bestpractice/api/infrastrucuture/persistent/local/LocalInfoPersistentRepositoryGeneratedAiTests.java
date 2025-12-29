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
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private InfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    public void givenEmptyList_whenFindAll_thenReturnsEmptyList() {
        // GIVEN: Empty list of infos
        // WHEN: Call findAll()
        List<Info> result = repository.findAll();

        // THEN: Should return an empty list
        assertThat(result).isEmpty();
    }

    @Test
    public void givenOneInfo_whenFindById_thenReturnsTheInfo() {
        // GIVEN: Create an Info instance with a known ID
        Info info = new Info();
        info.setId("info-1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: Insert the info and then find by ID
        repository.insert(info);
        Info found = repository.findById("info-1");

        // THEN: Should return the correct info
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Test Title");
        assertThat(found.getDescription()).isEqualTo("Test Description");
    }

    @Test
    public void givenNonExistingId_whenFindById_thenReturnsNull() {
        // GIVEN: No info exists with the given ID
        String nonExistingId = "non-existing-id";

        // WHEN: Attempt to find info with non-existing ID
        Info result = repository.findById(nonExistingId);

        // THEN: Should return null
        assertThat(result).isNull();
    }

    @Test
    public void givenValidInfo_whenInsert_thenInfoIsAddedToCollection() {
        // GIVEN: An empty repository and a new info instance
        Info info = new Info();
        info.setId("info-1");
        info.setTitle("New Title");
        info.setDescription("New Description");

        // WHEN: Insert the info
        Info inserted = repository.insert(info);

        // THEN: The info should be present in the list
        List<Info> allInfos = repository.findAll();
        assertThat(allInfos).hasSize(1);
        assertThat(allInfos.get(0)).isSameAs(inserted);
    }

    @Test
    public void givenExistingInfo_whenReplace_thenOldInfoIsReplaced() {
        // GIVEN: An existing info with ID "info-1"
        Info original = new Info();
        original.setId("info-1");
        original.setTitle("Old Title");
        original.setDescription("Old Description");

        repository.insert(original);

        // WHEN: Replace with a new info
        Info updated = new Info();
        updated.setId("info-1");
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Description");
        Info result = repository.replace("info-1", updated);

        // THEN: The info should be updated and the old one should be replaced
        assertThat(result).isNull();
        Info found = repository.findById("info-1");
        assertThat(found.getTitle()).isEqualTo("Updated Title");
        assertThat(found.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void givenNonExistingInfo_whenReplace_thenThrowsRuntimeException() {
        // GIVEN: No info exists with ID "non-existing-id"
        String nonExistingId = "non-existing-id";

        // WHEN: Attempt to replace with a new info
        Info newInfo = new Info();
        newInfo.setId("new-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // THEN: Should throw RuntimeException
        assertThatThrownBy(() -> repository.replace(nonExistingId, newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    public void givenExistingInfo_whenRemoveById_thenInfoIsRemoved() {
        // GIVEN: An existing info with ID "info-1"
        Info info = new Info();
        info.setId("info-1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        repository.insert(info);

        // WHEN: Remove the info by ID
        boolean result = repository.removeById("info-1");

        // THEN: The info should be removed and the list should no longer contain it
        List<Info> remaining = repository.findAll();
        assertThat(remaining).isEmpty();
        assertThat(result).isTrue();
    }

    @Test
    public void givenEmptyList_whenRemoveById_thenReturnsTrue() {
        // GIVEN: Empty list
        // WHEN: Attempt to remove by ID
        boolean result = repository.removeById("non-existing-id");

        // THEN: Should return true (no data found)
        assertThat(result    @Test
    public void givenIdWithMultipleInfos_whenFindById_thenReturnsFirstMatchingInfo() {
        // GIVEN: Two infos with the same ID (should not happen in real use, but test logic)
        Info info1 = new Info();
        info1.setId("info-1");
        info1.setTitle("Title 1");
        info1.setDescription("Desc 1");

        Info info2 = new Info();
        info2.setId("info-1");
        info2.setTitle("Title 2");
        info2.setDescription("Desc 2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN: Find by ID
        Info found = repository.findById("info-1");

        // THEN: Should return the first one found (based on insertion order)
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Title 1");
    }

    @Test
    public void givenNewIdGeneration_whenNewIdIsCalled_thenReturnsValidUUID() {
        // GIVEN: No pre-existing info
        // WHEN: Call newId()
        String generatedId = repository.newId();

        // THEN: Should return a non-empty, valid UUID string
        assertThat(generatedId).isNotNull();
        assertThat(generatedId).isNotEmpty();
        assertThat(generatedId).matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }
}
