package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppBeanGeneratedAiTests {

    private AppBean appBean;
    private RequestInfoComponent requestInfo;
    private AuthComponent authComponent;
    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = Mockito.createMock(CredentialProperty.class);
        authComponent = Mockito.createMock(AuthComponent.class);
        requestInfo = Mockito.createMock(RequestInfoComponent.class);
        appBean = new AppBean(credentialProperty, authComponent, requestInfo);
    }

    @Test
    void decodeJwt_validToken_returnsDecodedJWT() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.any();
        Mockito.when(authComponent.decodeJwt("valid_token")).thenReturn(decodedJWT);

        // WHEN
        DecodedJWT result = appBean.decodeJwt("valid_token");

        // THEN
        assertNotNull(result);
        verify(authComponent).decodeJwt("valid_token");
    }

    @Test
    void decodeJwt_invalidToken_throwsUnAuthorized() {
        // GIVEN
        Mockito.doThrow(new UnAuthorized("Invalid token")).when(authComponent).decodeJwt("invalid_token");

        // WHEN
        // THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> appBean.decodeJwt("invalid_token"));
        assertEquals("Invalid token", exception.getMessage());
    }

    @Test
    void generateJwt_validParameters_returnsCredential() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.any();
        CredentialProperty credentialProperty = Mockito.createMock(CredentialProperty.class);
        Mockito.when(authComponent.decodeJwt("valid_token")).thenReturn(decodedJWT);
        Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN
        Credential credential = appBean.generateJwt("user_id", "user_email", false);

        // THEN
        assertNotNull(credential);
        assertEquals("user_id", credential.getToken());
    }

    @Test
    void generateJwt_invalidParameters_returnsCredential() {
        // GIVEN
        Mockito.doThrow(new InternalServerError("Unknown signature secret key")).when(authComponent).decodeJwt("invalid_token");

        // WHEN
        // THEN
        InternalServerError exception = assertThrows(InternalServerError.class, () -> appBean.generateJwt("user_id", "user_email", false));
        assertEquals("Unknown signature secret key", exception.getMessage());
    }
}
