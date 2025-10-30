package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
        // GIVEN: A UserRequest with a set username
        String expectedUsername = "testUser";

        // WHEN: Retrieving the username
        String actualUsername = userRequest.getUsername();

        // THEN: The username should match the expected value
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testSetUsername() {
        // GIVEN: A new username to set
        String newUsername = "newUser";

        // WHEN: Setting the username
        userRequest.setUsername(newUsername);

        // THEN: The username should be updated
        assertEquals(newUsername, userRequest.getUsername());
    }

    @Test
    public void testGetEmail() {
        // GIVEN: A UserRequest with a set email
        String expectedEmail = "test@example.com";

        // WHEN: Retrieving the email
        String actualEmail = userRequest.getEmail();

        // THEN: The email should match the expected value
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testSetEmail() {
        // GIVEN: A new email to set
        String newEmail = "new@example.com";

        // WHEN: Setting the email
        userRequest.setEmail(newEmail);

        // THEN: The email should be updated
        assertEquals(newEmail, userRequest.getEmail());
    }

    @Test
    public void testGetPassword() {
        // GIVEN: A UserRequest with a set password
        String expectedPassword = "securePassword";

        // WHEN: Retrieving the password
        String actualPassword = userRequest.getPassword();

        // THEN: The password should match the expected value
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testSetPassword() {
        // GIVEN: A new password to set
        String newPassword = "newSecurePassword";

        // WHEN: Setting the password
        userRequest.setPassword(newPassword);

        // THEN: The password should be updated
        assertEquals(newPassword, userRequest.getPassword());
    }

    @Test
    public void testConvert() {
        // GIVEN: A UserRequest with valid data and conversion parameters
        String id = "12345";
        String encodedPassword = "encodedSecurePassword";

        // WHEN: Converting UserRequest to User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: The User entity should have the expected values
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }
}
