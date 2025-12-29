package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MongoUserEntityGeneratedAiTests {

    @Test
    public void givenValidUser_whenConvertFrom_thenMongoUserEntityIsCreatedWithCorrectValues() {
        // GIVEN
        User user = new User("existing-id", "testuser", "test@example.com", "securepassword123");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertThat(result.getId()).isNotNull().isEqualTo(new ObjectId("existing-id"));
        assertThat(result.getUsername()).isEqualTo("testuser");
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        assertThat(result.getPassword()).isEqualTo("securepassword123");
    }

    @Test
    public void givenMongoUserEntity_whenConvertTo_thenUserIsCreatedWithCorrectValues() {
        // GIVEN
        ObjectId id = new ObjectId("1234567890abcdef");
        MongoUserEntity mongoUserEntity = new MongoUserEntity(id, "testuser", "test@example.com", "securepassword123");

        // WHEN
        User convertedUser = mongoUserEntity.convertTo();

        // THEN
        assertThat(convertedUser.getId()).isEqualTo("1234567890abcdef");
        assertThat(convertedUser.getUsername()).isEqualTo("testuser");
        assertThat(convertedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(convertedUser.getPassword()).isEqualTo("securepassword123");
    }

    @Test
    public void givenEmptyUsername_whenConvertFrom_thenUsernameShouldBeEmpty() {
        // GIVEN
        User user = new User("1234567890abcdef", "", "test@example.com", "securepassword123");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertThat(result.getUsername()).isEmpty();
    }

    @Test
    public void givenEmptyEmail_whenConvertFrom_thenEmailShouldBeEmpty() {
        // GIVEN
        User user = new User("1234567890abcdef", "testuser", "", "securepassword123");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertThat(result.getEmail()).isEmpty();
    }

    @Test
    public void givenEmptyPassword_whenConvertFrom_thenPasswordShouldBeEmpty() {
        // GIVEN
        User user = new User("1234567890abcdef", "testuser", "test@example.com", "");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertThat(result.getPassword()).isEmpty();
    }

    @Test
    public void givenMongoUserEntityWithNullValues_whenConvertTo_thenShouldNotThrowException() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setUsername(null);
        mongoUserEntity.setEmail(null);
        mongoUserEntity.setPassword(null);

        // WHEN
        User convertedUser = mongoUserEntity.convertTo();

        // THEN
        assertThat(convertedUser.getUsername()).isNull();
        assertThat(convertedUser.getEmail()).isNull();
        assertThat(convertedUser.getPassword()).isNull();
    }
}
