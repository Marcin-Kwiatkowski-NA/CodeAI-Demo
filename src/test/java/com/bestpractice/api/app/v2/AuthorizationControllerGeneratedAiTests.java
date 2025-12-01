package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthorizationControllerGeneratedAiTests {

    private AuthorizationController authorizationController;

    @BeforeEach
    void setUp() {
        authorizationController = new AuthorizationController();
    }

    @Test
    void givenControllerAnnotation_whenChecked_thenShouldBeRestController() {
        // GIVEN
        Class<?> clazz = authorizationController.getClass();

        // WHEN
        RestController restControllerAnnotation = clazz.getAnnotation(RestController.class);

        // THEN
        assertNotNull(restControllerAnnotation, "RestController annotation should not be null");
    }

    @Test
    void givenRequestMappingAnnotation_whenChecked_thenShouldHaveCorrectPath() {
        // GIVEN
        Class<?> clazz = authorizationController.getClass();

        // WHEN
        RequestMapping requestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);

        // THEN
        assertNotNull(requestMappingAnnotation, "RequestMapping annotation should not be null");
        assertEquals("/api/v2/", requestMappingAnnotation.value()[0], "RequestMapping path should match '/api/v2/'");
    }

    @Test
    void givenNullAnnotationType_whenChecked_thenShouldThrowException() {
        // GIVEN
        Class<?> clazz = authorizationController.getClass();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            clazz.getAnnotation(null);
        }, "Should throw NullPointerException when annotation type is null");
    }

    @Test
    void givenInvalidAnnotationType_whenChecked_thenShouldReturnNull() {
        // GIVEN
        Class<?> clazz = authorizationController.getClass();

        // WHEN
        Deprecated deprecatedAnnotation = clazz.getAnnotation(Deprecated.class);

        // THEN
        assertEquals(null, deprecatedAnnotation, "Should return null for an invalid annotation type");
    }
}
