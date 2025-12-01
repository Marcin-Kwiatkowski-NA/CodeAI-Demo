package com.bestpractice.api.infrastrucuture.persistent.local;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class LocalInfoPersistentRepository implements InfoPersistentRepository {

    private final List<Info> infos = Collections.synchronizedList(new ArrayList<>());

    @Override
    public String newId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Info> findAll() {
        return this.infos;
    }

    @Override
    public Info findById(String id) {
        try {
            var info = this.infos.stream().filter(u -> u.getId().equals(id)).findFirst();
            return info.get();
        } catch (NullPointerException | NoSuchElementException ignored) {
            return null;
        }
    }

    @Override
    public Info insert(Info info) {
        this.infos.add(info);
        return info;
    }

    @Override
    public Info replace(String id, Info info) {
        Integer removeIndex = null;
        for (int i = 0; i < this.infos.size(); i++) {
            if (this.infos.get(i).getId().equals(id)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == null) {
            throw new RuntimeException("Data does not exist.");
        }

        this.infos.set(removeIndex, info);
        return info;
    }

    @Override
    public boolean removeById(String id) {
        Integer removeIndex = null;
        for (int i = 0; i < this.infos.size(); i++) {
            if (this.infos.get(i).getId().equals(id)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == null) {
            return true;
        }

        this.infos.remove((int) removeIndex);
        return true;
    }
}

package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindAll() {
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
        List<Info> infos = repository.findAll();

        // THEN
        assertThat(infos).hasSize(2);
        assertThat(infos).containsExactly(info1, info2);
    }

    @Test
    void testFindById() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN
        Info foundInfo = repository.findById("123");

        // THEN
        assertThat(foundInfo).isNotNull();
        assertThat(foundInfo.getId()).isEqualTo("123");
        assertThat(foundInfo.getTitle()).isEqualTo("Test Title");
        assertThat(foundInfo.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void testFindByIdNotFound() {
        // GIVEN
        // No specific setup required

        // WHEN
        Info foundInfo = repository.findById("nonexistent-id");

        // THEN
        assertThat(foundInfo).isNull();
    }

    @Test
    void testInsert() {
        // GIVEN
        Info info = new Info();
        info.setId("456");
        info.setTitle("Insert Title");
        info.setDescription("Insert Description");

        // WHEN
        Info insertedInfo = repository.insert(info);

        // THEN
        assertThat(insertedInfo).isNotNull();
        assertThat(insertedInfo.getId()).isEqualTo("456");
        assertThat(insertedInfo.getTitle()).isEqualTo("Insert Title");
        assertThat(insertedInfo.getDescription()).isEqualTo("Insert Description");
        assertThat(repository.findAll()).containsExactly(insertedInfo);
    }

    @Test
    void testReplace() {
        // GIVEN
        Info originalInfo = new Info();
        originalInfo.setId("789");
        originalInfo.setTitle("Original Title");
        originalInfo.setDescription("Original Description");
        repository.insert(originalInfo);

        Info newInfo = new Info();
        newInfo.setId("789");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        Info replacedInfo = repository.replace("789", newInfo);

        // THEN
        assertThat(replacedInfo).isNotNull();
        assertThat(replacedInfo.getId()).isEqualTo("789");
        assertThat(replacedInfo.getTitle()).isEqualTo("New Title");
        assertThat(replacedInfo.getDescription()).isEqualTo("New Description");
    }

    @Test
    void testReplaceNonExistentId() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("nonexistent-id");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");

        // WHEN
        RuntimeException exception = null;
        try {
            repository.replace("nonexistent-id", newInfo);
        } catch (RuntimeException e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Data does not exist.");
    }
}
