package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        Date before = new Date();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        Date createdAt = sharedData.getCreatedAt();
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isCloseTo(before, within(1, TimeUnit.SECONDS));
    }

    @Test
    void testSetAndGetCreatedAt() {
        // GIVEN
        Date date = new Date();

        // WHEN
        sharedData.setCreatedAt(date);

        // THEN
        assertThat(sharedData.getCreatedAt()).isEqualTo(date);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);
        Date before = new Date();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        Date createdAt = sharedData.getCreatedAt();
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isNotEqualTo(oldDate);
        assertThat(createdAt).isCloseTo(before, within(1, TimeUnit.SECONDS));
    }
}
