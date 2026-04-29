package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN
        // Repository initialized

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testInsertAddsInfoSuccessfully() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN
        Info inserted = repository.insert(info);

        // THEN
        assertThat(inserted).isEqualTo(info);
        assertThat(repository.findAll()).contains(info);
    }

    @Test
    void testFindAllReturnsAllInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN
        List<Info> allInfos = repository.findAll();

        // THEN
        assertThat(allInfos).hasSize(2);
        assertThat(allInfos).containsExactlyInAnyOrder(info1, info2);
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        Info found = repository.findById("123");

        // THEN
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo("123");
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN
        // Empty repository

        // WHEN
        Info found = repository.findById("nonexistent");

        // THEN
        assertThat(found).isNull();
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Old Title");
        info1.setDescription("Old Description");
        repository.insert(info1);

        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        repository.insert(newInfo);

        // THEN
        Info found = repository.findById("1");
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("New Title");
        assertThat(found.getDescription()).isEqualTo("New Description");
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoDoesNotExist() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("999");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("999", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Data does not exist");
    }

    @Test
    void testRemoveByIdRemovesExistingInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findById("1")).isNull();
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN
        // Empty repository

        // WHEN
        boolean result = repository.removeById("nonexistent");

        // THEN
        assertThat(result).isTrue();
    }
}