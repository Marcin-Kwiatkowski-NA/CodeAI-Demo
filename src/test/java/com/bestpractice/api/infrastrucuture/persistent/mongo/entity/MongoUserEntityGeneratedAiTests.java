package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void givenValidId_whenSetId_thenIdIsSetCorrectly() {
        // GIVEN
        ObjectId id = new ObjectId();

        // WHEN
        mongoUserEntity.setId(id);

        // THEN
        assertThat(mongoUserEntity.getId()).isEqualTo(id);
    }

    @Test
    void givenValidUsername_whenSetUsername_thenUsernameIsSetCorrectly() {
        // GIVEN
        String username = "testUser";

        // WHEN
        mongoUserEntity.setUsername(username);

        // THEN
        assertThat(mongoUserEntity.getUsername()).isEqualTo(username);
    }

    @Test
    void givenValidEmail_whenSetEmail_thenEmailIsSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        mongoUserEntity.setEmail(email);

        // THEN
        assertThat(mongoUserEntity.getEmail()).isEqualTo(email);
    }

    @Test
    void givenValidPassword_whenSetPassword_thenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        mongoUserEntity.setPassword(password);

        // THEN
        assertThat(mongoUserEntity.getPassword()).isEqualTo(password);
    }

    @Test
    void givenValidUser_whenConvertFrom_thenMongoUserEntityIsCreated() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "testUser", "test@example.com", "securePassword");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void givenValidMongoUserEntity_whenConvertTo_thenUserIsCreated() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");

        // WHEN
        User result = mongoUserEntity.convertTo();

        // THEN
        assertThat(result.getId()).isEqualTo(id.toString());
        assertThat(result.getUsername()).isEqualTo(mongoUserEntity.getUsername());
        assertThat(result.getPassword()).isEqualTo(mongoUserEntity.getPassword());
    }
}
