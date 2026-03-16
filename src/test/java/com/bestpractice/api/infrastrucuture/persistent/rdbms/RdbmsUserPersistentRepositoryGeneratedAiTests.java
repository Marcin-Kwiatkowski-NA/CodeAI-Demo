package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RdbmsUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private RdbmsUserPersistentRepository repository;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        repository = new RdbmsUserPersistentRepository(jdbcTemplate);
        sampleUser = new User("id123", "john_doe", "john@example.com", "securePassword");
    }

    @Test
    void testNewIdGeneratesUniqueUUID() {
        // GIVEN
        // No setup required

        // WHEN
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
        assertThat(UUID.fromString(id1)).isInstanceOf(UUID.class);
    }

    @Test
    void testFindByEmailReturnsUser() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("john@example.com")))
                .thenReturn(sampleUser);

        // WHEN
        User result = repository.findByEmail("john@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john@example.com");
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("john@example.com"));
    }

    @Test
    void testFindByIdReturnsUser() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("id123")))
                .thenReturn(sampleUser);

        // WHEN
        User result = repository.findById("id123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("id123");
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("id123"));
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.insert(sampleUser);

        // THEN
        assertThat(result).isEqualTo(sampleUser);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(sampleUser.getId()), eq(sampleUser.getUsername()), eq(sampleUser.getEmail()), eq(sampleUser.getPassword()));
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DuplicateKeyException("Duplicate"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(sampleUser))
                .isInstanceOf(Conflict.class);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any());
    }

    @Test
    void testReplaceUpdatesUser() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // WHEN
        User result = repository.replace("id123", sampleUser);

        // THEN
        assertThat(result).isEqualTo(sampleUser);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(sampleUser.getUsername()), eq(sampleUser.getEmail()), eq(sampleUser.getPassword()), eq("id123"));
    }

    @Test
    void testRemoveByIdSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), eq("id123"))).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("id123");

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1)).update(anyString(), eq("id123"));
    }

    @Test
    void testRemoveByIdFailure() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), eq("id123"))).thenThrow(new RuntimeException("Error"));

        // WHEN
        boolean result = repository.removeById("id123");

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(anyString(), eq("id123"));
    }
}
