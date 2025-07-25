package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.entity.SharedData;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.mongodb.MongoCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraInfoPersistentRepository.class)
public class InfrastructureBeanGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Setup Cassandra session
        CqlSession session = new CqlSessionBuilder()
                .withLocalDatacenter("dc01")
                .withKeyspace(CqlIdentifier.fromCql("testdb"))
                .build();
    }

    @Test
    void testFindAllInfo() {
        // GIVEN: Create some sample data
        Info info1 = new Info("id1", "title1", "description1");
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        repository.insert(info1);

        // WHEN: Retrieve all info
        InfoPersistentRepository repository = new CassandraInfoPersistentRepository(session);
        List<Info> infos = repository.findAll();

        // THEN: Verify that the list is not empty and contains the expected info
        assertNotNull(infos);
        assertEquals(1, infos.size());
        assertEquals("title1", infos.get(0).getTitle());
        assertEquals("description1", infos.get(0).getDescription());
    }

    @Test
    void testFindByEmail() {
        // GIVEN: Setup Cassandra session
        CqlSession session = new CqlSessionBuilder()
                .withLocalDatacenter("dc01")
                .withKeyspace(CqlIdentifier.fromCql("testdb"))
                .build();

        // GIVEN: Create some sample data
        User user = new User("id1", "username1", "email1", "password1");
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        repository.insert(user);

        // WHEN: Retrieve user by email
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        User user = repository.findByEmail("email1");

        // THEN: Verify that the user is found
        assertNotNull(user);
        assertEquals("username1", user.getUsername());
        assertEquals("email1", user.getEmail());
    }

    @Test
    void testInsertUser() {
        // GIVEN: Setup Cassandra session
        CqlSession session = new CqlSessionBuilder()
                .withLocalDatacenter("dc01")
                .withKeyspace(CqlIdentifier.fromCql("testdb"))
                .build();

        // GIVEN: Create some sample data
        User user = new User("id1", "username1", "email1", "password1");
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        repository.insert(user);

        // WHEN: Insert user
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        User user = repository.insert(user);

        // THEN: Verify that the user is inserted
        assertNotNull(user);
        assertEquals("id1", user.getId());
        assertEquals("username1", user.getUsername());
        assertEquals("email1", user.getEmail());
        assertEquals("password1", user.getPassword());
    }

    @Test
    void testReplaceUser() {
        // GIVEN: Setup Cassandra session
        CqlSession session = new CqlSessionBuilder()
                .withLocalDatacenter("dc01")
                .withKeyspace(CqlIdentifier.fromCql("testdb"))
                .build();

        // GIVEN: Create some sample data
        User user = new User("id1", "username1", "email1", "password1");
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        repository.insert(user);

        // WHEN: Replace user```java
        User user = repository.replace("id1", new User("id1", "username1", "email1", "password1"));

        // THEN: Verify that the user is replaced
        assertNotNull(user);
        assertEquals("id1", user.getId());
        assertEquals("username1", user.getUsername());
        assertEquals("email1", user.getEmail());
        assertEquals("password1", user.getPassword());
    }

    @Test
    void testRemoveUser() {
        // GIVEN: Setup Cassandra session
        CqlSession session = new CqlSessionBuilder()
                .withLocalDatacenter("dc01")
                .withKeyspace(CqlIdentifier.fromCql("testdb"))
                .build();

        // GIVEN: Create some sample data
        User user = new User("id1", "username1", "email1", "password1");
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        repository.insert(user);

        // WHEN: Remove user
        UserPersistentRepository repository = new CassandraUserPersistentRepository(session);
        boolean removed = repository.removeById("id1");

        // THEN: Verify that the user is removed
        assertTrue(removed);
    }
}
