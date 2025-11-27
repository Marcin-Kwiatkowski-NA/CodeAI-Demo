package com.bestpractice.api.infrastrucuture.persistent.local;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewId() {
        // GIVEN
        // No specific setup required

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isNotEmpty();
    }

    @Test
    void testFindAllWhenEmpty() {
        // GIVEN
        // Repository is initialized but no data is added

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).isEmpty();
    }

    @Test
    void testFindAllWithData() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(1);
        assertThat(infos.get(0).getId()).isEqualTo("1");
        assertThat(infos.get(0).getTitle()).isEqualTo("Title");
        assertThat(infos.get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdWhenExists() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN
        Info foundInfo = repository.findById("1");

        // THEN
        assertThat(foundInfo).isNotNull();
        assertThat(foundInfo.getId()).isEqualTo("1");
        assertThat(foundInfo.getTitle()).isEqualTo("Title");
        assertThat(foundInfo.getDescription()).isEqualTo("Description");
    }

    @Test
    void testFindByIdWhenNotExists() {
        // GIVEN
        // No data added to the repository

        // WHEN
        Info foundInfo = repository.findById("1");

        // THEN
        assertThat(foundInfo).isNull();
    }

    @Test
    void testInsert() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertThat(insertedInfo).isNotNull();
        assertThat(insertedInfo.getId()).isEqualTo("1");
        assertThat(insertedInfo.getTitle()).isEqualTo("Title");
        assertThat(insertedInfo.getDescription()).isEqualTo("Description");
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void testReplaceWhenExists() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId("1");
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");
        repository.insert(originalInfo);

        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN & THEN
        assertDoesNotThrow(() -> repository.replace("1", newInfo));

        // THEN
        Info replacedInfo = repository.findById("1");
        assertThat(replacedInfo).isNotNull();
        assertThat(replacedInfo.getId()).isEqualTo("1");
        assertThat(replacedInfo.getTitle()).isEqualTo("New Title");
        assertThat(replacedInfo.getDescription()).isEqualTo("New Description");
    }

    @Test
    void testReplaceWhenNotExists() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace("1", newInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Data does not exist.");
    }

    @Test
    void testRemoveByIdWhenExists() {
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
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void testRemoveByIdWhenNotExists() {
        // GIVEN
        // No data added to the repository

        // WHEN
        boolean result = repository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        assertThat(repository.findAll()).isEmpty();
    }
}