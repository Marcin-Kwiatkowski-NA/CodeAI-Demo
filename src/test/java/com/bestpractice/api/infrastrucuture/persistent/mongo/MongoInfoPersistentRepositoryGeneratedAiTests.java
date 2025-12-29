package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MongoInfoPersistentRepository mongoInfoPersistentRepository;

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void findAll_ShouldReturnListOfInfo() {
        // GIVEN
        List<Info> infoList = List.of(info);
        when(mongoTemplate.findAll(Info.class)).thenReturn(infoList);

        // WHEN
        List<Info> result = mongoInfoPersistentRepository.findAll();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Title");
        verify(mongoTemplate, times(1)).findAll(Info.class);
    }

    @Test
    void findById_ShouldReturnInfo() {
        // GIVEN
        when(mongoTemplate.findOne(any(Query.class), eq(Info.class))).thenReturn(info);

        // WHEN
        Info result = mongoInfoPersistentRepository.findById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Info.class));
    }

    @Test
    void insert_ShouldSaveInfo() {
        // GIVEN
        when(mongoTemplate.save(info)).thenReturn(info);

        // WHEN
        Info result = mongoInfoPersistentRepository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(mongoTemplate, times(1)).save(info);
    }

    @Test
    void replace_ShouldUpdateInfo() {
        // GIVEN
        when(mongoTemplate.save(info)).thenReturn(info);

        // WHEN
        Info result = mongoInfoPersistentRepository.replace("1", info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(mongoTemplate, times(1)).save(info);
    }

    @Test
    void removeById_ShouldDeleteInfo() {
        // GIVEN
        doNothing().when(mongoTemplate).remove(any(Query.class), eq(Info.class));

        // WHEN
        boolean result = mongoInfoPersistentRepository.removeById("1");

        // THEN
        assertThat(result).isTrue();
        verify(mongoTemplate, times(1)).remove(any(Query.class), eq(Info.class));
    }
}
