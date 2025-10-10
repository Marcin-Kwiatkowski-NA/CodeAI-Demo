package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.mockito.MockitoExtension;

package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    private RdbmsUserPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = Mockito.of(JdbcTemplate.class);
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new RdbmsUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id);
        assertNotEquals("", id);
    }

    @Test
    void findByEmail_returnsUser() {
        // GIVEN: A User object with an email address.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The findByEmail() method is called with the email address.
        // THEN: The User object is returned.
        assertEquals(user, repository.findByEmail("test@example.com"));
    }

    @Test
    void findById_returnsUser() {
        // GIVEN: A User object with an id.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The findById() method is called with the id.
        // THEN: The User object is returned.
        assertEquals(user, repository.findById("1"));
    }

    @Test
    void insert_insertsUser() {
        // GIVEN: A User object.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The insert() method is called with the User object.
        // THEN: The User object is returned.
        assertEquals(user, repository.insert(user));
    }

    @Test
    void replace_replacesUser() {
        // GIVEN: A User object.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The replace() method is called with the id and the User object.
        // THEN: The User object is returned.
        assertEquals(user, repository.replace("1", user));
    }

    @Test
    void removeById_removesUser() {
        // GIVEN: A User object with an id.
        User user = new User("1", "test", "test@example.com", "password");
        // WHEN: The removeById() method is called with the id.
        // THEN: True is returned.
        assertTrue(repository.removeById("1"));
    }
}
