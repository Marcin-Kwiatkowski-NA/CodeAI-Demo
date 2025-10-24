package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    public void resetState() {
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
    public void testConvertWithValidData() {
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

    @Test
    public void testConvertWithNullUsernameAndEmail() {
        // GIVEN: a UserRequest with null username and email
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        String id = "12345";
        String encodedPassword = "encodedSecurePassword";

        // WHEN: converting to a User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: username and email should be null, id and password should match
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getUsername());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN: a UserRequest with valid username and email but null id
        String id = null;
        String encodedPassword = "encodedSecurePassword";

        // WHEN: converting to a User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: id should be null, other fields should match
        assertNull(user.getId());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    public void testConvertWithNullPassword() {
        // GIVEN: a UserRequest with valid username and email but null encoded password
        String id = "12345";
        String encodedPassword = null;

        // WHEN: converting to a User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: password should be null, other fields should match
        assertEquals(id, user.getId());
        assertNull(user.getPassword());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getUsername(), user.getUsername());
    }

    @Test
    public void testConvertWithAllNullFields() {
        // GIVEN: a completely empty UserRequest
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);
        String id = null;
        String encodedPassword = null;

        // WHEN: converting to a User entity
        User user = userRequest.convert(id, encodedPassword);

        // THEN: all fields in User should be null
        assertNull(user.getId());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getUsername());
    }

    @Test
    public void testSettersAndGettersWorkIndependently() {
        // GIVEN: a new UserRequest instance
        UserRequest request = new UserRequest();

        // WHEN: setting values
        request.setUsername("independentUser");
        request.setEmail("independent@example.com");
        request.setPassword("independentPassword");

        // THEN: getters should return the set values
        assertEquals("independentUser", request.getUsername());
        assertEquals("independent@example.com", request.getEmail());
        assertEquals("independentPassword", request.getPassword());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWithNullValues() {
        // GIVEN: a UserRequest with null fields
        userRequest.setUsername(null);
        userRequest.setEmail(null);
        userRequest.setPassword(null);

        // WHEN & THEN: convert should not throw any exception
        assertDoesNotThrow(() -> {
            User user = userRequest.convert(null, null);
            assertNull(user.getId());
            assertNull(user.getPassword());
            assertNull(user.getEmail());
            assertNull(user.getUsername());
        });
    }
}
