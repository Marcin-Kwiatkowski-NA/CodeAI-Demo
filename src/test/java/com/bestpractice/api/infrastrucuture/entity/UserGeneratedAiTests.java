package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
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

import org.junit.Test;
import static com.bestpractice.api.infrastrucuture.entity.SharedData.User;

class UserGeneratedTests {

    @Test
    void testCreateUser() {
        User user = new User();
        user.setId("12345");
        user.setUsername("testuser@example.com");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        assertEquals(user.getId(), "12345");
        assertEquals("testuser@example.com", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("password123", user.setPassword("password123"));
    }

    @Test
    void testSetUserData() {
        User user = new User();
        user.setId("99999");
        user.setUsername("anotheruser@example.com");
        user.setEmail("another@example.com");
        user.setPassword("secret");
        assertEquals("99999", user.getId());
        assertEquals("anotheruser@example.com", user.getUsername());
        assertEquals("another@example.com", user.getEmail());
        assertEquals("secret", user.getPassword());
        assertEquals("secret", user.setPassword("secret"));
    }
}
