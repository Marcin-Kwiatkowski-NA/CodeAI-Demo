package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity entity;

    @BeforeEach
    void setUp() {
        entity = new MongoUserEntity();
    }

    @Test
    void testDefaultConstructorAndGetters() {
        // GIVEN a MongoUserEntity created with the default constructor
        // WHEN getters are called
        // THEN all fields should be null
        assertThat(entity.getId()).isNull();
        assertThat(entity.getUsername()).isNull();
        assertThat(entity.getEmail()).isNull();
        assertThat(entity.getPassword()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN a MongoUserEntity
        // WHEN setting fields
        ObjectId id = new ObjectId();
        entity.setId(id);
        entity.setUsername("john_doe");
        entity.setEmail("john@example.com");
        entity.setPassword("secret");

        // THEN getters return the set values
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getUsername()).isEqualTo("john_doe");
        assertThat(entity.getEmail()).isEqualTo("john@example.com");
        assertThat(entity.getPassword()).isEqualTo("secret");
    }

    @Test
    void testParameterizedConstructor() {
        // GIVEN specific values
        ObjectId id = new ObjectId();
        String username = "alice";
        String email = "alice@example.com";
        String password = "password123";

        // WHEN creating a MongoUserEntity with the parameterized constructor
        MongoUserEntity constructed = new MongoUserEntity(id, username, email, password);

        // THEN the fields should match the provided values
        assertThat(constructed.getId()).isEqualTo(id);
        assertThat(constructed.getUsername()).isEqualTo(username);
        assertThat(constructed.getEmail()).isEqualTo(email);
        assertThat(constructed.getPassword()).isEqualTo(password);
    }

    @Test
    void testConvertFrom() {
        // GIVEN a User with all fields populated
        User user = new User();
        String userId = "507f1f77bcf86cd799439011";
        user.setId(userId);
        user.setUsername("bob");
        user.setEmail("bob@example.com");
        user.setPassword("bobpass");

        // WHEN converting to MongoUserEntity
        MongoUserEntity mongoEntity = MongoUserEntity.convertFrom(user);

        // THEN the MongoUserEntity should contain the same data
        assertThat(mongoEntity.getId()).isNotNull();
        assertThat(mongoEntity.getId().toString()).isEqualTo(userId);
        assertThat(mongoEntity.getUsername()).isEqualTo("bob");
        assertThat(mongoEntity.getEmail()).isEqualTo("bob@example.com");
        assertThat(mongoEntity.getPassword()).isEqualTo("bobpass");
    }

    @Test
    void testConvertTo() {
        // GIVEN a MongoUserEntity with all fields populated
        ObjectId id = new ObjectId();
        entity.setId(id);
        entity.setUsername("charlie");
        entity.setPassword("charliepass");
        entity.setEmail("charlie@example.com");

        // WHEN converting to User
        User user = entity.convertTo();

        // THEN the User should have the correct id string and other fields
        assertThat(user.getId()).isEqualTo(id.toString());
        assertThat(user.getUsername()).isEqualTo("charlie");
        assertThat(user.getPassword()).isEqualTo("charliepass");
        // Email is not set by convertTo, so it should be null
        assertThat(user.getEmail()).isNull();
    }

    @Test
    void testConvertFromWithNullUser() {
        // GIVEN a null User
        // WHEN converting from null
        // THEN a NullPointerException should be thrown
        assertThatThrownBy(() -> MongoUserEntity.convertFrom(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConvertFromWithNullUserId() {
        // GIVEN a User with a null id
        // WHEN converting from this User
        // THEN an IllegalArgumentException should be thrown by ObjectId constructor
        User user = new User();
        user.setId(null);
        user.setUsername("dave");
        user.setEmail("dave@example.com");
        user.setPassword("davepass");

        assertThatThrownBy(() -> MongoUserEntity.convertFrom(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertFromWithInvalidIdString() {
        // GIVEN a User with an id string that is not 24 hex characters
        // WHEN converting from this User
        // THEN an IllegalArgumentException should be thrown by ObjectId constructor
        User user = new User();
        user.setId("invalididstring"); // 15 characters, not 24 hex
        user.setUsername("frank");
        user.setEmail("frank@example.com");
        user.setPassword("frankpass");

        assertThatThrownBy(() -> MongoUserEntity.convertFrom(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConvertToWithNullId() {
        // GIVEN a MongoUserEntity with a null id
        // WHEN converting to User
        // THEN a NullPointerException should be thrown
        entity.setUsername("eve");
        entity.setPassword("evepass");

        assertThatThrownBy(() -> entity.convertTo())
                .isInstanceOf(NullPointerException.class);
    }
}
