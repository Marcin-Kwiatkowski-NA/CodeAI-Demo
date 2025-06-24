package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserEntityGeneratedAiTests.class)
class MongoUserEntityGeneratedAiTests {

  @BeforeEach
  void setUp() {
    mongoUser = new MongoUserEntity();
  }

  private MongoUserEntity mongoUser;

  @Test
  void constructor_should_initialize_fields_correctly() {
    // GIVEN: Create a new MongoUserEntity instance.
    // WHEN: The constructor is called with specific values.
    // THEN: The fields (id, username, email, password) should be initialized with the provided values.
    MongoUserEntity user = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password123");
    assertEquals("testUser", user.getUsername());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password123", user.getPassword());
  }

  @Test
  void setId_should_set_id() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: The setId method is called with a new ObjectId.
    // THEN: The id field should be updated with the new ObjectId.
    MongoUserEntity user = new MongoUserEntity();
    ObjectId newId = new ObjectId();
    user.setId(newId);
    assertEquals(newId, user.getId());
  }

  @Test
  void setUsername_should_set_username() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: The setUsername method is called with a new username.
    // THEN: The username field should be updated with the new username.
    MongoUserEntity user = new MongoUserEntity();
    user.setUsername("testUser");
    assertEquals("testUser", user.getUsername());
  }

  @Test
  void setEmail_should_set_email() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: The setEmail method is called with a new email.
    // THEN: The email field should be updated with the new email.
    MongoUserEntity user = new MongoUserEntity();
    user.setEmail("test@example.com");
    assertEquals("test@example.com", user.getEmail());
  }

  @Test
  void setPassword_should_set_password() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: The setPassword method is called with a new password.
    // THEN: The password field should be updated with the new password.
    MongoUserEntity user = new MongoUserEntity();
    user.setPassword("password123");
    assertEquals("password123", user.getPassword());
  }

  @Test
  void convertFrom_should_convert_user_to_mongo_user() {
    // GIVEN: A User object.
    // WHEN: The convertFrom method is called with the User object.
    // THEN: A new MongoUserEntity should be created with the same data as the User object.
    User user = new User("userId", "testUser", "test@example.com", "password123");
    MongoUserEntity mongoUser = MongoUserEntity.convertFrom(user);
    assertEquals("userId", mongoUser.getId().toString());
    assertEquals("testUser", mongoUser.getUsername());
    assertEquals("test@example.com", mongoUser.getEmail());
    assertEquals("password123", mongoUser.getPassword());
  }

  @Test
  void convertTo_should_convert_mongo_user_to_user() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: The convertTo method is called.
    // THEN: A new User object should be created with the same data as the MongoUserEntity.
    MongoUserEntity mongoUser = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password123");
    User user = mongoUser.convertTo();
    assertEquals("userId", user.getId());
    assertEquals("testUser", user.getUsername());
    assertEquals("password123", user.getPassword());
  }
}
