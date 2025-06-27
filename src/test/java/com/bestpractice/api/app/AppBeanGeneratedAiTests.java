package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MyTestExtension.class)
public class AppBeanGeneratedAiTests {

    private AppBean appBean;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;
    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        authComponent = new AuthComponent(credentialProperty);
        requestInfo = new RequestInfoComponent();
        appBean = new AppBean(credentialProperty, authComponent, requestInfo);
    }

    @Test
    void testDecodeJwt_validToken() {
        // GIVEN
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTYyMzMwMDAwMDAwMDAwMDAwMDAwMDAwMDAw";
        // WHEN
        DecodedJWT decodedJWT = appBean.authComponent.decodeJwt(token);
        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user_id", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
        assertEquals("user_email", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
    }

    @Test
    void testDecodeJwt_invalidToken() {
        // GIVEN
        String token = "invalid-token";
        // WHEN
        // THEN
        assertThrows(new UnAuthorized("Invalid token"), () -> appBean.authComponent.decodeJwt(token));
    }

    @Test
    void testDecodeJwt_expiredToken() {
        // GIVEN
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date expiredTime = calendar.getTime();
        // WHEN
        // THEN
        assertThrows(new TokenExpiredException(), () -> appBean.authComponent.decodeJwt("expired-token"));
    }

    @Test
    void testDecodeJwt_signatureVerificationException() {
        // GIVEN
        String token = "invalid-signature";
        // WHEN
        // THEN
        assertThrows(new SignatureVerificationException(), () -> appBean.authComponent.decodeJwt(token));
    }

    @Test
    void testGenerateJwt_valid() {
        // GIVEN
        String userId = "testUser";
        String email = "test@example.com";
        // WHEN
        Credential credential = appBean.authComponent.generateJwt(userId, email, false);
        // THEN
        assertNotNull(credential);
        assertEquals(credential.getToken(), "Bearer");
        assertEquals(credential.getExp().getTime(), new Date().getTime());
        assertEquals(credential.getClaim(AuthComponent.ClaimUserIdKey), userId);
        assertEquals(credential.getClaim(AuthComponent.ClaimUserEmailKey), email);
    }

    @Test
    void testGenerateJwt_withRefresh() {
        // GIVEN
        String userId = "testUser";
        String email = "test@example.com";
        // WHEN
        Credential credential = appBean.authComponent.generateJwt(userId, email, true);
        // THEN
        assertNotNull(credential);
        assertEquals(credential.getToken(), "Bearer");
        assertEquals(credential.getExp().getTime(), new Date().getTime());
        assertEquals(credential.getClaim(AuthComponent.ClaimUserIdKey), userId);
        assertEquals(credential.getClaim(AuthComponent.ClaimUserEmailKey), email);
        assertEquals(credential.isRefresh(), true);
    }
}
