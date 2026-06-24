package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsUserPersistentRepository repository;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User("id123", "john_doe", "john@example.com", "securePassword");
    }

    @Test
    void newId_ShouldReturnNonNullUniqueId() {
        // GIVEN

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), ArgumentMatchers.<org.springframework.jdbc.core.DataClassRowMapper<User>>any(), Mockito.eq("john@example.com")))
                .thenReturn(sampleUser);

        // WHEN
        User result = repository.findByEmail("john@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john@example.com");
        Mockito.verify(jdbcTemplate).queryForObject(Mockito.anyString(), ArgumentMatchers.<org.springframework.jdbc.core.DataClassRowMapper<User>>any(), Mockito.eq("john@example.com"));
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), ArgumentMatchers.<org.springframework.jdbc.core.DataClassRowMapper<User>>any(), Mockito.eq("id123")))
                .thenReturn(sampleUser);

        // WHEN
        User result = repository.findById("id123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("id123");
        Mockito.verify(jdbcTemplate).queryForObject(Mockito.anyString(), ArgumentMatchers.<org.springframework.jdbc.core.DataClassRowMapper<User>>any(), Mockito.eq("id123"));
    }

    @Test
    void insert_ShouldReturnUser_WhenInsertSucceeds() {
        // GIVEN
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        // WHEN
        User result = repository.insert(sampleUser);

        // THEN
        assertThat(result).isEqualTo(sampleUser);
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.eq(sampleUser.getId()), Mockito.eq(sampleUser.getUsername()), Mockito.eq(sampleUser.getEmail()), Mockito.eq(sampleUser.getPassword()));
    }

    @Test
    void insert_ShouldThrowConflict_WhenDuplicateKeyExceptionOccurs() {
        // GIVEN
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenThrow(new DuplicateKeyException("Duplicate"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(sampleUser))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void replace_ShouldUpdateUserSuccessfully() {
        // GIVEN
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        // WHEN
        User result = repository.replace("id123", sampleUser);

        // THEN
        assertThat(result).isEqualTo(sampleUser);
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.eq(sampleUser.getUsername()), Mockito.eq(sampleUser.getEmail()), Mockito.eq(sampleUser.getPassword()), Mockito.eq("id123"));
    }

    @Test
    void removeById_ShouldReturnTrue_WhenDeleteSucceeds() {
        // GIVEN
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.eq("id123"))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("id123");

        // THEN
        assertThat(result).isTrue();
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.eq("id123"));
    }

    @Test
    void removeById_ShouldReturnFalse_WhenExceptionOccurs() {
        // GIVEN
        Mockito.doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(Mockito.anyString(), Mockito.anyString());

        // WHEN
        boolean result = repository.removeById("id123");

        // THEN
        assertThat(result).isFalse();
    }
}
