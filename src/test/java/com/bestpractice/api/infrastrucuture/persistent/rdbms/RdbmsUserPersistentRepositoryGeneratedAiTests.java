package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplateMock;

    @BeforeEach
    void setUp() {
        jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplateMock);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplateMock = null;
        repository = null;
    }

    @Test
    void newId() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A UUID string should be returned.
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assertTrue(id.length() > 30, "UUID should be long enough");
    }

    @Test
    void findByEmail() {
        // GIVEN: A user with a specific email exists.
        String email = "test@example.com";
        User user = new User("1", "test", email, "password");
        Mockito.when(jdbcTemplateMock.queryForObject(Mockito.anyString(), Mockito.any(DataClassRowMapper.class), email)).thenReturn(user);

        // WHEN: findByEmail("test@example.com") is called.
        // THEN: The user with the specified email should be returned.
        User foundUser = repository.findByEmail(email);
        assertEquals(email, foundUser.getEmail(), "Email should match");
        assertEquals("test", foundUser.getUsername(), "Username should match");
    }

    @Test
    void findById() {
        // GIVEN: A user with a specific ID exists.
        String id = "1";
        User user = new User(id, "test", "test@example.com", "password");
        Mockito.when(jdbcTemplateMock.queryForObject(Mockito.anyString(), Mockito.any(DataClassRowMapper.class), id)).thenReturn(user);

        // WHEN: findById("1") is called.
        // THEN: The user with the specified ID should be returned.
        User foundUser = repository.findById(id);
        assertEquals(id, foundUser.getId(), "ID should match");
        assertEquals("test", foundUser.getUsername(), "Username should match");
    }

    @Test
    void insert() {
        // GIVEN: A new user object is provided.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: insert(user) is called.
        // THEN: The user should be inserted into the database, and the same user object should be returned.
        User insertedUser = repository.insert(user);
        assertEquals(user.getId(), insertedUser.getId(), "ID should match");
        assertEquals(user.getUsername(), insertedUser.getUsername(), "Username should match");
        assertEquals(user.getEmail(), insertedUser.getEmail(), "Email should match");
        assertEquals(user.getPassword(), insertedUser.getPassword(), "Password should match");
    }
}
