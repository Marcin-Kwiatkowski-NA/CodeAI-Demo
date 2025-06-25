package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserEntityGeneratedAiTests.class)
class MongoUserEntityGeneratedAiTests {

  @Test
  void constructor_should_initialize_fields_correctly() {
    // GIVEN: Create a new MongoUserEntity instance.
    // WHEN: The constructor is called with specific values.
    // THEN: The fields (id, username, email, password) should be initialized with the provided values.
    MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password123");
    assertEquals("testUser", entity.getUsername());
    assertEquals("test@example.com", entity.getEmail());
    assertEquals("password123", entity.getPassword());
  }

  @Test
  void setId_should_set_id() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: setId is called with a new ObjectId.
    // THEN: The id field should be updated with the new ObjectId.
    MongoUserEntity entity = new MongoUserEntity();
    ObjectId newId = new ObjectId();
    entity.setId(newId);
    assertEquals(newId, entity.getId());
  }

  @Test
  void setUsername_should_set_username() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: setUsername is called with a new username.
    // THEN: The username field should be updated with the new username.
    MongoUserEntity entity = new MongoUserEntity();
    entity.setUsername("newUsername");
    assertEquals("newUsername", entity.getUsername());
  }

  @Test
  void setEmail_should_set_email() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: setEmail is called with a new email.
    // THEN: The email field should be updated with the new email.
    MongoUserEntity entity = new MongoUserEntity();
    entity.setEmail("newEmail@example.com");
    assertEquals("newEmail@example.com", entity.getEmail());
  }

  @Test
  void setPassword_should_set_password() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: setPassword is called with a new password.
    // THEN: The password field should be updated with the new password.
    MongoUserEntity entity = new MongoUserEntity();
    entity.setPassword("newPassword");
    assertEquals("newPassword", entity.getPassword());
  }

  @Test
  void convertFrom_should_convert_user_to_mongo_user_entity() {
    // GIVEN: A User object.
    // WHEN: convertFrom is called with the User object.
    // THEN: A MongoUserEntity should be created with the same values as the User object.
    User user = new User("userId", "userTest", "user@example.com", "userPassword");
    MongoUserEntity entity = MongoUserEntity.convertFrom(user);
    assertEquals("userId", entity.getId().toString());
    assertEquals("userTest", entity.getUsername());
    assertEquals("user@example.com", entity.getEmail());
    assertEquals("userPassword", entity.getPassword());
  }

  @Test
  void convertTo_should_convert_mongo_user_entity_to_user() {
    // GIVEN: A MongoUserEntity instance.
    // WHEN: convertTo is called on the MongoUserEntity.
    // THEN: A User object should be created with the same values as the MongoUserEntity.
    MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "mongoUser", "mongo@example.com", "mongoPassword");
    User user = entity.convertTo();
    assertEquals("mongoUser", user.getId());
    assertEquals("mongoUser", user.getUsername());
    assertEquals("mongoPassword", user.getPassword());
  }
}
