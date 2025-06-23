package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.regex.Pattern;

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

// GeneratedAiTests
import com.bestpractice.api.infrastrucuture.entity.SharedData;
import com.bestpractice.api.infrastrucuture.entity.SharedData.SharedData;
import com.bestpractice.api.infrastrucuture.entity.SharedData.SharedData.SharedData.SharedData.SharedData.SharedData.SharedData.SharedData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import java.util.Date;
import java.util.regex.Pattern;

class SharedDataTest {

    @Test
    void testCreateAndGetCreatedAt() {
        SharedData sharedData = new SharedData();
        assertEquals(1678886400, sharedData.getCreatedAt());
    }

    @Test
    void testCreateAndGetCreatedAtWithInvalidDate() {
        SharedData sharedData = new SharedData();
        assertFalse(sharedData.getCreatedAt(), "Expected false");
    }

    @Test
    void testCreateAndGetCreatedAtWithNullDate() {
        SharedData sharedData = new SharedData();
        assertFalse(sharedData.getCreatedAt(), "Expected false");
    }

    @Test
    void testCreateAndGetCreatedAtWithValidDate() {
        SharedData sharedData = new SharedData();
        assertEquals(1678886400, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        SharedData sharedData = new SharedData();
        sharedData.setCreatedAt(new Date());
        assertEquals(1678886400, sharedData.getCreatedAt());
    }

    @Test
    void testGetCreatedAt() {
        SharedData sharedData = new SharedData();
        assertEquals(1678886400, sharedData.getCreatedAt());
    }
}
