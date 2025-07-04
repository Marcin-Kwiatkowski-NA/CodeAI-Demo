package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.common.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppBeanGeneratedAiTests {

    private AppBean appBean;
    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;

    @BeforeEach
    void setUp() {
        credentialProperty = Mockito.mockCredentialProperty();
        authComponent = Mockito.when().thenReturn(Mockito.any(AuthComponent.class)).callAny();
        requestInfo = Mockito.mockRequestInfoComponent();
        appBean = new AppBean(credentialProperty, authComponent, requestInfo);
    }

    @Test
    void testDecodeJwt_validToken() {
        String token = "Bearer someValidToken";
        DecodedJWT decodedJWT = appBean.authComponent.decodeJwt(token);
        assertEquals("user_id", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
        assertEquals("user_email", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
    }

    @Test
    void testDecodeJwt_expiredToken() {
        String token = "Bearer expiredToken";
        Mockito.doThrow(new TokenExpiredException()).when(appBean.authComponent.decodeJwt(token));
        assertThrows(UnAuthorized.class, () -> appBean.authComponent.decodeJwt(token));
    }

    @Test
    void testDecodeJwt_invalidToken() {
        String token = "Bearer invalidToken";
        Mockito.doThrow(new InternalServerError("Invalid token")).when(appBean.authComponent.decodeJwt(token));
        assertThrows(InternalServerError.class, () -> appBean.authComponent.decodeJwt(token));
    }

    @Test
    void testGenerateJwt_valid() {
        String userId = "user123";
        String email = "user@example.com";
        Credential credential = appBean.authComponent.generateJwt(userId, email, false);
        assertEquals("user123", credential.getToken());
        assertEquals("user@example.com", credential.getToken());
    }

    @Test
    void testGenerateJwt_withRefresh() {
        String userId = "user123";
        String email = "user@example.com";
        Credential credential = appBean.authComponent.generateJwt(userId, email, true);
        assertEquals("user123", credential.getToken());
        assertEquals("user@example.com", credential.getToken());
        assertTrue(credential.isRefresh());
    }
}