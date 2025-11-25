package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AuthorizationControllerGeneratedAiTests {

    private AuthorizationController authorizationController;

    @BeforeEach
    void setUp() {
        authorizationController = new AuthorizationController();
    }

    @Test
    void givenControllerAnnotation_whenChecked_thenShouldBeRestController() {
        // GIVEN
        Class<?> clazz = AuthorizationController.class;

        // WHEN
        RestController restControllerAnnotation = clazz.getAnnotation(RestController.class);

        // THEN
        assertThat(restControllerAnnotation).isNotNull();
    }

    @Test
    void givenRequestMappingAnnotation_whenChecked_thenShouldHaveCorrectPath() {
        // GIVEN
        Class<?> clazz = AuthorizationController.class;

        // WHEN
        RequestMapping requestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);

        // THEN
        assertThat(requestMappingAnnotation).isNotNull();
        assertThat(requestMappingAnnotation.value()).containsExactly("/api/v2/");
    }
}
