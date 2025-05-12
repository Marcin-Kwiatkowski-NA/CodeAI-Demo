package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InterceptorControllerGeneratedAiTests {

    @Test
    void preHandleTest() {
        InterceptorController controller = new InterceptorController(new AuthComponent(), new RequestInfoComponent());
        HttpServletRequest request = new HttpServletRequest();
        request.addHeader("Authorization", "Bearer validToken");
        boolean result = controller.preHandle(request, null, null);
        assertTrue(result);
    }

    @Test
    void preHandleTestNoAuthHeader() {
        InterceptorController controller = new InterceptorController(new AuthComponent(), new RequestInfoComponent());
        HttpServletRequest request = new HttpServletRequest();
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> controller.preHandle(request, null, null));
        assertEquals("Authorization header is empty", exception.getMessage());
    }

    @Test
    void preHandleTestInvalidToken() {
        InterceptorController controller = new InterceptorController(new AuthComponent(), new RequestInfoComponent());
        HttpServletRequest request = new HttpServletRequest();
        request.addHeader("Authorization", "Bearer invalidToken");
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> controller.preHandle(request, null, null));
        assertEquals("Invalid token", exception.getMessage());
    }

    @Test
    void preHandleTestExpiredToken() {
        InterceptorController controller = new InterceptorController(new AuthComponent(), new RequestInfoComponent());
        HttpServletRequest request = new HttpServletRequest();
        request.addHeader("Authorization", "Bearer expiredToken");
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> controller.preHandle(request, null, null));
        assertEquals("Token is expired time", exception.getMessage());
    }

    @Test
    void preHandleTestMissingClaim() {
        InterceptorController controller = new InterceptorController(new AuthComponent(), new RequestInfoComponent());
        HttpServletRequest request = new HttpServletRequest();
        request.addHeader("Authorization", "Bearer validToken");
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> controller.preHandle(request, null, null));
        assertEquals("Missing Claim", exception.getMessage());
    }
}
