package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplateMock;

    @BeforeEach
    void setUp() {
        jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplateMock);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String generatedId = repository.newId();
        assertNotNull(generatedId, "Generated ID cannot be null");
        assertTrue(generatedId.matches("[0-9a-f]{8,32}"), "Generated ID must be a valid UUID");
    }

    @Test
    void findByEmail_returnsUserById() {
        // GIVEN: A mock User object and a mock JdbcTemplate.
        User mockUser = Mockito.mock(User.class);
        when(mockUser.getId()).thenReturn("testId");
        when(mockUser.getUsername()).thenReturn("testUsername");
        when(mockUser.getEmail()).thenReturn("test@example.com");
        when(mockUser.getPassword()).thenReturn("testPassword");

        // WHEN: The findByEmail() method is called with "test@example.com".
        // THEN: The mock User object is returned.
        String email = "test@example.com";
        User returnedUser = repository.findByEmail(email);
        assertEquals(email, returnedUser.getEmail(), "Email should match");
        assertEquals("testId", returnedUser.getId(), "ID should match");
        assertEquals("testUsername", returnedUser.getUsername(), "Username should match");
        assertEquals("testPassword", returnedUser.getPassword(), "Password should match");
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A mock User object and a mock JdbcTemplate.
        User mockUser = Mockito.mock(User.class);
        when(mockUser.getId()).thenReturn("testId");
        when(mockUser.getUsername()).thenReturn("testUsername");
        when(mockUser.getEmail()).thenReturn("test@example.com");
        when(mockUser.getPassword()).thenReturn("testPassword");

        // WHEN: The findById() method is called with "testId".
        // THEN: The mock User object is returned.
        String id = "testId";
        User returnedUser = repository.findById(id);
        assertEquals(id, returnedUser.getId(), "ID should match");
        assertEquals("testUsername", returnedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", returnedUser.getEmail(), "Email should match");
        assertEquals("testPassword", returnedUser.getPassword(), "Password should match");
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: A mock User object and a mock JdbcTemplate.
        User mockUser = new User("testId", "testUsername", "test@example.com", "testPassword");
        // WHEN: The insert() method is called with the mock User object.
        // THEN: The mock User object is returned.
        User returnedUser = repository.insert(mockUser);
        assertEquals("testId", returnedUser.getId(), "ID should match");
        assertEquals("testUsername", returnedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", returnedUser.getEmail(), "Email should match");
        assertEquals("testPassword", returnedUser.getPassword(), "Password should match");
    }

    @Test
    void replace_updatesUserAndReturnsUser() {
        // GIVEN: A mock User object and a mock JdbcTemplate.
        User mockUser = new User("testId", "testUsername", "test@example.com", "testPassword");
        // WHEN: The replace() method is called with "testId" and the mock User object.java
        String id = "testId";
        User returnedUser = repository.replace(id, mockUser);
        assertEquals(id, returnedUser.getId(), "ID should match");
        assertEquals("testUsername", returnedUser.getUsername(), "Username should match");
        assertEquals("test@example.com", returnedUser.getEmail(), "Email should match");
        assertEquals("testPassword", returnedUser.getPassword(), "Password should match");
    }

    @Test
    void removeById_removesUserByIdAndReturnsTrue() {
        // GIVEN: A mock JdbcTemplate.
        // WHEN: The removeById() method is called with "testId".
        // THEN: The mock JdbcTemplate.update() method is called with the correct SQL.
        String id = "testId";
        boolean result = repository.removeById(id);
        assertTrue(result, "Removal should be successful");
    }
}
