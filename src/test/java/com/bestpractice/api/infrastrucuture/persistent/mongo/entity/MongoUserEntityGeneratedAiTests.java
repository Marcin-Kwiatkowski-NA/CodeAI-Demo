package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MongoUserEntityGeneratedAiTests {

    private User user;
    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        user = new User("507f1f77bcf86cd799439011", "john_doe", "john@example.com", "password123");
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void defaultConstructorShouldInitializeFieldsToNull() {
        // GIVEN
        MongoUserEntity entity = new MongoUserEntity();

        // WHEN
        ObjectId id = entity.getId();
        String username = entity.getUsername();
        String email = entity.getEmail();
        String password = entity.getPassword();

        // THEN
        assertThat(id).isNull();
        assertThat(username).isNull();
        assertThat(email).isNull();
        assertThat(password).isNull();
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "alice";
        String email = "alice@example.com";
        String password = "alicePass";

        // WHEN
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(username);
        mongoUserEntity.setEmail(email);
        mongoUserEntity.setPassword(password);

        // THEN
        assertThat(mongoUserEntity.getId()).isEqualTo(id);
        assertThat(mongoUserEntity.getUsername()).isEqualTo(username);
        assertThat(mongoUserEntity.getEmail()).isEqualTo(email);
        assertThat(mongoUserEntity.getPassword()).isEqualTo(password);
    }

    @Test
    void convertFromShouldMapAllFieldsCorrectly() {
        // GIVEN
        User sourceUser = new User("507f1f77bcf86cd799439011", "bob", "bob@example.com", "bobPass");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(sourceUser);

        // THEN
        assertThat(result.getId()).isEqualTo(new ObjectId("507f1f77bcf86cd799439011"));
        assertThat(result.getUsername()).isEqualTo("bob");
        assertThat(result.getEmail()).isEqualTo("bob@example.com");
        assertThat(result.getPassword()).isEqualTo("bobPass");
    }

    @Test
    void convertToShouldMapFieldsFromMongoEntityToUser() {
        // GIVEN
        ObjectId id = new ObjectId("507f1f77bcf86cd799439011");
        MongoUserEntity entity = new MongoUserEntity(id, "charlie", "charlie@example.com", "charliePass");

        // WHEN
        User userResult = entity.convertTo();

        // THEN
        assertThat(userResult.getId()).isEqualTo("507f1f77bcf86cd799439011");
        assertThat(userResult.getUsername()).isEqualTo("charlie");
        assertThat(userResult.getPassword()).isEqualTo("charliePass");
        // Email is intentionally not set in convertTo, should be null
        assertThat(userResult.getEmail()).isNull();
    }

    @Test
    void convertFromShouldThrowWhenUserIdIsNull() {
        // GIVEN
        User invalidUser = new User(null, "dave", "dave@example.com", "davePass");

        // WHEN / THEN
        assertThatThrownBy(() -> MongoUserEntity.convertFrom(invalidUser))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void convertToShouldThrowWhenEntityIdIsNull() {
        // GIVEN
        MongoUserEntity entityWithNullId = new MongoUserEntity(null, "eve", "eve@example.com", "evePass");

        // WHEN / THEN
        assertThatThrownBy(entityWithNullId::convertTo)
                .isInstanceOf(NullPointerException.class);
    }
}
