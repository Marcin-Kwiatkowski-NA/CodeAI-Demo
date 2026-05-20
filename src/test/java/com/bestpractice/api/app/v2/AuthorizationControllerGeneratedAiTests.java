package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AuthorizationControllerGeneratedAiTests {

    private AuthorizationController authorizationController;

    @BeforeEach
    void setUp() {
        authorizationController = new AuthorizationController();
    }

    @Test
    void givenController_whenInstantiated_thenShouldNotBeNull() {
        // GIVEN
        // Controller is instantiated in setup

        // WHEN
        AuthorizationController controller = authorizationController;

        // THEN
        assertNotNull(controller);
    }

    @Test
    void givenControllerClass_whenCheckedForAnnotations_thenShouldContainRestControllerAndRequestMapping() {
        // GIVEN
        Class<?> controllerClass = AuthorizationController.class;

        // WHEN
        RestController restControllerAnnotation = controllerClass.getAnnotation(RestController.class);
        RequestMapping requestMappingAnnotation = controllerClass.getAnnotation(RequestMapping.class);

        // THEN
        assertNotNull(restControllerAnnotation);
        assertNotNull(requestMappingAnnotation);
        assertEquals("/api/v2/", requestMappingAnnotation.value()[0]);
    }

    @Test
    void givenController_whenInstantiated_thenShouldNotThrowAnyException() {
        // GIVEN
        // No special setup required

        // WHEN & THEN
        assertDoesNotThrow(() -> new AuthorizationController());
    }
}
