package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.within;

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
    void testOnPrePersistOverwritesExistingCreatedAt() {
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
    }

    @Test
    void testSetCreatedAtWithNullDoesNotThrowException() {
        Date nullDate = null;
        assertThatCode(() -> sharedData.setCreatedAt(nullDate)).doesNotThrowAnyException();
        assertEquals(null, sharedData.getCreatedAt());
    }

    @Test
    void testGetCreatedAtAfterNullSetReturnsNull() {
        sharedData.setCreatedAt(null);
        Date result = sharedData.getCreatedAt();
        assertEquals(null, result);
    }

    @Test
    void testOnPrePersistHandlesInternalExceptionGracefully() {
        SharedData faultySharedData = new SharedData() {
            @Override
            public void setCreatedAt(Date createdAt) {
                throw new RuntimeException("Simulated failure in setCreatedAt");
            }
        };
        RuntimeException thrown = assertThrows(RuntimeException.class, faultySharedData::onPrePersist);
        assertEquals("Simulated failure in setCreatedAt", thrown.getMessage());
    }

    @Test
    void testSetCreatedAtWithEpochBoundary() {
        Date epochDate = new Date(0L);
        sharedData.setCreatedAt(epochDate);
        assertEquals(epochDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(0L);
    }

    @Test
    void testSetCreatedAtWithFarFutureDate() {
        Date farFutureDate = new Date(Long.MAX_VALUE);
        sharedData.setCreatedAt(farFutureDate);
        assertEquals(farFutureDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(Long.MAX_VALUE);
    }

    @Test
    void testSetCreatedAtWithFarPastDate() {
        Date farPastDate = new Date(Long.MIN_VALUE);
        sharedData.setCreatedAt(farPastDate);
        assertEquals(farPastDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(Long.MIN_VALUE);
    }

    @Test
    void testOnPrePersistAfterSettingBoundaryDate() {
        Date boundaryDate = new Date(0L);
        sharedData.setCreatedAt(boundaryDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isAfter(boundaryDate);
    }

    @Test
    void testOnPrePersistCreatesNewDateInstance() {
        sharedData.setCreatedAt(new Date(0L));
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt().getTime()).isNotEqualTo(0L);
    }

    @Test
    void testSetCreatedAtWithCurrentTimeBoundary() {
        Date currentDate = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(currentDate);
        assertEquals(currentDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isCloseTo(System.currentTimeMillis(), within(50L));
    }

    @Test
    void testSetCreatedAtWithSingleMillisecondDifference() {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = new Date(date1.getTime() + 1);
        sharedData.setCreatedAt(date1);
        Date firstSet = sharedData.getCreatedAt();
        sharedData.setCreatedAt(date2);
        Date secondSet = sharedData.getCreatedAt();
        assertThat(secondSet).isAfter(firstSet);
        assertThat(secondSet.getTime() - firstSet.getTime()).isEqualTo(1L);
    }
}
