package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestpractice.api.infrastructure.entity.User;

@ExtendWith(MockitoExtension.class)
class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    @DisplayName("Should create a new MongoUserEntity with default constructor")
    void shouldCreateNewMongoUserEntityWithDefaultConstructor() {
        // GIVEN
        // WHEN
        // THEN
        assertThat(mongoUserEntity).isNotNull();
    }

    @Test
    @DisplayName("Should set and get id")
    void shouldSetAndGetId() {
        // GIVEN
        ObjectId id = new ObjectId();
        // WHEN
        mongoUserEntity.setId(id);
        // THEN
        assertThat(mongoUserEntity.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should set and get username")
    void shouldSetAndGetUsername() {
        // GIVEN
        String username = "testUser";
        // WHEN
        mongoUserEntity.setUsername(username);
        // THEN
        assertThat(mongoUserEntity.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Should set and get email")
    void shouldSetAndGetEmail() {
        // GIVEN
        String email = "test@example.com";
        // WHEN
        mongoUserEntity.setEmail(email);
        // THEN
        assertThat(mongoUserEntity.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("Should set and get password")
    void shouldSetAndGetPassword() {
        // GIVEN
        String password = "password123";
        // WHEN
        mongoUserEntity.setPassword(password);
        // THEN
        assertThat(mongoUserEntity.getPassword()).isEqualTo(password);
    }

    @Test
    @DisplayName("Should convert from User to MongoUserEntity")
    void shouldConvertFromUserToMongoUserEntity() {
        // GIVEN
        User user = new User();
        user.setId("123");
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(new ObjectId(user.getId()));
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    @DisplayName("Should convert to User")
    void shouldConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("password123");
        // WHEN
        User result = mongoUserEntity.convertTo();
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id.toString());
        assertThat(result.getUsername()).isEqualTo(mongoUserEntity.getUsername());
        assertThat(result.getEmail()).isEqualTo(mongoUserEntity.getEmail());
        assertThat(result.getPassword()).isEqualTo(mongoUserEntity.getPassword());
    }
}
