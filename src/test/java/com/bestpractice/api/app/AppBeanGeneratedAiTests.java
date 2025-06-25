package com.bestpractice.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.app.AppBean;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Test
class AppBeanGeneratedAiTests {

    private AppBean appBean;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;

    @BeforeEach
    void setUp() {
        // GIVEN
        Map<String, Object> properties = new HashMap<>();
        properties.put("hmacSecret", "testSecret");
        properties.put("provider", "testProvider");
        properties.put("subject", "testSubject");
        properties.put("alg", "HMAC256");
        properties.put("expiresHourStr", "1");

        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setProvider("testProvider");
        credentialProperty.setSubject("testSubject");
        credentialProperty.setAlg("HMAC256");
        credentialProperty.setExpiresHourStr("1");

        // Mock AuthComponent
        authComponent = new AuthComponent(credentialProperty);

        // Mock RequestInfoComponent
        requestInfo = new RequestInfoComponent();

        // Initialize AppBean
        appBean = new AppBean();
    }

    @Test
    void testDecodeJwt() {
        // GIVEN
        String token = "Bearer testToken";

        // WHEN
        DecodedJWT decodedJWT = appBean.authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("testToken", decodedJWT.getSubject());
        assertEquals("testToken", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
        assertEquals("testToken", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
    }

    @Test
    void testGenerateJwt() {
        // GIVEN
        String userId = "testUser";
        String userEmail = "test@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = appBean.authComponent.generateJwt(userId, userEmail, isRefresh);

        // THEN
        assertNotNull(credential);
        assertEquals("testToken", credential.getToken());
        assertEquals("testUser", credential.getClaim(AuthComponent.ClaimUserIdKey));
        assertEquals("test@example.com", credential.getClaim(AuthComponent.ClaimUserEmailKey));
        assertEquals(isRefresh, credential.isRefresh());
    }
}
