package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        assertEquals(null, sharedData.getCreatedAt());
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt().getTime()).isLessThanOrEqualTo(System.currentTimeMillis());
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
    }

    @Test
    void testSetCreatedAtWithNullDoesNotThrowException() {
        Date nullDate = null;
        sharedData.setCreatedAt(nullDate);
        assertEquals(null, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtBoundaryDateEpoch() {
        Date boundaryDate = new Date(0);
        sharedData.setCreatedAt(boundaryDate);
        assertEquals(boundaryDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtBoundaryDateFarFuture() {
        Date futureDate = new Date(Long.MAX_VALUE);
        sharedData.setCreatedAt(futureDate);
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtBoundaryDateFarPast() {
        Date pastDate = new Date(Long.MIN_VALUE);
        sharedData.setCreatedAt(pastDate);
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistAfterSettingBoundaryDate() {
        Date pastDate = new Date(Long.MIN_VALUE);
        sharedData.setCreatedAt(pastDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isAfter(pastDate);
    }

    @Test
    void testSetCreatedAtWithSingleMillisecondDifference() {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = new Date(date1.getTime() + 1);
        sharedData.setCreatedAt(date1);
        sharedData.setCreatedAt(date2);
        assertEquals(date2, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithCurrentTimeBoundary() {
        Date now = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(now);
        assertEquals(now, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistImmediatelyAfterSetCreatedAt() {
        Date now = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(now);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt().getTime()).isGreaterThanOrEqualTo(now.getTime());
    }

    @Test
    void testSetCreatedAtWithSameDateReference() {
        Date sameDate = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(sameDate);
        sharedData.setCreatedAt(sameDate);
        assertEquals(sameDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotChangeIfAlreadyRecent() {
        Date recentDate = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(recentDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt().getTime()).isGreaterThanOrEqualTo(recentDate.getTime());
    }
}
