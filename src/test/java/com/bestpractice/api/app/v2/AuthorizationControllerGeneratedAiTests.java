package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

@RestController
@RequestMapping("/api/v2/")
class AuthorizationController {
}

@ExtendWith(SpringExtension.class)
public class AuthorizationControllerGeneratedAiTests {

    private Class<?> authorizationControllerClass;

    @BeforeEach
    void setUp() {
        authorizationControllerClass = AuthorizationController.class;
    }

    @Test
    void givenControllerAnnotation_whenChecked_thenRestControllerAnnotationPresent() {
        // GIVEN
        // AuthorizationController class is already initialized in setUp()

        // WHEN
        boolean isRestControllerAnnotationPresent = authorizationControllerClass.isAnnotationPresent(RestController.class);

        // THEN
        assertThat(isRestControllerAnnotationPresent).isTrue();
    }

    @Test
    void givenRequestMappingAnnotation_whenChecked_thenCorrectBasePathConfigured() {
        // GIVEN
        // AuthorizationController class is already initialized in setUp()

        // WHEN
        RequestMapping requestMapping = authorizationControllerClass.getAnnotation(RequestMapping.class);

        // THEN
        assertThat(requestMapping).isNotNull();
        assertThat(requestMapping.value()).containsExactly("/api/v2/");
    }
}
