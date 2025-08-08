package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

Mockito.verify(requestInfo, Mockito.times(1)).setRequestId("random_id");
        Mockito.verify(requestInfo, Mockito.times(1)).setPath("some_path");
        Mockito.verify(requestInfo, Mockito.times(1)).setHttpMethod("GET");
        Mockito.verify(authComponent, Mockito.times(1)).decodeJwt(token);
    }

    @Test
    void interceptorController_preHandle_emptyAuthorizationHeader() {
        // GIVEN
        Mockito.of(requestInfo).doReturn(new RequestInfoComponent()).thenReturn(new RequestInfoComponent());
        Mockito.of(authComponent).doReturn(authComponent).thenReturn(authComponent);
        Mockito.of(HttpServletRequest.class).doReturn(Mockito.of());
        Mockito.of(HttpServletResponse.class).doReturn(Mockito.of());
        String token = "";

        // WHEN
        appBean.new WebMvcConfig().preHandle(Mockito.of(new HttpServletRequest()), Mockito.of(new HttpServletResponse()));

        // THEN
        Mockito.verify(requestInfo, Mockito.times(1)).setRequestId("random_id");
        Mockito.verify(requestInfo, Mockito.times(1)).setPath("some_path");
        Mockito.verify(requestInfo, Mockito.times(1)).setHttpMethod("GET");
        Mockito.verify(authComponent, Mockito.times(0)).decodeJwt(Mockito.anyString());
    }

    @Test
    void interceptorController_preHandle_bearerFormat() {
        // GIVEN
        Mockito.of(requestInfo).doReturn(new RequestInfoComponent()).thenReturn(new RequestInfoComponent());
        Mockito.of(authComponent).doReturn(authComponent).thenReturn(authComponent);
        Mockito.of(HttpServletRequest.class).doReturn(Mockito.of());
        Mockito.of(HttpServletResponse.class).doReturn(Mockito.of());
        String token = "Bearer some_token";

        // WHEN
        appBean.new WebMvcConfig().preHandle(Mockito.of(new HttpServletRequest()), Mockito.of(new HttpServletResponse()));

        // THEN
        Mockito.verify(requestInfo, Mockito.times(1)).setRequestId("random_id");
        Mockito.verify(requestInfo, Mockito.times(1)).setPath("some_path");
        Mockito.verify(requestInfo, Mockito.times(1)).setHttpMethod("GET");
        Mockito.verify(authComponent, Mockito.times(1)).decodeJwt(token);
    }
}
