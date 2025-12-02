package com.bestpractice.api.infrastrucuture.entity;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetId() {
        // GIVEN
        String expectedId = "12345";
        user.setId(expectedId);

        // WHEN
        String actualId = user.getId();

        // THEN
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        user.setId(expectedId);

        // THEN
        assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";
        user.setUsername(expectedUsername);

        // WHEN
        String actualUsername = user.getUsername();

        // THEN
        assertThat(actualUsername).isEqualTo(expectedUsername);
    }

    @Test
    void testSetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        user.setUsername(expectedUsername);

        // THEN
        assertThat(user.getUsername()).isEqualTo(expectedUsername);
    }

    @Test
    void testGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";
        user.setEmail(expectedEmail);

        // WHEN
        String actualEmail = user.getEmail();

        // THEN
        assertThat(actualEmail).isEqualTo(expectedEmail);
    }

    @Test
    void testSetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        user.setEmail(expectedEmail);

        // THEN
        assertThat(user.getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    void testGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";
        user.setPassword(expectedPassword);

        // WHEN
        String actualPassword = user.getPassword();

        // THEN
        assertThat(actualPassword).isEqualTo(expectedPassword);
    }

    @Test
    void testSetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        user.setPassword(expectedPassword);

        // THEN
        assertThat(user.getPassword()).isEqualTo(expectedPassword);
    }

    @Test
    void testGetCreatedAt() {
        // GIVEN
        SharedData sharedData = new User();
        sharedData.onPrePersist();

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertThat(createdAt).isNotNull();
    }
}
