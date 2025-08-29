package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.Extension;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplateMock;

    @BeforeEach
    void setUp() {
        jdbcTemplateMock = Mockito.mockito();
        repository = new RdbmsUserPersistentRepository(jdbcTemplateMock);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String generatedId = repository.newId();
        assertNotNull(generatedId);
        assertTrue(generatedId.length() == 36);
    }

    @Test
    void findByEmail_returnsUserWhenEmailExists() {
        // GIVEN: A user with a specific email should exist in the database.
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setUsername("testUser");
        user.setId("123");

        when(jdbcTemplateMock.queryForObject(Mockito.anyString(), Mockito.any(DataClassRowMapper.class), email)).thenReturn(user);

        // WHEN: The findByEmail() method is called with the email "test@example.com".
        // THEN: The User object with the matching email is returned.
        User foundUser = repository.findByEmail(email);
        assertEquals(email, foundUser.getEmail());
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("123", foundUser.getId());
    }

    @Test
    void findById_returnsUserWhenIdExists() {
        // GIVEN: A user with a specific ID should exist in the database.
        String id = "123";
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        when(jdbcTemplateMock.queryForObject(Mockito.anyString(), Mockito.any(DataClassRowMapper.class), id)).thenReturn(user);

        // WHEN: The findById() method is called with the ID "123".
        // THEN: The User object with the matching ID is returned.
        User foundUser = repository.findById(id);
        assertEquals(id, foundUser.getId());
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void insert_insertsUserAndReturnsIt() {
        // GIVEN: A new User object is created.
        User user = new User("user123", "testUser", "test@example.com", "password123");

        // WHEN: The insert() method is called with the User object.
        // THEN: The User object is returned after the insertion.
        User insertedUser = repository.insert(user);
        assertEquals(user.getId(), insertedUser.getId());
        assertEquals(user.getUsername(), insertedUser.getUsername());
        assertEquals(user.getEmail(), insertedUser.getEmail());
        assertEquals(user.getPassword(), insertedUser.getPassword());
    }

    @Test
    void replace_updatesUserAndReturnsIt() {
        // GIVEN: A user with a specific ID should exist in the database.
        String id = "123";
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");java
        user.setEmail("newemail@example.com");

        when(jdbcTemplateMock.update(Mockito.anyString(), anyString(), anyString(), anyString())).thenReturn(1);

        // WHEN: The replace() method is called with the ID "123" and the updated User object.
        // THEN: The User object is returned after the update.
        User updatedUser = repository.replace(id, user);
        assertEquals(id, updatedUser.getId());
        assertEquals("testUser", updatedUser.getUsername());
        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    @Test
    void removeById_removesUserByIdAndReturnsTrue() {
        // GIVEN: A user with a specific ID should exist in the database.
        String id = "123";
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        // WHEN: The removeById() method is called with the ID "123".
        // THEN: The User object is removed from the database and true is returned.
        boolean removed = repository.removeById(id);
        assertTrue(removed);
    }
}
