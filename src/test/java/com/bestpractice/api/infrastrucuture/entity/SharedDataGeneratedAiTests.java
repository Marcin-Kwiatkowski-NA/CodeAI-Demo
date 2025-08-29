package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SharedData {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    @Test
    public void testOnPrePersistSetsCreatedAtToCurrentDate() {
        // GIVEN: A new SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();
        // THEN: The createdAt field is set to the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    public void testGetCreatedAtReturnsCreatedAtDate() {
        // GIVEN: A SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The getCreatedAt() method is called.
        Date createdAt = sharedData.getCreatedAt();
        // THEN: The getCreatedAt() method returns the createdAt date.
        assertNotNull(createdAt);
    }

    @Test
    public void testSetCreatedAtSetsNewDate() {
        // GIVEN: A SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The setCreatedAt() method is called with a new Date object.
        sharedData.setCreatedAt(new Date());
        // THEN: The createdAt field is updated to the new date.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    public void testGetCreatedAtReturnsDateAfterSet() {
        // GIVEN: A SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The setCreatedAt() method is called with a new Date object.
        sharedData.setCreatedAt(new Date());
        // THEN: The getCreatedAt() method returns the new date.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @BeforeEach
    public void beforeEachTest() {
        // Reset createdAt to a default value before each test.
        // This ensures that tests are independent and don't rely on previous test results.
        // This is important for reliable test execution.
        this.createdAt = null;
    }
}
