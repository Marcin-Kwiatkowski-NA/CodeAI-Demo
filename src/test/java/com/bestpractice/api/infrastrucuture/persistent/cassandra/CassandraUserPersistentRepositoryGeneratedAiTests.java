package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

class CassandraUserPersistentRepositoryGeneratedAiTests {

    @Test
    void shouldGenerateNewId() {
        // GIVEN
        // WHEN
        String generatedId = new CassandraUserPersistentRepository().newId();

        // THEN
        assertThat(generatedId).isNotNull();
        assertThat(generatedId).isNotEmpty();
    }

    @Test
    void shouldFindUserByEmail() {
        // GIVEN
        String email = "test@example.com";
        User expectedUser = new User("id-1", "username", email, "password");

        // WHEN
        User foundUser = new CassandraUserPersistentRepository().findByEmail(email);

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldFindUserById() {
        // GIVEN
        String id = "id-1";
        User expectedUser = new User(id, "username", "email@example.com", "password");

        // WHEN
        User foundUser = new CassandraUserPersistentRepository().findById(id);

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(id);
    }

    @Test
    void shouldInsertUser() {
        // GIVEN
        User user = new User("id-1", "username", "email@example.com", "password");

        // WHEN
        User insertedUser = new CassandraUserPersistentRepository().insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getId()).isEqualTo(user.getId());
    }

    @Test
    void shouldReplaceUser() {
        // GIVEN
        String id = "id-1";
        User existingUser = new User(id, "old-username", "old-email@example.com", "old-password");
        User updatedUser = new User(id, "new-username", "new-email@example.com", "new-password");

        // WHEN
        User replacedUser = new CassandraUserPersistentRepository().replace(id, updatedUser);

        // THEN
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getUsername()).isEqualTo("new-username");
    }

    @Test
    void shouldRemoveUserById() {
        // GIVEN
        String id = "id-1";

        // WHEN
        boolean result = new CassandraUserPersistentRepository().removeById(id);

        // THEN
        assertThat(result).isTrue();
    }
}
