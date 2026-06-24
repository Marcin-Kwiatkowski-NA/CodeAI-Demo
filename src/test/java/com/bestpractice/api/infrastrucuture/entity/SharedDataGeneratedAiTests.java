package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void shouldSetCreatedAtAndRetrieveItSuccessfully() {
        // GIVEN - a specific date to set
        Date expectedDate = new Date();

        // WHEN - setting the createdAt field
        sharedData.setCreatedAt(expectedDate);

        // THEN - verify that the getter returns the same date
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void shouldAutomaticallySetCreatedAtOnPrePersist() {
        // GIVEN - a new SharedData instance with no createdAt set
        assertThat(sharedData.getCreatedAt()).isNull();

        // WHEN - invoking onPrePersist
        sharedData.onPrePersist();

        // THEN - verify that createdAt is now set to a non-null value
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isBeforeOrEqualTo(new Date());
    }

    @Test
    void shouldOverrideExistingCreatedAtOnPrePersist() {
        // GIVEN - an existing createdAt value
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN - invoking onPrePersist to update createdAt
        sharedData.onPrePersist();

        // THEN - verify that createdAt has been updated to a newer timestamp
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
    }

    @Test
    void shouldHandleNullCreatedAtWithoutException() {
        // GIVEN - explicitly setting createdAt to null
        sharedData.setCreatedAt(null);

        // WHEN - invoking getCreatedAt
        Date result = sharedData.getCreatedAt();

        // THEN - verify that null is returned safely
        assertEquals(null, result);
    }

    @Test
    void shouldHandleBoundaryDateAtEpochStart() {
        // GIVEN - a boundary date at epoch start (0 milliseconds)
        Date epochStart = new Date(0);

        // WHEN - setting createdAt to epoch start
        sharedData.setCreatedAt(epochStart);

        // THEN - verify that the stored date matches epoch start
        assertEquals(epochStart, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(0);
    }

    @Test
    void shouldHandleBoundaryDateAtCurrentTimeMinusOneMillisecond() {
        // GIVEN - a boundary date just before current time
        Date boundaryDate = new Date(System.currentTimeMillis() - 1);

        // WHEN - setting createdAt to boundary date
        sharedData.setCreatedAt(boundaryDate);

        // THEN - verify that the stored date matches boundary date
        assertEquals(boundaryDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isLessThanOrEqualTo(System.currentTimeMillis());
    }

    @Test
    void shouldHandleBoundaryDateAtMaximumLongValue() {
        // GIVEN - a boundary date at Long.MAX_VALUE
        Date maxDate = new Date(Long.MAX_VALUE);

        // WHEN - setting createdAt to max date
        sharedData.setCreatedAt(maxDate);

        // THEN - verify that the stored date matches max date
        assertEquals(maxDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(Long.MAX_VALUE);
    }

    @Test
    void shouldHandleBoundaryDateAtMinimumLongValue() {
        // GIVEN - a boundary date at Long.MIN_VALUE
        Date minDate = new Date(Long.MIN_VALUE);

        // WHEN - setting createdAt to min date
        sharedData.setCreatedAt(minDate);

        // THEN - verify that the stored date matches min date
        assertEquals(minDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(Long.MIN_VALUE);
    }

    @Test
    void shouldHandleSingleMillisecondDifferenceBetweenDates() {
        // GIVEN - two dates differing by one millisecond
        Date firstDate = new Date(System.currentTimeMillis());
        Date secondDate = new Date(firstDate.getTime() + 1);

        // WHEN - setting createdAt to first date and then second date
        sharedData.setCreatedAt(firstDate);
        sharedData.setCreatedAt(secondDate);

        // THEN - verify that the stored date is the second one
        assertEquals(secondDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(firstDate.getTime() + 1);
    }

    @Test
    void shouldHandleBoundaryDateAtFutureTimePlusOneMillisecond() {
        // GIVEN - a boundary date slightly in the future
        long now = System.currentTimeMillis();
        Date futureDate = new Date(now + 1);

        // WHEN - setting createdAt to future date
        sharedData.setCreatedAt(futureDate);

        // THEN - verify that the stored date matches future date
        assertEquals(futureDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isGreaterThanOrEqualTo(now);
    }

    @Test
    void shouldNotChangeCreatedAtWhenSameDateIsSet() {
        // GIVEN - a specific date
        Date sameDate = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(sameDate);

        // WHEN - setting the same date again
        sharedData.setCreatedAt(sameDate);

        // THEN - verify that createdAt remains unchanged
        assertEquals(sameDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(sameDate.getTime());
    }

    @Test
    void shouldMaintainConsistencyWhenMultipleSequentialUpdatesOccur() {
        // GIVEN - multiple sequential updates
        Date firstDate = new Date(System.currentTimeMillis() - 5000);
        Date secondDate = new Date(System.currentTimeMillis() - 1000);
        Date thirdDate = new Date(System.currentTimeMillis());

        // WHEN - setting createdAt multiple times
        sharedData.setCreatedAt(firstDate);
        sharedData.setCreatedAt(secondDate);
        sharedData.setCreatedAt(thirdDate);

        // THEN - verify that the last update is retained
        assertEquals(thirdDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt().getTime()).isEqualTo(thirdDate.getTime());
    }

    @Test
    void shouldEnsureCreatedAtIsUpdatedToCurrentTimeOnPrePersist() {
        // GIVEN - an old date
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN - invoking onPrePersist
        sharedData.onPrePersist();

        // THEN - verify that createdAt is updated to a newer timestamp
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
        assertThat(sharedData.getCreatedAt().getTime()).isGreaterThan(oldDate.getTime());
    }

    @Test
    void shouldNotModifyCreatedAtIfAlreadySetToCurrentTime() {
        // GIVEN - a date equal to current time
        Date currentDate = new Date(System.currentTimeMillis());
        sharedData.setCreatedAt(currentDate);

        // WHEN - invoking onPrePersist immediately
        sharedData.onPrePersist();

        // THEN - verify that createdAt is not significantly changed (within small tolerance)
        long difference = sharedData.getCreatedAt().getTime() - currentDate.getTime();
        assertThat(Math.abs(difference)).isLessThan(100);
    }

    @Test
    void shouldHandleRapidSequentialPrePersistCalls() {
        // GIVEN - multiple rapid invocations
        sharedData.onPrePersist();
        Date firstPersistDate = sharedData.getCreatedAt();

        // WHEN - invoking onPrePersist again immediately
        sharedData.onPrePersist();
        Date secondPersistDate = sharedData.getCreatedAt();

        // THEN - verify that the second date is equal or after the first one
        assertThat(secondPersistDate).isAfterOrEqualTo(firstPersistDate);
    }
}
