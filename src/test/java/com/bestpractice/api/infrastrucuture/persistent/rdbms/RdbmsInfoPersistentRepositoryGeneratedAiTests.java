package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void newId_shouldGenerateUniqueId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(UUID.fromString(id)).isInstanceOf(UUID.class);
    }

    @Test
    void findAll_shouldReturnListOfInfos() {
        // GIVEN
        List<Info> mockInfos = List.of(new Info(), new Info());
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockInfos);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("123");
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(mockInfo);

        // WHEN
        Info result = repository.findById("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(DataClassRowMapper.class), eq("123"));
    }

    @Test
    void insert_shouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        doNothing().when(jdbcTemplate).update(any(), eq(keyHolder));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
    }

    @Test
    void insert_shouldThrowConflictExceptionOnDuplicateKey() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        doThrow(org.springframework.dao.DuplicateKeyException.class).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class);
        verify(jdbcTemplate, times(1)).update(any(), any(KeyHolder.class));
    }

    @Test
    void replace_shouldUpdateInfo() {
        // GIVEN
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");
        doNothing().when(jdbcTemplate).update(anyString(), eq("Updated Title"), eq("Updated Description"), eq("123"));

        // WHEN
        Info result = repository.replace("123", info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        verify(jdbcTemplate, times(1)).update(anyString(), eq("Updated Title"), eq("Updated Description"), eq("123"));
    }

    @Test
    void removeById_shouldDeleteInfo() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(anyString(), eq("123"));

        // WHEN
        boolean result = repository.removeById("123");

        // THEN
        assertThat(result).isTrue();
        verify(jdbcTemplate, times(1).update(anyString(), eq("123"));
    }

    @Test
    void removeById_shouldReturnFalseOnException() {
        // GIVEN
        doThrow(RuntimeException.class).when(jdbcTemplate).update(anyString(), eq("123"));

        // WHEN
        boolean result = repository.removeById("123");

        // THEN
        assertThat(result).isFalse();
        verify(jdbcTemplate, times(1)).update(anyString(), eq("123"));
    }
}
