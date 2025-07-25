package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.component.Credential;
import com.bestpractice.api.domain.model.Credential;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.common.util.Util;

public class AppBeanGeneratedAiTests {

    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;
    private AppBean appBean;

    @BeforeEach
    void setUp() {
        CredentialProperty credentialProperty = new CredentialProperty();
        authComponent = new AuthComponent(credentialProperty);
        requestInfo = new RequestInfoComponent();
        appBean = new AppBean();
    }

    @Test
    void testDecodeJwt() {
        // GIVEN
        String token = "Bearer someToken";
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertEquals("user_id", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
        assertEquals("user_email", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
        assertTrue(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }

    @Test
    void testGenerateJwt() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertEquals("someToken", credential.getToken());
        assertEquals("Bearer", credential.getTokenType());
        assertNotNull(credential.getExp());
        assertTrue(credential.isRefresh());
    }

    @Test
    void testGetSpringProfileActive() {
        // GIVEN
        String springProfile = "local";
        System.getenv("SPRING_PROFILES_ACTIVE") = springProfile;

        // WHEN
        String profile = Util.getSpringProfileActive();

        // THEN
        assertEquals(springProfile, profile);
    }
}
