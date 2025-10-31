package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {
        userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("securePassword");
    }

    @Test
    public void testGetUsername() {
        // GIVEN: a UserRequest with a username set
        String expectedUsername = "testUser";

        // WHEN: getting the username
        String actualUsername = userRequest.getUsername();

        // THEN: the username should match the expected value
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testSetUsername() {
        // GIVEN: a new username
        String newUsername = "newUser";

        // WHEN: setting the username
        userRequest.setUsername(newUsername);

        // THEN: the username should be updated
        assertEquals(newUsername, userRequest.getUsername());
    }

    @Test
    public void testGetEmail() {
        // GIVEN: a UserRequest with an email set
        String expectedEmail = "test@example.com";

        // WHEN: getting the email
        String actualEmail = userRequest.getEmail();

        // THEN: the email should match the expected value
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testSetEmail() {
        // GIVEN: a new email
        String newEmail = "new@example.com";

        // WHEN: setting the email
        userRequest.setEmail(newEmail);

        // THEN: the email should be updated
        assertEquals(newEmail, userRequest.getEmail());
    }

    @Test
    public void testGetPassword() {
        // GIVEN: a UserRequest with a password set
        String expectedPassword = "securePassword";

        // WHEN: getting the password
        String actualPassword = userRequest.getPassword();

        // THEN: the password should match the expected value
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testSetPassword() {
        // GIVEN: a new password
        String newPassword = "newSecurePassword";

        // WHEN: setting the password
        userRequest.setPassword(newPassword);

        // THEN: the password should be updated
        assertEquals(newPassword, userRequest.getPassword());
    }

    @Test
    public void testConvert() {
        // GIVEN: a UserRequest with valid data and an encoded password
        String id = "12345";
        String encodedPassword = "encodedSecurePassword";

        // WHEN: converting to a User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: the User entity should have the expected values
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }
}
