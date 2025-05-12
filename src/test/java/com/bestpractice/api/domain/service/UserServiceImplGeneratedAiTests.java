package com.bestpractice.api.domain.service;

import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.*;

package com.bestpractice.api.domain.service;

public class UserServiceImplTest {

    @Test
    public void testGetUserById_Successful() {
        User user = new User();
        user.id = "123";
        user.username = "testuser";
        user.email = "test@example.com";
        user.password = "password123";

        UserPersistentRepository repository = new UserPersistentRepository();
        assertTrue(repository.findById(user.id));
    }

    @Test
    public void testGetUserById_InvalidId() {
        User user = new User();
        user.id = "456";
        user.username = "testuser";
        user.email = "test@example.com";
        user.password = "password123";
        UserPersistentRepository repository = new UserPersistentRepository();
        assertFalse(repository.findById(user.id));
    }

    @Test
    public void testGetUserAuthenticatedUser_Successful() {
        User user = new User();
        user.id = "123";
        user.username = "testuser";
        user.email = "test@example.com";
        user.password = "password123";

        UserPersistentRepository repository = new UserPersistentRepository();
        assertTrue(repository.findById(user.id));
    }

    @Test
    public void testGetUserAuthenticatedUser_InvalidEmail() {
        User user = new User();
        user.id = "123";
        user.username = "testuser";
        user.email = "test@example.com";
        user.password = "password123";
        UserPersistentRepository repository = new UserPersistentRepository();
        assertFalse(repository.findById(user.email));
    }

    @Test
    public void testGetUserAuthenticatedUser_InvalidPassword() {
        User user = new User();
        user.id = "123";
        user.username = "testuser";
        user.email = "test@example.com";
        user.password = "password123";
        UserPersistentRepository repository = new UserPersistentRepository();
        assertFalse(repository.findById(user.password));
    }

    @Test
    public void testGenerateUser_Successful() {
        UserRequest request = new UserRequest();
        request.convert(123, "password123");
        User response = userServiceImpl.generateUser(request);
        assertTrue(response.getId());
    }

    @Test
    public void testGenerateUser_InvalidInput() {
        UserRequest request = new UserRequest();
        request.convert("456", "invalid");
        User response = userServiceImpl.generateUser(request);
        assertFalse(response.getId());
    }

    @Test
    public void testGenerateUser_InvalidPassword() {
        UserRequest request = new UserRequest();
        request.convert("123", "wrong");
        User response = userServiceImpl.generateUser(request);
        assertFalse(response.getId());
    }

    @Test
    public void testGenerateUser_InvalidEmail() {
        UserRequest request = new UserRequest();
        request.convert("123", "test");
        User response = userServiceImpl.generateUser(request);
        assertFalse(response.getId());
    }
}
