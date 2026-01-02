package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructorInitializesFieldsToNull() {
        // GIVEN
        // user is initialized in setUp

        // WHEN
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        Date createdAt = user.getCreatedAt();

        // THEN
        assertThat(id).isNull();
        assertThat(username).isNull();
        assertThat(email).isNull();
        assertThat(password).isNull();
        assertThat(createdAt).isNull();
    }

    @Test
    void testParameterizedConstructorSetsFields() {
        // GIVEN
        String id = "123";
        String username = "john";
        String email = "john@example.com";
        String password = "secret";

        // WHEN
        User paramUser = new User(id, username, email, password);

        // THEN
        assertThat(paramUser.getId()).isEqualTo(id);
        assertThat(paramUser.getUsername()).isEqualTo(username);
        assertThat(paramUser.getEmail()).isEqualTo(email);
        assertThat(paramUser.getPassword()).isEqualTo(password);
        assertThat(paramUser.getCreatedAt()).isNull();
    }

    @Test
    void testSettersUpdateFields() {
        // GIVEN
        String newId = "456";
        String newUsername = "alice";
        String newEmail = "alice@example.com";
        String newPassword = "newpass";

        // WHEN
        user.setId(newId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // THEN
        assertThat(user.getId()).isEqualTo(newId);
        assertThat(user.getUsername()).isEqualTo(newUsername);
        assertThat(user.getEmail()).isEqualTo(newEmail);
        assertThat(user.getPassword()).isEqualTo(newPassword);
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        // user created in setUp

        // WHEN
        user.onPrePersist();
        Date createdAt = user.getCreatedAt();

        // THEN
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isCloseTo(new Date(), within(1000).millis());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEachCall() throws InterruptedException {
        // GIVEN
        user.onPrePersist();
        Date first = user.getCreatedAt();

        // simulate delay
        Thread.sleep(10);

        // WHEN
        user.onPrePersist();
        Date second = user.getCreatedAt();

        // THEN
        assertThat(second).isAfter(first);
    }

    @Test
    void testSerializationPreservesFields() throws IOException, ClassNotFoundException {
        // GIVEN
        User original = new User("789", "bob", "bob@example.com", "pass123");
        original.onPrePersist();

        // WHEN
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User deserialized = (User) ois.readObject();
        ois.close();

        // THEN
        assertThat(deserialized.getId()).isEqualTo(original.getId());
        assertThat(deserialized.getUsername()).isEqualTo(original.getUsername());
        assertThat(deserialized.getEmail()).isEqualTo(original.getEmail());
        assertThat(deserialized.getPassword()).isEqualTo(original.getPassword());
        assertThat(deserialized.getCreatedAt()).isEqualTo(original.getCreatedAt());
    }

    @Test
    void testPasswordCanBeNull() {
        // GIVEN
        // user created in setUp

        // WHEN
        user.setPassword(null);
        String pwd = user.getPassword();

        // THEN
        assertThat(pwd).isNull();
    }
}
