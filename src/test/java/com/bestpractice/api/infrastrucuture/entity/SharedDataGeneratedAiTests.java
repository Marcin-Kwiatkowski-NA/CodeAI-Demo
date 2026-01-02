package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetCreatedAtReturnsNullBeforeSet() {
        // GIVEN
        // sharedData is freshly instantiated, createdAt is not set

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNull();
    }

    @Test
    void testSetCreatedAtSetsValue() {
        // GIVEN
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isSameAs(now);
    }

    @Test
    void testOnPrePersistSetsCreatedAtToCurrentTime() {
        // GIVEN
        // sharedData is freshly instantiated

        // WHEN
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNotNull();
        Date now = new Date();
        assertThat(createdAt.getTime()).isCloseTo(now.getTime(), within(1000));
    }

    @Test
    void testSetCreatedAtAllowsNull() {
        // GIVEN
        // sharedData is freshly instantiated

        // WHEN
        sharedData.setCreatedAt(null);
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNull();
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN
        Date earlier = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(earlier);

        // WHEN
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNotEqualTo(earlier);
        assertThat(createdAt).isNotNull();
        Date now = new Date();
        assertThat(createdAt.getTime()).isCloseTo(now.getTime(), within(1000));
    }
}
