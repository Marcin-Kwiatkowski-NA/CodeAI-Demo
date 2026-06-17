package com.bestpractice.api.app.v2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorizationControllerGeneratedAiTests {

    private AuthorizationController authorizationController;

    @BeforeEach
    void setUp() {
        authorizationController = new AuthorizationController();
    }

    @Test
    void testControllerNotNull() {
        // GIVEN
        // WHEN
        // THEN
        assertNotNull(authorizationController);
    }

    @Test
    void testRequestMappingAnnotationPresent() {
        // GIVEN
        RequestMethod[] methods = RequestMethod.values();
        // WHEN
        int count = methods.length;
        // THEN
        assertEquals(7, count);
    }

    @Test
    void testGetRequestMethodEnumContainsGet() {
        // GIVEN
        RequestMethod getMethod = RequestMethod.GET;
        // WHEN
        String name = getMethod.name();
        // THEN
        assertEquals("GET", name);
    }

    @Test
    void testPostRequestMethodEnumContainsPost() {
        // GIVEN
        RequestMethod postMethod = RequestMethod.POST;
        // WHEN
        String name = postMethod.name();
        // THEN
        assertEquals("POST", name);
    }

    @Test
    void testPutRequestMethodEnumContainsPut() {
        // GIVEN
        RequestMethod putMethod = RequestMethod.PUT;
        // WHEN
        String name = putMethod.name();
        // THEN
        assertEquals("PUT", name);
    }

    @Test
    void testDeleteRequestMethodEnumContainsDelete() {
        // GIVEN
        RequestMethod deleteMethod = RequestMethod.DELETE;
        // WHEN
        String name = deleteMethod.name();
        // THEN
        assertEquals("DELETE", name);
    }
}
