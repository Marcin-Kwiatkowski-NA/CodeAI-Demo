package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.common.util.Util;

public class AppBeanGeneratedAiTests {

    @Mock
    private RequestInfoComponent requestInfo;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private CredentialProperty credentialProperty;

    private AppBean appBean;

    @BeforeEach
    void setUp() {
        appBean = new AppBean(credentialProperty);
    }

    @ExtendWith(MockitoExtension.class)
    public class SwaggerConfigGeneratedAiTests {

        @Mock
        private Docket swaggerSpringMvcPlugin;

        @BeforeEach
        void setUp() {
            appBean = new AppBean(credentialProperty);
        }

        @Test
        void testSwaggerSpringMvcPlugin() {
            Mockito.when(swaggerSpringMvcPlugin.select()).thenReturn(Mockito.empty());
            Mockito.when(swaggerSpringMvcPlugin.paths(Mockito.any())).thenReturn(swaggerSpringMvcPlugin);
            Mockito.when(swaggerSpringMvcPlugin.build()).thenReturn(swaggerSpringMvcPlugin);
            Mockito.when(swaggerSpringMvcPlugin.apiInfo()).thenReturn(new ApiInfo("Spring boot best practice API", "Spring boot best practice API document", "0.0.1", "", "Spring boot best practice", "", ""));
            assertNotNull(appBean.swaggerSpringMvcPlugin());
        }

        @Test
        void testGenerateJwt() {
            String userId = "testUser";
            String email = "test@example.com";
            boolean isRefresh = false;
            Credential credential = appBean.generateJwt(userId, email, isRefresh);
            assertNotNull(credential);
            assertEquals(userId, credential.getUserId());
            assertEquals(email, credential.getUserEmail());
            assertEquals(isRefresh, credential.isRefresh());
        }

        @Test
        void testDecodeJwt() {
            String token = "testToken";
            DecodedJWT decodedJWT = appBean.decodeJwt(token);
            assertNotNull(decodedJWT);
            assertEquals(userId, decodedJWT.getSubject());
            assertEquals(email, decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
            assertEquals(isRefresh, decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        }

        @Test
        void testInterceptorController() {
            Mockito.when(requestInfo.getRequestId()).thenReturn("requestId");
            Mockito.when(requestInfo.getPath()).thenReturn("/api/v1/user");
            Mockito.when(requestInfo.getHttpMethod()).thenReturn("GET");
            appBean.interceptorController();
        }

        @Test
        void testAddInterceptors() {
            Mockito.when(requestInfo.getPath()).thenReturn("/api/v1/user");
            Mockito.when(requestInfo.getHttpMethod()).thenReturn("GET");
            appBean.addInterceptors();
        }
    }
}
