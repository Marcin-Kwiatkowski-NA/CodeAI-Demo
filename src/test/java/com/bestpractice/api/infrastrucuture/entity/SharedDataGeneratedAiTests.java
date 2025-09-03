package com.bestpractice.api.infrastrucuture.entity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    SharedData sharedData = new SharedData();

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void onPrePersist_setsCreatedAtToCurrentTimestamp() {
        // GIVEN: A new instance of SharedData is created.
        // WHEN: The onPrePersist method is called.
        // THEN: The createdAt field is set to the current date and time.
        sharedData.onPrePersist();
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now, sharedData.getCreatedAt());
    }
}