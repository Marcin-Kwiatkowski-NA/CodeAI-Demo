package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserEntityGeneratedAiTests.class)
class MongoUserEntityGeneratedAiTests {
    @BeforeEach
    void setUp() {
        // No setup needed for this class
    }
}

class MongoUserEntity {
    private ObjectId  id;
    private String username;
    private String email;
    private String password;

    public MongoUserEntity() {
    }

    public MongoUserEntity(ObjectId id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static MongoUserEntity convertFrom(User user) {
        return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User convertTo() {
        User user = new User();
        user.setId(this.id.toString());
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}

class User {
    @Column(name = "id")
    private String  id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "password")
    private String password;

    public User() {
    }

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class SharedData {
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
