package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
        // Proper initialization of the mock repository
        cassandraUserPersistentRepository = Mockito.mock(CassandraUserPersistentRepository.class);
    }

    @Test
    void testNewId() {
        // GIVEN
        when(cassandraUserPersistentRepository.newId()).thenReturn("12345");

        // WHEN
        String newId = cassandraUserPersistentRepository.newId();

        // THEN
        assertThat(newId).isEqualTo("12345");
    }

    @Test
    void testFindByEmail() {
        // GIVEN
        String email = "test@example.com";
        User mockUser = new User("1", "testUser", email, "password123");
        when(cassandraUserPersistentRepository.findByEmail(email)).thenReturn(mockUser);

        // WHEN
        User user = cassandraUserPersistentRepository.findByEmail(email);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void testFindById() {
        // GIVEN
        String id = "1";
        User mockUser = new User(id, "testUser", "test@example.com", "password123");
        when(cassandraUserPersistentRepository.findById(id)).thenReturn(mockUser);

        // WHEN
        User user = cassandraUserPersistentRepository.findById(id);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void testInsert() {
        // GIVEN
        User userToInsert = new User("1", "testUser", "test@example.com", "password123");
        when(cassandraUserPersistentRepository.insert(userToInsert)).thenReturn(userToInsert);

        // WHEN
        User insertedUser = cassandraUserPersistentRepository.insert(userToInsert);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo("1");
    }

    @Test
    void testReplace() {
        // GIVEN
        String id = "1";
        User userToReplace = new User(id, "updatedUser", "updated@example.com", "newPassword123");
        when(cassandraUserPersistentRepository.replace(id, userToReplace)).thenReturn(userToReplace);

        // WHEN
        User replacedUser = cassandraUserPersistentRepository.replace(id, userToReplace);

        // THEN
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getUsername()).isEqualTo("updatedUser");
    }

    @Test
    void testRemoveById() {
        // GIVEN
        String id = "1";
        when(cassandraUserPersistentRepository.removeById(id)).thenReturn(true);

        // WHEN
        boolean isRemoved = cassandraUserPersistentRepository.removeById(id);

        // THEN
        assertThat(isRemoved).isTrue();
    }
}
