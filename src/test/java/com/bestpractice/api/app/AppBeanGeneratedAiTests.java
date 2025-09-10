package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.common.util.Util;

public class AppBeanGeneratedAiTests {

    private AppBean appBean;
    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        authComponent = new AuthComponent(credentialProperty);
        requestInfo = new RequestInfoComponent();
        appBean = new AppBean(credentialProperty, authComponent, requestInfo);
    }

    @Test
    void decodeJwt_validToken() {
        // GIVEN
        String validToken = "Bearer valid_token";
        // WHEN
        // THEN
        assertDoesNotThrow(() -> appBean.decodeJwt(validToken));
    }

    @Test
    void decodeJwt_expiredToken() {
        // GIVEN
        String expiredToken = "Bearer expired_token";
        // WHEN
        // THEN
        assertThrows(new UnAuthorized("Token is expired time"), () -> appBean.decodeJwt(expiredToken));
    }

    @Test
    void decodeJwt_invalidToken() {
        // GIVEN
        String invalidToken = "Bearer invalid_token";
        // WHEN
        // THEN
        assertThrows(new UnAuthorized("Invalid token"), () -> appBean.decodeJwt(invalidToken));
    }

    @Test
    void generateJwt_valid() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;
        // WHEN
        // THEN
        assertNotNull(appBean.generateJwt(userId, email, isRefresh));
    }

    @Test
    void calculateDate_oneYearFromNow() {
        // GIVEN
        // WHEN
        // THEN
        assertNotNull(Util.calculateDate());
    }
}
