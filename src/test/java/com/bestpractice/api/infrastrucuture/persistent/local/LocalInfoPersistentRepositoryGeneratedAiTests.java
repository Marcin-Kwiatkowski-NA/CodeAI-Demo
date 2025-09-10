package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.InnerNameExclusions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@InnerNameExclusions
public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private final LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();

    @BeforeEach
    void setUp() {
        repository.infos.clear();
    }

    @org.junit.jupiter.api.Test
    void newId_returnsValidUUID() {
        String id = repository.newId();
        assert id != null;
        assert id.length() > 20;
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
        List<Info> allInfos = repository.findAll();
        assert allInfos.size() == 0;
    }
}
