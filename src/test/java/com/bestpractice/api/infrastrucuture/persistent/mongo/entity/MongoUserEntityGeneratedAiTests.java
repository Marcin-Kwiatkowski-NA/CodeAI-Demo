package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
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
    return new MongoUserEntity(new ObjectId(), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}

class MongoUserEntityGeneratedAiTests {

    MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @org.junit.jupiter.api.Test
    void constructor_shouldCreateNewEntity() {
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "testPassword");
        assert mongoUserEntity.getUsername().equals("testUser");
        assert mongoUserEntity.getEmail().equals("test@example.com");
        assert mongoUserEntity.getPassword().equals("testPassword");
    }

    @org.junit.jupiter.api.Test
    void setId_shouldSetId() {
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        assert mongoUserEntity.getId().equals(id);
    }

    @org.junit.jupiter.api.Test
    void setUsername_shouldSetUsername() {
        mongoUserEntity.setUsername("newUsername");
        assert mongoUserEntity.getUsername().equals("newUsername");
    }

    @org.junit.jupiter.api.Test
    void setEmail_shouldSetEmail() {
        mongoUserEntity.setEmail("newEmail@example.com");
        assert mongoUserEntity.getEmail().equals("newEmail@example.com");
    }

    @org.junit.jupiter.api.Test
    void setPassword_shouldSetPassword() {
        mongoUserEntity.setPassword("newPassword");
        assert mongoUserEntity.getPassword().equals("newPassword");
    }

    @org.junit.jupiter.api.Test
    void convertFrom_shouldCreateEntityFromUser() {
        User user = new User("user123", "testUser", "test@example.com", "testPassword");
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        assert entity.getId().equals(new ObjectId());
        assert entity.getUsername().equals("testUser");
        assert entity.getEmail().equals("test@example.com");
        assert entity.getPassword().equals("testPassword");
    }
}
