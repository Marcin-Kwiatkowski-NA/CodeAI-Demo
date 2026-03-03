package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        // sharedData is freshly instantiated, createdAt is null

        // WHEN
        sharedData.onPrePersist();

        // THEN
        Date createdAt = sharedData.getCreatedAt();
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isCloseToNow(ChronoUnit.SECONDS);
    }

    @Test
    void testSetAndGetCreatedAt() {
        // GIVEN
        Date pastDate = new Date(System.currentTimeMillis() - 100000);

        // WHEN
        sharedData.setCreatedAt(pastDate);
        Date retrievedDate = sharedData.getCreatedAt();

        // THEN
        assertThat(retrievedDate).isEqualTo(pastDate);
    }

    @Test
    void testOnPrePersistOverridesExistingDate() {
        // GIVEN
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        Date newCreatedAt = sharedData.getCreatedAt();
        assertThat(newCreatedAt).isNotNull();
        assertThat(newCreatedAt).isCloseToNow(ChronoUnit.SECONDS);
        assertThat(newCreatedAt).isNotEqualTo(oldDate);
    }
}
