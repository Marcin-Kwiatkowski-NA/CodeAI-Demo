package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenInfoNotFound() {
        // GIVEN - repository is empty

        // WHEN - remove bypackage com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new IDs
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - verify IDs are unique and not null
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - verify list contains inserted infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById("123");

        // THEN - verify correct info is returned
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - repository is empty

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing");

        // THEN - verify null is returned
        assertNull(found);
    }

    @Test
    void testInsertAddsInfoToRepository() {
        // GIVEN - create info
        Info info = new Info();
        info.setId("insertId");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDescription");

        // WHEN - insert info
        Info returned = repository.insert(info);

        // THEN - verify info is added
        assertEquals(info, returned);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info original = new Info();
        original.setId("replaceId");
        original.setTitle("OriginalTitle");
        original.setDescription("OriginalDescription");
        repository.insert(original);

        Info updated = new Info();
        updated.setId("replaceId");
        updated.setTitle("UpdatedTitle");
        updated.setDescription("UpdatedDescription");

        // WHEN - replace info
        Info result = repository.replace("replaceId", updated);

        // THEN - verify info is replaced
        assertNull(result);
        Info found = repository.findById("replaceId");
        assertEquals("UpdatedTitle", found.getTitle());
        assertEquals("UpdatedDescription", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - repository is empty
        Info updated = new Info();
        updated.setId("nonExistingId");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("nonExistingId", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN - insert info
        Info info = new Info();
        info.setId("removeId");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - remove by id
        boolean result = repository.removeById("removeId");

        // THEN - verify info is removed
        assertTrue(result);
        assertNull(repository.findById("removeId"));
    }
