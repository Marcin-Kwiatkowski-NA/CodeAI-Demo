package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

class UserRequestGeneratedAiTests {

    private UserRequest userRequest;
    private Validator validator;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenValidUsername_whenSetUsername_thenUsernameIsSetCorrectly() {
        // GIVEN
        String username = "testUser";

        // WHEN
        userRequest.setUsername(username);

        // THEN
        assertThat(userRequest.getUsername()).isEqualTo(username);
    }

    @Test
    void givenValidEmail_whenSetEmail_thenEmailIsSetCorrectly() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        userRequest.setEmail(email);

        // THEN
        assertThat(userRequest.getEmail()).isEqualTo(email);
    }

    @Test
    void givenValidPassword_whenSetPassword_thenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        userRequest.setPassword(password);

        // THEN
        assertThat(userRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void givenValidUserRequest_whenConvert_thenUserIsCreatedCorrectly() {
        // GIVEN
        String id = "123";
        String encodedPassword = "encodedPassword";
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("securePassword");

        // WHEN
        User user = userRequest.convert(id, encodedPassword);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
        assertThat(user.getEmail()).isEqualTo(userRequest.getEmail());
        assertThat(user.getUsername()).isEqualTo(userRequest.getUsername());
    }

    @Test
    void givenInvalidEmail_whenValidate_thenValidationFails() {
        // GIVEN
        userRequest.setEmail("invalidEmail");

        // WHEN
        var violations = validator.validate(userRequest);

        // THEN
        assertThat(violations).isNotEmpty();
    }

    @Test
    void givenNullUsername_whenValidate_thenValidationFails() {
        // GIVEN
        userRequest.setUsername(null);

        // WHEN
        var violations = validator.validate(userRequest);

        // THEN
        assertThat(violations).isNotEmpty();
    }

    @Test
    void givenNullPassword_whenValidate_thenValidationFails() {
        // GIVEN
        userRequest.setPassword(null);

        // WHEN
        var violations = validator.validate(userRequest);

        // THEN
        assertThat(violations).isNotEmpty();
    }
}
