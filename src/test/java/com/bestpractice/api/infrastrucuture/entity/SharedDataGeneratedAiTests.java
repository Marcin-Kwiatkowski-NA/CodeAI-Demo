package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

    @BeforeEach
    public void setUp() {
        // Reset createdAt before each test
        SharedData sharedData = new SharedData();
        sharedData.setCreatedAt(null);
    }

    @Test
    public void testOnPrePersistSetsCreatedAtToCurrentTime() {
        // GIVEN: A new SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();
        // THEN: The createdAt field is set to the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    public void testGetCreatedAtReturnsCreatedAtDate() {
        // GIVEN: A new SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The getCreatedAt() method is called.
        Date createdAt = sharedData.getCreatedAt();
        // THEN: The getCreatedAt() method returns the createdAt date.
        assertNotNull(createdAt);
    }

    @Test
    public void testSetCreatedAtSetsNewDate() {
        // GIVEN: A new SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The setCreatedAt() method is called with a new Date object.
        Date newDate = new Date();
        sharedData.setCreatedAt(newDate);
        // THEN: The createdAt field is updated to the new date.
        assertEquals(newDate, sharedData.getCreatedAt());
    }

    @Test
    public void testGetCreatedAtBeforePersist() {
        // GIVEN: A new SharedData instance is created.
        SharedData sharedData = new SharedData();
        // WHEN: The getCreatedAt() method is called before any persistence operations.
        Date createdAt = sharedData.getCreatedAt();
        // THEN: The createdAt field is initialized to the current date and time.
        assertNotNull(createdAt);
    }
}
