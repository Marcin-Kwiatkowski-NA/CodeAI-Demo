package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthByEmailRequestGeneratedAiTests {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthByEmailRequest authByEmailRequest;

    private Validator validator;
    private final String validEmail = "test@example.com";
    private final String validPassword = "securePassword123";
    private final String invalidEmail = "invalid-email";
    private final String emptyPassword = "";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @Order(1)
    public void givenValidEmailAndPassword_whenCreateRequest_thenEmailAndPasswordAreSet() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(validEmail);
        request.setPassword(validPassword);

        // WHEN
        String email = request.getEmail();
        String password = request.getPassword();

        // THEN
        assertThat(email).isEqualTo(validEmail);
        assertThat(password).isEqualTo(validPassword);
    }

    @Test
    @Order(2)
    public void givenValidEmailAndPassword_whenJsonSerialize_thenJsonContainsExpectedFields() throws JsonProcessingException {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(validEmail);
        request.setPassword(validPassword);

        // WHEN
        String json = objectMapper.writeValueAsString(request);

        // THEN
        JsonNode node = objectMapper.readTree(json);
        assertThat(node.get("email").asText()).isEqualTo(validEmail);
        assertThat(node.get("password").asText()).isEqualTo(validPassword);
    }

    @Test
    @Order(3)
    public void givenInvalidEmail_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(invalidEmail);
        request.setPassword(validPassword);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgumentNotValidException.class);
    }

    @Test
    @Order(4)
    public void givenEmptyPassword_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(validEmail);
        request.setPassword(emptyPassword);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgumentNotValidException.class);
    }

    @Test
    @Order(5)
    public void givenNullEmail_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(null);
        request.setPassword(validPassword);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgumentNotValidException.class);
    }

    @Test
    @Order(6)
    public void givenNullPassword_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail(validEmail);
        request.setPassword(null);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgumentNotValidException.class);
    }

    @Test
    @Order(7)
    public void givenEmptyEmail_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("");
        request.setPassword(validPassword);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgument    }

    @Test
    @Order(7)
    public void givenEmptyEmail_whenCreateRequest_thenValidationExceptionIsThrown() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("");
        request.setPassword(validPassword);

        // WHEN & THEN
        assertThatThrownBy(() -> {
            Set<ConstraintViolation<AuthByEmailRequest>> violations = validator.validate(request);
            assertThat(violations).isNotEmpty();
        }).isInstanceOf(MethodArgumentNotValidException.class);
    }
}
