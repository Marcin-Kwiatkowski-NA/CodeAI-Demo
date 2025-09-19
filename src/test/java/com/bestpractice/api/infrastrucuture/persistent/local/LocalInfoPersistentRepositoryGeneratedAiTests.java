package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.extension.InnerAutoDetectExtensionFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InnerAutoDetectExtensionFactory.class)
class LocalInfoPersistentRepositoryGeneratedAiTests {

    private final LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();

    @org.junit.jupiter.api.Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assert id != null;
        assert id.length() > 20; // UUIDs are typically longer than 20 characters
    }

    @org.junit.jupiter.api.Test
    void findAll_returnsAllInfo() {
        Info info1 = new Info();
        info1.setId("test1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        List<Info> allInfos = repository.findAll();
        assert allInfos != null;
        assert allInfos.size() == 1;
        assert allInfos.get(0).getId().equals("test1");
        assert allInfos.get(0).getTitle().equals("Title1");
        assert allInfos.get(0).getDescription().equals("Description1");
    }

    @org.junit.jupiter.api.Test
    void findById_returnsInfoById() {
        Info info1 = new Info();
        info1.setId("test1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info foundInfo = repository.findById("test1");
        assert foundInfo != null;
        assert foundInfo.getId().equals("test1");
        assert foundInfo.getTitle().equals("Title1");
        assert foundInfo.getDescription().equals("Description1");
    }

    @org.junit.jupiter.api.Test
    void insert_insertsInfoAndReturnsInfo() {
        Info info1 = new Info();
        info1.setId("test1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info insertedInfo = repository.insert(info1);

        assert insertedInfo != null;
        assert insertedInfo.getId().equals("test1");
        assert insertedInfo.getTitle().equals("Title1");
        assert insertedInfo.getDescription().equals("Description1");
    }

    @org.junit.jupiter.api.Test
    void replace_replacesInfoById() {
        Info info1 = new Info();
        info1.setId("test1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info updatedInfo = new Info();
        updatedInfo.setId("test1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDescription");
        repository.replace("test1", updatedInfo);

        Info foundInfo = repository.findById("test1");
        assert foundInfo != null;
        assert foundInfo.getId().equals("test1");
        assert foundInfo.getTitle().equals("NewTitle");
        assert foundInfo.getDescription().equals("NewDescription");
    }

    @org.junit.jupiter.api.Test
    void removeById_removesInfoById() {
        Info info1 = new Info();
        info1.setId("test1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        boolean removed = repository.removeById("test1");
        assert removed;
        assert repository.findAll().isEmpty();
    }
}