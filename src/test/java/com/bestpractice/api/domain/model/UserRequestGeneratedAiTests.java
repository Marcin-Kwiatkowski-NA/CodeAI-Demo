package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.bestpractice.api.infrastrucuture.entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({})
public class UserRequestGeneratedAiTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    public void testValidUserRequest() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // THEN
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidUsername() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // THEN
        assertEquals(1, violations.size());
    }

    @Test
    public void testInvalidEmail() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("password");

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // THEN
        assertEquals(1, violations.size());
    }

    @Test
    public void testInvalidPassword() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // THEN
        assertEquals(1, violations.size());
    }

    @Test
    public void testConvertMethod() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        String id = "123";
        String encodedPw = "encodedPassword";

        // WHEN
        User user = userRequest.convert(id, encodedPw);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(encodedPw, user.getPassword());
        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getEmail(), user.getEmail());
    }
}
