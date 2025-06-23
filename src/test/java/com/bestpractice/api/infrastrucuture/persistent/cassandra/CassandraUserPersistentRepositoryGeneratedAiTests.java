package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.*;

package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

class CassandraUserPersistentRepositoryTest {

    @Test
    public void testNewId() {
        // Arrange
        User user = new User();
        assertEquals("1", user.getId());

        // Act
        UserPersistentRepository.findByEmail("test@example.com") = user;

        // Assert
        assertTrue(user.getId().equals(user));
    }

    @Test
    public void testFindByEmail(User user) {
        // Arrange
        User user = new User();
        assertEquals(1, user.getId());

        // Act
        UserPersistentRepository.findByEmail("test@example.com") = user;

        // Assert
        assertTrue(user.getId().equals(user));
    }

    @Test
    public void testFindById(User user) {
        // Arrange
        User user = new User();
        assertEquals(1, user.getId());

        // Act
        UserPersistentRepository.findById(1) = user;

        // Assert
        assertTrue(user.getId().equals(user));
    }

    @Test
    public void testInsert(User user) {
        // Arrange
        User user = new User();
        user.setId("123");
        

        // Act
        UserPersistentRepository.insert(user);

        // Assert
        assertEquals("123", user.getId());
    }

    @Test
    public void testReplace(User user) {
        // Arrange
        User user = new User();
        user.setId("123");
        

        // Act
        UserPersistentRepository.replace("123", user);

        // Assert
        assertEquals("123", user.getId());
    }

    @Test
    public void testRemoveById(User user) {
        // Arrange
        User user = new User();
        user.setId("123");
        

        // Act
        UserPersistentRepository.removeById("123", user);

        // Assert
        assertEquals("123", user.getId());
    }

    @Test
    public void testNullInput(User user) {
        // Arrange
        User user = new User();
        

        // Act
        UserPersistentRepository.insert(user);

        // Assert
        assertNull(user.getId());
    }

    @Test
    public void testNullInput(User user) {
        // Arrange
        User user = new User();
        

        // Act
        UserPersistentRepository.insert(user);

        // Assert
        assertNone(user.getId());
    }
}
