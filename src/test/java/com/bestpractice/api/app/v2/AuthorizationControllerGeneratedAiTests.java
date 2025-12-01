package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorizationControllerGeneratedAiTests {

    private AuthorizationController authorizationController;

    @BeforeEach
    void setUp() {
        authorizationController = new AuthorizationController();
    }

    @Test
    void givenAuthorizationController_whenClassAnnotationsChecked_thenAnnotationsArePresent() {
        // GIVEN: A new AuthorizationController instance is created in the setup

        // WHEN: Checking for class-level annotations
        RestController restControllerAnnotation = AuthorizationController.class.getAnnotation(RestController.class);
        RequestMapping requestMappingAnnotation = AuthorizationController.class.getAnnotation(RequestMapping.class);

        // THEN: The annotations should be present and correctly configured
        assertNotNull(restControllerAnnotation, "RestController annotation should be present");
        assertNotNull(requestMappingAnnotation, "RequestMapping annotation should be present");
        assertEquals("/api/v2/", requestMappingAnnotation.value()[0], "RequestMapping value should be '/api/v2/'");
    }

    @Test
    void givenAuthorizationController_whenInvalidAnnotationAccess_thenAnnotationShouldBeNull() {
        // GIVEN: A new AuthorizationController instance is created in the setup

        // WHEN: Attempting to access an annotation that does not exist
        Deprecated deprecatedAnnotation = AuthorizationController.class.getAnnotation(Deprecated.class);

        // THEN: The result should be null
        assertEquals(null, deprecatedAnnotation, "Deprecated annotation should not be present");
    }

    @Test
    void givenAuthorizationController_whenRequestMappingAnnotationValueAccessed_thenValueShouldMatch() {
        // GIVEN: A new AuthorizationController instance is created in the setup

        // WHEN: Accessing the value of the RequestMapping annotation
        RequestMapping requestMappingAnnotation = AuthorizationController.class.getAnnotation(RequestMapping.class);

        // THEN: The value should match the expected result
        assertNotNull(requestMappingAnnotation, "RequestMapping annotation should be present");
        assertEquals("/api/v2/", requestMappingAnnotation.value()[0], "RequestMapping value should be '/api/v2/'");
    }
}
