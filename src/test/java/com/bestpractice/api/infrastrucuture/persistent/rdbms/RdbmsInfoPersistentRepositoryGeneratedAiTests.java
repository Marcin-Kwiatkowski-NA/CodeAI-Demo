package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
public class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(id).isNotEmpty();
    }

    @Test
    void testFindAllReturnsListOfInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(List.of(info1));

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
    }

    @Test
    void testFindByIdReturnsInfo() {
        // GIVEN
        when(jdbcTemplate.queryForObject(anyString(), any(DataClassRowMapper.class), anyString())).thenReturn(info);

        // WHEN
        Info result = repository.findById("some-id");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
    }

    @Test
    void testInsertSuccess() {
        // GIVEN
        doNothing().when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(jdbcTemplate, times(1)).update(any(), any(KeyHolder.class));
    }

    @Test
    void testInsertThrowsConflictOnDuplicateKey() {
        // GIVEN
        doThrow(new DuplicateKeyException("Duplicate")).when(jdbcTemplate).update(any(), any(KeyHolder.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(info)).isInstanceOf(Conflict.class);
    }

    @Test
    void testReplaceUpdatesInfo() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // WHEN
        Info result = repository.replace("some-id", info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any());
    }

    @Test
    void testRemoveByIdReturnsTrueOnSuccess() {
        // GIVEN
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        // WHEN
        boolean result = repository.removeById("some-id");

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveByIdReturnsFalseOnException() {
        // GIVEN
        doThrow(new RuntimeException("Error")).when(jdbcTemplate).update(anyString(), anyString());

        // WHEN
        boolean result = repository.removeById("some-id");

        // THEN
        assertThat(result).isFalse();
    }
}
