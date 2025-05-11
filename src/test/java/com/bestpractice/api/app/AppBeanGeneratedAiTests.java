package com.bestpractice.api.app;

@SuppressWarnings("unused")

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
private AppBean appBean;

@BeforeEach
void setUp() {
    appBean = new AppBean();
    appBean.setUserId("testUser");
    appBean.setUserEmail("testEmail");
    appBean.setRequestInfo(new RequestInfoComponent());
    appBean.setPath("/api/resource");
    appBean.setHttpMethod("GET");
    appBean.setRequestId("12345");
}
