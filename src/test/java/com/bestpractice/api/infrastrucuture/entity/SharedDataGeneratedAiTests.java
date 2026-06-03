package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testSetAndGetCreatedAt() {
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        assertThat(sharedData.getCreatedAt()).isNull();
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isInstanceOf(Date.class);
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        Date oldDate = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(oldDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
    }

    @Test
    void testSetCreatedAtWithNullDoesNotThrowException() {
        Date nullDate = null;
        assertDoesNotThrow(() -> sharedData.setCreatedAt(nullDate));
        assertEquals(null, sharedData.getCreatedAt());
    }

    @Test
    void testGetCreatedAtWhenNullReturnsNull() {
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
        assertThatThrownBy(faultySharedData::onPrePersist)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Simulated failure in setCreatedAt");
    }

    @Test
    void testSetCreatedAtWithEpochBoundaryValue() {
        Date epochDate = new Date(0L);
        sharedData.setCreatedAt(epochDate);
        assertEquals(epochDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithFutureBoundaryValue() {
        Date farFutureDate = new Date(Long.MAX_VALUE);
        sharedData.setCreatedAt(farFutureDate);
        assertEquals(farFutureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithPastBoundaryValue() {
        Date farPastDate = new Date(Long.MIN_VALUE);
        sharedData.setCreatedAt(farPastDate);
        assertEquals(farPastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistAfterSettingBoundaryDate() {
        Date farPastDate = new Date(Long.MIN_VALUE);
        sharedData.setCreatedAt(farPastDate);
        sharedData.onPrePersist();
        assertThat(sharedData.getCreatedAt()).isAfter(farPastDate);
    }

    @Test
    void testSetCreatedAtWithSingleMillisecondDifference() {
        Date date1 = new Date(1000L);
        Date date2 = new Date(1001L);
        sharedData.setCreatedAt(date1);
        Date firstSet = sharedData.getCreatedAt();
        sharedData.setCreatedAt(date2);
        Date secondSet = sharedData.getCreatedAt();
        assertThat(secondSet).isAfter(firstSet);
    }

    @Test
    void testOnPrePersistDoesNotChangeIfCalledTwiceQuickly() {
        sharedData.onPrePersist();
        Date firstPersist = sharedData.getCreatedAt();
        sharedData.onPrePersist();
        Date secondPersist = sharedData.getCreatedAt();
        assertThat(secondPersist).isAfterOrEqualTo(firstPersist);
    }

    @Test
    void testSetCreatedAtWithCurrentTimeBoundary() {
        Date now = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(now);
        assertEquals(now, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithOneMillisecondBeforeNow() {
        Date beforeNow = new Date(System.currentTimeMillis() - 1);
        sharedData.setCreatedAt(beforeNow);
        assertEquals(beforeNow, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithOneMillisecondAfterNow() {
        Date afterNow = new Date(System.currentTimeMillis() + 1);
        sharedData.setCreatedAt(afterNow);
        assertEquals(afterNow, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistCreatesNewDateInstance() {
        sharedData.onPrePersist();
        Date firstDate = sharedData.getCreatedAt();
        sharedData.onPrePersist();
        Date secondDate = sharedData.getCreatedAt();
        assertThat(secondDate).isNotSameAs(firstDate);
        assertThat(secondDate).isAfterOrEqualTo(firstDate);
    }
}
