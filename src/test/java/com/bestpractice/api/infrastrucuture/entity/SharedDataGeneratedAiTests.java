package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        Date beforeInvocation = new Date();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt())
                .isNotNull()
                .isAfterOrEqualTo(beforeInvocation);
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
    void givenSharedDataInstance_whenGetCreatedAtCalled_thenReturnsCorrectValue() {
        // GIVEN
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);

        // WHEN
        Date actualDate = sharedData.getCreatedAt();

        // THEN
        assertThat(actualDate).isEqualTo(expectedDate);
    }
}
