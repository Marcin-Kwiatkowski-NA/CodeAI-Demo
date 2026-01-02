package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

import static org.assertj.core.api.Assertions.*;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void testDefaultConstructorAndGetters() {
        // GIVEN
        // WHEN
        ObjectId id = mongoUserEntity.getId();
        String username = mongoUserEntity.getUsername();
        String email = mongoUserEntity.getEmail();
        String password = mongoUserEntity.getPassword();
        // THEN
        assertThat(id).isNull();
        assertThat(username).isNull();
        assertThat(email).isNull();
        assertThat(password).isNull();
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "john";
        String email = "john@example.com";
        String password = "secret";
        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);
        // THEN
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getUsername()).isEqualTo(username);
        assertThat(entity.getEmail()).isEqualTo(email);
        assertThat(entity.getPassword()).isEqualTo(password);
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "alice";
        String email = "alice@example.com";
        String password = "pass";
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
    void testConvertFromWithValidUser() {
        // GIVEN
        String userId = new ObjectId().toHexString();
        User user = new User(userId, "bob", "bob@example.com", "pwd");
        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        // THEN
        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getId().toHexString()).isEqualTo(userId);
        assertThat(entity.getUsername()).isEqualTo("bob");
        assertThat(entity.getEmail()).isEqualTo("bob@example.com");
        assertThat(entity.getPassword()).isEqualTo("pwd");
    }

    @Test
    void testConvertFromWithNullUser() {
        // GIVEN
        // WHEN
        // THEN
        assertThatThrownBy(() -> MongoUserEntity.convertFrom(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConvertFromWithNullUserId() {
        // GIVEN
        User user = new User(null, "charlie", "charlie@example.com", "pwd");
        // WHEN
        // THEN
        assertThatThrownBy(() -> MongoUserEntity.convertFrom(user))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertTo() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("dave");
        mongoUserEntity.setPassword("secret");
        // WHEN
        User user = mongoUserEntity.convertTo();
        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id.toString());
        assertThat(user.getUsername()).isEqualTo("dave");
        assertThat(user.getPassword()).isEqualTo("secret");
        assertThat(user.getEmail()).isNull(); // email not set in convertTo
    }

    @Test
    void testConvertToWithNullId() {
        // GIVEN
        mongoUserEntity.setUsername("eve");
        mongoUserEntity.setPassword("pwd");
        // WHEN
        // THEN
        assertThatThrownBy(() -> mongoUserEntity.convertTo())
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConvertToDoesNotModifyOriginalEntity() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("frank");
        mongoUserEntity.setPassword("pwd");
        // WHEN
        User user = mongoUserEntity.convertTo();
        mongoUserEntity.setUsername("changed");
        // THEN
        assertThat(user.getUsername()).isEqualTo("frank");
        assertThat(mongoUserEntity.getUsername()).isEqualTo("changed");
    }

    @Test
    void testConvertFromDoesNotModifyOriginalUser() {
        // GIVEN
        User user = new User("123", "grace", "grace@example.com", "pwd");
        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        user.setUsername("changed");
        // THEN
        assertThat(entity.getUsername()).isEqualTo("grace");
        assertThat(user.getUsername()).isEqualTo("changed");
    }
}
