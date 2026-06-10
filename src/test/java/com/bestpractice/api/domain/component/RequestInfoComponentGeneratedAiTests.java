package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void testSetAndGetUserId() {
        // GIVEN
        String expectedUserId = "user123";

        // WHEN
        requestInfoComponent.setUserId(expectedUserId);
        String actualUserId = requestInfoComponent.getUserId();

        // THEN
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN
        String expectedEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(expectedEmail);
        String actualEmail = requestInfoComponent.getUserEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetAndIsRefreshTokenTrue() {
        // GIVEN
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertEquals(expectedRefreshToken, actualRefreshToken);
    }

    @Test
    void testSetAndIsRefreshTokenFalse() {
        // GIVEN
        boolean expectedRefreshToken = false;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertEquals(expectedRefreshToken, actualRefreshToken);
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN
        String expectedPath = "/api/test";

        // WHEN
        requestInfoComponent.setPath(expectedPath);
        String actualPath = requestInfoComponent.getPath();

        // THEN
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN
        String expectedMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(expectedMethod);
        String actualMethod = requestInfoComponent.getHttpMethod();

        // THEN
        assertEquals(expectedMethod, actualMethod);
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN
        String expectedRequestId = "req-001";

        // WHEN
        requestInfoComponent.setRequestId(expectedRequestId);
        String actualRequestId = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(expectedRequestId, actualRequestId);
    }

    @Test
    void testSetUserIdWithNullValue() {
        // GIVEN
        String userId = null;

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(null, requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserEmailWithNullValue() {
        // GIVEN
        String email = null;

        // WHEN
        requestInfoComponent.setUserEmail(email);

        // THEN
        assertEquals(null, requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetPathWithNullValue() {
        // GIVEN
        String path = null;

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals(null, requestInfoComponent.getPath());
    }

    @Test
    void testSetHttpMethodWithNullValue() {
        // GIVEN
        String method = null;

        // WHEN
        requestInfoComponent.setHttpMethod(method);

        // THEN
        assertEquals(null, requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetRequestIdWithNullValue() {
        // GIVEN
        String requestId = null;

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals(null, requestInfoComponent.getRequestId());
    }

    @Test
    void testGettersReturnNullWhenNotSet() {
        // GIVEN - no values set

        // WHEN & THEN
        assertEquals(null, requestInfoComponent.getUserId());
        assertEquals(null, requestInfoComponent.getUserEmail());
        assertEquals(null, requestInfoComponent.getPath());
        assertEquals(null, requestInfoComponent.getHttpMethod());
        assertEquals(null, requestInfoComponent.getRequestId());
    }

    @Test
    void testSetUserIdWithEmptyString() {
        // GIVEN
        String userId = "";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("", requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserIdWithWhitespaceString() {
        // GIVEN
        String userId = "   ";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("   ", requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserEmailWithEmptyString() {
        // GIVEN
        String email = "";

        // WHEN
        requestInfoComponent.setUserEmail(email);

        // THEN
        assertEquals("", requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetUserEmailWithWhitespaceString() {
        // GIVEN
        String email = "   ";

        // WHEN
        requestInfoComponent.setUserEmail(email);

        // THEN
        assertEquals("   ", requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetPathWithEmptyString() {
        // GIVEN
        String path = "";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("", requestInfoComponent.getPath());
    }

    @Test
    void testSetPathWithWhitespaceString() {
        // GIVEN
        String path = "   ";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("   ", requestInfoComponent.getPath());
    }

    @Test
    void testSetHttpMethodWithEmptyString() {
        // GIVEN
        String method = "";

        // WHEN
        requestInfoComponent.setHttpMethod(method);

        // THEN
        assertEquals("", requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetHttpMethodWithWhitespaceString() {
        // GIVEN
        String method = "   ";

        // WHEN
        requestInfoComponent.setHttpMethod(method);

        // THEN
        assertEquals("   ", requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetRequestIdWithEmptyString() {
        // GIVEN
        String requestId = "";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("", requestInfoComponent.getRequestId());
    }

    @Test
    void testSetRequestIdWithWhitespaceString() {
        // GIVEN
        String requestId = "   ";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("   ", requestInfoComponent.getRequestId());
    }

    @Test
    void testSetUserIdWithLongString() {
        // GIVEN
        String longUserId = "a".repeat(10000);

        // WHEN
        requestInfoComponent.setUserId(longUserId);

        // THEN
        assertEquals(longUserId, requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserEmailWithLongString() {
        // GIVEN
        String longEmail = "a".repeat(10000);

        // WHEN
        requestInfoComponent.setUserEmail(longEmail);

        // THEN
        assertEquals(longEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetPathWithLongString() {
        // GIVEN
        String longPath = "/".concat("a".repeat(10000));

        // WHEN
        requestInfoComponent.setPath(longPath);

        // THEN
        assertEquals(longPath, requestInfoComponent.getPath());
    }

    @Test
    void testSetHttpMethodWithLongString() {
        // GIVEN
        String longMethod = "a".repeat(10000);

        // WHEN
        requestInfoComponent.setHttpMethod(longMethod);

        // THEN
        assertEquals(longMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetRequestIdWithLongString() {
        // GIVEN
        String longRequestId = "a".repeat(10000);

        // WHEN
        requestInfoComponent.setRequestId(longRequestId);

        // THEN
        assertEquals(longRequestId, requestInfoComponent.getRequestId());
    }
}
