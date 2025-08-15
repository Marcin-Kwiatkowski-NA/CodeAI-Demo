package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.component.RequestInfoComponent;

@ExtendWith(RequestInfoComponentGeneratedAiTests.class)
class RequestInfoComponentTest {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void getUserId() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getUserId() method is called.
        // THEN: The userId property is returned.
        String userId = requestInfoComponent.getUserId();
        assertEquals("", userId);
    }

    @Test
    void setUserId() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setUserId() method is called with "testUserId".
        // THEN: The userId property is set to "testUserId".
        requestInfoComponent.setUserId("testUserId");
        assertEquals("testUserId", requestInfoComponent.getUserId());
    }

    @Test
    void getUserEmail() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getUserEmail() method is called.
        // THEN: The userEmail property is returned.
        String userEmail = requestInfoComponent.getUserEmail();
        assertEquals("", userEmail);
    }

    @Test
    void setUserEmail() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setUserEmail() method is called with "testEmail".
        // THEN: The userEmail property is set to "testEmail".
        requestInfoComponent.setUserEmail("testEmail");
        assertEquals("testEmail", requestInfoComponent.getUserEmail());
    }

    @Test
    void isRefreshToken() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The isRefreshToken() method is called.
        // THEN: The isRefreshToken property is returned.
        boolean refreshToken = requestInfoComponent.isRefreshToken();
        assertEquals(false, refreshToken);
    }

    @Test
    void setRefreshToken() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setRefreshToken() method is called with true.
        // THEN: The isRefreshToken property is set to true.
        requestInfoComponent.setRefreshToken(true);
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void getPath() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getPath() method is called.
        // THEN: The path property is returned.
        String path = requestInfoComponent.getPath();
        assertEquals("", path);
    }

    @Test
    void setPath() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setPath() method is called with "testPath".
        // THEN: The path property is set to "testPath".
        requestInfoComponent.setPath("testPath");
        assertEquals("testPath", requestInfoComponent.getPath());
    }

    @Test
    void getHttpMethod() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getHttpMethod() method is called.
        // THEN: The httpMethod property is returned.
        String httpMethod = requestInfoComponent.getHttpMethod();
        assertEquals("", httpMethod);
    }

    @Test
    void setHttpMethod() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setHttpMethod() method is called with "testHttpMethod".
        // THEN: The httpMethod property is set to "testHttpMethod".
        requestInfoComponent.setHttpMethod("testHttpMethod");
        assertEquals("testHttpMethod", requestInfoComponent.getHttpMethod());
    }

    @Test
    void getRequestId() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getRequestId        // WHEN: The setRequestId() method is called with "testRequestId".
        // THEN: The requestId property is set to "testRequestId".
        requestInfoComponent.setRequestId("testRequestId");
        assertEquals("testRequestId", requestInfoComponent.getRequestId());
    }
}