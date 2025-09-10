package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplGeneratedAiTests {

    @Test
    public void getUserById_validId_returnsUser() {
        // GIVEN
        String id = "123";
        UserPersistentRepository userRepo = Mockito.of(UserPersistentRepository.class);
        User user = userRepo.findById(id).orElse(null);

        // WHEN
        // THEN
        assertNotNull(user, "User should not be null");
        assertEquals("123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void getAuthenticatedUser_validCredentials_returnsUser() {
        // GIVEN
        String email = "test@example.com";
        String rawPw = "password";
        UserPersistentRepository userRepo = Mockito.of(UserPersistentRepository.class);

        // WHEN
        // THEN
        assertThrows(new UnAuthorized().getClass(), () -> getAuthenticatedUser(email, rawPw));
    }
}
