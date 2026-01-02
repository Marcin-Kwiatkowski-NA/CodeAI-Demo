package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(jdbcTemplate);
    }

    @Test
    void newId_ShouldReturnNonEmptyUniqueString() {
        // GIVEN
        // No additional setup needed

        // WHEN
        String id = repository.newId();

        // THEN
        Assertions.assertThat(id)
                .isNotNull()
                .isNotEmpty()
                .matches("^[0-9a-fA-F-]{36}$");
    }

    @Test
    void findAll_ShouldReturnListOfInfos() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        List<Info> mockList = Arrays.asList(info1, info2);
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any()))
                .thenReturn(mockList);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        Assertions.assertThat(result)
                .isSameAs(mockList)
                .hasSize(2)
                .containsExactly(info1, info2);
    }

    @Test
    void findById_ShouldReturnInfoWhenFound() {
        // GIVEN
        String id = "123";
        Info expectedInfo = new Info();
        expectedInfo.setId(id);
        expectedInfo.setTitle("Test Title");
        expectedInfo.setDescription("Test Description");
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.eq(id)))
                .thenReturn(expectedInfo);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        Assertions.assertThat(result).isSameAs(expectedInfo);
    }

    @Test
    void insert_ShouldPersistInfoAndReturnIt() {
        // GIVEN
        Info info = new Info();
        info.setId("uuid-123");
        info.setTitle("Insert Title");
        info.setDescription("Insert Description");
        Mockito.doNothing().when(jdbcTemplate)
                .update(Mockito.any(), Mockito.any());

        // WHEN
        Info result = repository.insert(info);

        // THEN
        Assertions.assertThat(result).isSameAs(info);
        ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(jdbcTemplate).update(sqlCaptor.capture(), Mockito.any());
        Assertions.assertThat(sqlCaptor.getValue())
                .contains("INSERT INTO infos")
                .contains("?")
                .contains("?")
                .contains("?");
    }

    @Test
    void insert_WhenDuplicateKeyExceptionThrown_ShouldWrapInConflict() {
        // GIVEN
        Info info = new Info();
        info.setId("uuid-dup");
        info.setTitle("Duplicate Title");
        info.setDescription("Duplicate Description");
        Mockito.doThrow(new DuplicateKeyException("duplicate"))
                .when(jdbcTemplate).update(Mockito.any(), Mockito.any());

        // WHEN
        Conflict thrown = Assertions.assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(Conflict.class)
                .extracting(Throwable::getCause)
                .isInstanceOf(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getClass)
                .isEqualTo(DuplicateKeyException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate")
                .extracting(Throwable::getCause)
                .isNull()
                .extracting(Throwable::getMessage)
                .isEqualTo("duplicate");
    }

    @Test
    void replace_ShouldUpdateInfoAndReturnIt() {
        // GIVEN
        String id = "replace-123";
        Info info = new Info();
        info.setTitle("New Title");
        info.setDescription("New Description");
        Mockito.doNothing().when(jdbcTemplate).update(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any());

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        Assertions.assertThat(result).isSameAs(info);
        Mockito.verify(jdbcTemplate).update(
                Mockito.eq("UPDATE infos SET title = ?, description = ? WHERE id = ?"),
                Mockito.eq(info.getTitle()),
                Mockito.eq(info.getDescription()),
                Mockito.eq(id));
    }

    @Test
    void removeById_WhenDeleteSucceeds_ShouldReturnTrue() {
        // GIVEN
        String id = "delete-123";
        Mockito.doNothing().when(jdbcTemplate).update(Mockito.anyString(), Mockito.eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isTrue();
        Mockito.verify(jdbcTemplate).update(Mockito.eq("DELETE FROM infos WHERE id = ?"), Mockito.eq(id));
    }

    @Test
    void removeById_WhenExceptionThrown_ShouldReturnFalse() {
        // GIVEN
        String id = "delete-fail";
        Mockito.doThrow(new RuntimeException("db error")).when(jdbcTemplate).update(Mockito.anyString(), Mockito.eq(id));

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        Assertions.assertThat(result).isFalse();
    }
}
