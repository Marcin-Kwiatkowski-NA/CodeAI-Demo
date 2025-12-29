package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
public class MongoInfoEntityGeneratedAiTests {

    @Autowired
    private MongoInfoRepository mongoInfoRepository;

    @Test
    void shouldSaveAndFindMongoInfoEntity() {
        // Given
        MongoInfoEntity mongoInfoEntity = new MongoInfoEntity();
        mongoInfoEntity.setName("Test Mongo Info");
        mongoInfoEntity.setDescription("Test Description");

        // When
        MongoInfoEntity savedEntity = mongoInfoRepository.save(mongoInfoEntity);
        
        // Then
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo("Test Mongo Info");
        assertThat(savedEntity.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void shouldFindAllMongoInfoEntities() {
        // Given
        MongoInfoEntity mongoInfoEntity1 = new MongoInfoEntity();
        mongoInfoEntity1.setName("Test Mongo Info 1");
        mongoInfoEntity1.setDescription("Test Description 1");

        MongoInfoEntity mongoInfoEntity2 = new MongoInfoEntity();
        mongoInfoEntity2.setName("Test Mongo Info 2");
        mongoInfoEntity2.setDescription("Test Description 2");

        mongoInfoRepository.save(mongoInfoEntity1);
        mongoInfoRepository.save(mongoInfoEntity2);

        // When
        List<MongoInfoEntity> allEntities = mongoInfoRepository.findAll();

        // Then
        assertThat(allEntities).hasSize(2);
    }
}