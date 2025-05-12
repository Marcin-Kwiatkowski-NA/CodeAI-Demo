package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.Test;
import static com.bestpractice.api.domain.component.RequestInfoComponent.RequestInfoComponent;

import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions.throws;

class RequestInfoComponentTest {

    @Test
    void testGetUserId() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertEquals("12345", component.getUserId());
    }

    @Test
    void testGetUserEmail() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertEquals("test@example.com", component.getUserId());
    }

    @Test
    void testIsRefreshToken() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertTrue(component.isRefreshToken());
    }

    @Test
    void testSetRefreshToken() {
        RequestInfoComponent component = new RequestInfoComponent();
        component.isRefreshToken = false;
        assertEquals("12345", component.getUserId());
    }

    @Test
    void testGetPath() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertEquals(" /home", component.getPath());
    }

    @Test
    void testSetPath() {
        RequestInfoComponent component = new RequestInfoComponent();
        component.setPath("/home/user");
        assertEquals("/home/user", component.getPath());
    }

    @Test
    void testGetHttpMethod() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertEquals("GET", component.getHttpMethod());
    }

    @Test
    void testSetHttpMethod() {
        RequestInfoComponent component = new RequestInfoComponent();
        component.setHttpMethod("GET");
        assertEquals("GET", component.getHttpMethod());
    }

    @Test
    void testGetRequestId() {
        RequestInfoComponent component = new RequestInfoComponent();
        assertEquals("12345", component.getRequestId());
    }

    @Test
    void testSetRequestId() {
        RequestInfoComponent component = new RequestInfoComponent();
        component.setRequestId("12345");
        assertEquals("12345", component.getRequestId());
    }
}
