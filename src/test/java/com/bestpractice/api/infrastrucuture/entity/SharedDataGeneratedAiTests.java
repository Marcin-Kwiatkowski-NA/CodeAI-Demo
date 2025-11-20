package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        assertThat(sharedData.getCreatedAt()).isNull();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isInstanceOf(Date.class);
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalled_thenCreatedAtIsUpdated() {
        // GIVEN
        Date newDate = new Date();

        // WHEN
        sharedData.setCreatedAt(newDate);

        // THEN
        assertThat(sharedData.getCreatedAt()).isEqualTo(newDate);
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAtCalled_thenCorrectValueIsReturned() {
        // GIVEN
        Date newDate = new Date();
        sharedData.setCreatedAt(newDate);

        // WHEN
        Date retrievedDate = sharedData.getCreatedAt();

        // THEN
        assertThat(retrievedDate).isEqualTo(newDate);
    }
}
