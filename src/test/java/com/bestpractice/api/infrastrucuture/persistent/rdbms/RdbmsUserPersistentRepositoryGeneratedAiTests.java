package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
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
        jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplateMock);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String generatedId = repository.newId();
        assertNotNull(generatedId, "Generated ID should not be null");
        assertTrue(generatedId.matches("[0-9a-f]{8,32}"), "Generated ID should be a valid UUID");
    }

    @Test
    void findByEmail_returnsUserById() {
        // GIVEN: A mock user object with email.
        User mockUser = new User("", "testUser", "test@example.com", "password");
        when(jdbcTemplateMock.queryForObject(any(String.class), any(DataClassRowMapper.class), any(String.class)))
                .thenReturn(mockUser);

        // WHEN: findByEmail("test@example.com") is called.
        // THEN: The mock user object is returned.
        User foundUser = repository.findByEmail("test@example.com");

        // Assert that the returned user has the correct email.
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findById_returnsUserById() {
        // GIVEN: A mock user object with id.
        User mockUser = new User("123", "testUser", "test@example.com", "password");
        when(jdbcTemplateMock.queryForObject(any(String.class), any(DataClassRowMapper.class), any(String.class)))
                .thenReturn(mockUser);

        // WHEN: findById("123") is called.
        // THEN: The mock user object is returned.
        User foundUser = repository.findById("123");

        // Assert that the returned user has the correct id.
        assertEquals("123", foundUser.getId());
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: A mock user object.
        User mockUser = new User("456", "newUser", "new@example.com", "newPassword");

        // WHEN: insert(mockUser) is called.
        // THEN: The user is inserted into the database, and the mock user object is returned.
        User insertedUser = repository.insert(mockUser);

        // Assert that the returned user has the correct id.
        assertEquals("456", insertedUser.getId());
    }

    @Test
    void replace_replacesUserAndReturnsUser() {
        // GIVEN: A mock user object.
        User mockUser = new User("789", "replacedUser", "replaced@example.com", "newPassword");

        // WHEN: replace("789", mockUser) is called.
        // THEN: The user is replaced in the database, and the mock user object is returned.
        User replacedUser = repository.replace("789", mockUser);

        // Assert that the returned user has the correct id.
        assertEquals("789", replacedUser.getId());
    }

    @Test
    void removeById_removesUserById() {
        // GIVEN: A mock user object with id.
        User mockUser = new User("123", "testUser", "test@example.com", "password");

        // WHEN: removeById("123") is called.
        // THEN: The user is removed from the database, and true is returned.
        boolean removed = repository.removeById("123");

        assertTrue(removed);
    }
}