package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CassandraUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private CassandraUserPersistentRepository cassandraUserPersistentRepository;

    @BeforeEach
    void setUp() {
        cassandraUserPersistentRepository = Mockito.mock(CassandraUserPersistentRepository.class);
    }

    @Test
    void testNewId() {
        // GIVEN
        String expectedId = "12345";
        when(cassandraUserPersistentRepository.newId()).thenReturn(expectedId);

        // WHEN
        String result = cassandraUserPersistentRepository.newId();

        // THEN
        assertThat(result).isEqualTo(expectedId);
    }

    @Test
    void testFindByEmail() {
        // GIVEN
        String email = "test@example.com";
        User mockUser = new User("1", "testuser", email, "password123");
        when(cassandraUserPersistentRepository.findByEmail(email)).thenReturn(mockUser);

        // WHEN
        User result = cassandraUserPersistentRepository.findByEmail(email);

        // THEN
        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    void testFindById() {
        // GIVEN
        String id = "1";
        User mockUser = new User(id, "testuser", "test@example.com", "password123");
        when(cassandraUserPersistentRepository.findById(id)).thenReturn(mockUser);

        // WHEN
        User result = cassandraUserPersistentRepository.findById(id);

        // THEN
        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    void testInsert() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password123");
        when(cassandraUserPersistentRepository.insert(user)).thenReturn(user);

        // WHEN
        User result = cassandraUserPersistentRepository.insert(user);

        // THEN
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testReplace() {
        // GIVEN
        String id = "1";
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword123");
        when(cassandraUserPersistentRepository.replace(id, user)).thenReturn(user);

        // WHEN
        User result = cassandraUserPersistentRepository.replace(id, user);

        // THEN
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testRemoveById() {
        // GIVEN
        String id = "1";
        when(cassandraUserPersistentRepository.removeById(id)).thenReturn(true);

        // WHEN
        boolean result = cassandraUserPersistentRepository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }
}
