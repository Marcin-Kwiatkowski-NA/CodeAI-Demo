package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.MockExtension;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.google.common.base.Predicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockExtension.class)
public class AppBeanGeneratedAiTests {

    @Autowired
    private AppBean appBean;

    @MockBean
    private AuthComponent authComponent;

    @MockBean
    private RequestInfoComponent requestInfo;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        appBean = new AppBean();
        authComponent = mock(AuthComponent.class);
        requestInfo = mock(RequestInfoComponent.class);
        mockMvc = MockMvcBuilders.standaloneSetup(appBean).build();
    }

    @Test
    void testPreHandle() {
        // GIVEN
        String path = "/api/v1/user";
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(appBean).build();

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get(path));

        // THEN
        verify(requestInfo, times(1)).setRequestId(anyString());
        verify(requestInfo, times(1)).setPath(path);
        verify(requestInfo, times(1)).setHttpMethod("GET");
    }

    @Test
    void testPreHandle_emptyAuthorizationHeader() {
        // GIVEN
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(appBean).build();

        // WHEN
        MockMvcRequestBuilders.get("/api/v1/user").accept(MockMvcRequestBuilders.Accept.APPLICATION_JSON).perform(mockMvc -> {
            return mockMvc.request(mockMvc.requestBuilders.get("/api/v1/user"));
        });

        // THEN
        verify(requestInfo, never()).setRequestId(anyString());
        verify(requestInfo, never()).setPath(anyString());
        verify(requestInfo, never()).setHttpMethod(anyString());
    }

    @Test
    void testPreHandle_disableAuthEndpoints() {
        // GIVEN
        String path = "/api/v1/auth";
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(appBean).build();

        // WHEN
        MockMvcRequestBuilders.get(path).accept(MockMvcRequestBuilders.Accept.APPLICATION_JSON).perform(mockMvc -> {
            return mockMvc.request(mockMvc.requestBuilders.get(path));
        });

        // THEN
        verify(requestInfo, never()).setRequestId(anyString());
        verify(requestInfo, never()).setPath(anyString());
        verify(requestInfo, never()).setHttpMethod(anyString());
    }

    @Test
    void testDecodeJwt() {
        // GIVEN
        String token = "dummyToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn("testUser");
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn("test@example.com");

        // WHEN
        appBean.decodeJwt(token);

        // THEN
        verify(authComponent).decodeJwt(token);
    }

    @Test
    void testGenerateJwt() {
        // GIVEN
        String userId = "testUser";
        String userEmail = "test@example.com";
        boolean isRefresh = false;

        // WHEN
        appBean.generateJwt(userId, userEmail, isRefresh);

        // THEN
        verify(authComponent).generateJwt(userId, userEmail, isRefresh);
    }
}
