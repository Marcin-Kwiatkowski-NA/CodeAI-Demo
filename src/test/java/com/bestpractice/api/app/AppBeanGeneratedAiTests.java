package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import springfox.documentation.spring.web.MockMvcUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@org.junit.jupiter.api.Test
public class AppBeanGeneratedAiTests {

    private RequestInfoComponent requestInfo;
    private AuthComponent authComponent;
    private AppBean appBean;

    @BeforeEach
    void setUp() {
        CredentialProperty credentialProperty = Mockito.createMock(CredentialProperty.class);
        authComponent = Mockito.createMock(AuthComponent.class);
        requestInfo = Mockito.createMock(RequestInfoComponent.class);
        appBean = new AppBean(credentialProperty);
    }

    @org.junit.jupiter.api.Test
    void testDecodeJwt() {
        // GIVEN
        Mockito.when(authComponent.decodeJwt("valid_token")).thenReturn(Mockito.any(DecodedJWT.class));

        // WHEN
        DecodedJWT decodedJWT = appBean.decodeJwt("valid_token");

        // THEN
        Mockito.verify(authComponent).decodeJwt("valid_token");
    }

    @org.junit.jupiter.api.Test
    void testGenerateJwt() {
        // GIVEN
        Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN
        Credential credential = appBean.generateJwt("user_id", "user_email", false);

        // THEN
        Mockito.verify(authComponent).generateJwt("user_id", "user_email", false);
    }

    @org.junit.jupiter.api.Test
    void testPreHandle() {
        // GIVEN
        String path = "/api/v1/user";
        Mockito.when(requestInfo.getPath()).thenReturn(path);
        Mockito.when(requestInfo.getHttpMethod()).thenReturn("GET");

        // WHEN
        appBean.preHandle(Mockito.any(), Mockito.any(), Mockito.any());

        // THEN
        Mockito.verify(requestInfo).setRequestId(Mockito.anyString());
        Mockito.verify(requestInfo).setPath(path);
        Mockito.verify(requestInfo).setHttpMethod("GET");
    }

    @org.junit.jupiter.api.Test
    void testPostHandle() {
        // GIVEN
        String path = "/api/v1/user";
        Mockito.when(requestInfo.getPath()).thenReturn(path);

        // WHEN
        appBean.postHandle(Mockito.any(), Mockito.any(), Mockito.any());

        // THEN
        Mockito.verify(requestInfo).setPath(path);
    }

    @org.junit.jupiter.api.Test
    void testAfterCompletion() {
        // GIVEN
        // WHEN
        appBean.afterCompletion(Mockito.any(), Mockito.any(), Mockito.any());

        // THEN
    }
}
