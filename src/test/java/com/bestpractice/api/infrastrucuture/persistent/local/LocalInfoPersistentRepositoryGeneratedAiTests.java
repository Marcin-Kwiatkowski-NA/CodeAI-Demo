package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    public void testNewIdGeneratesUniqueId() {
        // GIVEN no specific setup

        // WHEN generating a new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN the IDs should be unique and non-null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    public void testFindAllReturnsInsertedInfos() {
        // GIVEN an inserted Info object
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN retrieving all infos
        List<Info> infos = repository.findAll();

        // THEN the list should contain the inserted info
        assertNotNull(infos);
        assertTrue(infos.contains(info));
    }

    @Test
    public void testFindByIdReturnsCorrectInfo() {
        // GIVEN an inserted Info object with a known ID
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN finding by the known ID
        Info found = repository.findById("123");

        // THEN the returned Info should match the inserted one
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
    }

    @Test
    public void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN no matching Info in repository

        // WHEN finding by a non-existent ID
        Info found = repository.findById("non-existent");

        // THEN the result should be null
        assertNull(found);
    }

    @Test
    public void testInsertAddsInfo() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("abc");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN inserting the Info
        Info inserted = repository.insert(info);

        // THEN the inserted Info should be returned and present in repository
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    public void testReplaceUpdatesExistingInfo() {
        // GIVEN an existing Info in repository
        Info oldInfo = new Info();
        oldInfo.setId("id1");
        oldInfo.setTitle("Old");
        oldInfo.setDescription("Old desc");
        repository.insert(oldInfo);

        Info newInfo = new Info();
        newInfo.setId("id1");
        newInfo.setTitle("New");
        newInfo.setDescription("New desc");

        // WHEN replacing the existing Info
        repository.replace("id1", newInfo);

        // THEN the repository should contain the new Info
        Info found = repository.findById("id1");
        assertNotNull(found);
        assertEquals("New", found.getTitle());
        assertEquals("New desc", found.getDescription());
    }

    @Test
    public void testReplaceThrowsWhenInfoDoesNotExist() {
        // GIVEN no matching Info in repository
        Info newInfo = new Info();
        newInfo.setId("id2");
        newInfo.setTitle("Title");
        newInfo.setDescription("Desc");

        // WHEN & THEN replacing should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("id2", newInfo));
    }

    @Test
    public void testRemoveByIdRemovesInfo() {
        // GIVEN an inserted Info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN removing by ID
        boolean result = repository.removeById("removeId");

        // THEN the result should be true and the Info should be removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN no matching Info in repository

        // WHEN removing by a non-existent ID
        boolean result = repository.removeById("non-existent");

        // THEN the result should be true
        assertTruepackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    public void testNewIdGeneratesUniqueId() {
        // GIVEN no specific setup

        // WHEN generating a new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN the IDs should be unique and non-null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    public void testFindAllReturnsInsertedInfos() {
        // GIVEN an inserted Info object
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN retrieving all infos
        List<Info> infos = repository.findAll();

        // THEN the list should contain the inserted info
        assertNotNull(infos);
        assertTrue(infos.contains(info));
    }

    @Test
    public void testFindByIdReturnsCorrectInfo() {
        // GIVEN an inserted Info object with a known ID
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN finding by the known ID
        Info found = repository.findById("123");

        // THEN the returned Info should match the inserted one
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
    }

    @Test
    public void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN no matching Info in repository

        // WHEN finding by a non-existent ID
        Info found = repository.findById("non-existent");

        // THEN the result should be null
        assertNull(found);
    }

    @Test
    public void testInsertAddsInfo() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("abc");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN inserting the Info
        Info inserted = repository.insert(info);

        // THEN the inserted Info should be returned and present in repository
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    public void testReplaceUpdatesExistingInfo() {
        // GIVEN an existing Info in repository
        Info oldInfo = new Info();
        oldInfo.setId("id1");
        oldInfo.setTitle("Old");
        oldInfo.setDescription("Old desc");
        repository.insert(oldInfo);

        Info newInfo = new Info();
        newInfo.setId("id1");
        newInfo.setTitle("New");
        newInfo.setDescription("New desc");

        // WHEN replacing the existing Info
        repository.replace("id1", newInfo);

        // THEN the repository should contain the new Info
        Info found = repository.findById("id1");
        assertNotNull(found);
        assertEquals("New", found.getTitle());
        assertEquals("New desc", found.getDescription());
    }

    @Test
    public void testReplaceThrowsWhenInfoDoesNotExist() {
        // GIVEN no matching Info in repository
        Info newInfo = new Info();
        newInfo.setId("id2");
        newInfo.setTitle("Title");
        newInfo.setDescription("Desc");

        // WHEN & THEN replacing should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("id2", newInfo));
    }

    @Test
    public void testRemoveByIdRemovesInfo() {
        // GIVEN an inserted Info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN removing by ID
        boolean result = repository.removeById("removeId");

        // THEN the result should be true and the Info should be removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }
